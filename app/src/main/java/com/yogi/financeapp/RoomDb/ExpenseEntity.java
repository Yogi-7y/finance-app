package com.yogi.financeapp.RoomDb;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "expense_table")
public class ExpenseEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private int amount;
    private String category;
    private String description;
//    private Date date;
    private String transactionType;

    public ExpenseEntity(int amount, String category, String description, String transactionType) {
        this.amount = amount;
        this.category = category;
        this.description = description;
//        this.date = date;
        this.transactionType = transactionType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    public Date getDate() {
//        return date;
//    }
//
//    public void setDate(Date date) {
//        this.date = date;
//    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
}
