package com.myproject.offlinebudgettrackerappproject;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.myproject.offlinebudgettrackerappproject.adapter.ProductNameSearchListViewAdapter;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerSpending;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerSpendingViewModel;

import java.util.List;

public class QuickProductNameSearchActivity extends AppCompatActivity {

    private BudgetTrackerSpendingViewModel budgetTrackerSpendingViewModel;
    EditText searchName;
    Button searchBtn;
    private ListView listView;
    List<BudgetTrackerSpending> searchList;
    BudgetTrackerSpending budgetTrackerSpending;
    ProductNameSearchListViewAdapter productTypeSearchListViewAdapter;
    String calcSumStr;
    TextView searchCalcResultTxt;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_product_name_search);

        searchName = findViewById(R.id.quick_product_name);
        searchBtn = findViewById(R.id.quick_product_name_btn);
        searchCalcResultTxt = findViewById(R.id.quick_product_name_calc_result_txt);
        listView = findViewById(R.id.quick_product_name_listview);

        budgetTrackerSpendingViewModel = new ViewModelProvider.AndroidViewModelFactory(QuickProductNameSearchActivity.this
                .getApplication())
                .create(BudgetTrackerSpendingViewModel.class);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searchKey = searchName.getText().toString();

                searchProductType(searchKey);
            }
        });


    }

    private void searchProductType(String searchKey) {
        budgetTrackerSpending = new BudgetTrackerSpending(searchKey);
        searchList = budgetTrackerSpendingViewModel.getQuickProductNameList(searchKey);
        productTypeSearchListViewAdapter = new ProductNameSearchListViewAdapter(this, searchList);
        listView.setAdapter(productTypeSearchListViewAdapter);
        calcSumStr = String.valueOf(budgetTrackerSpendingViewModel.getQuickProductNameSum(searchKey));
        searchCalcResultTxt.setText(calcSumStr);
        searchCalcResultTxt = findViewById(R.id.quick_product_name_calc_result_txt);
    }
}