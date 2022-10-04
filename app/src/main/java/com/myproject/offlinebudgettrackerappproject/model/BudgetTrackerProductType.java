package com.myproject.offlinebudgettrackerappproject.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "budget_tracker_product_type_table")
public class BudgetTrackerProductType {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "product_type")
    private String productType;

    public BudgetTrackerProductType(@NonNull String productType) {
        this.id = id;
        this.productType = productType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }
}
