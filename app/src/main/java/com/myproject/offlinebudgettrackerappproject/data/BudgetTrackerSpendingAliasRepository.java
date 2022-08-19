package com.myproject.offlinebudgettrackerappproject.data;

import android.app.Application;
import android.util.Log;

import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerSpendingAlias;
import com.myproject.offlinebudgettrackerappproject.util.BudgetTrackerDatabase;
import com.myproject.offlinebudgettrackerappproject.util.Callback;
import com.myproject.offlinebudgettrackerappproject.util.CallbackTask;
import com.myproject.offlinebudgettrackerappproject.util.ListCallback;
import com.myproject.offlinebudgettrackerappproject.util.ListCallbackTask;

import java.util.List;

public class BudgetTrackerSpendingAliasRepository {
    private BudgetTrackerSpendingAliasDao budgetTrackerSpendingAliasDao;
    private List<BudgetTrackerSpendingAlias> budgetTrackerSpendingAliasList;

    public BudgetTrackerSpendingAliasRepository(Application application) {
        BudgetTrackerDatabase db = BudgetTrackerDatabase.getDatabase(application);
        budgetTrackerSpendingAliasDao = db.budgetTrackerSpendingAliasDao();
    }

    public void insertStoreName(String dateTo, String dateFrom, String storeName, Callback callback) {
        Log.d("noifuji", "BudgetTrackerSpendingAliasRepository.insertStoreName start");
        BudgetTrackerDatabase.dataWritableExecutor.execute(
                new CallbackTask(() -> {
                    budgetTrackerSpendingAliasDao.insertStoreName(dateTo, dateFrom, storeName);
                    Log.d("noifuji", "BudgetTrackerSpendingAliasRepository.insertStoreName end");
                }, callback)
        );
    }

    public void insertProductName(String dateTo, String dateFrom, String productName, Callback callback) {
        Log.d("noifuji", "BudgetTrackerSpendingAliasRepository.insertProductName start");
        BudgetTrackerDatabase.dataWritableExecutor.execute(
                new CallbackTask(() -> {
                    budgetTrackerSpendingAliasDao.insertProductName(dateTo, dateFrom, productName);
                    Log.d("noifuji", "BudgetTrackerSpendingAliasRepository.insertProductName end");
                }, callback)
        );
    }

    public void insertProductType(String dateTo, String dateFrom, String productType, Callback callback) {
        Log.d("noifuji", "BudgetTrackerSpendingAliasRepository.insertProductType start");
        BudgetTrackerDatabase.dataWritableExecutor.execute(
                new CallbackTask(() -> {
                    budgetTrackerSpendingAliasDao.insertProductType(dateTo, dateFrom, productType);
                    Log.d("noifuji", "BudgetTrackerSpendingAliasRepository.insertProductType end");
                }, callback)
        );
    }

    public void getAllBudgetTrackerSpendingAliasList(ListCallback callback) {
        BudgetTrackerDatabase.dataWritableExecutor.execute(
                new ListCallbackTask(() -> {
                    budgetTrackerSpendingAliasList = budgetTrackerSpendingAliasDao.getAllBudgetTrackerSpendingAliasList();
                    return budgetTrackerSpendingAliasList;
                }, callback)
        );
    }

    public void deleteAllSpendingAlias(Callback callback) {
        Log.d("noifuji", "BudgetTrackerSpendingAliasRepository.deleteAllSpendingAlias start");
        BudgetTrackerDatabase.dataWritableExecutor.execute(
                new CallbackTask(() -> {
                    budgetTrackerSpendingAliasDao.deleteAllSpendingAlias();
                    Log.d("noifuji", "BudgetTrackerSpendingAliasRepository.deleteAllSpendingAlias end");
                }, callback)
        );
    }

    public void deleteSequence(Callback callback) {
        Log.d("noifuji", "BudgetTrackerSpendingAliasViewModel.deleteSequence start");
        BudgetTrackerDatabase.dataWritableExecutor.execute(
                new CallbackTask(() -> {
                    budgetTrackerSpendingAliasDao.deleteSequence();
                    Log.d("noifuji", "BudgetTrackerSpendingAliasViewModel.deleteSequence end");
                }, callback));
    }
}
