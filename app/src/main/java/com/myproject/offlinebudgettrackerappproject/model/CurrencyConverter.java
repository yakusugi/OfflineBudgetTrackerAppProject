package com.myproject.offlinebudgettrackerappproject.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "bank_currency_converter_table")
public class CurrencyConverter {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "date")
    private String date;

    @ColumnInfo(name = "bank_name")
    private String bankName;

    @ColumnInfo(name = "original_currency")
    private String originalCurrency;

    @ColumnInfo(name = "original_currency_balance")
    private Double originalCurrencyBalance;

    @ColumnInfo(name = "converted_currency")
    private String convertedCurrency;

    @ColumnInfo(name = "converted_currency_balance")
    private Double convertedCurrencyBalance;

    public CurrencyConverter(@NonNull String date, String bankName, String originalCurrency, Double originalCurrencyBalance, String convertedCurrency, Double convertedCurrencyBalance) {
        this.id = id;
        this.date = date;
        this.bankName = bankName;
        this.originalCurrency = originalCurrency;
        this.originalCurrencyBalance = originalCurrencyBalance;
        this.convertedCurrency = convertedCurrency;
        this.convertedCurrencyBalance = convertedCurrencyBalance;
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

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getOriginalCurrency() {
        return originalCurrency;
    }

    public void setOriginalCurrency(String originalCurrency) {
        this.originalCurrency = originalCurrency;
    }

    public Double getOriginalCurrencyBalance() {
        return originalCurrencyBalance;
    }

    public void setOriginalCurrencyBalance(Double originalCurrencyBalance) {
        this.originalCurrencyBalance = originalCurrencyBalance;
    }

    public String getConvertedCurrency() {
        return convertedCurrency;
    }

    public void setConvertedCurrency(String convertedCurrency) {
        this.convertedCurrency = convertedCurrency;
    }

    public Double getConvertedCurrencyBalance() {
        return convertedCurrencyBalance;
    }

    public void setConvertedCurrencyBalance(Double convertedCurrencyBalance) {
        this.convertedCurrencyBalance = convertedCurrencyBalance;
    }
}
