package com.myproject.offlinebudgettrackerappproject.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerIncome;
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

    @Query("SELECT * FROM budget_tracker_incomes_table WHERE category LIKE '%' || :category|| '%'")
    List<BudgetTrackerIncome> getIncomesCategoryLists(String category);

    @Query("SELECT * FROM budget_tracker_incomes_table WHERE budget_tracker_incomes_table.id == :id")
    LiveData<BudgetTrackerIncome> getBudgetTrackerIncomesId(int id);

    @Update
    void updateBudgetTrackerIncomes(BudgetTrackerIncomes budgetTrackerIncomes);

    @Delete
    void deleteBudgetTrackerIncomes(BudgetTrackerIncomes budgetTrackerIncomes);
}
