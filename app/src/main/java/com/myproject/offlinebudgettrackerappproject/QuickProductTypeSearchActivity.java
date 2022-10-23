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

import com.myproject.offlinebudgettrackerappproject.adapter.ProductTypeSearchListViewAdapter;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerSpending;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerSpendingViewModel;

import java.util.List;

public class QuickProductTypeSearchActivity extends AppCompatActivity {

    private BudgetTrackerSpendingViewModel budgetTrackerSpendingViewModel;
    EditText searchName;
    Button searchBtn;
    private ListView listView;
    List<BudgetTrackerSpending> searchList;
    BudgetTrackerSpending budgetTrackerSpending;
    ProductTypeSearchListViewAdapter productTypeSearchListViewAdapter;
    String calcSumStr;
    TextView searchCalcResultTxt;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_product_type_search);

        searchName = findViewById(R.id.quick_product_type_name);
        searchBtn = findViewById(R.id.quick_product_type_btn);
        searchCalcResultTxt = findViewById(R.id.quick_product_type_calc_result_txt);
        listView = findViewById(R.id.quick_product_type_listview);

        budgetTrackerSpendingViewModel = new ViewModelProvider.AndroidViewModelFactory(QuickProductTypeSearchActivity.this
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
        searchList = budgetTrackerSpendingViewModel.getQuickProductTypeList(searchKey);
        productTypeSearchListViewAdapter = new ProductTypeSearchListViewAdapter(this, searchList);
        listView.setAdapter(productTypeSearchListViewAdapter);
        calcSumStr = String.valueOf(budgetTrackerSpendingViewModel.getQuickProductTypeSum(searchKey));
        searchCalcResultTxt.setText(calcSumStr);
        searchCalcResultTxt = findViewById(R.id.quick_product_type_calc_result_txt);
    }
}