package com.myproject.offlinebudgettrackerappproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
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
    DrawerLayout drawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.main_drawer_layout);

        budgetTrackerViewModel = new ViewModelProvider.AndroidViewModelFactory(MainActivity.this
                .getApplication())
                .create(BudgetTrackerViewModel.class);

        budgetTrackerViewModel.getAllBudgetTrackerLists().observe(this, budgetTrackers -> {
//            Log.d("TAG", "onCreate: " + budgetTrackers.get(0).getProductName());
        });

        //Floating action menu
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

        //Todo: July 9 2022 New Floating action button
        FloatingActionButton floatingActionButton = findViewById(R.id.budget_tracker_fab);
        floatingActionButton.setOnClickListener(view -> {
            Intent intent4 = new Intent(MainActivity.this, AddBudgetTracker.class);
            startActivity(intent4);
//            startActivityForResult(intent3, NEW_BUDGET_TRACKER_BANK_ACTIVITY_REQUEST_CODE);

        });

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setItemIconTintList(null);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_container, new DashboardFragment()).commit();

        bottomNavigationView.setSelectedItemId(R.id.nav_dash);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()) {
                    case R.id.nav_dash:
                        fragment = new DashboardFragment();
                        fabMenu.setVisibility(View.VISIBLE);
                        break;
                    case R.id.nav_stats:
                        fragment = new StatsFragment();
                        fabMenu.setVisibility(View.VISIBLE);
                        break;
                    case R.id.nav_search:
                        fragment = new SearchFragment();
                        fabMenu.setVisibility(View.VISIBLE);
                        break;
                    case R.id.nav_shop:
                        fragment = new ShopFragment();
                        break;
                    case R.id.nav_converter:
                        fragment = new ConverterFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.main_container, fragment).commit();
                return true;
            }
        });
    }

    //feb 15 追加
    public void ClickMenu(View view) {
        //open drawer
        openDrawer(drawerLayout);
    }

    public void ClickSettingsMenu(View view) {
        //open drawer
        redirectActivity(this,BudgetTrackerSettingsActivity.class);
    }

    public void ClickAboutMe(View view) {
        //open drawer
        redirectActivity(this,AboutActivity.class);
    }

    private static void openDrawer(DrawerLayout drawerLayout) {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public void ClickLogo(View view) {
        //open drawer
        closeDrawer(drawerLayout);
    }

    private static void closeDrawer(DrawerLayout drawerLayout) {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    public static void redirectActivity(Activity activity, Class aClass) {
        Intent intent = new Intent(activity, aClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }

}