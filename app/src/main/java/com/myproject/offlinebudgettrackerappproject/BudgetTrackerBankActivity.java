package com.myproject.offlinebudgettrackerappproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.myproject.offlinebudgettrackerappproject.adapter.BankNameListViewAdapter;
import com.myproject.offlinebudgettrackerappproject.databinding.ActivityMainBinding;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerBank;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerBankViewModel;

import java.util.List;

public class BudgetTrackerBankActivity extends AppCompatActivity {

    private BankNameListViewAdapter bankNameListViewAdapter;
    ActivityMainBinding activityMainBinding;
    private List<BudgetTrackerBank> budgetTrackerBank;
    BudgetTrackerBankViewModel budgetTrackerBankViewModel;
    private ListView bankNameListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_tracker_bank);

        EditText enterBankNameForQuery = findViewById(R.id.bank_search_txt);
        Button bankNameSearchQueryBtn = findViewById(R.id.btn_bank_search);
        bankNameListView = findViewById(R.id.bank_listview);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        bankNameListViewAdapter = new BankNameListViewAdapter(this, budgetTrackerBank);

        bankNameSearchQueryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BudgetTrackerBank budgetTrackerBank;
//                ShopRecyclerViewAdapter shopRecyclerViewAdapter;
                //      2022/02/11 追加
                BankNameListViewAdapter bankNameListViewAdapter;
//                RecyclerView recyclerView = null;

                String bankName = enterBankNameForQuery.getText().toString();
                budgetTrackerBankViewModel = new ViewModelProvider.AndroidViewModelFactory(BudgetTrackerBankActivity.this
                        .getApplication())
                        .create(BudgetTrackerBankViewModel.class);

                budgetTrackerBank = new BudgetTrackerBank();
                budgetTrackerBank.setBankName(bankName);


                List<BudgetTrackerBank> viewModelBankNameLists = budgetTrackerBankViewModel.getBankNameLists(bankName);

                bankNameListViewAdapter = new BankNameListViewAdapter(BudgetTrackerBankActivity.this, viewModelBankNameLists);
                bankNameListView.setAdapter(bankNameListViewAdapter);

            }

        });
    }
}