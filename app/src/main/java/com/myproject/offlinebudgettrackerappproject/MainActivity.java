package com.myproject.offlinebudgettrackerappproject;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerViewModel;
import com.myproject.offlinebudgettrackerappproject.model.ItemSpendingViewModel;

public class MainActivity extends AppCompatActivity implements SearchFragment.OnDataPass {

    private static final int NEW_BUDGET_TRACKER_ACTIVITY_REQUEST_CODE = 1;
    private static final int NEW_BUDGET_TRACKER_INCOME_ACTIVITY_REQUEST_CODE = 1;
    private static final int NEW_BUDGET_TRACKER_BANK_ACTIVITY_REQUEST_CODE = 1;
    BottomNavigationView bottomNavigationView;
    private BudgetTrackerViewModel budgetTrackerViewModel;
    DrawerLayout drawerLayout;
    ItemSpendingViewModel itemSpendingViewModel;
    public static final String STORE_FRAGMENT_ID = "store_search_id";
    AddBudgetTracker addBudgetTracker;


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
                        break;
                    case R.id.nav_stats:
                        fragment = new StatsFragment();
                        break;
                    case R.id.nav_search:
                        fragment = new SearchFragment();
                        break;
                    case R.id.nav_converter:
                        fragment = new ConverterFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.main_container, fragment).commit();
                return true;
            }
        });

        //                pass data to viewModel and share it with host activity


    }

    //feb 15 追加
    public void ClickMenu(View view) {
        //open drawer
        openDrawer(drawerLayout);
    }

    public void ClickQuickStoreSearch(View view) {
        //open drawer
        redirectActivity(this,QuickStoreSearchActivity.class);
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


    @Override
    public void onDataPass(Integer data) {
        Log.d(TAG, "onDataPass: " + data);
        itemSpendingViewModel = new ViewModelProvider(this).get(ItemSpendingViewModel.class);
        itemSpendingViewModel.setData(data);
//        Intent searchStoreIntent = new Intent(this, AddBudgetTracker.class);
//        searchStoreIntent.putExtra(itemSpendingViewModel.getSelectedItem(), data);
//        startActivityForResult(searchStoreIntent, 1);
        //todo: from here call AddBudgetTracker's method (not created yet) and use FragmentManager to activate a specific fragment
        addBudgetTracker = new AddBudgetTracker();
        addBudgetTracker.startAddFragment();
    }
}