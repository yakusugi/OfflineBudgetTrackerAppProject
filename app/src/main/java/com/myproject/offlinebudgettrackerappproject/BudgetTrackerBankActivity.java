package com.myproject.offlinebudgettrackerappproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.Nullable;
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
    public static final String BUDGET_TRACKER_BANK_ID = "budget_bank_id";
    String bankName;

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

                bankName = enterBankNameForQuery.getText().toString();
                budgetTrackerBankViewModel = new ViewModelProvider.AndroidViewModelFactory(BudgetTrackerBankActivity.this
                        .getApplication())
                        .create(BudgetTrackerBankViewModel.class);

                budgetTrackerBank = new BudgetTrackerBank();
                budgetTrackerBank.setBankName(bankName);


                List<BudgetTrackerBank> viewModelBankNameLists = budgetTrackerBankViewModel.getBankNameLists(bankName);

                bankNameListViewAdapter = new BankNameListViewAdapter(BudgetTrackerBankActivity.this, viewModelBankNameLists);
                bankNameListView.setAdapter(bankNameListViewAdapter);

                bankNameListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                        String date = adapterView.getItemAtPosition(position).toString();
                        List<BudgetTrackerBank> bankListItems = viewModelBankNameLists;
                        int intId = (int) id;
                        BudgetTrackerBank bankItemId = bankListItems.get(intId);
                        Intent bankActivityIntent = new Intent(BudgetTrackerBankActivity.this, NewBudgetTrackerBank.class);
                        bankActivityIntent.putExtra(BUDGET_TRACKER_BANK_ID, bankItemId.getId());
                        startActivityForResult(bankActivityIntent, 1);

                        Log.d("TAG", "onItemClick: " + date);
                    }
                });

            }

        });

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                int result = data.getIntExtra("result", 0);
                List<BudgetTrackerBank> viewModelBankNameLists = budgetTrackerBankViewModel.getBankNameLists(bankName);

                bankNameListViewAdapter = new BankNameListViewAdapter(BudgetTrackerBankActivity.this, viewModelBankNameLists);
                bankNameListView.setAdapter(bankNameListViewAdapter);
            }
        }
    }
}