package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.Data.DbHandler;
import com.example.myapplication.Models.ExpenseEntries;
import com.example.myapplication.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText editText_Type, editText_Amount;
    DbHandler dbHandler = new DbHandler(MainActivity.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inserting Entries in table
//        insertEntries("Daal", "400");
//        insertEntries("Chawal", "300");
//        insertEntries("Aata", "600");
//        Log.d("insert", "data inserted sucessfully.");

        // Reading Entries of table
        readEntries();

        // Update Entries of table
        updateEntries(15, "Desi Daru, with chakna", "125");
        updateEntries(17, "Pan wali ice cream", "35");

    }

    public void insertEntries(View view){
        // getting input data from editText
        editText_Type = findViewById(R.id.editTextExp_Type);
        editText_Amount = findViewById(R.id.editTextExp_Amount);
        String exp_type, exp_amount;
        exp_type = editText_Type.getText().toString();
        exp_amount = editText_Amount.getText().toString();

        ExpenseEntries exp_dal = new ExpenseEntries();
        exp_dal.setExp_type(exp_type);    // setting values of object
        exp_dal.setExp_amount(exp_amount);

        dbHandler.addEntry(exp_dal);    // sending object as an argument to insert these values to table
        readEntries();
    }

    public void readEntries() {

        List<ExpenseEntries> table_data = dbHandler.getAllEntries();    // getting all rows of the table in a list

        for (ExpenseEntries exp : table_data) {   // reading all entries in list
            Log.d("insert_data", "id: " + exp.getId() + ", expense type: " + exp.getExp_type() + ", expense amount: " + exp.getExp_amount());
        }
    }

    public void updateEntries(int id, String exp_type, String exp_amount){

        ExpenseEntries exp_update = new ExpenseEntries();
        exp_update.setId(id);
        exp_update.setExp_type(exp_type);
        exp_update.setExp_amount(exp_amount);

        int affected_rows = dbHandler.updateEntries(exp_update);

    }
}
