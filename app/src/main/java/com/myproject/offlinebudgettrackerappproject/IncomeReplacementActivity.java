package com.myproject.offlinebudgettrackerappproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.myproject.offlinebudgettrackerappproject.adapter.IncomeReplacementListViewAdapter;
import com.myproject.offlinebudgettrackerappproject.databinding.ActivityMainBinding;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerIncomes;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerIncomesViewModel;

import java.util.List;

public class IncomeReplacementActivity extends AppCompatActivity {

    private ListView replacedListView;
    BudgetTrackerIncomesViewModel budgetTrackerIncomesViewModel;
    List<BudgetTrackerIncomes> searchCategoryNameLists;
    BudgetTrackerIncomes budgetTrackerIncomes;
    List<BudgetTrackerIncomes> budgetTrackerIncomesList;
    IncomeReplacementListViewAdapter incomeReplacementListViewAdapter;
    ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_replacement);

        EditText searchWordTxt = (EditText) findViewById(R.id.replacement_income_search_txt);
        EditText replaceWithText = (EditText) findViewById(R.id.replacement_income_search_with_txt);
        Button replaceBtn = (Button) findViewById(R.id.rep_income_search_btn);
        replacedListView = (ListView) findViewById(R.id.replacement_income_listview);

        budgetTrackerIncomesViewModel = new ViewModelProvider.AndroidViewModelFactory(IncomeReplacementActivity.this
                .getApplication())
                .create(BudgetTrackerIncomesViewModel.class);

        replaceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searchWord = searchWordTxt.getText().toString();
                String replaceWith = replaceWithText.getText().toString();
                categoryNameReplace(searchWord, replaceWith);

                replacedListView.setOnItemClickListener((adapterView, view1, position, id) -> {
                    Intent intent = new Intent(IncomeReplacementActivity.this, AddBudgetTracker.class);
                    intent.putExtra(AddBudgetTracker.EXTRA_DATA_INCOME, (BudgetTrackerIncomes)replacedListView.getItemAtPosition(position));
                    startActivity(intent);
                });
            }
        });
    }

    private void categoryNameReplace(String searchWord, String replaceWith) {
        budgetTrackerIncomesViewModel.replaceCategoryName(searchWord, replaceWith);
        budgetTrackerIncomesList = budgetTrackerIncomesViewModel.getCategoryNameList(replaceWith);
        incomeReplacementListViewAdapter = new IncomeReplacementListViewAdapter(getApplication(), budgetTrackerIncomesList);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        replacedListView.setAdapter(incomeReplacementListViewAdapter);
    }

}