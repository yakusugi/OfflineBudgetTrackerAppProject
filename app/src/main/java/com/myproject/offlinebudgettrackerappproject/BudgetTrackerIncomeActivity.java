package com.myproject.offlinebudgettrackerappproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.myproject.offlinebudgettrackerappproject.adapter.IncomeListViewAdapter;
import com.myproject.offlinebudgettrackerappproject.databinding.ActivityMainBinding;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerIncome;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerIncomeViewModel;

import java.util.List;

public class BudgetTrackerIncomeActivity extends AppCompatActivity {

    private IncomeListViewAdapter incomeListViewAdapter;
    ActivityMainBinding activityMainBinding;
    private List<BudgetTrackerIncome> budgetTrackerIncome;
    BudgetTrackerIncomeViewModel budgetTrackerIncomeViewModel;
    private ListView incomeCategoryListView;
    public static final String BUDGET_TRACKER_INCOME_ID = "budget_income_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_tracker_income);

        EditText enterIncomeCategoryForQuery = findViewById(R.id.income_search_txt);
        Button incomeCategorySearchQueryBtn = findViewById(R.id.btn_income_search);
        incomeCategoryListView = findViewById(R.id.income_listview);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        incomeListViewAdapter = new IncomeListViewAdapter(this, budgetTrackerIncome);

        incomeCategorySearchQueryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BudgetTrackerIncome budgetTrackerIncome;
//                ShopRecyclerViewAdapter shopRecyclerViewAdapter;
                //      2022/02/11 追加
                IncomeListViewAdapter incomeListViewAdapter;
//                RecyclerView recyclerView = null;

                String incomeCategory = enterIncomeCategoryForQuery.getText().toString();
                budgetTrackerIncomeViewModel = new ViewModelProvider.AndroidViewModelFactory(BudgetTrackerIncomeActivity.this
                        .getApplication())
                        .create(BudgetTrackerIncomeViewModel.class);

                budgetTrackerIncome = new BudgetTrackerIncome();
                budgetTrackerIncome.setCategory(incomeCategory);


                List<BudgetTrackerIncome> viewModelIncomeCategoryLists = budgetTrackerIncomeViewModel.getIncomeCategoryLists(incomeCategory);

                incomeListViewAdapter = new IncomeListViewAdapter(BudgetTrackerIncomeActivity.this, viewModelIncomeCategoryLists);
                incomeCategoryListView.setAdapter(incomeListViewAdapter);

                incomeCategoryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                        String date = adapterView.getItemAtPosition(position).toString();
                        List<BudgetTrackerIncome> incomeListItems = viewModelIncomeCategoryLists;
                        int intId = (int) id;
                        BudgetTrackerIncome incomeItemId = incomeListItems.get(intId);
                        Intent incomeActivityIntent = new Intent(BudgetTrackerIncomeActivity.this, NewBudgetTrackerIncome.class);
                        incomeActivityIntent.putExtra(BUDGET_TRACKER_INCOME_ID, incomeItemId.getId());
                        startActivity(incomeActivityIntent);

                        Log.d("TAG", "onItemClick: " + date);
                    }
                });

            }

        });


    }
}