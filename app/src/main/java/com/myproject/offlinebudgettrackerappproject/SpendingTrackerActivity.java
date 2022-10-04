package com.myproject.offlinebudgettrackerappproject;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import com.myproject.offlinebudgettrackerappproject.adapter.SpendingTrackerListViewAdapter;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerSpending;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerSpendingViewModel;

import java.util.List;

public class SpendingTrackerActivity extends AppCompatActivity {

    private BudgetTrackerSpendingViewModel budgetTrackerSpendingViewModel;
    private ListView listView;
    private SpendingTrackerListViewAdapter spendingTrackerListViewAdapter;
    LiveData<List<BudgetTrackerSpending>> searchLists;

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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                List<BudgetTrackerSpending> budgetSpendingListItems = (List<BudgetTrackerSpending>) searchLists;
                int intId = (int) id;
                BudgetTrackerSpending itemId = budgetSpendingListItems.get(intId);
                Bundle result = new Bundle();
                result.putInt("itemId", itemId.getId());

                Fragment fragment = new AddSpendingFragment();
                fragment.setArguments(result);
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.setFragmentResult("itemId", result);
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.activity_add_container, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();


//                BudgetTracker storeItemId = budgetListItems.get(intId);
//                Intent shopFragmentIntent = new Intent(getActivity(), NewBudgetTracker.class);
//                shopFragmentIntent.putExtra(SHOP_FRAGMENT_ID, storeItemId.getId());
//                startActivityForResult(shopFragmentIntent, 1);
//
//                BudgetTrackerSpending budgetTrackerSpending = Objects.requireNonNull(budgetTrackerSpendingViewModel.getAllSpendingData().getValue().get(position));
//                Log.d(TAG, "onContactClick: " + position + budgetTrackerSpending.getId());
//
//                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                fragmentTransaction.replace(R.id.activity_add_container, new AddSpendingFragment()).commit();
            }
        });
    }

}