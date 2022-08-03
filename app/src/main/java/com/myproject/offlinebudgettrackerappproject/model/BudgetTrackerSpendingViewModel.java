package com.myproject.offlinebudgettrackerappproject.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.myproject.offlinebudgettrackerappproject.data.BudgetTrackerSpendingRepository;

public class BudgetTrackerSpendingViewModel extends AndroidViewModel {
    public static BudgetTrackerSpendingRepository repository;

    public BudgetTrackerSpendingViewModel(@NonNull Application application) {
        super(application);
        repository = new BudgetTrackerSpendingRepository(application);
    }
//    public final LiveData<List<BudgetTrackerSpending>> allBudgetTrackerList;
//    public final List<BudgetTrackerSpending> allBudgetTrackerSpdList;

//    public BudgetTrackerSpendingViewModel(LiveData<List<BudgetTrackerSpending>> allBudgetTrackerList, List<BudgetTrackerSpending> allBudgetTrackerSpdList) {
//        this.allBudgetTrackerList = allBudgetTrackerList;
//        this.allBudgetTrackerSpdList = allBudgetTrackerSpdList;
//    }

//    public BudgetTrackerSpendingViewModel(List<BudgetTrackerSpending> allBudgetTrackerSpdList) {
//        this.allBudgetTrackerSpdList = allBudgetTrackerSpdList;
//    }



    public static void insert(BudgetTrackerSpending budgetTrackerSpending) {
        repository.insert(budgetTrackerSpending);
    }
}
