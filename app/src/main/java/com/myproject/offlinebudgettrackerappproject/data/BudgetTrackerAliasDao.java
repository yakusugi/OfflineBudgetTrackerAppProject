package com.myproject.offlinebudgettrackerappproject.data;

import androidx.room.Dao;
import androidx.room.Query;

import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerAlias;

import java.util.List;

@Dao
public interface BudgetTrackerAliasDao {

//    @Insert("insert into budget_tracker_table_alias (date_alias, store_name_alias, product_name_alias, product_type_alias, price_alias, product_type_percentage) select date, store_name, product_name, product_type, price, count(*) * 100.0 / (select count(*) from budget_tracker_table where date >= '2022-4-22' and date <= '2022-4-23') as 'Percentage' from budget_tracker_table where date >= '2022-4-22' and date <= '2022-4-23' group by store_name;")
//    void insert(BudgetTrackerAlias budgetTrackerAlias);

    @Query("insert into budget_tracker_table_alias (date_alias, store_name_alias, product_name_alias, product_type_alias, price_alias, product_type_percentage) select date, store_name, product_name, product_type, price, count(product_type) * 100.0 / (select count(*) from budget_tracker_table where date >= :date1 and date <= :date2 and store_name LIKE '%' || :storeName || '%') as 'Percentage' from budget_tracker_table where date >= :date1 and date <= :date2 and store_name LIKE '%' || :storeName || '%'  group by product_type;")
    void insert(String date1, String date2, String storeName);

    @Query("SELECT * FROM budget_tracker_table_alias")
    List<BudgetTrackerAlias> getAllBudgetTrackerAliasList();

    @Query("DELETE FROM budget_tracker_table_alias")
    void deleteAllAlias();

}
