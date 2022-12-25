package com.myproject.offlinebudgettrackerappproject;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.myproject.offlinebudgettrackerappproject.adapter.SearchListViewAdapter;
import com.myproject.offlinebudgettrackerappproject.databinding.ActivityMainBinding;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerSpending;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerSpendingViewModel;
import com.myproject.offlinebudgettrackerappproject.model.Currency;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {

    private static final String PREF_CURRENCY_FILENAME = "CURRENCY_SHARED";
    private static final String PREF_CURRENCY_VALUE = "currencyValue";
    BudgetTrackerSpendingViewModel budgetTrackerSpendingViewModel;

    List<BudgetTrackerSpending> searchedSpendingList = new ArrayList<>();
    String spendingSum = "";

    private ListView searchListView;
    RadioGroup radioGroup;
    ActivityMainBinding activityMainBinding;
    SharedPreferences sharedPreferences;
    TextView searchCalcResultTxt;
    int searchMode = 0;


    public SearchFragment() {
        // Required empty public constructor
    }

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        radioGroup = (RadioGroup) view.findViewById(R.id.search_radio_group);
        searchListView = (ListView) view.findViewById(R.id.search_listview);

        EditText searchName = (EditText) view.findViewById(R.id.search_name);
        EditText searchDateFrom = (EditText) view.findViewById(R.id.search_date_from_txt);
        EditText searchDateTo = (EditText) view.findViewById(R.id.search_date_to_txt);
        Button searchBtn = (Button) view.findViewById(R.id.search_btn);
        searchCalcResultTxt = (TextView) view.findViewById(R.id.search_calc_result_txt);

        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());

        sharedPreferences = getActivity().getSharedPreferences(PREF_CURRENCY_FILENAME, 0);

        //選択された通貨の設定
        int currentCurrencyNum = sharedPreferences.getInt(PREF_CURRENCY_VALUE, 0);
        Currency currency = Currency.getCurrencyArrayList().get(currentCurrencyNum);
        searchCalcResultTxt.setCompoundDrawablesWithIntrinsicBounds(currency.getCurrencyImage(), 0, 0, 0);

        budgetTrackerSpendingViewModel = new ViewModelProvider(requireActivity()).get(BudgetTrackerSpendingViewModel.class);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        searchDateFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        String date = year + "-" + month + "-" + dayOfMonth;
                        searchDateFrom.setText(date);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        searchDateTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        String date = year + "-" + month + "-" + dayOfMonth;
                        searchDateTo.setText(date);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searchKey = searchName.getText().toString();
                String dateFrom = searchDateFrom.getText().toString();
                String dateTo = searchDateTo.getText().toString();



                if (radioGroup.getCheckedRadioButtonId() == R.id.search_radio_store_name) {
                    searchedSpendingList = budgetTrackerSpendingViewModel.getSearchStoreNameLists(searchKey, dateFrom, dateTo);
                    spendingSum = String.valueOf(budgetTrackerSpendingViewModel.getSearchStoreSum(searchKey, dateFrom, dateTo));
                } else if (radioGroup.getCheckedRadioButtonId() == R.id.search_radio_product_name) {
                    searchedSpendingList = budgetTrackerSpendingViewModel.getSearchProductNameLists(searchKey, dateFrom, dateTo);
                    spendingSum = String.valueOf(budgetTrackerSpendingViewModel.getSearchProductSum(searchKey, dateFrom, dateTo));
                    searchMode = 1;
                } else if (radioGroup.getCheckedRadioButtonId() == R.id.search_radio_product_type) {
                    searchedSpendingList = budgetTrackerSpendingViewModel.getSearchProductTypeLists(searchKey, dateFrom, dateTo);
                    spendingSum = String.valueOf(budgetTrackerSpendingViewModel.getSearchProductTypeSum(searchKey, dateFrom, dateTo));
                    searchMode = 2;
                }

                searchListView.setAdapter(new SearchListViewAdapter(getActivity(), searchedSpendingList));
                searchCalcResultTxt.setText(spendingSum);
                searchListView.setOnItemClickListener((adapterView, view1, position, id) -> {
                    MainActivity mainActivity = ((MainActivity)getActivity());
                    if(mainActivity != null) {
                        Intent intent = new Intent(getActivity(), AddBudgetTracker.class);
                        intent.putExtra(AddBudgetTracker.EXTRA_DATA, (BudgetTrackerSpending)searchListView.getItemAtPosition(position));
                        startActivity(intent);

                    }
                });
            }
        });

        return view;
    }
}