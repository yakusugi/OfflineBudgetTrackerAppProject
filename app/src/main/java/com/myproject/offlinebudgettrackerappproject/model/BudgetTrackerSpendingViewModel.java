package com.myproject.offlinebudgettrackerappproject.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.myproject.offlinebudgettrackerappproject.data.BudgetTrackerSpendingRepository;

import java.util.List;

public class BudgetTrackerSpendingViewModel extends AndroidViewModel {
    public static BudgetTrackerSpendingRepository repository;
    public List<BudgetTrackerSpending> radioSearchStoreNameLists;
    public List<BudgetTrackerSpending> radioSearchProductNameLists;
    public List<BudgetTrackerSpending> radioSearchProductTypeLists;
    private double searchStoreSum;
    private double searchProductSum;
    private double searchProductTypeSum;
    public List<BudgetTrackerSpending> storeNameList;
    public List<BudgetTrackerSpending> productNameList;
    public List<BudgetTrackerSpending> productTypeList;
    public LiveData<List<BudgetTrackerSpending>> allBudgetTrackerSpendingList;

    public BudgetTrackerSpendingViewModel(@NonNull Application application) {
        super(application);
        repository = new BudgetTrackerSpendingRepository(application);
        allBudgetTrackerSpendingList = repository.getAllBudgetTrackerSpendingData();
    }

    public LiveData<List<BudgetTrackerSpending>> getAllSpendingData() { return allBudgetTrackerSpendingList; }

    public static void insert(BudgetTrackerSpending budgetTrackerSpending) {
        repository.insert(budgetTrackerSpending);
    }

    public static void updateBudgetTrackerSpending(BudgetTrackerSpending budgetTrackerSpending) {repository.updateBudgetTrackerSpending(budgetTrackerSpending);}
    public static void deleteBudgetTrackerSpending(BudgetTrackerSpending budgetTrackerSpending) {repository.deleteBudgetTrackerSpending(budgetTrackerSpending);}

    //  SearchFragment
    public List<BudgetTrackerSpending> getSearchStoreNameLists(String storeName, String dateFrom, String dateTo) {
        radioSearchStoreNameLists = repository.getSearchStoreNameLists(storeName, dateFrom, dateTo);
        return radioSearchStoreNameLists;
    }

    public double getSearchStoreSum(String storeName, String dateFrom, String dateTo) {
        searchStoreSum = repository.getSearchStoreSum(storeName, dateFrom, dateTo);
        return searchStoreSum;
    }

    public List<BudgetTrackerSpending> getSearchProductNameLists(String productName, String dateFrom, String dateTo) {
        radioSearchProductNameLists = repository.getSearchProductNameLists(productName, dateFrom, dateTo);
        return radioSearchProductNameLists;
    }

    public double getSearchProductSum(String productName, String dateFrom, String dateTo) {
        searchProductSum = repository.getSearchProductSum(productName, dateFrom, dateTo);
        return searchProductSum;
    }

    public List<BudgetTrackerSpending> getSearchProductTypeLists(String productType, String dateFrom, String dateTo) {
        radioSearchProductTypeLists = repository.getSearchProductTypeLists(productType, dateFrom, dateTo);
        return radioSearchProductTypeLists;
    }

    public double getSearchProductTypeSum(String productType, String dateFrom, String dateTo) {
        searchProductTypeSum = repository.getSearchProductTypeSum(productType, dateFrom, dateTo);
        return searchProductTypeSum;
    }

//  ReplaceFragment
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

    //For getting ID for tapped item in a listview
    public LiveData<BudgetTrackerSpending> getBudgetTrackerSpendingId(int id) {return repository.getBudgetTrackerSpendingId(id);}

}
