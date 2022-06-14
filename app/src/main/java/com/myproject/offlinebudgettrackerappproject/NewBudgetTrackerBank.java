package com.myproject.offlinebudgettrackerappproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerBank;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerBankViewModel;

public class NewBudgetTrackerBank extends AppCompatActivity {

    private EditText enterBankName;
    private EditText enterBankBalance;
    private Button saveButtonBudgetTrackerBank;
    private BudgetTrackerBankViewModel budgetTrackerBankViewModel;
    private int budgetTrackerBankIntentId = 0;
    boolean isEdit = false;
    private Button updateBankButton;
    private Button deleteBankButton;

    SharedPreferences sharedPreferences;
    private static final String PREF_CURRENCY_FILENAME = "CURRENCY_SHARED";
    private static final String PREF_CURRENCY_VALUE = "currencyValue";


    public NewBudgetTrackerBank() {

        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_budget_tracker_bank);

        enterBankName = findViewById(R.id.enter_bank_name);
        enterBankBalance = findViewById(R.id.enter_bank_balance);
        saveButtonBudgetTrackerBank = findViewById(R.id.save_button_budget_tracker_bank);
        updateBankButton = findViewById(R.id.bank_update_btn);
        deleteBankButton = findViewById(R.id.bank_delete_btn);

        sharedPreferences = getSharedPreferences(PREF_CURRENCY_FILENAME, 0);

        //選択された通貨の設定
        int currentCurrencyNum = sharedPreferences.getInt(PREF_CURRENCY_VALUE, 0);
        Currency currency = Currency.getCurrencyArrayList().get(currentCurrencyNum);

        enterBankBalance.setCompoundDrawablesWithIntrinsicBounds(currency.getCurrencyImage(), 0, 0, 0);

        budgetTrackerBankViewModel = new ViewModelProvider.AndroidViewModelFactory(NewBudgetTrackerBank.this
                .getApplication())
                .create(BudgetTrackerBankViewModel.class);

        saveButtonBudgetTrackerBank.setOnClickListener(view -> {
            Intent replyIntent = new Intent();

            if (!TextUtils.isEmpty(enterBankName.getText())
                    && !TextUtils.isEmpty(enterBankBalance.getText())) {
                String bankName = enterBankName.getText().toString();
                int bankBalance = Integer.parseInt(enterBankBalance.getText().toString());

                BudgetTrackerBank budgetTrackerBank = new BudgetTrackerBank(bankName, bankBalance);
                budgetTrackerBankViewModel.insert(budgetTrackerBank);

//                replyIntent.putExtra(REPLY_BANK_NAME, bankName);
//                replyIntent.putExtra(REPLY_BANK_BALANCE, String.valueOf(bankBalance));
//                setResult(RESULT_OK, replyIntent);

            }
//            else {
//                setResult(RESULT_CANCELED, replyIntent);
//            }
            finish();

        });

        //get intent from BudgetTrackerBankActivity
        Intent budgetTrackerBankGetIntent = getIntent();
        Bundle budgetTrackerBankGetIntentBundle = budgetTrackerBankGetIntent.getExtras();
        if (budgetTrackerBankGetIntentBundle != null) {
            budgetTrackerBankIntentId = getIntent().getIntExtra(BudgetTrackerBankActivity.BUDGET_TRACKER_BANK_ID, 0);
            Log.d("TAG", "shopFragment: " + budgetTrackerBankIntentId);
            //observe only works when using LiveData
            budgetTrackerBankViewModel.getBudgetTrackerBankId(budgetTrackerBankIntentId).observe(this, new Observer<BudgetTrackerBank>() {
                @Override
                public void onChanged(BudgetTrackerBank budgetTrackerBank) {
                    if (budgetTrackerBank != null) {
                        enterBankName.setText(budgetTrackerBank.getBankName());
                        enterBankBalance.setText(String.valueOf(budgetTrackerBank.getBankBalance()));
                    }
                }
            });
            isEdit = true;
        }

        //Delete button
        deleteBankButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (budgetTrackerBankIntentId != 0) {
                    int idBank = budgetTrackerBankIntentId;
                    String bankName = enterBankName.getText().toString();
                    int bankBalance = Integer.parseInt(enterBankBalance.getText().toString());

                    if (TextUtils.isEmpty(bankName) || TextUtils.isEmpty(String.valueOf(bankBalance))) {
                        Toast toast=Toast.makeText(getApplicationContext(),bankName,Toast.LENGTH_SHORT);
                        toast.show();
                    } else {
                        BudgetTrackerBank budgetTrackerbank = new BudgetTrackerBank();
                        budgetTrackerbank.setId(idBank);
                        budgetTrackerbank.setBankName(bankName);
                        budgetTrackerbank.setBankBalance(bankBalance);
                        BudgetTrackerBankViewModel.deleteBudgetTrackerBank(budgetTrackerbank);
                        finish();
                    }
                }
            }
        });

        //Update button
        updateBankButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (budgetTrackerBankIntentId != 0) {
                    int idBank = budgetTrackerBankIntentId;
                    String bankName = enterBankName.getText().toString();
                    int bankBalance = Integer.parseInt(enterBankBalance.getText().toString());
                    if (TextUtils.isEmpty(bankName) || TextUtils.isEmpty(String.valueOf(bankBalance))) {
                        Toast toast=Toast.makeText(getApplicationContext(),bankName,Toast.LENGTH_SHORT);
                        toast.show();
                    } else {
                        BudgetTrackerBank budgetTrackerbank = new BudgetTrackerBank();
                        budgetTrackerbank.setId(idBank);
                        budgetTrackerbank.setBankName(bankName);
                        budgetTrackerbank.setBankBalance(bankBalance);
                        BudgetTrackerBankViewModel.updateBudgetTrackerBank(budgetTrackerbank);
                        finish();
                    }
                }

            }
        });

        if (isEdit) {
            saveButtonBudgetTrackerBank.setVisibility(View.GONE);
        } else {
            updateBankButton.setVisibility(View.GONE);
            deleteBankButton.setVisibility(View.GONE);
        }


    }
}