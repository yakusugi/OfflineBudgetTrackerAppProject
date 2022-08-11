package com.myproject.offlinebudgettrackerappproject.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerSpending;
import com.myproject.offlinebudgettrackerappproject.util.BudgetTrackerDatabase;

import java.util.List;

public class BudgetTrackerSpendingRepository {
    private BudgetTrackerSpendingDao budgetTrackerSpendingDao;
    private LiveData<List<BudgetTrackerSpending>> allBudgetTrackerList;
    private List<BudgetTrackerSpending> radioSearchStoreNameLists;
    private double searchStoreSum;

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

    public List<BudgetTrackerSpending> getSearchStoreNameLists(String storeName, String dateFrom, String dateTo) {
        radioSearchStoreNameLists = budgetTrackerSpendingDao.getSearchStoreNameLists(storeName, dateFrom, dateTo);
        return radioSearchStoreNameLists;
    }

    public double getSearchStoreSum(String storeName, String date1, String date2) {
        searchStoreSum = budgetTrackerSpendingDao.getSearchStoreSum(storeName, date1, date2);
        return searchStoreSum;
    }

}
