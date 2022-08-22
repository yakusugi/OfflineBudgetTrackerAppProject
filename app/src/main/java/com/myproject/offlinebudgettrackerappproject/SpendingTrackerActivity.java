package com.myproject.offlinebudgettrackerappproject;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.myproject.offlinebudgettrackerappproject.adapter.SpendingTrackerListViewAdapter;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerSpendingViewModel;

public class SpendingTrackerActivity extends AppCompatActivity {

    private BudgetTrackerSpendingViewModel budgetTrackerSpendingViewModel;
    private ListView listView;
    private SpendingTrackerListViewAdapter spendingTrackerListViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spending_tracker);

        listView = findViewById(R.id.spending_listview);

        budgetTrackerSpendingViewModel = new ViewModelProvider.AndroidViewModelFactory(SpendingTrackerActivity.this
                .getApplication())
                .create(BudgetTrackerSpendingViewModel.class);

        budgetTrackerSpendingViewModel.getAllSpendingData().observe(this, contacts -> {
            spendingTrackerListViewAdapter = new SpendingTrackerListViewAdapter(contacts, SpendingTrackerActivity.this);
            listView.setAdapter(spendingTrackerListViewAdapter);

        });
    }
}