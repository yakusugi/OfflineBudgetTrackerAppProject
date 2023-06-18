package com.myproject.offlinebudgettrackerappproject.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerSpending;

import java.util.List;

@Dao
public interface BudgetTrackerSpendingDao {

    //CRUD
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(BudgetTrackerSpending budgetTrackerSpending);

    @Query("DELETE FROM budget_tracker_spending_table")
    void deleteAll();

    @Update
    void updateBudgetTrackerSpending(BudgetTrackerSpending budgetTrackerSpending);

    @Delete
    void deleteBudgetTrackerSpending(BudgetTrackerSpending budgetTrackerSpending);

    @Query("SELECT * FROM budget_tracker_spending_table ORDER BY store_name ASC")
    LiveData<List<BudgetTrackerSpending>> getAllBudgetTrackerSpendingList();

    //for mysql sync
    @Query("SELECT * FROM budget_tracker_spending_table")
    List<BudgetTrackerSpending> getBudgetTrackerSpendingListForMySQL();

    //SearchFragment
    @Query("SELECT * FROM budget_tracker_spending_table WHERE store_name LIKE '%' || :storeName|| '%' and date >= :dateFrom and date <= :dateTo")
    List<BudgetTrackerSpending> getSearchStoreNameLists(String storeName, String dateFrom, String dateTo);

    @Query("SELECT SUM(price) FROM budget_tracker_spending_table WHERE store_name LIKE '%' || :storeName|| '%' and date >= :dateFrom and date <= :dateTo")
    double getSearchStoreSum(String storeName, String dateFrom, String dateTo);

    @Query("SELECT * FROM budget_tracker_spending_table WHERE product_name LIKE '%' || :productName|| '%' and date >= :dateFrom and date <= :dateTo")
    List<BudgetTrackerSpending> getSearchProductNameLists(String productName, String dateFrom, String dateTo);

    @Query("SELECT SUM(price) FROM budget_tracker_spending_table WHERE product_name LIKE '%' || :productName|| '%' and date >= :dateFrom and date <= :dateTo")
    double getSearchProductSum(String productName, String dateFrom, String dateTo);

    @Query("SELECT * FROM budget_tracker_spending_table WHERE product_type LIKE '%' || :productType|| '%' and date >= :dateFrom and date <= :dateTo")
    List<BudgetTrackerSpending> getSearchProductTypeLists(String productType, String dateFrom, String dateTo);

    @Query("SELECT SUM(price) FROM budget_tracker_spending_table WHERE product_type LIKE '%' || :productType|| '%' and date >= :dateFrom and date <= :dateTo")
    double getSearchProductTypeSum(String productType, String dateFrom, String dateTo);

    //ReplaceSpendingActivity
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

    //For getting ID for tapped item in a listview
    @Query("SELECT * FROM budget_tracker_spending_table WHERE budget_tracker_spending_table.id == :id")
    LiveData<BudgetTrackerSpending> getBudgetTrackerSpendingId(int id);

    //For Quick search (Store)
    @Query("SELECT * FROM budget_tracker_spending_table WHERE store_name LIKE '%' || :storeName|| '%'")
    List<BudgetTrackerSpending> getQuickStoreNameList(String storeName);

    @Query("SELECT SUM(price) FROM budget_tracker_spending_table WHERE store_name LIKE '%' || :storeName|| '%'")
    double getQuickStoreSum(String storeName);

    //For Quick search (Product type)
    @Query("SELECT * FROM budget_tracker_spending_table WHERE product_type LIKE '%' || :productType|| '%'")
    List<BudgetTrackerSpending> getQuickProductTypeList(String productType);

    @Query("SELECT SUM(price) FROM budget_tracker_spending_table WHERE product_type LIKE '%' || :productType|| '%'")
    double getQuickProductTypeSum(String productType);

    //For Quick search (Product name)
    @Query("SELECT * FROM budget_tracker_spending_table WHERE product_name LIKE '%' || :productName|| '%'")
    List<BudgetTrackerSpending> getQuickProductNameList(String productName);

    @Query("SELECT SUM(price) FROM budget_tracker_spending_table WHERE product_name LIKE '%' || :productName|| '%'")
    double getQuickProductNameSum(String productName);

    //For Quick date search
    @Query("SELECT * FROM budget_tracker_spending_table WHERE date >= :dateFrom and date <= :dateTo")
    List<BudgetTrackerSpending> getQuickDateList(String dateFrom, String dateTo);

}
