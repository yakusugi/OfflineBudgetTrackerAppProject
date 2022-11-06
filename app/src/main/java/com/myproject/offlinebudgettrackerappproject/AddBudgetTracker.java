package com.myproject.offlinebudgettrackerappproject;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.myproject.offlinebudgettrackerappproject.model.ItemSpendingViewModel;

public class AddBudgetTracker extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    ItemSpendingViewModel itemSpendingViewModel;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_budget_tracker);

        bottomNavigationView = findViewById(R.id.add_bottom_navigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.activity_add_container, new AddSpendingFragment()).commit();

        bottomNavigationView.setSelectedItemId(R.id.nav_spending);
        bottomNavigationView.setItemIconTintList(null);
        itemSpendingViewModel = new ViewModelProvider(this).get(ItemSpendingViewModel.class);
        itemSpendingViewModel.getSelectedItem().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer liveId) {
                id = liveId.intValue();
            }
        });
//        Toast.makeText(this, id, Toast.LENGTH_SHORT).show();
//        id = itemSpendingViewModel.getSelectedItem();
//        Log.d(TAG, "startAddFragment1008: " + id);



        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()) {
                    case R.id.nav_spending:
                        fragment = new AddSpendingFragment();
                        break;
                    case R.id.nav_converter:
                        fragment = new AddConverterFragment();
                        break;
                    case R.id.nav_income:
                        fragment = new AddIncomeFragment();
                        break;
                    case R.id.nav_bank:
                        fragment = new AddBankFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.activity_add_container, fragment).commit();
                return true;
            }
        });
    }

    //todo: new method receive info from MainActivity and activate a specific fragment
    protected void startAddFragment() {

        Log.d(TAG, "startAddFragment1008: " + id);

    }

}