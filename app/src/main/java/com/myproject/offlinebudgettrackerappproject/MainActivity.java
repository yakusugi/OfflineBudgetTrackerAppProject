package com.myproject.offlinebudgettrackerappproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerViewModel;

public class MainActivity extends AppCompatActivity {

    private static final int NEW_BUDGET_TRACKER_ACTIVITY_REQUEST_CODE = 1;
    private static final int NEW_BUDGET_TRACKER_INCOME_ACTIVITY_REQUEST_CODE = 1;
    private static final int NEW_BUDGET_TRACKER_BANK_ACTIVITY_REQUEST_CODE = 1;
    BottomNavigationView bottomNavigationView;
    private BudgetTrackerViewModel budgetTrackerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        budgetTrackerViewModel = new ViewModelProvider.AndroidViewModelFactory(MainActivity.this
                .getApplication())
                .create(BudgetTrackerViewModel.class);

        budgetTrackerViewModel.getAllBudgetTrackerLists().observe(this, budgetTrackers -> {
//            Log.d("TAG", "onCreate: " + budgetTrackers.get(0).getProductName());
        });

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setItemIconTintList(null);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_container, new HomeFragment()).commit();

        bottomNavigationView.setSelectedItemId(R.id.nav_home);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        fragment = new HomeFragment();
                        break;
                    case R.id.nav_shop:
                        fragment = new ShopFragment();
                        break;
                    case R.id.nav_product:
                        fragment = new ProductFragment();
                        break;
                    case R.id.nav_date:
                        fragment = new DateFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.main_container, fragment).commit();
                return true;
            }
        });

        FloatingActionsMenu fabMenu = findViewById(R.id.add_budget_tracker_fab_menu);
        fabMenu.setOnClickListener(view -> {
            //            TODO: When the fab menu is pressed, change the image to wallet icon.
        });

        FloatingActionButton fabBudgetTracker = findViewById(R.id.add_budget_tracker_fab);
        fabBudgetTracker.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, NewBudgetTracker.class);
            startActivity(intent);
//            startActivityForResult(intent, NEW_BUDGET_TRACKER_ACTIVITY_REQUEST_CODE);

        });

        FloatingActionButton fabBudgetTrackerIncome = findViewById(R.id.add_budget_tracker_income_fab);
        fabBudgetTrackerIncome.setOnClickListener(view -> {
            Intent intent2 = new Intent(MainActivity.this, NewBudgetTrackerIncome.class);
            startActivity(intent2);
//            startActivityForResult(intent2, NEW_BUDGET_TRACKER_INCOME_ACTIVITY_REQUEST_CODE);

        });

        FloatingActionButton fabBudgetTrackerBank = findViewById(R.id.add_budget_tracker_bank_fab);
        fabBudgetTrackerBank.setOnClickListener(view -> {
            Intent intent3 = new Intent(MainActivity.this, NewBudgetTrackerBank.class);
            startActivity(intent3);
//            startActivityForResult(intent3, NEW_BUDGET_TRACKER_BANK_ACTIVITY_REQUEST_CODE);

        });
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == NEW_BUDGET_TRACKER_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
//            assert data != null;
//            String date = data.getStringExtra(NewBudgetTracker.REPLY_DATE);
//            String storeName = data.getStringExtra(NewBudgetTracker.REPLY_STORE_NAME);
//            String productName = data.getStringExtra(NewBudgetTracker.REPLY_PRODUCT_NAME);
//            String productType = data.getStringExtra(NewBudgetTracker.REPLY_PRODUCT_TYPE);
//            int price = Integer.parseInt(data.getStringExtra(NewBudgetTracker.PRICE));
//
//            BudgetTracker budgetTracker = new BudgetTracker(date, storeName, productName, productType, price);
//            BudgetTrackerViewModel.insert(budgetTracker);
//        } else if (requestCode == NEW_BUDGET_TRACKER_INCOME_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
//            assert data != null;
//            String incomeDate = data.getStringExtra(NewBudgetTrackerIncome.REPLY_INCOME_DATE);
//            String incomeCategory = data.getStringExtra(NewBudgetTrackerIncome.REPLY_INCOME_CATEGORY);
//            int incomeAmount = Integer.parseInt(data.getStringExtra(NewBudgetTrackerIncome.REPLY_INCOME_AMOUNT));
//
//            BudgetTrackerIncome budgetTrackerIncome = new BudgetTrackerIncome(incomeDate, incomeCategory, incomeAmount);
//            BudgetTrackerIncomeViewModel.insert(budgetTrackerIncome);
//        } else if (requestCode == NEW_BUDGET_TRACKER_BANK_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
//            assert data != null;
//            String bankName = data.getStringExtra(NewBudgetTrackerBank.REPLY_BANK_NAME);
//            int bankBalance = Integer.parseInt(data.getStringExtra(NewBudgetTrackerBank.REPLY_BANK_BALANCE));
//
//            BudgetTrackerBank budgetTrackerBank = new BudgetTrackerBank(bankName, bankBalance);
//            BudgetTrackerBankViewModel.insert(budgetTrackerBank);
//        }
//
//    }


}