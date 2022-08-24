package com.myproject.offlinebudgettrackerappproject.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "budget_tracker_banking_table")
public class BudgetTrackerBanking {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "bank_name")
    private String bankName;
    @ColumnInfo(name = "bank_balance")
    private double bankBalance;

    public BudgetTrackerBanking() {
    }

    public BudgetTrackerBanking(@NonNull String bankName, double bankBalance) {
        this.id = id;
        this.bankName = bankName;
        this.bankBalance = bankBalance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public double getBankBalance() {
        return bankBalance;
    }

    public void setBankBalance(double bankBalance) {
        this.bankBalance = bankBalance;
    }
}

