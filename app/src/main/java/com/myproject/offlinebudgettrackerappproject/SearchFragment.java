package com.myproject.offlinebudgettrackerappproject;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.myproject.offlinebudgettrackerappproject.adapter.SearchListViewAdapter;
import com.myproject.offlinebudgettrackerappproject.databinding.ActivityMainBinding;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerSpending;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerSpendingViewModel;
import com.myproject.offlinebudgettrackerappproject.model.Currency;
import com.myproject.offlinebudgettrackerappproject.model.ItemSpendingViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment {

    private static final String PREF_CURRENCY_FILENAME = "CURRENCY_SHARED";
    private static final String PREF_CURRENCY_VALUE = "currencyValue";
    private static final int RESULT_OK = -1;
    BudgetTrackerSpendingViewModel budgetTrackerSpendingViewModel;

    List<BudgetTrackerSpending> searchStoreNameLists;
    List<BudgetTrackerSpending> searchProductNameLists;
    List<BudgetTrackerSpending> searchProductTypeLists;
    BudgetTrackerSpending budgetTrackerSpending;
    SearchListViewAdapter searchListViewAdapter;
    private ListView searchListView;
    RadioGroup radioGroup;
    RadioButton radioButton;
    private List<BudgetTrackerSpending> budgetTrackerSpendingList;
    ActivityMainBinding activityMainBinding;
    //todo: when an item in the listview is tapped
    public static final String SEARCH_FRAGMENT_ID = "search_fragment_id";
    String storeName;
    String productName;
    String productType;
    String dateFrom;
    String dateTo;
    SharedPreferences sharedPreferences;
    String calcSumStr;
    TextView searchCalcResultTxt;
    ItemSpendingViewModel itemSpendingViewModel;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
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
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        radioGroup = (RadioGroup) view.findViewById(R.id.search_radio_group);
        searchListView = (ListView) view.findViewById(R.id.search_listview);

        EditText searchName = (EditText) view.findViewById(R.id.search_name);
        EditText searchDateFrom = (EditText) view.findViewById(R.id.search_date_from_txt);
        EditText searchDateTo = (EditText) view.findViewById(R.id.search_date_to_txt);
        Button searchBtn = (Button) view.findViewById(R.id.search_btn);
        searchCalcResultTxt = (TextView) view.findViewById(R.id.search_calc_result_txt);

        searchListViewAdapter = new SearchListViewAdapter(getActivity(), 1);
        searchListView.setAdapter(searchListViewAdapter);

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
                    transferStore(searchKey, dateFrom, dateTo);
                } else if (radioGroup.getCheckedRadioButtonId() == R.id.search_radio_product_name) {
                    transferProductName(searchKey, dateFrom, dateTo);
                } else if (radioGroup.getCheckedRadioButtonId() == R.id.search_radio_product_type) {
                    transferProductType(searchKey, dateFrom, dateTo);
                }
            }
        });

        return view;
    }

    private void transferStore(String searchKey, String dateFrom, String dateTo) {
        budgetTrackerSpending = new BudgetTrackerSpending(searchKey, dateFrom, dateTo);
        searchStoreNameLists = budgetTrackerSpendingViewModel.getSearchStoreNameLists(searchKey, dateFrom, dateTo);
        searchListViewAdapter = new SearchListViewAdapter(getActivity(), searchStoreNameLists);
        searchListView.setAdapter(searchListViewAdapter);
        calcSumStr = String.valueOf(budgetTrackerSpendingViewModel.getSearchStoreSum(searchKey, dateFrom, dateTo));
        searchCalcResultTxt.setText(calcSumStr);

        searchListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                List<BudgetTrackerSpending> budgetSpendingListItems = searchStoreNameLists;
                int intId = (int) id;
                BudgetTrackerSpending storeItemId = budgetSpendingListItems.get(intId);

                //new ItemSpendingViewModel
//                itemSpendingViewModel = new ViewModelProvider(requireActivity()).get(ItemSpendingViewModel.class);

                //set id of the particular record and pass it to ViewModel
//                itemSpendingViewModel.setData(Integer.parseInt(String.valueOf(id)));

                passData(storeItemId.getId());


                //fragment intent
//                Fragment fragment = new AddSpendingFragment();
//                Bundle result = new Bundle();
//                result.putInt("storeId", storeItemId.getId());
//                fragment.setArguments(result);
//                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//                fragmentManager.setFragmentResult("shop_search_id", result);
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.main_container, fragment);
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();
            }
        });
    }

    private void transferProductName(String searchKey, String dateFrom, String dateTo) {
        budgetTrackerSpending = new BudgetTrackerSpending(searchKey, dateFrom, dateTo);
        searchProductNameLists = budgetTrackerSpendingViewModel.getSearchProductNameLists(searchKey, dateFrom, dateTo);
        searchListViewAdapter = new SearchListViewAdapter(getActivity(), searchProductNameLists);
        searchListView.setAdapter(searchListViewAdapter);
        calcSumStr = String.valueOf(budgetTrackerSpendingViewModel.getSearchProductSum(searchKey, dateFrom, dateTo));
        searchCalcResultTxt.setText(calcSumStr);

        searchListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String date = adapterView.getItemAtPosition(position).toString();
                List<BudgetTrackerSpending> budgetSpendingListItems = searchProductNameLists;
                int intId = (int) id;
                BudgetTrackerSpending storeItemId = budgetSpendingListItems.get(intId);

                //fragment intent
                Fragment fragment = new AddSpendingFragment();
                Bundle result = new Bundle();
                result.putInt("storeId", storeItemId.getId());
                fragment.setArguments(result);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.setFragmentResult("shop_search_id", result);
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.main_container, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }

    private void transferProductType(String searchKey, String dateFrom, String dateTo) {
        budgetTrackerSpending = new BudgetTrackerSpending(searchKey, dateFrom, dateTo);
        searchProductTypeLists = budgetTrackerSpendingViewModel.getSearchProductTypeLists(searchKey, dateFrom, dateTo);
        searchListViewAdapter = new SearchListViewAdapter(getActivity(), searchProductTypeLists);
        searchListView.setAdapter(searchListViewAdapter);
        calcSumStr = String.valueOf(budgetTrackerSpendingViewModel.getSearchProductTypeSum(searchKey, dateFrom, dateTo));
        searchCalcResultTxt.setText(calcSumStr);

        searchListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String date = adapterView.getItemAtPosition(position).toString();
                List<BudgetTrackerSpending> budgetSpendingListItems = searchProductTypeLists;
                int intId = (int) id;
                BudgetTrackerSpending storeItemId = budgetSpendingListItems.get(intId);

                //fragment intent
                Fragment fragment = new AddSpendingFragment();
                Bundle result = new Bundle();
                result.putInt("storeId", storeItemId.getId());
                fragment.setArguments(result);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.setFragmentResult("shop_search_id", result);
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.main_container, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }


    public interface OnDataPass {
        public void onDataPass(Integer data);
    }

    OnDataPass dataPasser;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        dataPasser = (OnDataPass) context;
    }

    public void passData(Integer data) {
        dataPasser.onDataPass(data);
    }

}