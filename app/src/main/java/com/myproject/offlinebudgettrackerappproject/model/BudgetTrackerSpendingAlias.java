package com.myproject.offlinebudgettrackerappproject.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "budget_tracker_spending_alias_table")
public class BudgetTrackerSpendingAlias {
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
    private double priceAlias;
    @Nullable
    @ColumnInfo(name = "is_tax_alias")
    private Boolean isTaxAlias;
    @Nullable
    @ColumnInfo(name = "tax_rate_alias")
    private Double taxRateAlias;
    @Nullable
    @ColumnInfo(name = "alias_percentage")
    private transient float aliasPercentage;

    public BudgetTrackerSpendingAlias(@NonNull @Nullable String dateAlias, @Nullable String storeNameAlias, @Nullable String productNameAlias, @Nullable String productTypeAlias, double priceAlias, @Nullable Boolean isTaxAlias, @Nullable Double taxRateAlias, float aliasPercentage) {
        this.id = id;
        this.dateAlias = dateAlias;
        this.storeNameAlias = storeNameAlias;
        this.productNameAlias = productNameAlias;
        this.productTypeAlias = productTypeAlias;
        this.priceAlias = priceAlias;
        this.isTaxAlias = isTaxAlias;
        this.taxRateAlias = taxRateAlias;
        this.aliasPercentage = aliasPercentage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Nullable
    public String getDateAlias() {
        return dateAlias;
    }

    public void setDateAlias(@Nullable String dateAlias) {
        this.dateAlias = dateAlias;
    }

    @Nullable
    public String getStoreNameAlias() {
        return storeNameAlias;
    }

    public void setStoreNameAlias(@Nullable String storeNameAlias) {
        this.storeNameAlias = storeNameAlias;
    }

    @Nullable
    public String getProductNameAlias() {
        return productNameAlias;
    }

    public void setProductNameAlias(@Nullable String productNameAlias) {
        this.productNameAlias = productNameAlias;
    }

    @Nullable
    public String getProductTypeAlias() {
        return productTypeAlias;
    }

    public void setProductTypeAlias(@Nullable String productTypeAlias) {
        this.productTypeAlias = productTypeAlias;
    }

    public double getPriceAlias() {
        return priceAlias;
    }

    public void setPriceAlias(double priceAlias) {
        this.priceAlias = priceAlias;
    }

    @Nullable
    public Boolean getTaxAlias() {
        return isTaxAlias;
    }

    public void setTaxAlias(@Nullable Boolean taxAlias) {
        isTaxAlias = taxAlias;
    }

    @Nullable
    public Double getTaxRateAlias() {
        return taxRateAlias;
    }

    public void setTaxRateAlias(@Nullable Double taxRateAlias) {
        this.taxRateAlias = taxRateAlias;
    }

    public float getAliasPercentage() {
        return aliasPercentage;
    }

    public void setAliasPercentage(float aliasPercentage) {
        this.aliasPercentage = aliasPercentage;
    }
}
