package com.myproject.offlinebudgettrackerappproject.data;

import android.app.Application;

import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerBank;
import com.myproject.offlinebudgettrackerappproject.util.BudgetTrackerDatabase;

import java.util.List;

public class BudgetTrackerBankRepository {

    private BudgetTrackerBankDao budgetTrackerBankDao;
    private List<String> repositoryBankNames;
    private List<BudgetTrackerBank> repositoryBankList;

    public BudgetTrackerBankRepository(Application application) {
        BudgetTrackerDatabase db = BudgetTrackerDatabase.getDatabase(application);
        budgetTrackerBankDao = db.budgetTrackerBankDao();

        repositoryBankNames = budgetTrackerBankDao.getBankNames();
        repositoryBankList = budgetTrackerBankDao.getBankList();
    }

    public List<String> getBankRepositoryBankNames() {

        return repositoryBankNames;
    }

    public List<BudgetTrackerBank> getBankRepositoryBankList() {

        return repositoryBankList;
    }

    public void insert(BudgetTrackerBank budgetTrackerBank) {
        BudgetTrackerDatabase.dataWritableExecutor.execute(() -> {
            budgetTrackerBankDao.insert(budgetTrackerBank);
        });
    }
}
