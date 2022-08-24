package com.myproject.offlinebudgettrackerappproject.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerBanking;

import java.util.List;

@Dao
public interface BudgetTrackerBankingDao {
    //CRUD
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(BudgetTrackerBanking budgetTrackerBanking);

    @Query("DELETE FROM budget_tracker_banking_table")
    void deleteAll();

    @Query("SELECT * FROM budget_tracker_banking_table")
    List<BudgetTrackerBanking> getBankBalanceList();

    @Query("SELECT bank_name FROM budget_tracker_banking_table")
    List<String> getBankNames();

    @Query("SELECT * FROM budget_tracker_banking_table")
    List<BudgetTrackerBanking> getBankList();


}
