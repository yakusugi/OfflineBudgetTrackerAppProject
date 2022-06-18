package com.myproject.offlinebudgettrackerappproject;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.myproject.offlinebudgettrackerappproject.adapter.DateListViewAdapter;
import com.myproject.offlinebudgettrackerappproject.databinding.ActivityMainBinding;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTracker;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DateFragment extends Fragment {

    private static final int RESULT_OK = -1;
    BudgetTrackerViewModel budgetTrackerViewModel;
    RadioGroup radioGroup;
    RadioButton radioButton;
    private ListView dateListView;
    private List<BudgetTracker> budgetTrackerList;
    ActivityMainBinding activityMainBinding;
    public static final String DATE_FRAGMENT_ID = "date_fragment_id";
    List<BudgetTracker> radioStoreNameLists;
    String storeName;
    String date1;
    String date2;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DateFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DateFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DateFragment newInstance(String param1, String param2) {
        DateFragment fragment = new DateFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_date, container, false);
        radioGroup = (RadioGroup) view.findViewById(R.id.radio_group);
        dateListView = (ListView) view.findViewById(R.id.date_listview);
        DateListViewAdapter dateListViewAdapter;

        EditText radioSearchName = (EditText) view.findViewById(R.id.radio_search_name);
        EditText radioSearchDateFrom = (EditText) view.findViewById(R.id.radio_search_date_from_txt);
        EditText radioSearchDateTo = (EditText) view.findViewById(R.id.radio_search_date_to_txt);
        Button radioSearchBtn = (Button) view.findViewById(R.id.radio_search_btn);
        TextView searchCalcResultTxt = (TextView) view.findViewById(R.id.radio_search_calc_result_txt);

        BaseAdapter adapter = new DateListViewAdapter(getActivity(), budgetTrackerList);

        dateListViewAdapter = new DateListViewAdapter(getActivity(), 1);
        dateListView.setAdapter(dateListViewAdapter);

        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        radioSearchDateFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        String date = year + "-" + month + "-" + dayOfMonth;
                        radioSearchDateFrom.setText(date);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        radioSearchDateTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        String date = year + "-" + month + "-" + dayOfMonth;
                        radioSearchDateTo.setText(date);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        radioSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                storeName = radioSearchName.getText().toString();
                date1 = radioSearchDateFrom.getText().toString();
                date2 = radioSearchDateTo.getText().toString();
                budgetTrackerViewModel = new ViewModelProvider(requireActivity()).get(BudgetTrackerViewModel.class);
                radioStoreNameLists = budgetTrackerViewModel.getRadioStoreNameLists(storeName, date1, date2);
                DateListViewAdapter dateListViewAdapter = new DateListViewAdapter(getActivity(), radioStoreNameLists);
                dateListView.setAdapter(dateListViewAdapter);
                String calcSumStr = String.valueOf(budgetTrackerViewModel.getDateStoreSum(storeName, date1, date2));
                searchCalcResultTxt.setText(calcSumStr);

            }
        });

        //              Todo  2022/04/10 Tapped modified
        dateListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String date = adapterView.getItemAtPosition(position).toString();
                List<BudgetTracker> budgetListItems = radioStoreNameLists;
                int intId = (int) id;
                BudgetTracker dateItemId = budgetListItems.get(intId);
                Intent dateFragmentIntent = new Intent(getActivity(), NewBudgetTracker.class);
                dateFragmentIntent.putExtra(DATE_FRAGMENT_ID, dateItemId.getId());
                startActivityForResult(dateFragmentIntent, 1);

                Log.d("TAG-June", "onItemClick June02: " + date);
            }
        });


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            DateListViewAdapter dateListViewAdapter;
            List<BudgetTracker> radioStoreNameLists;
            List<BudgetTracker> radioProductNameLists;
            List<BudgetTracker> radioProductTypeLists;
            String date1;
            String date2;
            BudgetTracker budgetTracker;
            String calcSumStr;

            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId) {
                    case R.id.radio_store_name:
                        radioSearchBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String storeName = radioSearchName.getText().toString();
                                date1 = radioSearchDateFrom.getText().toString();
                                date2 = radioSearchDateTo.getText().toString();
                                budgetTrackerViewModel = new ViewModelProvider(requireActivity()).get(BudgetTrackerViewModel.class);
                                budgetTracker = new BudgetTracker(storeName, date1, date2);
                                radioStoreNameLists = budgetTrackerViewModel.getRadioStoreNameLists(storeName, date1, date2);
                                dateListViewAdapter = new DateListViewAdapter(getActivity(), radioStoreNameLists);
                                dateListView.setAdapter(dateListViewAdapter);
                                calcSumStr = String.valueOf(budgetTrackerViewModel.getDateStoreSum(storeName, date1, date2));
                                searchCalcResultTxt.setText(calcSumStr);

                            }
                        });

                        break;
                    case R.id.radio_product_name:
                        radioSearchBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String productName = radioSearchName.getText().toString();
                                date1 = radioSearchDateFrom.getText().toString();
                                date2 = radioSearchDateTo.getText().toString();
                                budgetTrackerViewModel = new ViewModelProvider(requireActivity()).get(BudgetTrackerViewModel.class);
                                budgetTracker = new BudgetTracker(productName, date1, date2);
                                radioProductNameLists = budgetTrackerViewModel.getRadioProductNameLists(productName, date1, date2);
                                dateListViewAdapter = new DateListViewAdapter(getActivity(), radioProductNameLists);
                                dateListView.setAdapter(dateListViewAdapter);
                                calcSumStr = String.valueOf(budgetTrackerViewModel.getDateProductNameSum(productName, date1, date2));
                                searchCalcResultTxt.setText(calcSumStr);

                            }
                        });
                        break;
                    case R.id.radio_product_type:
                        radioSearchBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String productType = radioSearchName.getText().toString();
                                date1 = radioSearchDateFrom.getText().toString();
                                date2 = radioSearchDateTo.getText().toString();
                                budgetTrackerViewModel = new ViewModelProvider(requireActivity()).get(BudgetTrackerViewModel.class);
                                budgetTracker = new BudgetTracker(productType, date1, date2);
                                radioProductTypeLists = budgetTrackerViewModel.getRadioProductTypeLists(productType, date1, date2);
                                dateListViewAdapter = new DateListViewAdapter(getActivity(), radioProductTypeLists);
                                dateListView.setAdapter(dateListViewAdapter);
                                calcSumStr = String.valueOf(budgetTrackerViewModel.getDateProductTypeSum(productType, date1, date2));
                                searchCalcResultTxt.setText(calcSumStr);

                            }
                        });
                        break;
                }


            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                int result = data.getIntExtra("result", 0);
                radioStoreNameLists = budgetTrackerViewModel.getRadioStoreNameLists(storeName, date1, date2);
                DateListViewAdapter dateListViewAdapter = new DateListViewAdapter(getActivity(), radioStoreNameLists);
                dateListView.setAdapter(dateListViewAdapter);
            }
        }
    }

}