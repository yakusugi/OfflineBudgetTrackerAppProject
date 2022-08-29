package com.myproject.offlinebudgettrackerappproject.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.myproject.offlinebudgettrackerappproject.data.BudgetTrackerIncomesRepository;

import java.util.List;

public class BudgetTrackerIncomesViewModel extends AndroidViewModel {

    public List<BudgetTrackerIncomes> incomesCategoryLists;

    public static BudgetTrackerIncomesRepository incomesRepository;

    public BudgetTrackerIncomesViewModel(@NonNull Application application) {
        super(application);
        incomesRepository = new BudgetTrackerIncomesRepository(application);

    }


    public static void insert(BudgetTrackerIncomes budgetTrackerIncomes) {
        incomesRepository.insert(budgetTrackerIncomes);
    }

//    public List<BudgetTrackerIncome> getIncomeCategoryLists(String incomeCategory) {
//        incomeCategoryLists = incomeRepository.queryIncomeCategory(incomeCategory);
//        return incomeCategoryLists;
//    }
//
//    public LiveData<BudgetTrackerIncome> getBudgetTrackerIncomeId(int id) {return incomeRepository.getBudgetTrackerIncomeId(id);}
//    public static void updateBudgetTrackerIncome(BudgetTrackerIncome budgetTrackerIncome) {incomeRepository.updateBudgetTrackerIncome(budgetTrackerIncome);}
//    public static void deleteBudgetTrackerIncome(BudgetTrackerIncome budgetTrackerIncome) {incomeRepository.deleteBudgetTrackerIncome(budgetTrackerIncome);}


}
