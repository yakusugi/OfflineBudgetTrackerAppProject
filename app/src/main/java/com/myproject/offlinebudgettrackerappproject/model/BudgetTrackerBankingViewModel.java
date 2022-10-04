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

    public void updateSubtraction(double spendingNum, String bankName) {
        bankRepository.updateSubtraction(spendingNum, bankName);
    }

    public void updateAddition(double incomesNum, String bankName) {
        bankRepository.updateAddition(incomesNum, bankName);
    }


}
