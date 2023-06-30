package com.example.myapplication.Models;

public class ExpenseEntries {
    private int id;
    private String exp_type;
    private String exp_amount;

    public ExpenseEntries(String exp_type, String exp_amount) {
        this.exp_type = exp_type;
        this.exp_amount = exp_amount;
    }

    public ExpenseEntries(int id, String exp_type, String exp_amount) {
        this.id = id;
        this.exp_type = exp_type;
        this.exp_amount = exp_amount;
    }

    public ExpenseEntries(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExp_type() {
        return exp_type;
    }

    public void setExp_type(String exp_type) {
        this.exp_type = exp_type;
    }

    public String getExp_amount() {
        return exp_amount;
    }

    public void setExp_amount(String exp_amount) {
        this.exp_amount = exp_amount;
    }
}