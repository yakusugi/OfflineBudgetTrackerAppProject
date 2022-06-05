package com.myproject.offlinebudgettrackerappproject;

import android.os.Bundle;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.myproject.offlinebudgettrackerappproject.adapter.CurrencySpinnerAdapter;

public class BudgetTrackerSettingsActivity extends AppCompatActivity {

    Spinner spinner;
    private String spinnerText;
    Currency currency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_tracker_settings);
        Currency.initCurrencies();
        spinner = (Spinner) findViewById(R.id.currency_spinner);

        CurrencySpinnerAdapter currencySpinnerAdapter = new CurrencySpinnerAdapter(this, R.layout.currency_spinner_adopter,
                Currency.getCurrencyArrayList());
        spinner.setAdapter(currencySpinnerAdapter);
//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
//
//                spinnerText = currency.getCurrency();
//                Log.d("TAG_Spinner", "onItemSelected: " + spinnerText);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//                spinnerText = null;
//            }
//        });
    }
}