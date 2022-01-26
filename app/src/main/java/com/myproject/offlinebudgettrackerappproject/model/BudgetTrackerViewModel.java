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
    public int productTypeSum;
    public List<BudgetTracker> dateLists;

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

    public int getProductTypeSumNum(String productType) {
        productTypeSum = repository.queryProductTypeSum(productType);
        return productTypeSum;
    }

    public List<BudgetTracker> getDateLists(String date1, String date2) {
        dateLists = repository.queryDate(date1, date2);
        return dateLists;
    }

    public static void insert(BudgetTracker budgetTracker) {
        repository.insert(budgetTracker);
    }
}
