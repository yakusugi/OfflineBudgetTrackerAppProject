package com.myproject.offlinebudgettrackerappproject;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.myproject.offlinebudgettrackerappproject.adapter.IncomesTrackerListViewAdapter;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerIncomesViewModel;

public class BudgetTrackerIncomesActivity extends AppCompatActivity {

    private BudgetTrackerIncomesViewModel budgetTrackerIncomesViewModel;
    private ListView listView;
    private IncomesTrackerListViewAdapter incomesTrackerListViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_tracker_incomes);

        listView = findViewById(R.id.incomes_listview);

        budgetTrackerIncomesViewModel = new ViewModelProvider.AndroidViewModelFactory(BudgetTrackerIncomesActivity.this
                .getApplication())
                .create(BudgetTrackerIncomesViewModel.class);

        budgetTrackerIncomesViewModel.getAllIncomesData().observe(this, contacts -> {
            incomesTrackerListViewAdapter = new IncomesTrackerListViewAdapter(contacts, BudgetTrackerIncomesActivity.this);
            listView.setAdapter(incomesTrackerListViewAdapter);

        });
    }
}