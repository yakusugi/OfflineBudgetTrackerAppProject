package com.myproject.offlinebudgettrackerappproject.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "budget_tracker_table_alias")
public class BudgetTrackerAlias {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @Nullable
    @ColumnInfo(name = "date_alias")
    private String dateAlias;
    @Nullable
    @ColumnInfo(name = "store_name_alias")
    private String storeNameAlias;
    @Nullable
    @ColumnInfo(name = "product_name_alias")
    private String productNameAlias;
    @Nullable
    @ColumnInfo(name = "product_type_alias")
    private String productTypeAlias;
    @Nullable
    @ColumnInfo(name = "price_alias")
    private int priceAlias;
    @Nullable
    @ColumnInfo(name = "product_type_percentage")
    private transient float productTypePercentage;

//    public BudgetTrackerAlias() {
//    }

    public BudgetTrackerAlias(@NonNull String storeName, String date1, String date2) {
        this.storeNameAlias = storeNameAlias;
        this.dateAlias = dateAlias;
        this.dateAlias = dateAlias;
    }

    public BudgetTrackerAlias(@NonNull @Nullable String dateAlias, @Nullable String storeNameAlias, @Nullable String productNameAlias, @Nullable String productTypeAlias, @Nullable int priceAlias, @Nullable float productTypePercentage) {
        this.id = id;
        this.dateAlias = dateAlias;
        this.storeNameAlias = storeNameAlias;
        this.productNameAlias = productNameAlias;
        this.productTypeAlias = productTypeAlias;
        this.priceAlias = priceAlias;
        this.productTypePercentage = productTypePercentage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDateAlias() {
        return dateAlias;
    }

    public void setDateAlias(String dateAlias) {
        this.dateAlias = dateAlias;
    }

    public String getStoreNameAlias() {
        return storeNameAlias;
    }

    public void setStoreNameAlias(String storeNameAlias) {
        this.storeNameAlias = storeNameAlias;
    }

    public String getProductNameAlias() {
        return productNameAlias;
    }

    public void setProductNameAlias(String productNameAlias) {
        this.productNameAlias = productNameAlias;
    }

    public String getProductTypeAlias() {
        return productTypeAlias;
    }

    public void setProductTypeAlias(String productTypeAlias) {
        this.productTypeAlias = productTypeAlias;
    }

    public int getPriceAlias() {
        return priceAlias;
    }

    public void setPriceAlias(int priceAlias) {
        this.priceAlias = priceAlias;
    }

    public float getProductTypePercentage() {
        return productTypePercentage;
    }

    public void setProductTypePercentage(float productTypePercentage) {
        this.productTypePercentage = productTypePercentage;
    }
}
