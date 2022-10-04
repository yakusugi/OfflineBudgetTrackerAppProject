package com.myproject.offlinebudgettrackerappproject;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class AddBudgetTracker extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_budget_tracker);

        bottomNavigationView = findViewById(R.id.add_bottom_navigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.activity_add_container, new AddSpendingFragment()).commit();

        bottomNavigationView.setSelectedItemId(R.id.nav_spending);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()) {
                    case R.id.nav_spending:
                        fragment = new AddSpendingFragment();
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
}