package com.myproject.offlinebudgettrackerappproject.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerIncomes;

import java.util.List;

@Dao
public interface BudgetTrackerIncomesDao {
    //CRUD
    @Query("SELECT * FROM budget_tracker_incomes_table ORDER BY category ASC")
    LiveData<List<BudgetTrackerIncomes>> getAllBudgetTrackerIncomesList();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(BudgetTrackerIncomes budgetTrackerIncomes);

    @Query("DELETE FROM budget_tracker_incomes_table")
    void deleteAll();

    @Query("SELECT amount FROM budget_tracker_incomes_table")
    int getAmountLists();

//    @Query("SELECT * FROM budget_tracker_incomes_table WHERE category LIKE '%' || :category|| '%'")
//    List<BudgetTrackerIncome> getIncomesCategoryLists(String category);

//    @Query("SELECT * FROM budget_tracker_incomes_table WHERE budget_tracker_incomes_table.id == :id")
//    LiveData<BudgetTrackerIncome> getBudgetTrackerIncomesId(int id);

    //ReplaceIncomeActivity
    @Query("update budget_tracker_incomes_table set category = `replace`(category,:categoryNameFrom,:categoryNameTo) where category like :categoryNameFrom || '%'")
    void replaceCategoryName(String categoryNameFrom, String categoryNameTo);

    @Query("SELECT * FROM budget_tracker_incomes_table WHERE category LIKE '%' || :categoryName|| '%'")
    List<BudgetTrackerIncomes> getCategoryList(String categoryName);

    //update
    @Update
    void updateBudgetTrackerIncomes(BudgetTrackerIncomes budgetTrackerIncomes);

    //delete
    @Delete
    void deleteBudgetTrackerIncomes(BudgetTrackerIncomes budgetTrackerIncomes);

    //For Quick search (Category)

    @Query("SELECT SUM(amount) FROM budget_tracker_incomes_table WHERE category LIKE '%' || :category|| '%'")
    double getQuickCategorySum(String category);
}
