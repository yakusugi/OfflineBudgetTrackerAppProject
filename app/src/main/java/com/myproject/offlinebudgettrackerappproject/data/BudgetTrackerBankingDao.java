package com.myproject.offlinebudgettrackerappproject.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerBanking;

import java.util.List;

@Dao
public interface BudgetTrackerBankingDao {
    //CRUD
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(BudgetTrackerBanking budgetTrackerBanking);

    @Query("DELETE FROM budget_tracker_banking_table")
    void deleteAll();

    //update
    @Update
    void updateBudgetTrackerBanking(BudgetTrackerBanking budgetTrackerBanking);

    //delete
    @Delete
    void deleteBudgetTrackerBanking(BudgetTrackerBanking budgetTrackerBanking);

    @Query("SELECT * FROM budget_tracker_banking_table ORDER BY bank_name ASC")
    LiveData<List<BudgetTrackerBanking>> getAllBudgetTrackerBankingList();

    @Query("SELECT * FROM budget_tracker_banking_table")
    List<BudgetTrackerBanking> getBankBalanceList();

    @Query("SELECT bank_name FROM budget_tracker_banking_table")
    List<String> getBankNames();

    @Query("SELECT * FROM budget_tracker_banking_table")
    List<BudgetTrackerBanking> getBankList();

    @Query("UPDATE budget_tracker_banking_table SET bank_balance = bank_balance - :spendingNum WHERE bank_name = :bankName")
    void updateSubtraction(double spendingNum, String bankName);

    @Query("UPDATE budget_tracker_banking_table SET bank_balance = bank_balance + :incomesNum WHERE bank_name = :bankName")
    void updateAddition(double incomesNum, String bankName);


}
