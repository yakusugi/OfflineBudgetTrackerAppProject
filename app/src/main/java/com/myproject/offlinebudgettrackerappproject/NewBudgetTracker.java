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

import com.myproject.offlinebudgettrackerappproject.model.BudgetTracker;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerViewModel;

import java.util.Calendar;

public class NewBudgetTracker extends AppCompatActivity {

//    public static final String REPLY_DATE = "reply_date";
//    public static final String REPLY_STORE_NAME = "reply_store_name";
//    public static final String REPLY_PRODUCT_NAME = "reply_product_name";
//    public static final String REPLY_PRODUCT_TYPE = "reply_product_type";
//    public static final String PRICE = "price";
    private EditText enterDate;
    private EditText enterStoreName;
    private EditText enterProductName;
    private EditText enterProductType;
    private EditText enterPrice;
    private Button saveInfoButton;

    private BudgetTrackerViewModel budgetTrackerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_budget_tracker);

        enterDate = findViewById(R.id.enter_date);
        enterStoreName = findViewById(R.id.enter_store_name);
        enterProductName = findViewById(R.id.enter_product_name);
        enterProductType = findViewById(R.id.enter_product_type);
        enterPrice = findViewById(R.id.enter_price);
        saveInfoButton = findViewById(R.id.saveIndoButton);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        enterDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        NewBudgetTracker.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        String date = year + "-" + month + "-" + dayOfMonth;
                        enterDate.setText(date);
                    }
                }, year,month, day);
                datePickerDialog.show();
            }
        });

        budgetTrackerViewModel = new ViewModelProvider.AndroidViewModelFactory(NewBudgetTracker.this
                .getApplication())
                .create(BudgetTrackerViewModel.class);

        saveInfoButton.setOnClickListener(view -> {
            Intent replyIntent = new Intent();

            if (!TextUtils.isEmpty(enterDate.getText())
                && !TextUtils.isEmpty(enterStoreName.getText())
                && !TextUtils.isEmpty(enterProductName.getText())
                && !TextUtils.isEmpty(enterProductType.getText())
                && !TextUtils.isEmpty(enterPrice.getText())) {
                String date = enterDate.getText().toString();
                String storeName = enterStoreName.getText().toString();
                String productName = enterProductName.getText().toString();
                String productType = enterProductType.getText().toString();
                int price = Integer.parseInt(enterPrice.getText().toString());

                BudgetTracker budgetTracker = new BudgetTracker(date, storeName, productName, productType, price);
                budgetTrackerViewModel.insert(budgetTracker);

//                replyIntent.putExtra(REPLY_DATE, date);
//                replyIntent.putExtra(REPLY_STORE_NAME, storeName);
//                replyIntent.putExtra(REPLY_PRODUCT_NAME, productName);
//                replyIntent.putExtra(REPLY_PRODUCT_TYPE, productType);
//                replyIntent.putExtra(PRICE, String.valueOf(price));
//                setResult(RESULT_OK, replyIntent);

            }
//            else {
//                setResult(RESULT_CANCELED, replyIntent);
//            }
            finish();

        });

    }
}