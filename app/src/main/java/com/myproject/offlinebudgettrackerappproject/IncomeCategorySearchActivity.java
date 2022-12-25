package com.myproject.offlinebudgettrackerappproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.myproject.offlinebudgettrackerappproject.adapter.IncomesTrackerListViewAdapter;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerIncomes;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerIncomesViewModel;

import java.util.List;

public class IncomeCategorySearchActivity extends AppCompatActivity {

    private BudgetTrackerIncomesViewModel budgetTrackerIncomesViewModel;
    EditText searchName;
    Button searchBtn;
    private ListView listView;
    List<BudgetTrackerIncomes> searchList;
    BudgetTrackerIncomes budgetTrackerIncomes;
    IncomesTrackerListViewAdapter incomesTrackerListViewAdapter;
    String calcSumStr;
    TextView searchCalcResultTxt;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_income_category_search);

        searchName = findViewById(R.id.quick_category_name);
        searchBtn = findViewById(R.id.quick_category_btn);
        searchCalcResultTxt = findViewById(R.id.quick_category_calc_result_txt);
        listView = findViewById(R.id.quick_category_listview);

        budgetTrackerIncomesViewModel = new ViewModelProvider.AndroidViewModelFactory(IncomeCategorySearchActivity.this
                .getApplication())
                .create(BudgetTrackerIncomesViewModel.class);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searchKey = searchName.getText().toString();

                searchProductType(searchKey);

                listView.setOnItemClickListener((adapterView, view1, position, id) -> {
                    Intent intent = new Intent(IncomeCategorySearchActivity.this, AddBudgetTracker.class);
                    intent.putExtra(AddBudgetTracker.EXTRA_DATA_INCOME, (BudgetTrackerIncomes)listView.getItemAtPosition(position));
                    startActivity(intent);
                });
            }
        });


    }

    private void searchProductType(String searchKey) {
        budgetTrackerIncomes = new BudgetTrackerIncomes(searchKey);
        searchList = budgetTrackerIncomesViewModel.getCategoryNameList(searchKey);
        incomesTrackerListViewAdapter = new IncomesTrackerListViewAdapter(searchList, this);
        listView.setAdapter(incomesTrackerListViewAdapter);
        calcSumStr = String.valueOf(budgetTrackerIncomesViewModel.getQuickCategorySum(searchKey));
        searchCalcResultTxt.setText(calcSumStr);
        searchCalcResultTxt = findViewById(R.id.quick_product_type_calc_result_txt);
    }
}