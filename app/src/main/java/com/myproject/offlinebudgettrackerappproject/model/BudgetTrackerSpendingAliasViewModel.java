package com.myproject.offlinebudgettrackerappproject.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.myproject.offlinebudgettrackerappproject.data.BudgetTrackerSpendingAliasRepository;

import java.util.List;

public class BudgetTrackerSpendingAliasViewModel extends AndroidViewModel {
    public static BudgetTrackerSpendingAliasRepository repository;
    public List<BudgetTrackerSpendingAlias> budgetTrackerSpendingAliasList;

    public BudgetTrackerSpendingAliasViewModel(@NonNull Application application) {
        super(application);
        repository = new BudgetTrackerSpendingAliasRepository(application);
    }

    public static void insertStoreName(String dateTo, String dateFrom, String storeName) {
        repository.insertStoreName(dateTo, dateFrom, storeName);
    }

    public List<BudgetTrackerSpendingAlias> getAllBudgetTrackerSpendingAliasList() {
        budgetTrackerSpendingAliasList = repository.getAllBudgetTrackerSpendingAliasList();
        return budgetTrackerSpendingAliasList;
    }

    public static void deleteAllSpendingAlias() {
        repository.deleteAllSpendingAlias();
    }

    public static void deleteSequence() {
        repository.deleteSequence();
    }
}
