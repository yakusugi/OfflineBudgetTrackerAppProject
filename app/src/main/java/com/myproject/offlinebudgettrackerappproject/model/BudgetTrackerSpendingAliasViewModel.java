package com.myproject.offlinebudgettrackerappproject.model;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.myproject.offlinebudgettrackerappproject.data.BudgetTrackerSpendingAliasRepository;
import com.myproject.offlinebudgettrackerappproject.util.Callback;
import com.myproject.offlinebudgettrackerappproject.util.ListCallback;

import java.util.List;

public class BudgetTrackerSpendingAliasViewModel extends AndroidViewModel {
    public static BudgetTrackerSpendingAliasRepository repository;
    public List<BudgetTrackerSpendingAlias> budgetTrackerSpendingAliasList;

    public BudgetTrackerSpendingAliasViewModel(@NonNull Application application) {
        super(application);
        repository = new BudgetTrackerSpendingAliasRepository(application);
    }

    public static void insertStoreName(String dateTo, String dateFrom, String storeName, Callback callback) {
        Log.d("noifuji", "BudgetTrackerSpendingAliasViewModel.insertStoreName start");
        repository.insertStoreName(dateTo, dateFrom, storeName, callback);
        Log.d("noifuji", "BudgetTrackerSpendingAliasViewModel.insertStoreName end");
    }

    public void getAllBudgetTrackerSpendingAliasList(ListCallback callback) {
        Log.d("noifuji", "BudgetTrackerSpendingAliasViewModel.getAllBudgetTrackerSpendingAliasList start");
        repository.getAllBudgetTrackerSpendingAliasList(callback);
        Log.d("noifuji", "BudgetTrackerSpendingAliasViewModel.getAllBudgetTrackerSpendingAliasList end");
    }

    public static void deleteAllSpendingAlias(Callback callback) {
        Log.d("noifuji", "BudgetTrackerSpendingAliasViewModel.deleteAllSpendingAlias start");
        repository.deleteAllSpendingAlias(callback);
        Log.d("noifuji", "BudgetTrackerSpendingAliasViewModel.deleteAllSpendingAlias end");
    }

    public static void deleteSequence(Callback callback) {
        Log.d("noifuji", "BudgetTrackerSpendingAliasViewModel.deleteSequence start");
        repository.deleteSequence(callback);
        Log.d("noifuji", "BudgetTrackerSpendingAliasViewModel.deleteSequence end");
    }
}
