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
    private List<BudgetTrackerSpending> radioSearchProductNameLists;
    private List<BudgetTrackerSpending> radioSearchProductTypeLists;
    private double searchStoreSum;
    private double searchProductSum;
    private double searchProductTypeSum;
    private List<BudgetTrackerSpending> storeNameList;
    private List<BudgetTrackerSpending> productNameList;
    private List<BudgetTrackerSpending> productTypeList;

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
//  SearchFragment
    public List<BudgetTrackerSpending> getSearchStoreNameLists(String storeName, String dateFrom, String dateTo) {
        radioSearchStoreNameLists = budgetTrackerSpendingDao.getSearchStoreNameLists(storeName, dateFrom, dateTo);
        return radioSearchStoreNameLists;
    }

    public double getSearchStoreSum(String storeName, String date1, String date2) {
        searchStoreSum = budgetTrackerSpendingDao.getSearchStoreSum(storeName, date1, date2);
        return searchStoreSum;
    }

    public List<BudgetTrackerSpending> getSearchProductNameLists(String productName, String dateFrom, String dateTo) {
        radioSearchProductNameLists = budgetTrackerSpendingDao.getSearchProductNameLists(productName, dateFrom, dateTo);
        return radioSearchProductNameLists;
    }

    public double getSearchProductSum(String productName, String date1, String date2) {
        searchProductSum = budgetTrackerSpendingDao.getSearchProductSum(productName, date1, date2);
        return searchProductSum;
    }

    public List<BudgetTrackerSpending> getSearchProductTypeLists(String productType, String dateFrom, String dateTo) {
        radioSearchProductTypeLists = budgetTrackerSpendingDao.getSearchProductTypeLists(productType, dateFrom, dateTo);
        return radioSearchProductTypeLists;
    }

    public double getSearchProductTypeSum(String productType, String date1, String date2) {
        searchProductTypeSum = budgetTrackerSpendingDao.getSearchProductTypeSum(productType, date1, date2);
        return searchProductTypeSum;
    }

    //  ReplaceFragment
    public void replaceStoreName(String storeNameFrom, String storeNameTo) {
        BudgetTrackerDatabase.dataWritableExecutor.execute(() -> {
            budgetTrackerSpendingDao.replaceStoreName(storeNameFrom, storeNameTo);
        });
    }

    public List<BudgetTrackerSpending> getStoreName(String storeName) {
        storeNameList = budgetTrackerSpendingDao.getStoreNameList(storeName);
        return storeNameList;
    }

    public void replaceProductName(String productNameFrom, String productNameTo) {
        BudgetTrackerDatabase.dataWritableExecutor.execute(() -> {
            budgetTrackerSpendingDao.replaceProductName(productNameFrom, productNameTo);
        });
    }

    public List<BudgetTrackerSpending> getProductName(String productName) {
        productNameList = budgetTrackerSpendingDao.getProductNameList(productName);
        return productNameList;
    }

    public void replaceProductType(String productTypeFrom, String productTypeTo) {
        BudgetTrackerDatabase.dataWritableExecutor.execute(() -> {
            budgetTrackerSpendingDao.replaceProductType(productTypeFrom, productTypeTo);
        });
    }

    public List<BudgetTrackerSpending> getProductType(String productType) {
        productTypeList = budgetTrackerSpendingDao.getProductTypeList(productType);
        return productTypeList;
    }

    //For getting ID for tapped item in a listview
    public LiveData<BudgetTrackerSpending> getBudgetTrackerSpendingId(int id) {
        return budgetTrackerSpendingDao.getBudgetTrackerSpendingId(id);
    }
}
