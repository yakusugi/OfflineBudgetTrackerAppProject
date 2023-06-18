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
    private List<BudgetTrackerSpending> allBudgetTrackerListForMySQL;
    private List<BudgetTrackerSpending> radioSearchProductNameLists;
    private List<BudgetTrackerSpending> radioSearchProductTypeLists;
    private List<BudgetTrackerSpending> quickStoreNameList;
    private List<BudgetTrackerSpending> quickProductTypeList;
    private List<BudgetTrackerSpending> quickProductNameList;
    private List<BudgetTrackerSpending> quickDateList;
    private double searchStoreSum;
    private double searchProductSum;
    private double searchProductTypeSum;
    private double quickStoreSum;
    private double quickProductTypeSum;
    private double quickProductNameSum;
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

    //for mysql sync
    public List<BudgetTrackerSpending> getBudgetTrackerSpendingListForMySQL() {
        allBudgetTrackerListForMySQL = budgetTrackerSpendingDao.getBudgetTrackerSpendingListForMySQL();
        return allBudgetTrackerListForMySQL;
    }

    public void insert(BudgetTrackerSpending budgetTrackerSpending) {
        BudgetTrackerDatabase.dataWritableExecutor.execute(() -> {
            budgetTrackerSpendingDao.insert(budgetTrackerSpending);
        });
    }

    public void updateBudgetTrackerSpending(BudgetTrackerSpending budgetTrackerSpending) {
        BudgetTrackerDatabase.dataWritableExecutor.execute(() -> budgetTrackerSpendingDao.updateBudgetTrackerSpending(budgetTrackerSpending));
    }

    public void deleteBudgetTrackerSpending(BudgetTrackerSpending budgetTrackerSpending) {
        BudgetTrackerDatabase.dataWritableExecutor.execute(() -> budgetTrackerSpendingDao.deleteBudgetTrackerSpending(budgetTrackerSpending));
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

    //For Quick search (Store)
    public List<BudgetTrackerSpending> getQuickStoreNameList(String storeName) {
        quickStoreNameList = budgetTrackerSpendingDao.getQuickStoreNameList(storeName);
        return quickStoreNameList;
    }

    public double getQuickStoreSum(String storeName) {
        quickStoreSum = budgetTrackerSpendingDao.getQuickStoreSum(storeName);
        return quickStoreSum;
    }

    //For Quick search (Product type)
    public List<BudgetTrackerSpending> getQuickProductTypeList(String productType) {
        quickProductTypeList = budgetTrackerSpendingDao.getQuickProductTypeList(productType);
        return quickProductTypeList;
    }

    public double getQuickProductTypeSum(String productType) {
        quickProductTypeSum = budgetTrackerSpendingDao.getQuickProductTypeSum(productType);
        return quickProductTypeSum;
    }

    //For Quick search (Product name)
    public List<BudgetTrackerSpending> getQuickProductNameList(String productName) {
        quickProductNameList = budgetTrackerSpendingDao.getQuickProductNameList(productName);
        return quickProductNameList;
    }

    public double getQuickProductNameSum(String productName) {
        quickProductNameSum = budgetTrackerSpendingDao.getQuickProductNameSum(productName);
        return quickProductNameSum;
    }

    public List<BudgetTrackerSpending> getQuickDate(String dateFrom, String dateTo) {
        quickDateList = budgetTrackerSpendingDao.getQuickDateList(dateFrom, dateTo);
        return quickDateList;
    }
}
