package com.myproject.offlinebudgettrackerappproject.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.myproject.offlinebudgettrackerappproject.model.BudgetTracker;

import java.util.List;

@Dao
public interface BudgetTrackerDao {
    //CRUD
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(BudgetTracker budgetTracker);

    @Query("DELETE FROM budget_tracker_table")
    void deleteAll();

    @Query("SELECT * FROM budget_tracker_table ORDER BY product_name ASC")
    LiveData<List<BudgetTracker>> getAllBudgetTrackerLists();

    //    @Query("SELECT * FROM budget_tracker_table WHERE store_name = :storeName")
    //    @Query("SELECT * FROM budget_tracker_table WHERE store_name = '%:storeName%'")
    @Query("SELECT * FROM budget_tracker_table WHERE store_name LIKE '%' || :storeName|| '%'")
    List<BudgetTracker> getStoreNameLists(String storeName);

    @Query("SELECT * FROM budget_tracker_table WHERE product_name LIKE '%' || :productName|| '%'")
    List<BudgetTracker> getProductNameLists(String productName);

    @Query("SELECT * FROM budget_tracker_table WHERE date >= :date1 and date <= :date2")
    List<BudgetTracker> getDateLists(String date1, String date2);
}
