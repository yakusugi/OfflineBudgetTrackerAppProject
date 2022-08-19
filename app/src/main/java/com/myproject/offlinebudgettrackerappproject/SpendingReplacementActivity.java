package com.myproject.offlinebudgettrackerappproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.myproject.offlinebudgettrackerappproject.adapter.SpendingReplacementListViewAdapter;
import com.myproject.offlinebudgettrackerappproject.databinding.ActivityMainBinding;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerSpending;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerSpendingViewModel;

import java.util.List;

public class SpendingReplacementActivity extends AppCompatActivity {

    private ListView replacedListView;
    RadioGroup radioGroup;
    BudgetTrackerSpendingViewModel budgetTrackerSpendingViewModel;
    List<BudgetTrackerSpending> searchStoreNameLists;
    BudgetTrackerSpending budgetTrackerSpending;
    List<BudgetTrackerSpending> budgetTrackerSpendingList;
    SpendingReplacementListViewAdapter spendingReplacementListViewAdapter;
    ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spending_replacement);

        EditText searchWordTxt = (EditText) findViewById(R.id.replacement_search_txt);
        EditText replaceWithText = (EditText) findViewById(R.id.replacement_search_with_txt);
        Button replaceBtn = (Button) findViewById(R.id.stats_search_btn);
        replacedListView = (ListView) findViewById(R.id.replacement_listview);
        radioGroup = (RadioGroup) findViewById(R.id.replacement_radio_group);

        budgetTrackerSpendingViewModel = new ViewModelProvider.AndroidViewModelFactory(SpendingReplacementActivity.this
                .getApplication())
                .create(BudgetTrackerSpendingViewModel.class);

        replaceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searchWord = searchWordTxt.getText().toString();
                String replaceWith = replaceWithText.getText().toString();
                if (radioGroup.getCheckedRadioButtonId() == R.id.replacement_radio_store_name) {
                    searchWord = searchWordTxt.getText().toString();
                    replaceWith = replaceWithText.getText().toString();
                    storeNameReplace(searchWord, replaceWith);
                } else if (radioGroup.getCheckedRadioButtonId() == R.id.replacement_radio_product_name) {
                    searchWord = searchWordTxt.getText().toString();
                    replaceWith = replaceWithText.getText().toString();
                    productNameReplace(searchWord, replaceWith);
                } else if (radioGroup.getCheckedRadioButtonId() == R.id.replacement_radio_product_type) {
                    searchWord = searchWordTxt.getText().toString();
                    replaceWith = replaceWithText.getText().toString();
                    productTypeReplace(searchWord, replaceWith);
                }

            }
        });
    }

    private void storeNameReplace(String searchWord, String replaceWith) {
        budgetTrackerSpendingViewModel.replaceStoreName(searchWord, replaceWith);
        budgetTrackerSpendingList = budgetTrackerSpendingViewModel.getStoreNameList(replaceWith);
        spendingReplacementListViewAdapter = new SpendingReplacementListViewAdapter(getApplication(), budgetTrackerSpendingList);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        replacedListView.setAdapter(spendingReplacementListViewAdapter);
    }

    private void productNameReplace(String searchWord, String replaceWith) {
        budgetTrackerSpendingViewModel.replaceProductName(searchWord, replaceWith);
        budgetTrackerSpendingList = budgetTrackerSpendingViewModel.getProductNameList(replaceWith);
        spendingReplacementListViewAdapter = new SpendingReplacementListViewAdapter(getApplication(), budgetTrackerSpendingList);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        replacedListView.setAdapter(spendingReplacementListViewAdapter);
    }

    private void productTypeReplace(String searchWord, String replaceWith) {
        budgetTrackerSpendingViewModel.replaceProductType(searchWord, replaceWith);
        budgetTrackerSpendingList = budgetTrackerSpendingViewModel.getProductTypeList(replaceWith);
        spendingReplacementListViewAdapter = new SpendingReplacementListViewAdapter(getApplication(), budgetTrackerSpendingList);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        replacedListView.setAdapter(spendingReplacementListViewAdapter);
    }
}