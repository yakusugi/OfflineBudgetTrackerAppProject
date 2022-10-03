package com.myproject.offlinebudgettrackerappproject.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.myproject.offlinebudgettrackerappproject.data.BudgetTrackerIncomesRepository;

import java.util.List;

public class BudgetTrackerIncomesViewModel extends AndroidViewModel {

    public List<BudgetTrackerIncomes> incomesCategoryLists;
    public static BudgetTrackerIncomesRepository incomesRepository;
    public LiveData<List<BudgetTrackerIncomes>> allBudgetTrackerIncomesList;
    public List<BudgetTrackerIncomes> categoryNameList;

    public BudgetTrackerIncomesViewModel(@NonNull Application application) {
        super(application);
        incomesRepository = new BudgetTrackerIncomesRepository(application);
        allBudgetTrackerIncomesList = incomesRepository.getAllBudgetTrackerIncomesData();
    }

    public LiveData<List<BudgetTrackerIncomes>> getAllIncomesData() { return allBudgetTrackerIncomesList; }

    public static void insert(BudgetTrackerIncomes budgetTrackerIncomes) {
        incomesRepository.insert(budgetTrackerIncomes);
    }

    //  ReplaceActivity
    public static void replaceCategoryName(String categoryNameFrom, String categoryNameToe) {
        incomesRepository.replaceCategoryName(categoryNameFrom, categoryNameToe);
    }

    public List<BudgetTrackerIncomes> getCategoryNameList(String categoryName) {
        categoryNameList = incomesRepository.getCategoryName(categoryName);
        return categoryNameList;
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
