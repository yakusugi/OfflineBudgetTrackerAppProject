package com.myproject.offlinebudgettrackerappproject.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerIncomeType;

import java.util.List;

@Dao
public interface BudgetTrackerIncomeTypeDao {
    //CRUD
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(BudgetTrackerIncomeType budgetTrackerIncomeType);

    @Query("SELECT * FROM budget_tracker_income_type_table ORDER BY income_type ASC")
    LiveData<List<BudgetTrackerIncomeType>> getIncomeTypeList();

    @Query("DELETE FROM budget_tracker_income_type_table")
    void deleteAll();
}
