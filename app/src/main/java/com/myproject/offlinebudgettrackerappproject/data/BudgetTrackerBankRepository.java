package com.myproject.offlinebudgettrackerappproject.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerBank;
import com.myproject.offlinebudgettrackerappproject.util.BudgetTrackerDatabase;

import java.util.ArrayList;
import java.util.List;

public class BudgetTrackerBankRepository {

    private BudgetTrackerBankDao budgetTrackerBankDao;
    private List<String> repositoryBankNames;
    private List<BudgetTrackerBank> repositoryBankList;
    private ArrayList<BudgetTrackerBank> repositoryArrayBankList;
    private List<BudgetTrackerBank> bankNameLists;

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
        budgetTrackerBankDao.getBankBalanceList();
        return repositoryBankList;
    }

    public void insert(BudgetTrackerBank budgetTrackerBank) {
        BudgetTrackerDatabase.dataWritableExecutor.execute(() -> {
            budgetTrackerBankDao.insert(budgetTrackerBank);
        });
    }

    public List<BudgetTrackerBank> queryBankName(String bankName) {
        bankNameLists = budgetTrackerBankDao.getBankNameLists(bankName);
        return bankNameLists;
    }

    public LiveData<BudgetTrackerBank> getBudgetTrackerBankId(int id) {
        return budgetTrackerBankDao.getBudgetTrackerBankId(id);
    }

    public void updateAddition(int incomeNum, String bankName) {
        BudgetTrackerDatabase.dataWritableExecutor.execute(() -> budgetTrackerBankDao.updateAddition(incomeNum, bankName));
    }

    public void updateSubtraction(int spendingNum, String bankName) {
        BudgetTrackerDatabase.dataWritableExecutor.execute(() -> budgetTrackerBankDao.updateSubtraction(spendingNum, bankName));
    }

    public void updateBudgetTrackerBank(BudgetTrackerBank budgetTrackerBank) {
        BudgetTrackerDatabase.dataWritableExecutor.execute(() -> budgetTrackerBankDao.updateBudgetTrackerBank(budgetTrackerBank));
    }

    public void deleteBudgetTrackerBank(BudgetTrackerBank budgetTrackerBank) {
        BudgetTrackerDatabase.dataWritableExecutor.execute(() -> budgetTrackerBankDao.deleteBudgetTrackerBank(budgetTrackerBank));
    }
}
