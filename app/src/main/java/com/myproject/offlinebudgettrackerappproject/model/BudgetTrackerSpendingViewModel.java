package com.myproject.offlinebudgettrackerappproject.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.myproject.offlinebudgettrackerappproject.data.BudgetTrackerSpendingRepository;

import java.util.List;

public class BudgetTrackerSpendingViewModel extends AndroidViewModel {
    public static BudgetTrackerSpendingRepository repository;
    public List<BudgetTrackerSpending> radioSearchStoreNameLists;
    private double searchStoreSum;
    public List<BudgetTrackerSpending> storeNameList;
    public List<BudgetTrackerSpending> productNameList;
    public List<BudgetTrackerSpending> productTypeList;

    public BudgetTrackerSpendingViewModel(@NonNull Application application) {
        super(application);
        repository = new BudgetTrackerSpendingRepository(application);
    }
//    public final LiveData<List<BudgetTrackerSpending>> allBudgetTrackerList;
//    public final List<BudgetTrackerSpending> allBudgetTrackerSpdList;

//    public BudgetTrackerSpendingViewModel(LiveData<List<BudgetTrackerSpending>> allBudgetTrackerList, List<BudgetTrackerSpending> allBudgetTrackerSpdList) {
//        this.allBudgetTrackerList = allBudgetTrackerList;
//        this.allBudgetTrackerSpdList = allBudgetTrackerSpdList;
//    }

//    public BudgetTrackerSpendingViewModel(List<BudgetTrackerSpending> allBudgetTrackerSpdList) {
//        this.allBudgetTrackerSpdList = allBudgetTrackerSpdList;
//    }



    public static void insert(BudgetTrackerSpending budgetTrackerSpending) {
        repository.insert(budgetTrackerSpending);
    }

    public List<BudgetTrackerSpending> getSearchStoreNameLists(String storeName, String dateFrom, String dateTo) {
        radioSearchStoreNameLists = repository.getSearchStoreNameLists(storeName, dateFrom, dateTo);
        return radioSearchStoreNameLists;
    }

    public double getSearchStoreSum(String storeName, String dateFrom, String dateTo) {
        searchStoreSum = repository.getSearchStoreSum(storeName, dateFrom, dateTo);
        return searchStoreSum;
    }

    public static void replaceStoreName(String storeNameFrom, String storeNameToe) {
        repository.replaceStoreName(storeNameFrom, storeNameToe);
    }

    public List<BudgetTrackerSpending> getStoreNameList(String storeName) {
        storeNameList = repository.getStoreName(storeName);
        return storeNameList;
    }

    public static void replaceProductName(String productNameFrom, String productNameTo) {
        repository.replaceProductName(productNameFrom, productNameTo);
    }

    public List<BudgetTrackerSpending> getProductNameList(String productName) {
        productNameList = repository.getProductName(productName);
        return productNameList;
    }

    public static void replaceProductType(String productTypeFrom, String productTypeTo) {
        repository.replaceProductType(productTypeFrom, productTypeTo);
    }

    public List<BudgetTrackerSpending> getProductTypeList(String productType) {
        productTypeList = repository.getProductType(productType);
        return productTypeList;
    }
}
