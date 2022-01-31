package com.myproject.offlinebudgettrackerappproject.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.myproject.offlinebudgettrackerappproject.model.BudgetTracker;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerIncome;
import com.myproject.offlinebudgettrackerappproject.util.BudgetTrackerDatabase;

import java.util.List;

public class BudgetTrackerIncomeRepository {

    private BudgetTrackerIncomeDao budgetTrackerIncomeDao;
    private LiveData<List<BudgetTracker>> allBudgetTrackerLists;
    private List<BudgetTracker> storeNameLists;
    private List<BudgetTracker> productTypeLists;
    private int productTypeSum;
    private List<BudgetTracker> dateLists;

    public BudgetTrackerIncomeRepository(Application application) {
        BudgetTrackerDatabase db = BudgetTrackerDatabase.getDatabase(application);
        budgetTrackerIncomeDao = db.budgetTrackerIncomeDao();

//        allBudgetTrackerLists = budgetTrackerDao.getAllBudgetTrackerLists();
    }


    public void insert(BudgetTrackerIncome budgetTrackerIncome) {
        BudgetTrackerDatabase.dataWritableExecutor.execute(() -> {
            budgetTrackerIncomeDao.insert(budgetTrackerIncome);
        });


    }
}
