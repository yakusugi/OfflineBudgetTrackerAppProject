package com.myproject.offlinebudgettrackerappproject.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.myproject.offlinebudgettrackerappproject.model.BudgetTracker;
import com.myproject.offlinebudgettrackerappproject.util.BudgetTrackerDatabase;

import java.util.List;

public class BudgetTrackerRepository {

    private BudgetTrackerDao budgetTrackerDao;
    private LiveData<List<BudgetTracker>> allBudgetTrackerLists;
    private List<BudgetTracker> storeNameLists;
    private List<BudgetTracker> productTypeLists;
    private int productTypeSum;
    private List<BudgetTracker> dateLists;

    public BudgetTrackerRepository(Application application) {
        BudgetTrackerDatabase db = BudgetTrackerDatabase.getDatabase(application);
        budgetTrackerDao = db.budgetTrackerDao();

        allBudgetTrackerLists = budgetTrackerDao.getAllBudgetTrackerLists();
    }

    public LiveData<List<BudgetTracker>> getAllBudgetTrackerData() {
        return allBudgetTrackerLists;
    }

    public void insert(BudgetTracker budgetTracker) {
        BudgetTrackerDatabase.dataWritableExecutor.execute(() -> {
            budgetTrackerDao.insert(budgetTracker);
        });
    }

    public List<BudgetTracker> queryStoreName(String storeName) {
        storeNameLists = budgetTrackerDao.getStoreNameLists(storeName);
        return storeNameLists;
    }

    public List<BudgetTracker> queryProductType(String productType) {
        productTypeLists = budgetTrackerDao.getProductTypeLists(productType);
        return productTypeLists;
    }

    public int queryProductTypeSum(String productType) {
        productTypeSum = budgetTrackerDao.getProductSum(productType);
        return productTypeSum;
    }

    public List<BudgetTracker> queryDate(String date1, String date2) {
        dateLists = budgetTrackerDao.getDateLists(date1, date2);
        return dateLists;
    }

    public LiveData<BudgetTracker> getBudgetTrackerId(int id) {
        return budgetTrackerDao.getBudgetTrackerId(id);
    }

    public void updateBudgetTracker(BudgetTracker budgetTracker) {
        BudgetTrackerDatabase.dataWritableExecutor.execute(() -> budgetTrackerDao.updateBudgetTracker(budgetTracker));
    }

    public void deleteBudgetTracker(BudgetTracker budgetTracker) {
        BudgetTrackerDatabase.dataWritableExecutor.execute(() -> budgetTrackerDao.deleteBudgetTracker(budgetTracker));
    }


}
