package com.myproject.offlinebudgettrackerappproject;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.myproject.offlinebudgettrackerappproject.adapter.SpendingTrackerListViewAdapter;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerSpending;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerSpendingViewModel;

import java.util.ArrayList;
import java.util.List;

public class SpendingTrackerActivity extends AppCompatActivity {

    private BudgetTrackerSpendingViewModel budgetTrackerSpendingViewModel;
    private ListView listView;
    private SpendingTrackerListViewAdapter spendingTrackerListViewAdapter;
    LiveData<List<BudgetTrackerSpending>> searchLists;
    List<BudgetTrackerSpending> budgetSpendingListItems;
    Observer<List> listObserver = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spending_tracker);

        listView = findViewById(R.id.spending_listview);

        budgetTrackerSpendingViewModel = new ViewModelProvider.AndroidViewModelFactory(SpendingTrackerActivity.this
                .getApplication())
                .create(BudgetTrackerSpendingViewModel.class);

        searchLists = budgetTrackerSpendingViewModel.getAllSpendingData();

        budgetTrackerSpendingViewModel.getAllSpendingData().observe(this, contacts -> {
            spendingTrackerListViewAdapter = new SpendingTrackerListViewAdapter(contacts, SpendingTrackerActivity.this);
            listView.setAdapter(spendingTrackerListViewAdapter);

        });

        budgetSpendingListItems = new ArrayList<>();

        budgetTrackerSpendingViewModel.getAllSpendingData().observe(this, listObserver);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                BudgetTrackerSpending spending = budgetSpendingListItems.get(position);
                MainActivity mainActivity = new MainActivity();
                if (mainActivity != null) {
                    Fragment fragment = AddSpendingFragment.newInstance(spending);
                    mainActivity.getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.main_container, fragment)
                            .addToBackStack(null)
                            .commit();
                }
            }

        });
        listObserver = new Observer<List>() {
            @Override
            public void onChanged(@Nullable final List searchResultList) {
                // Update the UI, in this case, a TextView.
                budgetSpendingListItems = searchResultList;
            }
        };
        budgetTrackerSpendingViewModel.getAllSpendingData().observe(this, listObserver);

    }

}