package com.myproject.offlinebudgettrackerappproject.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.myproject.offlinebudgettrackerappproject.data.BudgetTrackerBankRepository;

import java.util.List;

public class BudgetTrackerBankViewModel extends AndroidViewModel {

    public static BudgetTrackerBankRepository bankRepository;
    private List<String> bankNames;

    public BudgetTrackerBankViewModel(@NonNull Application application) {
        super(application);
        bankRepository = new BudgetTrackerBankRepository(application);
        bankNames = bankRepository.getBankRepositoryBankNames();
    }


    public List<String> getBankViewModelBankNames() {
        return bankNames;
    }

    public static void insert(BudgetTrackerBank budgetTrackerBank) {
        bankRepository.insert(budgetTrackerBank);
    }


}
