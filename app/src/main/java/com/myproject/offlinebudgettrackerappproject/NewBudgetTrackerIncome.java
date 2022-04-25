package com.myproject.offlinebudgettrackerappproject;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerBank;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerBankViewModel;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerIncome;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerIncomeViewModel;

import java.util.Calendar;
import java.util.List;

public class NewBudgetTrackerIncome extends AppCompatActivity {

    private EditText enterIncomeDate;
    private EditText enterIncomeCategory;
    private EditText enterIncomeAmount;
    private Button saveButtonBudgetTrackerIncome;

    private BudgetTrackerIncomeViewModel budgetTrackerIncomeViewModel;
    private BudgetTrackerBankViewModel budgetTrackerBankViewModel;

    private List<BudgetTrackerBank> bankList;
    private int budgetTrackerIncomeIntentId = 0;
    boolean isEdit = false;
    private Button updateIncomeButton;
    private Button deleteIncomeButton;

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
        updateIncomeButton = findViewById(R.id.income_update_btn);
        deleteIncomeButton = findViewById(R.id.income_delete_btn);

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
//        bankList = new List<>();
//        bankList = budgetTrackerBankViewModel.getBankViewModelBankList();

//        for(BudgetTrackerBank budgetTrackerBankList : bankList) {
//            Log.d("TAG", "onClick: " + budgetTrackerBankList.getId());
//            Log.d("TAG", "onClick: " + budgetTrackerBankList.getBankName().toString());
//            Log.d("TAG", "onClick: " + budgetTrackerBankList.getBankBalance());
//        }

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

//                replyIntent.putExtra(REPLY_INCOME_DATE, incomeDate);
//                replyIntent.putExtra(REPLY_INCOME_CATEGORY, incomeCategory);
//                replyIntent.putExtra(REPLY_INCOME_AMOUNT, String.valueOf(incomeAmount));
//                setResult(RESULT_OK, replyIntent);


            }
//            else {
//                setResult(RESULT_CANCELED, replyIntent);
//            }
            finish();

        });

        //get intent from BudgetTrackerBankActivity
        Intent budgetTrackerIncomeGetIntent = getIntent();
        Bundle budgetTrackerIncomeGetIntentBundle = budgetTrackerIncomeGetIntent.getExtras();
        if (budgetTrackerIncomeGetIntentBundle != null) {
            budgetTrackerIncomeIntentId = getIntent().getIntExtra(BudgetTrackerIncomeActivity.BUDGET_TRACKER_INCOME_ID, 0);
            Log.d("TAG", "incomeActivity: " + budgetTrackerIncomeIntentId);
            //observe only works when using LiveData
            budgetTrackerIncomeViewModel.getBudgetTrackerIncomeId(budgetTrackerIncomeIntentId).observe(this, new Observer<BudgetTrackerIncome>() {
                @Override
                public void onChanged(BudgetTrackerIncome budgetTrackerIncome) {
                    if (budgetTrackerIncome != null) {
                        enterIncomeDate.setText(budgetTrackerIncome.getDate());
                        enterIncomeCategory.setText(String.valueOf(budgetTrackerIncome.getCategory()));
                        enterIncomeAmount.setText(String.valueOf(budgetTrackerIncome.getAmount()));
                    }
                }
            });
            isEdit = true;
        }

        //Delete button
        deleteIncomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (budgetTrackerIncomeIntentId != 0) {
                    int idIncome = budgetTrackerIncomeIntentId;
                    String incomeDate = enterIncomeDate.getText().toString();
                    String incomeCategory = enterIncomeCategory.getText().toString();
                    int incomeAmount = Integer.parseInt(enterIncomeAmount.getText().toString());

                    if (TextUtils.isEmpty(incomeDate) ||TextUtils.isEmpty(incomeCategory) || TextUtils.isEmpty(String.valueOf(incomeAmount))) {
                        Toast toast=Toast.makeText(getApplicationContext(),incomeCategory,Toast.LENGTH_SHORT);
                        toast.show();
                    } else {
                        BudgetTrackerIncome budgetTrackerIncome = new BudgetTrackerIncome();
                        budgetTrackerIncome.setId(idIncome);
                        budgetTrackerIncome.setDate(incomeDate);
                        budgetTrackerIncome.setCategory(incomeCategory);
                        budgetTrackerIncome.setAmount(incomeAmount);
                        budgetTrackerIncomeViewModel.deleteBudgetTrackerIncome(budgetTrackerIncome);
                        finish();
                    }
                }
            }
        });

        //Update button
        updateIncomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (budgetTrackerIncomeIntentId != 0) {
                    int idIncome = budgetTrackerIncomeIntentId;
                    String incomeDate = enterIncomeDate.getText().toString();
                    String incomeCategory = enterIncomeCategory.getText().toString();
                    int incomeAmount = Integer.parseInt(enterIncomeAmount.getText().toString());
                    if (TextUtils.isEmpty(incomeDate) ||TextUtils.isEmpty(incomeCategory) || TextUtils.isEmpty(String.valueOf(incomeAmount))) {
                        Toast toast=Toast.makeText(getApplicationContext(),incomeCategory,Toast.LENGTH_SHORT);
                        toast.show();
                    } else {
                        BudgetTrackerIncome budgetTrackerIncome = new BudgetTrackerIncome();
                        budgetTrackerIncome.setId(idIncome);
                        budgetTrackerIncome.setDate(incomeDate);
                        budgetTrackerIncome.setCategory(incomeCategory);
                        budgetTrackerIncome.setAmount(incomeAmount);
                        budgetTrackerIncomeViewModel.updateBudgetTrackerIncome(budgetTrackerIncome);
                        finish();
                    }
                }

            }
        });

        if (isEdit) {
            saveButtonBudgetTrackerIncome.setVisibility(View.GONE);
        } else {
            updateIncomeButton.setVisibility(View.GONE);
            deleteIncomeButton.setVisibility(View.GONE);
        }

    }
}