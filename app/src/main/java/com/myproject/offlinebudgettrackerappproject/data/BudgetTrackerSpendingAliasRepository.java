package com.myproject.offlinebudgettrackerappproject.data;

import android.app.Application;

import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerSpendingAlias;
import com.myproject.offlinebudgettrackerappproject.util.BudgetTrackerDatabase;

import java.util.List;

public class BudgetTrackerSpendingAliasRepository {
    private BudgetTrackerSpendingAliasDao budgetTrackerSpendingAliasDao;
    private List<BudgetTrackerSpendingAlias> budgetTrackerSpendingAliasList;

    public BudgetTrackerSpendingAliasRepository(Application application) {
        BudgetTrackerDatabase db = BudgetTrackerDatabase.getDatabase(application);
        budgetTrackerSpendingAliasDao = db.budgetTrackerSpendingAliasDao();
    }

    public void insertStoreName(String dateTo, String dateFrom, String storeName) {
        BudgetTrackerDatabase.dataWritableExecutor.execute(() -> {
            budgetTrackerSpendingAliasDao.insertStoreName(dateTo, dateFrom, storeName);
        });
    }

    public List<BudgetTrackerSpendingAlias> getAllBudgetTrackerSpendingAliasList() {
        budgetTrackerSpendingAliasList = budgetTrackerSpendingAliasDao.getAllBudgetTrackerSpendingAliasList();
        return budgetTrackerSpendingAliasList;
    }

    public void deleteAllSpendingAlias() {
        BudgetTrackerDatabase.dataWritableExecutor.execute(() -> {
            budgetTrackerSpendingAliasDao.deleteAllSpendingAlias();
        });
    }

    public void deleteSequence() {
        BudgetTrackerDatabase.dataWritableExecutor.execute(() -> {
            budgetTrackerSpendingAliasDao.deleteSequence();
        });
    }
}
