package com.myproject.offlinebudgettrackerappproject.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "budget_tracker_incomes_table")
public class BudgetTrackerIncomes implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "date")
    private String date;
    @Nullable
    @ColumnInfo(name = "category")
    private String category;
    @ColumnInfo(name = "amount")
    private double amount;
    @Nullable
    private String notes;

    public BudgetTrackerIncomes(@NonNull String date, String category, double amount, @Nullable String notes) {
        this.id = id;
        this.date = date;
        this.category = category;
        this.amount = amount;
        this.notes = notes;
    }

    public BudgetTrackerIncomes(String searchKey) {
    }

    public BudgetTrackerIncomes() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Nullable
    public String getCategory() {
        return category;
    }

    public void setCategory(@Nullable String category) {
        this.category = category;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Nullable
    public String getNotes() {
        return notes;
    }

    public void setNotes(@Nullable String notes) {
        this.notes = notes;
    }
}
