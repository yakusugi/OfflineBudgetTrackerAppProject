package com.myproject.offlinebudgettrackerappproject.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.myproject.offlinebudgettrackerappproject.data.BudgetTrackerBankRepository;

import java.util.List;

public class BudgetTrackerBankViewModel extends AndroidViewModel {

    public static BudgetTrackerBankRepository bankRepository;
    private List<String> bankNames;
    private List<BudgetTrackerBank> bankList;

    public BudgetTrackerBankViewModel(@NonNull Application application) {
        super(application);
        bankRepository = new BudgetTrackerBankRepository(application);
        bankNames = bankRepository.getBankRepositoryBankNames();
        bankList = bankRepository.getBankRepositoryBankList();
    }


    public List<String> getBankViewModelBankNames() {

        return bankNames;
    }

    public List<BudgetTrackerBank> getBankViewModelBankList() {

        return bankList;
    }

    public static void insert(BudgetTrackerBank budgetTrackerBank) {
        bankRepository.insert(budgetTrackerBank);
    }


}
