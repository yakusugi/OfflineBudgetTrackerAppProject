package com.myproject.offlinebudgettrackerappproject.data;

import android.app.Application;

import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerAlias;
import com.myproject.offlinebudgettrackerappproject.util.BudgetTrackerDatabase;

import java.util.List;

public class BudgetTrackerAliasRepository {

    private BudgetTrackerAliasDao budgetTrackerAliasDao;
    private List<BudgetTrackerAlias> budgetTrackerAliasList;

    public BudgetTrackerAliasRepository(Application application) {
        BudgetTrackerDatabase db = BudgetTrackerDatabase.getDatabase(application);
        budgetTrackerAliasDao = db.budgetTrackerAliasDao();
    }

    public void insert(String date1, String date2, String storeName) {
        BudgetTrackerDatabase.dataWritableExecutor.execute(() -> {
            budgetTrackerAliasDao.insert(date1, date2, storeName);
        });
    }

    public List<BudgetTrackerAlias> getAllBudgetTrackerAliasList() {
        budgetTrackerAliasList = budgetTrackerAliasDao.getAllBudgetTrackerAliasList();
        return budgetTrackerAliasList;
    }

    public void deleteAllAlias() {
        BudgetTrackerDatabase.dataWritableExecutor.execute(() -> {
            budgetTrackerAliasDao.deleteAllAlias();
        });
    }

}
