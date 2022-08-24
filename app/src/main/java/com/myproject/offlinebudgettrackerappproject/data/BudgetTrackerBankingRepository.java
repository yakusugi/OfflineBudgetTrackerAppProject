package com.myproject.offlinebudgettrackerappproject.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerBank;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerBanking;
import com.myproject.offlinebudgettrackerappproject.util.BudgetTrackerDatabase;

import java.util.ArrayList;
import java.util.List;

public class BudgetTrackerBankingRepository {

    private BudgetTrackerBankingDao budgetTrackerBankingDao;
    private List<String> repositoryBankingNames;
    private List<BudgetTrackerBanking> repositoryBankingList;
    private LiveData<List<BudgetTrackerBanking>> allBudgetTrackerBakingList;
    private ArrayList<BudgetTrackerBank> repositoryArrayBankingList;
    private List<BudgetTrackerBanking> bankingNameLists;

    public BudgetTrackerBankingRepository(Application application) {
        BudgetTrackerDatabase db = BudgetTrackerDatabase.getDatabase(application);
        budgetTrackerBankingDao = db.budgetTrackerBankingDao();

        repositoryBankingNames = budgetTrackerBankingDao.getBankNames();
        repositoryBankingList = budgetTrackerBankingDao.getBankList();
        allBudgetTrackerBakingList = budgetTrackerBankingDao.getAllBudgetTrackerBankingList();
    }

    public LiveData<List<BudgetTrackerBanking>> getAllBudgetTrackerBankingData() {
        return allBudgetTrackerBakingList;
    }

    public List<String> getBankRepositoryBankNames() {

        return repositoryBankingNames;
    }

    public List<BudgetTrackerBanking> getBankRepositoryBankList() {
        budgetTrackerBankingDao.getBankBalanceList();
        return repositoryBankingList;
    }

    public void insert(BudgetTrackerBanking budgetTrackerBanking) {
        BudgetTrackerDatabase.dataWritableExecutor.execute(() -> {
            budgetTrackerBankingDao.insert(budgetTrackerBanking);
        });
    }

//    public List<BudgetTrackerBank> queryBankName(String bankName) {
//        bankNameLists = budgetTrackerBankDao.getBankNameLists(bankName);
//        return bankNameLists;
//    }
//
//    public LiveData<BudgetTrackerBank> getBudgetTrackerBankId(int id) {
//        return budgetTrackerBankDao.getBudgetTrackerBankId(id);
//    }
//
//    public void updateAddition(int incomeNum, String bankName) {
//        BudgetTrackerDatabase.dataWritableExecutor.execute(() -> budgetTrackerBankDao.updateAddition(incomeNum, bankName));
//    }
//
//    public void updateSubtraction(int spendingNum, String bankName) {
//        BudgetTrackerDatabase.dataWritableExecutor.execute(() -> budgetTrackerBankDao.updateSubtraction(spendingNum, bankName));
//    }
//
//    public void updateBudgetTrackerBank(BudgetTrackerBank budgetTrackerBank) {
//        BudgetTrackerDatabase.dataWritableExecutor.execute(() -> budgetTrackerBankDao.updateBudgetTrackerBank(budgetTrackerBank));
//    }
//
//    public void deleteBudgetTrackerBank(BudgetTrackerBank budgetTrackerBank) {
//        BudgetTrackerDatabase.dataWritableExecutor.execute(() -> budgetTrackerBankDao.deleteBudgetTrackerBank(budgetTrackerBank));
//    }
}
