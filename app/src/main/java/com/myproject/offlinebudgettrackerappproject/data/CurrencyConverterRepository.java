package com.myproject.offlinebudgettrackerappproject.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.myproject.offlinebudgettrackerappproject.model.CurrencyConverter;
import com.myproject.offlinebudgettrackerappproject.util.BudgetTrackerDatabase;

import java.util.List;

public class CurrencyConverterRepository {
    private CurrencyConverterDao currencyConverterDao;
    private LiveData<List<CurrencyConverter>> allBankConverterList;

    public CurrencyConverterRepository(Application application) {
        BudgetTrackerDatabase db = BudgetTrackerDatabase.getDatabase(application);
        currencyConverterDao = db.currencyConverterDao();

        allBankConverterList = currencyConverterDao.getAllCurrencyConverterList();
    }

    public LiveData<List<CurrencyConverter>> getAllBankConverterData() {
        return allBankConverterList;
    }

    public void insert(CurrencyConverter currencyConverter) {
        BudgetTrackerDatabase.dataWritableExecutor.execute(() -> {
            currencyConverterDao.insert(currencyConverter);
        });
    }

    public void deleteBankConverter(CurrencyConverter currencyConverter) {
        BudgetTrackerDatabase.dataWritableExecutor.execute(() -> currencyConverterDao.deleteBankConverter(currencyConverter));
    }

}
