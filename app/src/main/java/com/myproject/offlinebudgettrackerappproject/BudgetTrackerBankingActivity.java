package com.myproject.offlinebudgettrackerappproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.myproject.offlinebudgettrackerappproject.adapter.BankingTrackerListViewAdapter;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerBanking;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerBankingViewModel;

import java.util.List;

public class BudgetTrackerBankingActivity extends AppCompatActivity {

    private BudgetTrackerBankingViewModel budgetTrackerBankingViewModel;
    private ListView listView;
    private BankingTrackerListViewAdapter bankingTrackerListViewAdapter;
    Observer<List> listObserver = null;
    List<BudgetTrackerBanking> budgetBankingListItems;
    static final String EXTRA_DATA_BANKING = "data_baking";

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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {


                Intent intent = new Intent(BudgetTrackerBankingActivity.this, AddBudgetTracker.class);
                intent.putExtra(AddBudgetTracker.EXTRA_DATA_BANKING, (BudgetTrackerBanking)listView.getItemAtPosition(position));
                startActivity(intent);


            }

        });
        listObserver = new Observer<List>() {
            @Override
            public void onChanged(@Nullable final List searchResultList) {
                // Update the UI, in this case, a TextView.
                budgetBankingListItems = searchResultList;
            }
        };
        budgetTrackerBankingViewModel.getAllBankingData().observe(this, listObserver);
    }
}