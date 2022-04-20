package com.myproject.offlinebudgettrackerappproject.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "budget_tracker_table")
public class BudgetTracker {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "date")
    private String date;
    @ColumnInfo(name = "store_name")
    private String storeName;
    @ColumnInfo(name = "product_name")
    private String productName;
    @ColumnInfo(name = "product_type")
    private String productType;
    @ColumnInfo(name = "price")
    private int price;

    @Ignore
    public BudgetTracker() {
    }

    public BudgetTracker(@NonNull String date, String storeName, String productName, String productType, int price) {
        this.id = id;
        this.date = date;
        this.storeName = storeName;
        this.productName = productName;
        this.productType = productType;
        this.price = price;
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

    public String getStoreName() {
        return this.storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
