package com.myproject.offlinebudgettrackerappproject;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.myproject.offlinebudgettrackerappproject.databinding.ActivityMainBinding;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTracker;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerAlias;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerAliasViewModel;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerViewModel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    private BudgetTrackerViewModel budgetTrackerViewModel;
    private BudgetTrackerAliasViewModel budgetTrackerAliasViewModel;
    RadioGroup radioGroup;
    RadioButton radioStoreButton;
    EditText searchName;
    EditText dateFromText;
    EditText dateToText;
    TextView calcResultTxt;
    Button searchBtn;
    ActivityMainBinding activityMainBinding;
    List<BudgetTrackerAlias> homeRadioList;
    PieChart pieChart;
    ArrayList<PieEntry> pieEntries;
    TextView currentCurrencyTv;
    SharedPreferences sharedPreferences;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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

            budgetTrackerViewModel = new ViewModelProvider.AndroidViewModelFactory(HomeFragment.this
                    .getActivity().getApplication())
                    .create(BudgetTrackerViewModel.class);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        radioGroup = (RadioGroup) view.findViewById(R.id.home_radio_group);
        radioStoreButton = (RadioButton) view.findViewById(R.id.home_radio_store_name);
        searchName = (EditText) view.findViewById(R.id.home_radio_search_name);
        dateFromText = (EditText) view.findViewById(R.id.home_radio_search_date_from_txt);
        dateToText = (EditText) view.findViewById(R.id.home_radio_search_date_to_txt);
        searchBtn = (Button) view.findViewById(R.id.home_radio_search_btn);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        pieChart = (PieChart) view.findViewById(R.id.pie_chart);
        budgetTrackerAliasViewModel = new ViewModelProvider(requireActivity()).get(BudgetTrackerAliasViewModel.class);
        currentCurrencyTv = (TextView) view.findViewById(R.id.currency_tv);
        sharedPreferences = getActivity().getSharedPreferences("CURRENCY_SHARED",0);

        //選択された通貨の設定
        String currentCurrency = sharedPreferences.getString("CURRENCY", "");
        String selectedCurrency = sharedPreferences.getString("SELECTED", "");
        String selectedCurrencyPath = sharedPreferences.getString("SELECTED_CURRENCY_SYMBOL", "");
        File vectorAsset = new File(selectedCurrencyPath);

        currentCurrencyTv.setText(currentCurrency);
        if (selectedCurrency == "US_Dollars") {
            currentCurrencyTv.setCompoundDrawablesWithIntrinsicBounds(Integer.parseInt(selectedCurrencyPath), 0, 0, 0);
        } else if (selectedCurrency == "JAPANESE_YEN") {
            currentCurrencyTv.setCompoundDrawablesWithIntrinsicBounds(Integer.parseInt(selectedCurrencyPath), 0, 0, 0);
        } else {
            currentCurrencyTv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_euro, 0, 0, 0);
        }

        pieEntries = new ArrayList<>();

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("TAG", "onClick: Alias Button Clicked");
                int radioId = radioGroup.getCheckedRadioButtonId();
                if (radioId == R.id.home_radio_store_name) {
                    Log.d("TAG", "onClick: Alias Button Clicked 222");
                }
            }
        });

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        dateFromText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        String date = year + "-" + month + "-" + dayOfMonth;
                        dateFromText.setText(date);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        dateToText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        String date = year + "-" + month + "-" + dayOfMonth;
                        dateToText.setText(date);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        //デフォルトでstore nameが選択背れている状態での検索処理
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("TAG", "onClick: Alias Button Clicked 111");
                String storeName = searchName.getText().toString();
                String dateFrom = dateFromText.getText().toString();
                String dateTo = dateToText.getText().toString();
                deleteAliasTable();
                deleteSequence();
                insertStoreDataAlias(dateFrom, dateTo, storeName);
                storePieChartShow();
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            String storeName;
            String productName;
            String productType;
            String dateFrom;
            String dateTo;
            BudgetTracker budgetTracker;
            String calcSumStr;

            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkId) {
                switch (checkId) {
                    case R.id.home_radio_store_name:
                        searchBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                storeName = searchName.getText().toString();
                                dateFrom = dateFromText.getText().toString();
                                dateTo = dateToText.getText().toString();
                                deleteAliasTable();
                                deleteSequence();
                                insertStoreDataAlias(dateFrom, dateTo, storeName);
                                storePieChartShow();
                            }
                        });
                        break;
                    case R.id.home_radio_product_name:
                        searchBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                productName = searchName.getText().toString();
                                dateFrom = dateFromText.getText().toString();
                                dateTo = dateToText.getText().toString();
                                deleteAliasTable();
                                deleteSequence();
                                insertProductNameDataAlias(dateFrom, dateTo, productName);
                                productNamePieChartShow();
                            }
                        });
                        break;
                    case R.id.home_radio_product_type:
                        searchBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                productType = searchName.getText().toString();
                                dateFrom = dateFromText.getText().toString();
                                dateTo = dateToText.getText().toString();
                                deleteAliasTable();
                                deleteSequence();
                                insertProductTypeDataAlias(dateFrom, dateTo, productType);
                                productTypePieChartShow();
                            }
                        });
                        break;
                }
            }
        });

        return view;

    }

    private void deleteAliasTable() {
        BudgetTrackerAliasViewModel.deleteAllAlias();
    }

    private void deleteSequence() {
        BudgetTrackerAliasViewModel.deleteSequence();
    }

    private void insertStoreDataAlias(String dateFrom, String dateTo, String storeName) {
        BudgetTrackerAliasViewModel.insert(dateFrom, dateTo, storeName);
    }

    private void storePieChartShow() {
        homeRadioList = budgetTrackerAliasViewModel.getAllBudgetTrackerAliasList();
        pieEntries = new ArrayList<PieEntry>();
        for (BudgetTrackerAlias budgetTrackerAlias : homeRadioList) {
            float value = (float) (budgetTrackerAlias.getProductTypePercentage());
            String productType = budgetTrackerAlias.getProductTypeAlias();
            PieEntry pieEntry = new PieEntry(value, productType);
            pieEntries.add(pieEntry);
        }

        PieDataSet pieDataSet = new PieDataSet(pieEntries, "Product Type Percentage");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setValueTextSize(20f);
        pieChart.notifyDataSetChanged();
        pieChart.invalidate();
        pieChart.setData(new PieData(pieDataSet));
        pieChart.animateXY(5000, 5000);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.getDescription().setEnabled(false);
    }

    private void insertProductNameDataAlias(String dateFrom, String dateTo, String productName) {
        BudgetTrackerAliasViewModel.insertProductName(dateFrom, dateTo, productName);
    }

    private void productNamePieChartShow() {
        homeRadioList = budgetTrackerAliasViewModel.getAllBudgetTrackerAliasList();
        pieEntries = new ArrayList<PieEntry>();
        for (BudgetTrackerAlias budgetTrackerAlias : homeRadioList) {
            float value = (float) (budgetTrackerAlias.getProductTypePercentage());
            String storeName = budgetTrackerAlias.getStoreNameAlias();
            PieEntry pieEntry = new PieEntry(value, storeName);
            pieEntries.add(pieEntry);
        }

        PieDataSet pieDataSet = new PieDataSet(pieEntries, "Store Percentage");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setValueTextSize(20f);
        pieChart.setData(new PieData(pieDataSet));
        pieChart.animateXY(5000, 5000);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.getDescription().setEnabled(false);
    }

    private void insertProductTypeDataAlias(String dateFrom, String dateTo, String productType) {
        BudgetTrackerAliasViewModel.insertProductType(dateFrom, dateTo, productType);
    }

    private void productTypePieChartShow() {
        homeRadioList = budgetTrackerAliasViewModel.getAllBudgetTrackerAliasList();
        pieEntries = new ArrayList<PieEntry>();
        for (BudgetTrackerAlias budgetTrackerAlias : homeRadioList) {
            float value = (float) (budgetTrackerAlias.getProductTypePercentage());
            String storeName = budgetTrackerAlias.getStoreNameAlias();
            PieEntry pieEntry = new PieEntry(value, storeName);
            pieEntries.add(pieEntry);
        }

        PieDataSet pieDataSet = new PieDataSet(pieEntries, "Store Percentage");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setValueTextSize(20f);
        pieChart.setData(new PieData(pieDataSet));
        pieChart.animateXY(5000, 5000);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.getDescription().setEnabled(false);
    }
}