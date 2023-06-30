
package com.example.myapplication.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.example.myapplication.Models.ExpenseEntries;
import com.example.myapplication.Parameters.DbParameters;

import java.util.ArrayList;
import java.util.List;

public class DbHandler extends SQLiteOpenHelper {
    public DbHandler(Context context) {
        super(context, DbParameters.DB_NAME, null, DbParameters.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_table= "CREATE TABLE " + DbParameters.TAB_NAME + "( " +
                DbParameters.COL_ID + " INTEGER PRIMARY KEY, " +
                DbParameters.COL_EXP_TYPE + " TEXT, " + DbParameters.COL_EXP_AMOUNT + " TEXT)";
        Log.d("createTable", "table is created successfully " + create_table);
        db.execSQL(create_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    // INSERT Query
    public void addEntry(ExpenseEntries exp){
        SQLiteDatabase db = this.getWritableDatabase(); // getting a writable database to perform operations

        ContentValues content = new ContentValues();
        content.put(DbParameters.COL_EXP_TYPE, exp.getExp_type());
        content.put(DbParameters.COL_EXP_AMOUNT, exp.getExp_amount());

        db.insert(DbParameters.TAB_NAME, null, content);    // inserting data to table
        db.close();
    }

    // SELECT Query
    public List<ExpenseEntries> getAllEntries(){
        List<ExpenseEntries> expense_table = new ArrayList<>(); // creating a list to store data of a table in it

        SQLiteDatabase db = this.getReadableDatabase();

        String select_query = "SELECT * FROM " + DbParameters.TAB_NAME;
        Cursor cursor = db.rawQuery(select_query, null);

        if(cursor.moveToFirst()){
            do{
                ExpenseEntries expense_entries = new ExpenseEntries();   // creating an object of ExpenseEntries class to get data of table to it
                expense_entries.setId(Integer.parseInt(cursor.getString(0)));
                expense_entries.setExp_type(cursor.getString(1));
                expense_entries.setExp_amount(cursor.getString(2));
                expense_table.add(expense_entries); // getting data of the table into an object and storing it into an arraylist of object type
            }while(cursor.moveToNext());
        }

        db.close();

        return expense_table;

    }

    // UPDATE Query
    public int updateEntries(ExpenseEntries exp){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DbParameters.COL_EXP_TYPE, exp.getExp_type());
        values.put(DbParameters.COL_EXP_AMOUNT, exp.getExp_amount());

        // updating here
        return db.update(DbParameters.TAB_NAME, values, DbParameters.COL_ID + "=?",
                new String[]{String.valueOf(exp.getId())});   // this function will return no of affected rows in table
    }
}