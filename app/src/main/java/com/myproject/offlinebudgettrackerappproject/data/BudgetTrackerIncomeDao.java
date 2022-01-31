package com.myproject.offlinebudgettrackerappproject.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerIncome;

@Dao
public interface BudgetTrackerIncomeDao {
    //CRUD
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(BudgetTrackerIncome budgetTrackerIncome);

    @Query("DELETE FROM budget_tracker_income_table")
    void deleteAll();

    @Query("SELECT amount FROM budget_tracker_income_table")
    int getAmountLists();
}
