package com.myproject.offlinebudgettrackerappproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.myproject.offlinebudgettrackerappproject.adapter.CurrencySpinnerAdapter;

public class BudgetTrackerSettingsActivity extends AppCompatActivity {

    Spinner spinner;
    public static String spinnerText;

    Button currencyBtn;
    String selectedCurrency;
    SharedPreferences sharedPreferences;
    private static final String PREF_CURRENCY_FILENAME = "CURRENCY_SHARED";
    private static final String PREF_CURRENCY_VALUE = "currencyValue";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_tracker_settings);
        Currency.initCurrencies();

        spinner = (Spinner) findViewById(R.id.currency_spinner);
        currencyBtn = (Button) findViewById(R.id.currency_btn);

        CurrencySpinnerAdapter currencySpinnerAdapter = new CurrencySpinnerAdapter(this, R.layout.currency_spinner_adopter,
                Currency.getCurrencyArrayList());
        spinner.setAdapter(currencySpinnerAdapter);

        sharedPreferences = getSharedPreferences(PREF_CURRENCY_FILENAME, Context.MODE_PRIVATE);
        spinner.setSelection(sharedPreferences.getInt(PREF_CURRENCY_VALUE, 0));

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                Currency currency = (Currency) spinner.getSelectedItem();
                spinnerText = currency.getCurrency();
                currencyBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        selectedCurrency = spinnerText;

                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("CURRENCY", selectedCurrency);
                        editor.putInt(PREF_CURRENCY_VALUE, position);
                        editor.commit();
                        Toast.makeText(BudgetTrackerSettingsActivity.this, "Currency Saved", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(BudgetTrackerSettingsActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                spinnerText = null;
            }
        });
//        spinner.setSelection(currency.ordinal());
    }


}