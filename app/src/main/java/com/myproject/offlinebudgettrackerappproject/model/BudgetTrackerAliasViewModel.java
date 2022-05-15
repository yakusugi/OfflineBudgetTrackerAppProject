package com.myproject.offlinebudgettrackerappproject.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.myproject.offlinebudgettrackerappproject.data.BudgetTrackerAliasRepository;

import java.util.List;

public class BudgetTrackerAliasViewModel extends AndroidViewModel {

    public static BudgetTrackerAliasRepository repository;
    public List<BudgetTrackerAlias> budgetTrackerAliasList;

    public BudgetTrackerAliasViewModel(@NonNull Application application) {
        super(application);
        repository = new BudgetTrackerAliasRepository(application);
    }

    public static void insert(String date1, String date2, String storeName) {
        repository.insert(date1, date2, storeName);
    }

    public static void insertProductName(String date1, String date2, String productName) {
        repository.insertProductName(date1, date2, productName);
    }

    public List<BudgetTrackerAlias> getAllBudgetTrackerAliasList() {
        budgetTrackerAliasList = repository.getAllBudgetTrackerAliasList();
        return budgetTrackerAliasList;
    }

    public static void deleteAllAlias() {
        repository.deleteAllAlias();
    }
}
