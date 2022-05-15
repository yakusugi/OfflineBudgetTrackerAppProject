package com.myproject.offlinebudgettrackerappproject.data;

import androidx.room.Dao;
import androidx.room.Query;

import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerAlias;

import java.util.List;

@Dao
public interface BudgetTrackerAliasDao {

    @Query("insert into budget_tracker_table_alias (date_alias, store_name_alias, product_name_alias, product_type_alias, price_alias, product_type_percentage) select date, store_name, product_name, product_type, price, count(product_type) * 100.0 / (select count(*) from budget_tracker_table where date >= :date1 and date <= :date2 and store_name LIKE '%' || :storeName || '%') as 'Percentage' from budget_tracker_table where date >= :date1 and date <= :date2 and store_name LIKE '%' || :storeName || '%'  group by product_type;")
    void insert(String date1, String date2, String storeName);

    @Query("insert into budget_tracker_table_alias (date_alias, store_name_alias, product_name_alias, product_type_alias, price_alias, product_type_percentage) select date, store_name, product_name, product_type, price, count(store_name) * 100.0 / (select count(*) from budget_tracker_table where date >= :date1 and date <= :date2 and product_name LIKE '%' || :productName || '%') as 'Percentage' from budget_tracker_table where date >= :date1 and date <= :date2 and product_name LIKE '%' || :productName || '%'  group by store_name;")
    void insertProductName(String date1, String date2, String productName);

    @Query("insert into budget_tracker_table_alias (date_alias, store_name_alias, product_name_alias, product_type_alias, price_alias, product_type_percentage) select date, store_name, product_name, product_type, price, count(store_name) * 100.0 / (select count(*) from budget_tracker_table where date >= :date1 and date <= :date2 and product_type LIKE '%' || :productType || '%') as 'Percentage' from budget_tracker_table where date >= :date1 and date <= :date2 and product_type LIKE '%' || :productType || '%'  group by store_name;")
    void insertProductType(String date1, String date2, String productType);

    @Query("SELECT * FROM budget_tracker_table_alias")
    List<BudgetTrackerAlias> getAllBudgetTrackerAliasList();

    @Query("DELETE FROM budget_tracker_table_alias")
    void deleteAllAlias();

}
