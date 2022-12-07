package com.myproject.offlinebudgettrackerappproject.model;

import static android.provider.Telephony.Mms.Part.FILENAME;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.myproject.offlinebudgettrackerappproject.data.BudgetTrackerSpendingRepository;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.List;

public class BudgetTrackerSpendingViewModel extends AndroidViewModel {
    public static BudgetTrackerSpendingRepository repository;
    public List<BudgetTrackerSpending> radioSearchStoreNameLists;
    public List<BudgetTrackerSpending> radioSearchProductNameLists;
    public List<BudgetTrackerSpending> radioSearchProductTypeLists;
    private List<BudgetTrackerSpending> quickStoreNameList;
    private List<BudgetTrackerSpending> quickProductTypeList;
    private List<BudgetTrackerSpending> quickProductNameList;
    private double searchStoreSum;
    private double searchProductSum;
    private double searchProductTypeSum;
    private double quickStoreSum;
    private double quickProductTypeSum;
    private double quickProductNameSum;
    public List<BudgetTrackerSpending> storeNameList;
    public List<BudgetTrackerSpending> productNameList;
    public List<BudgetTrackerSpending> productTypeList;
    public LiveData<List<BudgetTrackerSpending>> allBudgetTrackerSpendingList;
    private MutableLiveData<List<BudgetTrackerSpending>> dataListLiveData = new MutableLiveData<>(null);



    public BudgetTrackerSpendingViewModel(@NonNull Application application) {
        super(application);
        repository = new BudgetTrackerSpendingRepository(application);
        allBudgetTrackerSpendingList = repository.getAllBudgetTrackerSpendingData();
    }

    public LiveData<List<BudgetTrackerSpending>> getAllSpendingData() { return allBudgetTrackerSpendingList; }

    public static void insert(BudgetTrackerSpending budgetTrackerSpending) {
        repository.insert(budgetTrackerSpending);
    }

    public static void updateBudgetTrackerSpending(BudgetTrackerSpending budgetTrackerSpending) {repository.updateBudgetTrackerSpending(budgetTrackerSpending);}
    public static void deleteBudgetTrackerSpending(BudgetTrackerSpending budgetTrackerSpending) {repository.deleteBudgetTrackerSpending(budgetTrackerSpending);}

    //  SearchFragment
    public List<BudgetTrackerSpending> getSearchStoreNameLists(String storeName, String dateFrom, String dateTo) {
        radioSearchStoreNameLists = repository.getSearchStoreNameLists(storeName, dateFrom, dateTo);
        return radioSearchStoreNameLists;
    }

    public double getSearchStoreSum(String storeName, String dateFrom, String dateTo) {
        searchStoreSum = repository.getSearchStoreSum(storeName, dateFrom, dateTo);
        return searchStoreSum;
    }

    public List<BudgetTrackerSpending> getSearchProductNameLists(String productName, String dateFrom, String dateTo) {
        radioSearchProductNameLists = repository.getSearchProductNameLists(productName, dateFrom, dateTo);
        return radioSearchProductNameLists;
    }

    public double getSearchProductSum(String productName, String dateFrom, String dateTo) {
        searchProductSum = repository.getSearchProductSum(productName, dateFrom, dateTo);
        return searchProductSum;
    }

    public List<BudgetTrackerSpending> getSearchProductTypeLists(String productType, String dateFrom, String dateTo) {
        radioSearchProductTypeLists = repository.getSearchProductTypeLists(productType, dateFrom, dateTo);
        return radioSearchProductTypeLists;
    }

    public double getSearchProductTypeSum(String productType, String dateFrom, String dateTo) {
        searchProductTypeSum = repository.getSearchProductTypeSum(productType, dateFrom, dateTo);
        return searchProductTypeSum;
    }

//  ReplaceFragment
    public static void replaceStoreName(String storeNameFrom, String storeNameToe) {
        repository.replaceStoreName(storeNameFrom, storeNameToe);
    }

    public List<BudgetTrackerSpending> getStoreNameList(String storeName) {
        storeNameList = repository.getStoreName(storeName);
        return storeNameList;
    }

    public static void replaceProductName(String productNameFrom, String productNameTo) {
        repository.replaceProductName(productNameFrom, productNameTo);
    }

    public List<BudgetTrackerSpending> getProductNameList(String productName) {
        productNameList = repository.getProductName(productName);
        return productNameList;
    }

    public static void replaceProductType(String productTypeFrom, String productTypeTo) {
        repository.replaceProductType(productTypeFrom, productTypeTo);
    }

    public List<BudgetTrackerSpending> getProductTypeList(String productType) {
        productTypeList = repository.getProductType(productType);
        return productTypeList;
    }

    //todo temporality commented out For getting ID for tapped item in a listview
//    public LiveData<BudgetTrackerSpending> getBudgetTrackerSpendingId(int id) {return repository.getBudgetTrackerSpendingId(id);}

    //For Quick search (Store)
    public List<BudgetTrackerSpending> getQuickStoreNameList(String storeName) {
        quickStoreNameList = repository.getQuickStoreNameList(storeName);
        return quickStoreNameList;
    }

    public double getQuickStoreSum(String storeName) {
        quickStoreSum = repository.getQuickStoreSum(storeName);
        return quickStoreSum;
    }

    //For Quick search (Product Type)
    public List<BudgetTrackerSpending> getQuickProductTypeList(String productType) {
        quickProductTypeList = repository.getQuickProductTypeList(productType);
        return quickProductTypeList;
    }

    public double getQuickProductTypeSum(String productType) {
        quickProductTypeSum = repository.getQuickProductTypeSum(productType);
        return quickProductTypeSum;
    }

    //For Quick search (Product Name)
    public List<BudgetTrackerSpending> getQuickProductNameList(String productName) {
        quickProductNameList = repository.getQuickProductNameList(productName);
        return quickProductNameList;
    }

    public double getQuickProductNameSum(String productName) {
        quickProductNameSum = repository.getQuickProductNameSum(productName);
        return quickProductNameSum;
    }

    // todo: 2022/12/07 for listview tap

    private void writeFile() {
        try(Writer w = new PrintWriter(getApplication().openFileOutput(FILENAME, Context.MODE_PRIVATE))) {
            w.write(new Gson().toJson(dataListLiveData.getValue()));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

//    LiveData<List<BudgetTrackerSpending>> getDataList() { return dataListLiveData; }
//
//    void delete(long id) {
//        if(deleteFromList(id)) writeFile();
//    }
//    private boolean deleteFromList(long id) {
//        List<BudgetTrackerSpending> list = dataListLiveData.getValue();
//        for(int i=0; i<list.size(); i++) {
//            if(list.get(i).id == id) {
//                list.remove(i);
//                dataListLiveData.setValue(list); //一応オブザーバへ通知
//                return true; //更新有り
//            }
//        }
//        return false;
//    }
//
//    void update(Data data) {
//        if(updateListElement(data)) writeFile();
//    }
//    private boolean updateListElement(Data data) {
//        List<Data> list = dataListLiveData.getValue();
//        for(int i=0; i<list.size(); i++) {
//            if(list.get(i).id == data.id) {
//                list.set(i, data);
//                dataListLiveData.setValue(list); //一応オブザーバへ通知
//                return true; //更新有り
//            }
//        }
//        return false;
//    }
}
