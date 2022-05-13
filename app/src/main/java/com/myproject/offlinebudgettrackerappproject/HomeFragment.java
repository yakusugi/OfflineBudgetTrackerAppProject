package com.myproject.offlinebudgettrackerappproject;

import android.app.DatePickerDialog;
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
    RadioGroup radioHomeGroup;
    RadioButton radioHomeButton;
    EditText radioSearchHomeName;
    EditText radioSearchDateHomeFrom;
    EditText radioSearchDateHomeTo;
    TextView searchCalcResultHomeTxt;
    Button radioSearchHomeBtn;
    ActivityMainBinding activityMainBinding;
    List<BudgetTrackerAlias> homeRadioList;
    PieChart pieChart;

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

//            budgetTrackerViewModel.getAllBudgetTrackerLists().observe(this, budgetTrackers -> {
//                Log.d("TAG", "onCreate: " + budgetTrackers.get(0).getProductName());
//            });
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        radioHomeGroup = (RadioGroup) view.findViewById(R.id.home_radio_group);
        radioSearchHomeName = (EditText) view.findViewById(R.id.home_radio_search_name);
        radioSearchDateHomeFrom = (EditText) view.findViewById(R.id.home_radio_search_date_from_txt);
        radioSearchDateHomeTo = (EditText) view.findViewById(R.id.home_radio_search_date_to_txt);
        searchCalcResultHomeTxt = (TextView) view.findViewById(R.id.home_radio_search_calc_result_txt);
        radioSearchHomeBtn = (Button) view.findViewById(R.id.home_radio_search_btn);
//        radioSearchHomeBtn.setOnClickListener((View.OnClickListener) getActivity());
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        pieChart = (PieChart) view.findViewById(R.id.pie_chart);

        ArrayList<PieEntry> pieEntries = new ArrayList<>();

        radioSearchHomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("TAG", "onClick: Alias Button Clicked");
                int radioId = radioHomeGroup.getCheckedRadioButtonId();
                if (radioId == R.id.home_radio_store_name) {
                    Log.d("TAG", "onClick: Alias Button Clicked 222");
                }
            }
        });

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        radioSearchDateHomeFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        String date = year + "-" + month + "-" + dayOfMonth;
                        radioSearchDateHomeFrom.setText(date);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        radioSearchDateHomeTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        String date = year + "-" + month + "-" + dayOfMonth;
                        radioSearchDateHomeTo.setText(date);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        radioHomeGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            List<BudgetTracker> radioStoreNameLists;
            List<BudgetTracker> radioProductNameLists;
            List<BudgetTracker> radioProductTypeLists;
            String storeName;
            String date1;
            String date2;
            BudgetTracker budgetTracker;
            String calcSumStr;

            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkId) {
                switch (checkId) {
                    case R.id.home_radio_store_name:
                        Log.d("TAG", "onCheckedChanged: store chosen");
                        storeName = radioSearchHomeName.getText().toString();
                        date1 = radioSearchDateHomeFrom.getText().toString();
                        date2 = radioSearchDateHomeTo.getText().toString();
                        radioSearchHomeBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Log.d("TAG", "onClick: Alias Button Clicked 111");
                                BudgetTrackerAliasViewModel.deleteAllAlias();
                                BudgetTrackerAliasViewModel.insert(date1, date2, storeName);

                                budgetTrackerAliasViewModel = new ViewModelProvider(requireActivity()).get(BudgetTrackerAliasViewModel.class);
                                homeRadioList = budgetTrackerAliasViewModel.getAllBudgetTrackerAliasList();

                                for (BudgetTrackerAlias budgetTrackerAlias : homeRadioList) {
                                    float value = (float) (budgetTrackerAlias.getProductTypePercentage());
                                    String productType = budgetTrackerAlias.getProductTypeAlias();
                                    PieEntry pieEntry = new PieEntry(value, productType);
                                    pieEntries.add(pieEntry);
                                }

                                PieDataSet pieDataSet = new PieDataSet(pieEntries, "Product Type Percentage");
                                pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                                pieChart.setData(new PieData(pieDataSet));
                                pieChart.animateXY(5000, 5000);
                                pieChart.getDescription().setEnabled(false);
                            }
                        });
                        break;
                    case R.id.home_radio_product_name:
                        Log.d("TAG", "onCheckedChanged: product name chosen");
                        storeName = radioSearchHomeName.getText().toString();
                        date1 = radioSearchDateHomeFrom.getText().toString();
                        date2 = radioSearchDateHomeTo.getText().toString();
                        budgetTrackerAliasViewModel = new ViewModelProvider(requireActivity()).get(BudgetTrackerAliasViewModel.class);
                        budgetTracker = new BudgetTracker(storeName, date1, date2);
                        radioSearchHomeBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Log.d("TAG", "onClick: Alias Button Clicked");
                                BudgetTrackerAliasViewModel.insert(date1, date2, storeName);

                            }
                        });
                        break;
                }
            }
        });

        return view;

    }
}