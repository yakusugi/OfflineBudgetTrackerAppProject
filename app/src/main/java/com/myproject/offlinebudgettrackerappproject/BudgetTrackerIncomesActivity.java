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

import com.myproject.offlinebudgettrackerappproject.adapter.IncomesTrackerListViewAdapter;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerIncomes;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerIncomesViewModel;

import java.util.List;

public class BudgetTrackerIncomesActivity extends AppCompatActivity {

    private BudgetTrackerIncomesViewModel budgetTrackerIncomesViewModel;
    private ListView listView;
    private IncomesTrackerListViewAdapter incomesTrackerListViewAdapter;
    List<BudgetTrackerIncomes> budgetIncomeListItems;
    Observer<List> listObserver = null;

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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {


                Intent intent = new Intent(BudgetTrackerIncomesActivity.this, AddBudgetTracker.class);
                intent.putExtra(AddBudgetTracker.EXTRA_DATA_INCOME, (BudgetTrackerIncomes)listView.getItemAtPosition(position));
                startActivity(intent);


            }

        });
        listObserver = new Observer<List>() {
            @Override
            public void onChanged(@Nullable final List searchResultList) {
                // Update the UI, in this case, a TextView.
                budgetIncomeListItems = searchResultList;
            }
        };
        budgetTrackerIncomesViewModel.getAllIncomesData().observe(this, listObserver);
    }
}