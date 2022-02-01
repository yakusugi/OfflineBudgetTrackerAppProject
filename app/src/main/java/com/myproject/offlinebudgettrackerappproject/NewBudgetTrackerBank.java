package com.myproject.offlinebudgettrackerappproject;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerBank;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerBankViewModel;

public class NewBudgetTrackerBank extends AppCompatActivity {

//    public static final String REPLY_BANK_NAME = "reply_bank_name";
//    public static final String REPLY_BANK_BALANCE = "reply_bank_balance";

    private EditText enterBankName;
    private EditText enterBankBalance;
    private Button saveButtonBudgetTrackerBank;

    private BudgetTrackerBankViewModel budgetTrackerBankViewModel;


    public NewBudgetTrackerBank() {

        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_budget_tracker_bank);

        enterBankName = findViewById(R.id.enter_bank_name);
        enterBankBalance = findViewById(R.id.enter_bank_balance);
        saveButtonBudgetTrackerBank = findViewById(R.id.save_button_budget_tracker_bank);

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


    }
}