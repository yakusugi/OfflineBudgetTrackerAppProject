package com.myproject.offlinebudgettrackerappproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.myproject.offlinebudgettrackerappproject.model.BudgetTracker;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerViewModel;

public class NewBudgetTracker extends AppCompatActivity {

    public static final String REPLY_DATE = "reply_date";
    public static final String REPLY_STORE_NAME = "reply_store_name";
    public static final String REPLY_PRODUCT_NAME = "reply_product_name";
    public static final String REPLY_PRODUCT_TYPE = "reply_product_type";
    public static final String PRICE = "price";
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

                replyIntent.putExtra(REPLY_DATE, date);
                replyIntent.putExtra(REPLY_STORE_NAME, storeName);
                replyIntent.putExtra(REPLY_PRODUCT_NAME, productName);
                replyIntent.putExtra(REPLY_PRODUCT_TYPE, productType);
                replyIntent.putExtra(PRICE, String.valueOf(price));
                setResult(RESULT_OK, replyIntent);

            } else {
                setResult(RESULT_CANCELED, replyIntent);
            }
            finish();

        });

    }
}