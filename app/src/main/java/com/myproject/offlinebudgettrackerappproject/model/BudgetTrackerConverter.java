package com.myproject.offlinebudgettrackerappproject.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "budget_tracker_converter_table")
public class BudgetTrackerConverter {

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

    @ColumnInfo(name = "is_tax")
    private Boolean isTax;

    @ColumnInfo(name = "tax_rate")
    private Double taxRate;

    @Nullable
    @ColumnInfo(name = "notes")
    private String notes;

    @ColumnInfo(name = "original_currency")
    private String originalCurrency;

    @ColumnInfo(name = "original_currency_price")
    private Double originalCurrencyPrice;

    @ColumnInfo(name = "target_currency")
    private String targetCurrency;

    @ColumnInfo(name = "target_currency_price")
    private Double targetCurrencyPrice;

    @ColumnInfo(name = "converted_date")
    private String convertedDate;

    public BudgetTrackerConverter(@NonNull String date, @Nullable String storeName, @Nullable String productName, @Nullable String productType, Boolean isTax, Double taxRate, @Nullable String notes, String originalCurrency, Double originalCurrencyPrice, String targetCurrency, Double targetCurrencyPrice, String convertedDate) {
        this.id = id;
        this.date = date;
        this.storeName = storeName;
        this.productName = productName;
        this.productType = productType;
        this.isTax = isTax;
        this.taxRate = taxRate;
        this.notes = notes;
        this.originalCurrency = originalCurrency;
        this.originalCurrencyPrice = originalCurrencyPrice;
        this.targetCurrency = targetCurrency;
        this.targetCurrencyPrice = targetCurrencyPrice;
        this.convertedDate = convertedDate;
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

    public String getOriginalCurrency() {
        return originalCurrency;
    }

    public void setOriginalCurrency(String originalCurrency) {
        this.originalCurrency = originalCurrency;
    }

    public Double getOriginalCurrencyPrice() {
        return originalCurrencyPrice;
    }

    public void setOriginalCurrencyPrice(Double originalCurrencyPrice) {
        this.originalCurrencyPrice = originalCurrencyPrice;
    }

    public String getTargetCurrency() {
        return targetCurrency;
    }

    public void setTargetCurrency(String targetCurrency) {
        this.targetCurrency = targetCurrency;
    }

    public Double getTargetCurrencyPrice() {
        return targetCurrencyPrice;
    }

    public void setTargetCurrencyPrice(Double targetCurrencyPrice) {
        this.targetCurrencyPrice = targetCurrencyPrice;
    }

    public String getConvertedDate() {
        return convertedDate;
    }

    public void setConvertedDate(String convertedDate) {
        this.convertedDate = convertedDate;
    }
}
