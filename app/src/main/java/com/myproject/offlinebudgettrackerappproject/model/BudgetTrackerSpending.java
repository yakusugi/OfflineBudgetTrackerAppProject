package com.myproject.offlinebudgettrackerappproject.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "budget_tracker_spending_table")
public class BudgetTrackerSpending implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "date")
    private String date;

    @Nullable
    @ColumnInfo(name = "store_name")
    private String storeName;

    @Nullable
    @ColumnInfo(name = "product_name")
    private String productName;

    @Nullable
    @ColumnInfo(name = "product_type")
    private String productType;

    @ColumnInfo(name = "price")
    private Double price;

    @ColumnInfo(name = "is_tax")
    private Boolean isTax;

    @ColumnInfo(name = "tax_rate")
    private Double taxRate;

    @Nullable
    private String notes;

    public BudgetTrackerSpending(@NonNull String date, @Nullable String storeName, @Nullable String productName, @Nullable String productType, Double price, Boolean isTax, Double taxRate, @Nullable String notes) {
        this.id = id;
        this.date = date;
        this.storeName = storeName;
        this.productName = productName;
        this.productType = productType;
        this.price = price;
        this.isTax = isTax;
        this.taxRate = taxRate;
        this.notes = notes;
    }

    public BudgetTrackerSpending(String searchKey, String dateFrom, String dateTo) {
        this.id = id;
        this.storeName = storeName;
        this.date = dateFrom;
        this.date = dateTo;
    }

    public BudgetTrackerSpending(String searchWord, String replaceWith) {
    }

    @Ignore
    public BudgetTrackerSpending() {

    }

    public BudgetTrackerSpending(String searchKey) {
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
    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(@Nullable String storeName) {
        this.storeName = storeName;
    }

    @Nullable
    public String getProductName() {
        return productName;
    }

    public void setProductName(@Nullable String productName) {
        this.productName = productName;
    }

    @Nullable
    public String getProductType() {
        return productType;
    }

    public void setProductType(@Nullable String productType) {
        this.productType = productType;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean getTax() {
        return isTax;
    }

    public void setTax(Boolean tax) {
        isTax = tax;
    }

    public Double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Double taxRate) {
        this.taxRate = taxRate;
    }

    @Nullable
    public String getNotes() {
        return notes;
    }

    public void setNotes(@Nullable String notes) {
        this.notes = notes;
    }
}
