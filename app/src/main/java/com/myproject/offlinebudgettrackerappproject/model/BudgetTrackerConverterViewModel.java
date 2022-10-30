package com.myproject.offlinebudgettrackerappproject.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.myproject.offlinebudgettrackerappproject.data.BudgetTrackerConverterRepository;

import java.util.List;

public class BudgetTrackerConverterViewModel extends AndroidViewModel {
    public static BudgetTrackerConverterRepository repository;
    public LiveData<List<BudgetTrackerConverter>> allBudgetTrackerConverterList;

    public BudgetTrackerConverterViewModel(@NonNull Application application) {
        super(application);
        repository = new BudgetTrackerConverterRepository(application);
        allBudgetTrackerConverterList = repository.getAllBudgetTrackerConverterData();
    }

    public LiveData<List<BudgetTrackerConverter>> getAllConverterData() { return allBudgetTrackerConverterList; }

    public static void insert(BudgetTrackerConverter budgetTrackerConverter) {
        repository.insert(budgetTrackerConverter);
    }

    public static void updateBudgetTrackerConverter(BudgetTrackerConverter budgetTrackerConverter) {repository.updateBudgetTrackerConverter(budgetTrackerConverter);}
    public static void deleteBudgetTrackerConverter(BudgetTrackerConverter budgetTrackerConverter) {repository.deleteBudgetTrackerConverter(budgetTrackerConverter);}

}
