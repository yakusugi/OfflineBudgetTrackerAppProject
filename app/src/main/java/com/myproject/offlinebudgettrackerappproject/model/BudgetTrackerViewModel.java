package com.myproject.offlinebudgettrackerappproject.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.myproject.offlinebudgettrackerappproject.data.BudgetTrackerRepository;

import java.util.List;

public class BudgetTrackerViewModel extends AndroidViewModel {

    public static BudgetTrackerRepository repository;
    public final LiveData<List<BudgetTracker>> allBudgetTrackerLists;
    public List<BudgetTracker> storeNameLists;
    public List<BudgetTracker> productTypeLists;
    public List<BudgetTracker> productNameList;
    public int productTypeSum;
    public int storeNameSum;
    public List<BudgetTracker> dateLists;
    public List<BudgetTracker> radioStoreNameLists;
    public int dateStoreSum;
    public List<BudgetTracker> radioProductNameLists;
    public int dateProductNameSum;
    public List<BudgetTracker> radioProductTypeLists;
    public int dateProductTypeSum;
    private List<BudgetTracker> radioStoreSearchHomeLists;

    public BudgetTrackerViewModel(@NonNull Application application) {
        super(application);
        repository = new BudgetTrackerRepository(application);
        allBudgetTrackerLists = repository.getAllBudgetTrackerData();
    }

    public BudgetTrackerViewModel(@NonNull Application application, LiveData<List<BudgetTracker>> allBudgetTrackerLists, List<BudgetTracker> storeNameLists) {
        super(application);
        this.allBudgetTrackerLists = allBudgetTrackerLists;
        this.storeNameLists = storeNameLists;
    }

    public LiveData<List<BudgetTracker>> getAllBudgetTrackerLists() {
        return allBudgetTrackerLists;
    }

    public List<BudgetTracker> getStoreNameLists(String storeName) {
        storeNameLists = repository.queryStoreName(storeName);
        return storeNameLists;
    }

    public List<BudgetTracker> getProductTypeLists(String productType) {
        productTypeLists = repository.queryProductType(productType);
        return productTypeLists;
    }

    public List<BudgetTracker> getProductNameList(String productName) {
        productNameList = repository.queryProductName(productName);
        return productNameList;
    }

    public int getProductTypeSum(String productType) {
        productTypeSum = repository.queryProductTypeSum(productType);
        return productTypeSum;
    }

    public int getStoreNameSumSum(String storeName) {
        storeNameSum = repository.queryStoreNameSum(storeName);
        return storeNameSum;
    }

    public List<BudgetTracker> getDateLists(String date1, String date2) {
        dateLists = repository.queryDate(date1, date2);
        return dateLists;
    }

    public static void insert(BudgetTracker budgetTracker) {
        repository.insert(budgetTracker);
    }

    public LiveData<BudgetTracker> getBudgetTrackerId(int id) {return repository.getBudgetTrackerId(id);}
    public static void updateBudgetTracker(BudgetTracker budgetTracker) {repository.updateBudgetTracker(budgetTracker);}
    public static void deleteBudgetTracker(BudgetTracker budgetTracker) {repository.deleteBudgetTracker(budgetTracker);}

    public List<BudgetTracker> getRadioStoreNameLists(String storeName, String date1, String date2) {
        radioStoreNameLists = repository.getRadioStoreNameLists(storeName, date1, date2);
        return radioStoreNameLists;
    }

    public int getDateStoreSum(String storeName, String date1, String date2) {
        dateStoreSum = repository.getDateStoreSum(storeName, date1, date2);
        return dateStoreSum;
    }

    public List<BudgetTracker> getRadioProductNameLists(String productName, String date1, String date2) {
        radioProductNameLists = repository.getRadioProductNameLists(productName, date1, date2);
        return radioProductNameLists;
    }

    public int getDateProductNameSum(String productName, String date1, String date2) {
        dateProductNameSum = repository.getDateProductNameSum(productName, date1, date2);
        return dateProductNameSum;
    }

    public List<BudgetTracker> getRadioProductTypeLists(String productType, String date1, String date2) {
        radioProductTypeLists = repository.getRadioProductTypeLists(productType, date1, date2);
        return radioProductTypeLists;
    }

    public int getDateProductTypeSum(String productType, String date1, String date2) {
        dateProductTypeSum = repository.getDateProductTypeSum(productType, date1, date2);
        return dateProductTypeSum;
    }

    public static void replaceStoreName(String storeNameFrom, String storeNameToe) {
        repository.replaceStoreName(storeNameFrom, storeNameToe);
    }

    public static void replaceProductName(String productNameFrom, String productNameTo) {
        repository.replaceProductName(productNameFrom, productNameTo);
    }

    public static void replaceProductType(String productTypeFrom, String productTypeTo) {
        repository.replaceProductType(productTypeFrom, productTypeTo);
    }


}
