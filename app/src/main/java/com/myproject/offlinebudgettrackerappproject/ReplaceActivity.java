package com.myproject.offlinebudgettrackerappproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.myproject.offlinebudgettrackerappproject.adapter.ReplacedListViewAdapter;
import com.myproject.offlinebudgettrackerappproject.databinding.ActivityMainBinding;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTracker;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerViewModel;

import java.util.List;

public class ReplaceActivity extends AppCompatActivity {

    String searchWord;
    String replaceWith;
    BudgetTrackerViewModel budgetTrackerViewModel;
    List<BudgetTracker> budgetTrackerList;
    ReplacedListViewAdapter replacedListViewAdapter;
    private ListView replacedListView;
    ActivityMainBinding activityMainBinding;
    public static final String REPLACED_ACTIVITY_ID = "replaced_activity_id";
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_replace);

        EditText searchWordTxt = (EditText) findViewById(R.id.replace_search_txt);
        EditText replaceWithText = (EditText) findViewById(R.id.replace_search_with_txt);
        Button replaceBtn = (Button) findViewById(R.id.replace_btn);
        replacedListView = (ListView) findViewById(R.id.replaced_listview);
        radioGroup = (RadioGroup) findViewById(R.id.replace_radio_group);

        budgetTrackerViewModel = new ViewModelProvider.AndroidViewModelFactory(ReplaceActivity.this
                .getApplication())
                .create(BudgetTrackerViewModel.class);


        replaceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchWord = searchWordTxt.getText().toString();
                replaceWith = replaceWithText.getText().toString();
                budgetTrackerViewModel.replaceStoreName(searchWord, replaceWith);
                budgetTrackerList = budgetTrackerViewModel.getStoreNameLists(replaceWith);
                replacedListViewAdapter = new ReplacedListViewAdapter(getApplication(), budgetTrackerList);
                activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
                replacedListView.setAdapter(replacedListViewAdapter);

            }
        });

        //              Todo Tapped modified
        replacedListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String date = adapterView.getItemAtPosition(position).toString();
                List<BudgetTracker> budgetListItems = budgetTrackerList;
                int intId = (int) id;
                BudgetTracker replacedItemId = budgetListItems.get(intId);
                Intent replacedActivityIntent = new Intent(getApplication(), NewBudgetTracker.class);
                replacedActivityIntent.putExtra(REPLACED_ACTIVITY_ID, replacedItemId.getId());
                startActivityForResult(replacedActivityIntent, 1);

                Log.d("TAG-June", "onItemClick June02: " + date);
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId) {
                    case R.id.replace_radio_store_name:
                        replaceBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                searchWord = searchWordTxt.getText().toString();
                                replaceWith = replaceWithText.getText().toString();
                                storeNameReplace(searchWord, replaceWith);
//                                budgetTrackerViewModel.replaceStoreName(searchWord, replaceWith);
//                                budgetTrackerList = budgetTrackerViewModel.getStoreNameLists(replaceWith);
//                                replacedListViewAdapter = new ReplacedListViewAdapter(getApplication(), budgetTrackerList);
//                                activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
//                                replacedListView.setAdapter(replacedListViewAdapter);

                            }
                        });

                        break;
                    case R.id.replace_radio_product_name:
                        replaceBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                searchWord = searchWordTxt.getText().toString();
                                replaceWith = replaceWithText.getText().toString();
                                productNameReplace(searchWord, replaceWith);

                            }
                        });
                        break;
                    case R.id.radio_product_type:
                        replaceBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                searchWord = searchWordTxt.getText().toString();
                                replaceWith = replaceWithText.getText().toString();
                                productTypeReplace(searchWord, replaceWith);

                            }
                        });
                        break;
                }
            }
        });



    }

    private void storeNameReplace(String searchWord, String replaceWith) {
        budgetTrackerViewModel.replaceStoreName(searchWord, replaceWith);
        budgetTrackerList = budgetTrackerViewModel.getStoreNameLists(replaceWith);
        replacedListViewAdapter = new ReplacedListViewAdapter(getApplication(), budgetTrackerList);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        replacedListView.setAdapter(replacedListViewAdapter);
    }

    private void productNameReplace(String searchWord, String replaceWith) {
        budgetTrackerViewModel.replaceProductName(searchWord, replaceWith);
        budgetTrackerList = budgetTrackerViewModel.getProductNameList(replaceWith);
        replacedListViewAdapter = new ReplacedListViewAdapter(getApplication(), budgetTrackerList);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        replacedListView.setAdapter(replacedListViewAdapter);
    }

    private void productTypeReplace(String searchWord, String replaceWith) {
        budgetTrackerViewModel.replaceProductType(searchWord, replaceWith);
        budgetTrackerList = budgetTrackerViewModel.getProductTypeLists(replaceWith);
        replacedListViewAdapter = new ReplacedListViewAdapter(getApplication(), budgetTrackerList);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        replacedListView.setAdapter(replacedListViewAdapter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                int result = data.getIntExtra("result", 0);
                budgetTrackerList = budgetTrackerViewModel.getStoreNameLists(replaceWith);
                ReplacedListViewAdapter replacedListViewAdapter = new ReplacedListViewAdapter(this, budgetTrackerList);
                replacedListView.setAdapter(replacedListViewAdapter);
            }
        }
    }
}