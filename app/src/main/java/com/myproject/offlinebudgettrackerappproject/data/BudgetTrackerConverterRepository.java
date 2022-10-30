package com.myproject.offlinebudgettrackerappproject.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerConverter;
import com.myproject.offlinebudgettrackerappproject.util.BudgetTrackerDatabase;

import java.util.List;

public class BudgetTrackerConverterRepository {
    private BudgetTrackerConverterDao budgetTrackerConverterDao;
    private LiveData<List<BudgetTrackerConverter>> allBudgetTrackerConverterList;

    public BudgetTrackerConverterRepository(Application application) {
        BudgetTrackerDatabase db = BudgetTrackerDatabase.getDatabase(application);
        budgetTrackerConverterDao = db.budgetTrackerConverterDao();

        allBudgetTrackerConverterList = budgetTrackerConverterDao.getAllBudgetTrackerConverterList();
    }

    public LiveData<List<BudgetTrackerConverter>> getAllBudgetTrackerConverterData() {
        return allBudgetTrackerConverterList;
    }

    public void insert(BudgetTrackerConverter budgetTrackerConverter) {
        BudgetTrackerDatabase.dataWritableExecutor.execute(() -> {
            budgetTrackerConverterDao.insert(budgetTrackerConverter);
        });
    }

    public void updateBudgetTrackerConverter(BudgetTrackerConverter budgetTrackerConverter) {
        BudgetTrackerDatabase.dataWritableExecutor.execute(() -> budgetTrackerConverterDao.updateBudgetTrackerConverter(budgetTrackerConverter));
    }

    public void deleteBudgetTrackerConverter(BudgetTrackerConverter budgetTrackerConverter) {
        BudgetTrackerDatabase.dataWritableExecutor.execute(() -> budgetTrackerConverterDao.deleteBudgetTrackerConverter(budgetTrackerConverter));
    }


}
