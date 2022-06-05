package com.myproject.offlinebudgettrackerappproject;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

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


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                Currency currency = (Currency) spinner.getSelectedItem();
                spinnerText = currency.getCurrency();
                if (spinnerText == "US Dollars") {
                    Toast.makeText(BudgetTrackerSettingsActivity.this, "US Dollars", Toast.LENGTH_SHORT).show();
                } else if (spinnerText == "Japanese Yen") {
                    Toast.makeText(BudgetTrackerSettingsActivity.this, "Japanese Yen", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(BudgetTrackerSettingsActivity.this, "Euro", Toast.LENGTH_SHORT).show();
                }
                Log.d("Currency", "onCreate: " + spinnerText);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                spinnerText = null;
            }
        });
    }
}