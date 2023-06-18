package com.myproject.offlinebudgettrackerappproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerSpending;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerSpendingViewModel;
import com.myproject.offlinebudgettrackerappproject.model.MysqlRegistration;
import com.myproject.offlinebudgettrackerappproject.model.MysqlRegistrationViewModel;

import java.util.ArrayList;
import java.util.List;

public class MysqlRegistrationActivity extends AppCompatActivity {

    Button syncButton;
    MysqlRegistration mysqlRegistration;
    BudgetTrackerSpendingViewModel budgetTrackerSpendingViewModel;
    MysqlRegistrationViewModel mysqlRegistrationViewModel;
    List<BudgetTrackerSpending> searchedSpendingList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mysql_registration);

        syncButton = (Button) findViewById(R.id.mysql_sync_btn);

        budgetTrackerSpendingViewModel = new ViewModelProvider.AndroidViewModelFactory(MysqlRegistrationActivity.this
                .getApplication())
                .create(BudgetTrackerSpendingViewModel.class);

        mysqlRegistrationViewModel = new ViewModelProvider.AndroidViewModelFactory(MysqlRegistrationActivity.this
                .getApplication())
                .create(MysqlRegistrationViewModel.class);



        syncButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchedSpendingList = budgetTrackerSpendingViewModel.getBudgetTrackerSpendingListForMySQL();

                mysqlRegistrationViewModel.sendSearchedSpendingList(searchedSpendingList);

            }
        });


    }
}