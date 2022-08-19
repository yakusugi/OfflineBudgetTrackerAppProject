package com.myproject.offlinebudgettrackerappproject;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.myproject.offlinebudgettrackerappproject.databinding.ActivityMainBinding;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerSpendingAlias;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerSpendingAliasViewModel;
import com.myproject.offlinebudgettrackerappproject.util.Callback;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StatsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StatsFragment extends Fragment {

    private BudgetTrackerSpendingAliasViewModel budgetTrackerSpendingAliasViewModel;
    RadioGroup radioGroup;
    RadioButton radioStoreButton;
    EditText searchName;
    EditText dateFrom;
    EditText dateTo;
    Button searchBtn;
    ActivityMainBinding activityMainBinding;
    List<BudgetTrackerSpendingAlias> spendingAliasList;
    PieChart pieChart;
    ArrayList<PieEntry> pieEntries;
//    TextView currentCurrencyTv;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public StatsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StatsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StatsFragment newInstance(String param1, String param2) {
        StatsFragment fragment = new StatsFragment();
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
        View view = inflater.inflate(R.layout.fragment_stats, container, false);

        radioGroup = (RadioGroup) view.findViewById(R.id.stats_radio_group);
        radioStoreButton = (RadioButton) view.findViewById(R.id.stats_radio_store_name);
        searchName = (EditText) view.findViewById(R.id.stats_search_name);
        dateFrom = (EditText) view.findViewById(R.id.stats_search_date_from_txt);
        dateTo = (EditText) view.findViewById(R.id.stats_search_date_to_txt);
        searchBtn = (Button) view.findViewById(R.id.stats_search_btn);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        pieChart = (PieChart) view.findViewById(R.id.stats_pie_chart);
        budgetTrackerSpendingAliasViewModel = new ViewModelProvider(requireActivity()).get(BudgetTrackerSpendingAliasViewModel.class);

        pieEntries = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        dateFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        String date = year + "-" + month + "-" + dayOfMonth;
                        dateFrom.setText(date);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        dateTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        String date = year + "-" + month + "-" + dayOfMonth;
                        dateTo.setText(date);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("noifuji", "searchBtn.setOnClickListener.onClick");
                String searchKey = searchName.getText().toString();
                String searchDateFrom = dateFrom.getText().toString();
                String searchDateTo = dateTo.getText().toString();
                if (radioGroup.getCheckedRadioButtonId() == R.id.stats_radio_store_name) {
                    deleteAliasSpendingTable(() ->
                            deleteSequence(() ->
                                    insertStoreDataSpendingAlias(searchDateFrom, searchDateTo, searchKey, ()->storePieChartShow())));

                } else {
//                    spdBool = true;
//                    vatRate = Double.parseDouble(enterVatRate.getText().toString());
//                    price = price * vatRate;
                }
//                String notes = enterNotes.getText().toString();
//                BudgetTrackerSpending budgetTrackerSpending = new BudgetTrackerSpending(date, storeName, productName, productType, price, spdBool, vatRate, notes);
//                budgetTrackerSpendingViewModel.insert(budgetTrackerSpending);
                //getActivity().finish();
            }
        });

        return view;
    }

    private void deleteAliasSpendingTable(Callback callback) {
        BudgetTrackerSpendingAliasViewModel.deleteAllSpendingAlias(callback);
    }

    private void deleteSequence(Callback callback) {
        BudgetTrackerSpendingAliasViewModel.deleteSequence(callback);
    }

    private void insertStoreDataSpendingAlias(String dateFrom, String dateTo, String storeName, Callback callback) {
        BudgetTrackerSpendingAliasViewModel.insertStoreName(dateFrom, dateTo, storeName, callback);
    }

    private void storePieChartShow() {
        budgetTrackerSpendingAliasViewModel.getAllBudgetTrackerSpendingAliasList((spendingAliasList) -> {
            if (spendingAliasList == null) {
                Log.e("", "Error");
                return;
            }

            new Handler(Looper.getMainLooper())
                    .post(() -> {
                        pieEntries = new ArrayList<PieEntry>();
                        for (BudgetTrackerSpendingAlias budgetTrackerSpendingAlias : (List<BudgetTrackerSpendingAlias>)spendingAliasList) {
                            float value = (float) (budgetTrackerSpendingAlias.getAliasPercentage());
                            String productType = budgetTrackerSpendingAlias.getProductTypeAlias();
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
                    });

        });

    }
}