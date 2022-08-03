package com.myproject.offlinebudgettrackerappproject.data;

import androidx.room.Dao;
import androidx.room.Query;

import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerSpendingAlias;

import java.util.List;

@Dao
public interface BudgetTrackerSpendingAliasDao {
//    @Query("insert into budget_tracker_spending_alias_table (date_alias, store_name_alias, product_name_alias, product_type_alias, price_alias, is_tax_alias, tax_rate_alias, alias_percentage) select date, store_name, product_name, product_type, price, is_tax, tax_rate, count(product_type) * 100.0 / (select count(*) from budget_tracker_spending_table where date >= :date1 and date <= :date2 and store_name LIKE '%' || :storeName || '%') as 'Percentage' from budget_tracker_spending_table where date >= :date1 and date <= :date2 and store_name LIKE '%' || :storeName || '%'  group by product_type;")
//    void insertStoreName(String date1, String date2, String storeName);

    @Query("SELECT * FROM budget_tracker_spending_alias_table")
    List<BudgetTrackerSpendingAlias> getAllBudgetTrackerSpendingAliasList();

    @Query("insert into budget_tracker_spending_alias_table (date_alias, store_name_alias, product_name_alias, product_type_alias, price_alias, is_tax_alias, tax_rate_alias, alias_percentage) select date, store_name, product_name, product_type, price, is_tax, tax_rate, count(product_type) * 100.0 / (select count(*) from budget_tracker_spending_table where date >= :dateTo and date <= :dateFrom and store_name LIKE '%' || :storeName || '%') as 'alias_percentage' from budget_tracker_spending_table where date >= :dateTo and date <= :dateFrom and store_name LIKE '%' || :storeName || '%'  group by product_type;")
    void insertStoreName(String dateTo, String dateFrom, String storeName);

    @Query("DELETE FROM budget_tracker_spending_alias_table")
    void deleteAllSpendingAlias();

    @Query("DELETE FROM sqlite_sequence where name = 'budget_tracker_spending_alias_table'")
    void deleteSequence();

}
