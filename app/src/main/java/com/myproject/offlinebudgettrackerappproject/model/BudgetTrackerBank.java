package com.myproject.offlinebudgettrackerappproject.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "budget_tracker_bank_table")
public class BudgetTrackerBank {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "bank_name")
    private String bankName;
    @ColumnInfo(name = "bank_balance")
    private int bankBalance;

    @Ignore
    public BudgetTrackerBank() {
    }

    public BudgetTrackerBank(@NonNull String bankName, int bankBalance) {
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

    public int getBankBalance() {
        return bankBalance;
    }

    public void setBankBalance(int bankBalance) {
        this.bankBalance = bankBalance;
    }
}

