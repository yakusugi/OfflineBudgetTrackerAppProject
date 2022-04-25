package com.myproject.offlinebudgettrackerappproject.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerBank;

import java.util.List;

@Dao
public interface BudgetTrackerBankDao {
    //CRUD
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(BudgetTrackerBank budgetTrackerBank);

    @Query("DELETE FROM budget_tracker_bank_table")
    void deleteAll();

    @Query("SELECT bank_balance FROM budget_tracker_bank_table")
    int getBankBalanceLists();

    @Query("SELECT bank_name FROM budget_tracker_bank_table")
    List<String> getBankNames();

    @Query("SELECT * FROM budget_tracker_bank_table")
    List<BudgetTrackerBank> getBankList();

    @Query("SELECT * FROM budget_tracker_bank_table WHERE bank_name LIKE '%' || :bankName|| '%'")
    List<BudgetTrackerBank> getBankNameLists(String bankName);

    @Query("SELECT * FROM budget_tracker_bank_table WHERE budget_tracker_bank_table.id == :id")
    LiveData<BudgetTrackerBank> getBudgetTrackerBankId(int id);

    @Update
    void updateBudgetTrackerBank(BudgetTrackerBank budgetTrackerBank);

    @Delete
    void deleteBudgetTrackerBank(BudgetTrackerBank budgetTrackerBank);

}
