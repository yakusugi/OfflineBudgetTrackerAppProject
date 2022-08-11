package com.myproject.offlinebudgettrackerappproject.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerSpending;

import java.util.List;

@Dao
public interface BudgetTrackerSpendingDao {

    //CRUD
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(BudgetTrackerSpending budgetTrackerSpending);

    @Query("DELETE FROM budget_tracker_spending_table")
    void deleteAll();

    @Query("SELECT * FROM budget_tracker_spending_table ORDER BY product_name ASC")
    LiveData<List<BudgetTrackerSpending>> getAllBudgetTrackerSpendingList();

    @Query("SELECT * FROM budget_tracker_spending_table WHERE store_name LIKE '%' || :storeName|| '%' and date >= :dateFrom and date <= :dateTo")
    List<BudgetTrackerSpending> getSearchStoreNameLists(String storeName, String dateFrom, String dateTo);

    @Query("SELECT SUM(price) FROM budget_tracker_spending_table WHERE store_name LIKE '%' || :storeName|| '%' and date >= :dateFrom and date <= :dateTo")
    double getSearchStoreSum(String storeName, String dateFrom, String dateTo);

}
