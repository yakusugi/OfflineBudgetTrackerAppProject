package com.myproject.offlinebudgettrackerappproject.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerSpending;
import com.myproject.offlinebudgettrackerappproject.util.BudgetTrackerDatabase;

import java.util.List;

public class BudgetTrackerSpendingRepository {
    private BudgetTrackerSpendingDao budgetTrackerSpendingDao;
    private LiveData<List<BudgetTrackerSpending>> allBudgetTrackerList;

    public BudgetTrackerSpendingRepository(Application application) {
        BudgetTrackerDatabase db = BudgetTrackerDatabase.getDatabase(application);
        budgetTrackerSpendingDao = db.budgetTrackerSpendingDao();

        allBudgetTrackerList = budgetTrackerSpendingDao.getAllBudgetTrackerSpendingList();
    }

    public LiveData<List<BudgetTrackerSpending>> getAllBudgetTrackerSpendingData() {
        return allBudgetTrackerList;
    }

    public void insert(BudgetTrackerSpending budgetTrackerSpending) {
        BudgetTrackerDatabase.dataWritableExecutor.execute(() -> {
            budgetTrackerSpendingDao.insert(budgetTrackerSpending);
        });
    }

}
