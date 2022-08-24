package com.myproject.offlinebudgettrackerappproject;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.myproject.offlinebudgettrackerappproject.adapter.BankingTrackerListViewAdapter;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerBankingViewModel;

public class BudgetTrackerBankingActivity extends AppCompatActivity {

    private BudgetTrackerBankingViewModel budgetTrackerBankingViewModel;
    private ListView listView;
    private BankingTrackerListViewAdapter bankingTrackerListViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_tracker_banking);

        listView = findViewById(R.id.baking_listview);

        budgetTrackerBankingViewModel = new ViewModelProvider.AndroidViewModelFactory(BudgetTrackerBankingActivity.this
                .getApplication())
                .create(BudgetTrackerBankingViewModel.class);

        budgetTrackerBankingViewModel.getAllBankingData().observe(this, contacts -> {
            bankingTrackerListViewAdapter = new BankingTrackerListViewAdapter(contacts, BudgetTrackerBankingActivity.this);
            listView.setAdapter(bankingTrackerListViewAdapter);

        });
    }
}