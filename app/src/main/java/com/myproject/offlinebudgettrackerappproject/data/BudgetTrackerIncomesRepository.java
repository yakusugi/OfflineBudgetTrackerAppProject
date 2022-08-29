package com.myproject.offlinebudgettrackerappproject.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerIncomes;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerSpending;
import com.myproject.offlinebudgettrackerappproject.util.BudgetTrackerDatabase;

import java.util.List;

public class BudgetTrackerIncomesRepository {

    private BudgetTrackerIncomesDao budgetTrackerIncomesDao;
    private LiveData<List<BudgetTrackerSpending>> allBudgetTrackerSpendingList;
    private List<BudgetTrackerIncomes> incomesCategoryList;
    private int productTypeSum;
    private List<BudgetTrackerSpending> dateLists;

    public BudgetTrackerIncomesRepository(Application application) {
        BudgetTrackerDatabase db = BudgetTrackerDatabase.getDatabase(application);
        budgetTrackerIncomesDao = db.budgetTrackerIncomesDao();

//        allBudgetTrackerLists = budgetTrackerDao.getAllBudgetTrackerLists();
    }

//    public List<BudgetTrackerIncomes> queryIncomesCategory(String incomeCategory) {
//        incomesCategoryList = budgetTrackerIncomesDao.getIncomeCategoryLists(incomeCategory);
//        return incomeCategoryLists;
//    }


    public void insert(BudgetTrackerIncomes budgetTrackerIncomes) {
        BudgetTrackerDatabase.dataWritableExecutor.execute(() -> {
            budgetTrackerIncomesDao.insert(budgetTrackerIncomes);
        });
    }

//    public LiveData<BudgetTrackerIncome> getBudgetTrackerIncomeId(int id) {
//        return budgetTrackerIncomeDao.getBudgetTrackerIncomeId(id);
//    }
//
//    public void updateBudgetTrackerIncome(BudgetTrackerIncome budgetTrackerIncome) {
//        BudgetTrackerDatabase.dataWritableExecutor.execute(() -> budgetTrackerIncomeDao.updateBudgetTrackerIncome(budgetTrackerIncome));
//    }
//
//    public void deleteBudgetTrackerIncome(BudgetTrackerIncome budgetTrackerIncome) {
//        BudgetTrackerDatabase.dataWritableExecutor.execute(() -> budgetTrackerIncomeDao.deleteBudgetTrackerIncome(budgetTrackerIncome));
//    }
}