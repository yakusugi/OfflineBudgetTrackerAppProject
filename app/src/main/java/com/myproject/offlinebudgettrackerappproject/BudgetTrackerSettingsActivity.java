package com.myproject.offlinebudgettrackerappproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.myproject.offlinebudgettrackerappproject.adapter.CurrencySpinnerAdapter;

import java.util.Vector;

public class BudgetTrackerSettingsActivity extends AppCompatActivity {

    Spinner spinner;
    public static String spinnerText;
    String currentCurrency;
    CheckBox currencyRemember;
    Button currencyBtn;
    Currency currency;
    SharedPreferences sharedPreferences;
    String selectedCurrency;
    boolean currencyChecked;
    String currencySymbolPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_tracker_settings);
        Currency.initCurrencies();
        spinner = (Spinner) findViewById(R.id.currency_spinner);
        currencyRemember = (CheckBox) findViewById(R.id.currency_check_box);
        currencyBtn = (Button) findViewById(R.id.currency_btn);

        CurrencySpinnerAdapter currencySpinnerAdapter = new CurrencySpinnerAdapter(this, R.layout.currency_spinner_adopter,
                Currency.getCurrencyArrayList());
        spinner.setAdapter(currencySpinnerAdapter);

        sharedPreferences = getSharedPreferences("CURRENCY_SHARED", 0);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                Currency currency = (Currency) spinner.getSelectedItem();
                spinnerText = currency.getCurrency();
                if (spinnerText == "US Dollars") {
                    Toast.makeText(BudgetTrackerSettingsActivity.this, "US Dollars", Toast.LENGTH_SHORT).show();
                    currencyBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            selectedCurrency = spinnerText;
                            currencyChecked = currencyRemember.isChecked();
                            currentCurrency = "US_Dollars";
                            currencySymbolPath = "res/drawable/ic_money.xml";
                            Vector<Vector<Float>> data = new Vector<Vector<Float>>();

                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("CURRENCY", selectedCurrency);
                            editor.putString("SELECTED", currentCurrency);
                            editor.putString("SELECTED_CURRENCY_SYMBOL", currencySymbolPath);
                            editor.putBoolean("CHECKBOX", currencyChecked);
                            editor.apply();
                            Toast.makeText(BudgetTrackerSettingsActivity.this, "Currency Saved", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(BudgetTrackerSettingsActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    });
                } else if (spinnerText == "Japanese Yen") {
                    currencyBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            selectedCurrency = spinnerText;
                            currencyChecked = currencyRemember.isChecked();
                            currentCurrency = "JAPANESE_YEN";
                            currencySymbolPath = "res/drawable/ic_yen.xml";

                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("CURRENCY", selectedCurrency);
                            editor.putString("SELECTED", currentCurrency);
                            editor.putString("SELECTED_CURRENCY_SYMBOL", currencySymbolPath);
                            editor.putBoolean("CHECKBOX", currencyChecked);
                            editor.apply();
                            Toast.makeText(BudgetTrackerSettingsActivity.this, "Currency Saved", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(BudgetTrackerSettingsActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    });
                } else {
                    currencyBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            selectedCurrency = spinnerText;
                            currencyChecked = currencyRemember.isChecked();

                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("CURRENCY", selectedCurrency);
                            editor.putBoolean("CHECKBOX", currencyChecked);
                            editor.apply();
                            Toast.makeText(BudgetTrackerSettingsActivity.this, "Currency Saved", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(BudgetTrackerSettingsActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    });
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