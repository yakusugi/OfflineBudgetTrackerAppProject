package com.myproject.offlinebudgettrackerappproject.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.myproject.offlinebudgettrackerappproject.data.CurrencyConverterRepository;

import java.util.List;

public class CurrencyConverterViewModel extends AndroidViewModel {
    public static CurrencyConverterRepository repository;
    public List<BudgetTrackerSpending> radioSearchStoreNameLists;
    public List<BudgetTrackerSpending> radioSearchProductNameLists;
    public List<BudgetTrackerSpending> radioSearchProductTypeLists;
    private double searchStoreSum;
    private double searchProductSum;
    private double searchProductTypeSum;
    public List<BudgetTrackerSpending> storeNameList;
    public List<BudgetTrackerSpending> productNameList;
    public List<BudgetTrackerSpending> productTypeList;
    public LiveData<List<CurrencyConverter>> allBankConverterList;

    public CurrencyConverterViewModel(@NonNull Application application) {
        super(application);
        repository = new CurrencyConverterRepository(application);
        allBankConverterList = repository.getAllBankConverterData();
    }

    public LiveData<List<CurrencyConverter>> getAllCurrencyConverterData() { return allBankConverterList; }

    public static void insert(CurrencyConverter currencyConverter) {
        repository.insert(currencyConverter);
    }

    public static void deleteBankConverter(CurrencyConverter currencyConverter) {repository.deleteBankConverter(currencyConverter);}
}
