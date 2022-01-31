package com.myproject.offlinebudgettrackerappproject.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.myproject.offlinebudgettrackerappproject.data.BudgetTrackerIncomeRepository;

public class BudgetTrackerIncomeViewModel extends AndroidViewModel {

    public static BudgetTrackerIncomeRepository incomeRepository;

    public BudgetTrackerIncomeViewModel(@NonNull Application application) {
        super(application);
        incomeRepository = new BudgetTrackerIncomeRepository(application);

    }


    public static void insert(BudgetTrackerIncome budgetTrackerIncome) {
        incomeRepository.insert(budgetTrackerIncome);
    }


}
