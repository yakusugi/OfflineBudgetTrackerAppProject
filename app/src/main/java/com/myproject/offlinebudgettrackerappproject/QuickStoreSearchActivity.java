package com.myproject.offlinebudgettrackerappproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.myproject.offlinebudgettrackerappproject.adapter.SpendingTrackerListViewAdapter;
import com.myproject.offlinebudgettrackerappproject.adapter.StoreSearchListViewAdapter;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerSpending;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerSpendingViewModel;

import java.util.List;

public class QuickStoreSearchActivity extends AppCompatActivity {

    private BudgetTrackerSpendingViewModel budgetTrackerSpendingViewModel;
    EditText searchName;
    Button searchBtn;
    private ListView listView;
    private SpendingTrackerListViewAdapter spendingTrackerListViewAdapter;
    List<BudgetTrackerSpending> searchList;
    BudgetTrackerSpending budgetTrackerSpending;
    StoreSearchListViewAdapter searchListViewAdapter;
    String calcSumStr;
    TextView searchCalcResultTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_store_search);

        searchName = findViewById(R.id.quick_store_name);
        searchBtn = findViewById(R.id.quick_store_btn);
        searchCalcResultTxt = findViewById(R.id.quick_store_calc_result_txt);
        listView = findViewById(R.id.quick_product_type_listview);

        budgetTrackerSpendingViewModel = new ViewModelProvider.AndroidViewModelFactory(QuickStoreSearchActivity.this
                .getApplication())
                .create(BudgetTrackerSpendingViewModel.class);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searchKey = searchName.getText().toString();

                searchStore(searchKey);
                listView.setOnItemClickListener((adapterView, view1, position, id) -> {
                    BudgetTrackerSpending spending = searchList.get(position);
                    MainActivity mainActivity = new MainActivity();
                    if(mainActivity != null) {
//                        Fragment fragment = AddSpendingFragment.newInstance(spending);
//                        getSupportFragmentManager()
//                                .beginTransaction()
//                                .replace(R.id.main_container, fragment)
//                                .addToBackStack(null)
//                                .commit();
                    }
                });
            }
        });


    }

    private void searchStore(String searchKey) {
        budgetTrackerSpending = new BudgetTrackerSpending(searchKey);
        searchList = budgetTrackerSpendingViewModel.getQuickStoreNameList(searchKey);
        searchListViewAdapter = new StoreSearchListViewAdapter(this, searchList);
        listView.setAdapter(searchListViewAdapter);
        calcSumStr = String.valueOf(budgetTrackerSpendingViewModel.getQuickStoreSum(searchKey));
        searchCalcResultTxt.setText(calcSumStr);
        searchCalcResultTxt = findViewById(R.id.quick_store_calc_result_txt);
    }
}