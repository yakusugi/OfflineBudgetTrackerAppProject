package com.myproject.offlinebudgettrackerappproject.data;

import android.app.Application;

import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerBank;
import com.myproject.offlinebudgettrackerappproject.util.BudgetTrackerDatabase;

import java.util.List;

public class BudgetTrackerBankRepository {

    private BudgetTrackerBankDao budgetTrackerBankDao;
    private List<String> repositoryBankNames;

    public BudgetTrackerBankRepository(Application application) {
        BudgetTrackerDatabase db = BudgetTrackerDatabase.getDatabase(application);
        budgetTrackerBankDao = db.budgetTrackerBankDao();

        repositoryBankNames = budgetTrackerBankDao.getBankNames();
    }

    public List<String> getBankRepositoryBankNames() {
        return repositoryBankNames;
    }

    public void insert(BudgetTrackerBank budgetTrackerBank) {
        BudgetTrackerDatabase.dataWritableExecutor.execute(() -> {
            budgetTrackerBankDao.insert(budgetTrackerBank);
        });
    }
}
