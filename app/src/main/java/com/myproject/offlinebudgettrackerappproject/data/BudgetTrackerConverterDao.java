package com.myproject.offlinebudgettrackerappproject.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerConverter;

import java.util.List;

@Dao
public interface BudgetTrackerConverterDao {

    //CRUD
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(BudgetTrackerConverter budgetTrackerConverter);

    @Query("DELETE FROM budget_tracker_converter_table")
    void deleteAll();

    @Update
    void updateBudgetTrackerConverter(BudgetTrackerConverter budgetTrackerConverter);

    @Delete
    void deleteBudgetTrackerConverter(BudgetTrackerConverter budgetTrackerConverter);

    @Query("SELECT * FROM budget_tracker_converter_table ORDER BY store_name ASC")
    LiveData<List<BudgetTrackerConverter>> getAllBudgetTrackerConverterList();

}
