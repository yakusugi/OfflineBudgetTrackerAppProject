package com.myproject.offlinebudgettrackerappproject.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.myproject.offlinebudgettrackerappproject.data.BudgetTrackerBankingRepository;

import java.util.ArrayList;
import java.util.List;

public class BudgetTrackerBankingViewModel extends AndroidViewModel {

    public static BudgetTrackerBankingRepository bankRepository;
    private List<String> bankNames;
    private List<BudgetTrackerBanking> bankList;
    private ArrayList<BudgetTrackerBanking> bankArrayList;
    private List<BudgetTrackerBanking> bankNameLists;
    public LiveData<List<BudgetTrackerBanking>> allBudgetTrackerBankingList;

    public BudgetTrackerBankingViewModel(@NonNull Application application) {
        super(application);
        bankRepository = new BudgetTrackerBankingRepository(application);
        bankNames = bankRepository.getBankRepositoryBankNames();
        bankList = bankRepository.getBankRepositoryBankList();
        allBudgetTrackerBankingList = bankRepository.getAllBudgetTrackerBankingData();
    }

    public LiveData<List<BudgetTrackerBanking>> getAllBankingData() { return allBudgetTrackerBankingList; }

    public List<String> getBankViewModelBankNames() {

        return bankNames;
    }

    public List<BudgetTrackerBanking> getBankViewModelBankList() {
        bankRepository.getBankRepositoryBankList();
        return bankList;
    }

    public static void insert(BudgetTrackerBanking budgetTrackerBanking) {
        bankRepository.insert(budgetTrackerBanking);
    }

//    public List<BudgetTrackerBank> getBankNameLists(String bankName) {
//        bankNameLists = bankRepository.queryBankName(bankName);
//        return bankNameLists;
//    }
//
//    public LiveData<BudgetTrackerBank> getBudgetTrackerBankId(int id) {return bankRepository.getBudgetTrackerBankId(id);}
//
//    public void updateAddition(int incomeNum, String bankName) {
//        bankRepository.updateAddition(incomeNum, bankName);
//    }
//
//    public void updateSubtraction(int spendingNum, String bankName) {
//        bankRepository.updateSubtraction(spendingNum, bankName);
//    }
//
//    public static void updateBudgetTrackerBank(BudgetTrackerBank budgetTrackerBank) {bankRepository.updateBudgetTrackerBank(budgetTrackerBank);}
//
//    public static void deleteBudgetTrackerBank(BudgetTrackerBank budgetTrackerBank) {bankRepository.deleteBudgetTrackerBank(budgetTrackerBank);}


}
