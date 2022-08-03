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
    private List<BudgetTrackerIncome> incomeCategoryLists;
    private int productTypeSum;
    private List<BudgetTracker> dateLists;

    public BudgetTrackerIncomeRepository(Application application) {
        BudgetTrackerDatabase db = BudgetTrackerDatabase.getDatabase(application);
        budgetTrackerIncomeDao = db.budgetTrackerIncomeDao();

//        allBudgetTrackerLists = budgetTrackerDao.getAllBudgetTrackerLists();
    }

    public List<BudgetTrackerIncome> queryIncomeCategory(String incomeCategory) {
        incomeCategoryLists = budgetTrackerIncomeDao.getIncomeCategoryLists(incomeCategory);
        return incomeCategoryLists;
    }


    public void insert(BudgetTrackerIncome budgetTrackerIncome) {
        BudgetTrackerDatabase.dataWritableExecutor.execute(() -> {
            budgetTrackerIncomeDao.insert(budgetTrackerIncome);
        });
    }

    public LiveData<BudgetTrackerIncome> getBudgetTrackerIncomeId(int id) {
        return budgetTrackerIncomeDao.getBudgetTrackerIncomeId(id);
    }

    public void updateBudgetTrackerIncome(BudgetTrackerIncome budgetTrackerIncome) {
        BudgetTrackerDatabase.dataWritableExecutor.execute(() -> budgetTrackerIncomeDao.updateBudgetTrackerIncome(budgetTrackerIncome));
    }

    public void deleteBudgetTrackerIncome(BudgetTrackerIncome budgetTrackerIncome) {
        BudgetTrackerDatabase.dataWritableExecutor.execute(() -> budgetTrackerIncomeDao.deleteBudgetTrackerIncome(budgetTrackerIncome));
    }
}