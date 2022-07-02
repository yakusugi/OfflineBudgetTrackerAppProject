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
    private List<BudgetTracker> productNameList;
    private int productTypeSum;
    private int storeNameSum;
    private List<BudgetTracker> dateLists;
    private List<BudgetTracker> radioStoreNameLists;
    private int dateStoreSum;
    private List<BudgetTracker> radioProductNameLists;
    private int dateProductNameSum;
    private List<BudgetTracker> radioProductTypeLists;
    private int dateProductTypeSum;
    private List<BudgetTracker> radioStoreSearchHomeLists;

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

    public List<BudgetTracker> queryProductName(String productName) {
        productNameList = budgetTrackerDao.getProductNameList(productName);
        return productNameList;
    }

    public int queryProductTypeSum(String productType) {
        productTypeSum = budgetTrackerDao.getProductSum(productType);
        return productTypeSum;
    }

    public int queryStoreNameSum(String storeName) {
        storeNameSum = budgetTrackerDao.getStoreSum(storeName);
        return storeNameSum;
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

    public List<BudgetTracker> getRadioStoreNameLists(String storeName, String date1, String date2) {
        radioStoreNameLists = budgetTrackerDao.getRadioStoreNameLists(storeName, date1, date2);
        return radioStoreNameLists;
    }

    public int getDateStoreSum(String storeName, String date1, String date2) {
        dateStoreSum = budgetTrackerDao.getDateStoreSum(storeName, date1, date2);
        return dateStoreSum;
    }

    public List<BudgetTracker> getRadioProductNameLists(String productName, String date1, String date2) {
        radioProductNameLists = budgetTrackerDao.getRadioProductNameLists(productName, date1, date2);
        return radioProductNameLists;
    }

    public int getDateProductNameSum(String storeName, String date1, String date2) {
        dateProductNameSum = budgetTrackerDao.getDateProductNameSum(storeName, date1, date2);
        return dateProductNameSum;
    }

    public List<BudgetTracker> getRadioProductTypeLists(String productType, String date1, String date2) {
        radioProductTypeLists = budgetTrackerDao.getRadioProductTypeLists(productType, date1, date2);
        return radioProductTypeLists;
    }

    public int getDateProductTypeSum(String productType, String date1, String date2) {
        dateProductTypeSum = budgetTrackerDao.getDateProductTypeSum(productType, date1, date2);
        return dateProductTypeSum;
    }

    public void replaceStoreName(String storeNameFrom, String storeNameTo) {
        BudgetTrackerDatabase.dataWritableExecutor.execute(() -> {
            budgetTrackerDao.replaceStoreName(storeNameFrom, storeNameTo);
        });
    }

    public void replaceProductName(String productNameFrom, String productNameTo) {
        BudgetTrackerDatabase.dataWritableExecutor.execute(() -> {
            budgetTrackerDao.replaceProductName(productNameFrom, productNameTo);
        });
    }

    public void replaceProductType(String productTypeFrom, String productTypeTo) {
        BudgetTrackerDatabase.dataWritableExecutor.execute(() -> {
            budgetTrackerDao.replaceProductType(productTypeFrom, productTypeTo);
        });
    }



}
