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

    @Query("update budget_tracker_spending_table set store_name = `replace`(store_name,:storeNameFrom,:storeNameTo) where store_name like :storeNameFrom || '%'")
    void replaceStoreName(String storeNameFrom, String storeNameTo);

    @Query("SELECT * FROM budget_tracker_spending_table WHERE store_name LIKE '%' || :storeName|| '%'")
    List<BudgetTrackerSpending> getStoreNameList(String storeName);

    @Query("update budget_tracker_spending_table set product_name = `replace`(product_name,:productNameFrom,:productNameTo) where product_name like :productNameFrom || '%'")
    void replaceProductName(String productNameFrom, String productNameTo);

    @Query("SELECT * FROM budget_tracker_spending_table WHERE product_name LIKE '%' || :productName|| '%'")
    List<BudgetTrackerSpending> getProductNameList(String productName);

    @Query("update budget_tracker_spending_table set product_type = `replace`(product_type,:productTypeFrom,:productTypeTo) where product_type like :productTypeFrom || '%'")
    void replaceProductType(String productTypeFrom, String productTypeTo);

    @Query("SELECT * FROM budget_tracker_spending_table WHERE product_type LIKE '%' || :productType|| '%'")
    List<BudgetTrackerSpending> getProductTypeList(String productType);

}
