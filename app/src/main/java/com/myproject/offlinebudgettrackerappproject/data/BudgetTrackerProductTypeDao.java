package com.myproject.offlinebudgettrackerappproject.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerProductType;

import java.util.List;

@Dao
public interface BudgetTrackerProductTypeDao {
    //CRUD
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(BudgetTrackerProductType budgetTrackerProductType);

    @Query("SELECT * FROM budget_tracker_product_type_table ORDER BY product_type ASC")
    LiveData<List<BudgetTrackerProductType>> getProductTypeList();

    @Query("DELETE FROM budget_tracker_product_type_table")
    void deleteAll();
}
