package com.myproject.offlinebudgettrackerappproject.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "budget_tracker_income_type_table")
public class BudgetTrackerIncomeType {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "income_type")
    private String incomeType;

    public BudgetTrackerIncomeType(@NonNull String incomeType) {
        this.id = id;
        this.incomeType = incomeType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIncomeType() {
        return incomeType;
    }

    public void setIncomeType(String incomeType) {
        this.incomeType = incomeType;
    }
}
