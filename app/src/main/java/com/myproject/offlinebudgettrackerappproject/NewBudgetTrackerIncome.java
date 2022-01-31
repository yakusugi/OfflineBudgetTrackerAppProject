package com.myproject.offlinebudgettrackerappproject;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerBankViewModel;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerIncome;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerIncomeViewModel;

import java.util.Calendar;

public class NewBudgetTrackerIncome extends AppCompatActivity {

    private EditText enterIncomeDate;
    private EditText enterIncomeCategory;
    private EditText enterIncomeAmount;
    private Button saveButtonBudgetTrackerIncome;

    private BudgetTrackerIncomeViewModel budgetTrackerIncomeViewModel;
    private BudgetTrackerBankViewModel budgetTrackerBankViewModel;

    public NewBudgetTrackerIncome() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_budget_tracker_income);

        enterIncomeDate = findViewById(R.id.enter_income_date);
        enterIncomeCategory = findViewById(R.id.enter_income_category);
        enterIncomeAmount = findViewById(R.id.enter_income_amount);
        saveButtonBudgetTrackerIncome = findViewById(R.id.save_button_budget_tracker_income);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        enterIncomeDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        NewBudgetTrackerIncome.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        String date = year + "-" + month + "-" + dayOfMonth;
                        enterIncomeDate.setText(date);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        budgetTrackerIncomeViewModel = new ViewModelProvider.AndroidViewModelFactory(NewBudgetTrackerIncome.this
                .getApplication())
                .create(BudgetTrackerIncomeViewModel.class);

//        TODO: another button press action to confirm there is at least one data in bank table

//        List<String> bankNames = budgetTrackerBankViewModel.getBankViewModelBankNames();
//        Log.d("TAG", "onCreate: " + bankNames);

//        for (String bankName : bankNames) {
//            Log.d("TAG", "onCreate: " + bankName);
//        }

        saveButtonBudgetTrackerIncome.setOnClickListener(view -> {
            Intent replyIntent = new Intent();

            if (!TextUtils.isEmpty(enterIncomeDate.getText())
                    && !TextUtils.isEmpty(enterIncomeCategory.getText())
                    && !TextUtils.isEmpty(enterIncomeAmount.getText())) {
                String incomeDate = enterIncomeDate.getText().toString();
                String incomeCategory = enterIncomeCategory.getText().toString();
                int incomeAmount = Integer.parseInt(enterIncomeAmount.getText().toString());
                BudgetTrackerIncome budgetTrackerIncome = new BudgetTrackerIncome(incomeDate, incomeCategory, incomeAmount);

                budgetTrackerIncomeViewModel.insert(budgetTrackerIncome);

            }
            finish();

        });

    }
}