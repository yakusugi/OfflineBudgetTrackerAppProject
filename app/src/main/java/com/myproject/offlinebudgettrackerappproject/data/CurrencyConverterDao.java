package com.myproject.offlinebudgettrackerappproject.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.myproject.offlinebudgettrackerappproject.model.CurrencyConverter;

import java.util.List;

@Dao
public interface CurrencyConverterDao {

    //CRUD
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(CurrencyConverter currencyConverter);

    @Query("DELETE FROM bank_currency_converter_table")
    void deleteAll();

    @Delete
    void deleteBankConverter(CurrencyConverter currencyConverter);

    @Query("SELECT * FROM bank_currency_converter_table ORDER BY bank_name ASC")
    LiveData<List<CurrencyConverter>> getAllCurrencyConverterList();

}
