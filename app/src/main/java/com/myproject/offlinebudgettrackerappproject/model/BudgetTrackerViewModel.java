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
    public List<BudgetTracker> productNameLists;

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

    public List<BudgetTracker> getProductNameLists(String productName) {
        productNameLists = repository.queryProductName(productName);
        return productNameLists;
    }

    public List<BudgetTracker> getDateLists(String date1, String date2) {
        productNameLists = repository.queryDate(date1, date2);
        return productNameLists;
    }

    public static void insert(BudgetTracker budgetTracker) {
        repository.insert(budgetTracker);
    }
}
