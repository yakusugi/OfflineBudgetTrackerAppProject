package com.myproject.offlinebudgettrackerappproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import com.myproject.offlinebudgettrackerappproject.adapter.BankNameSpinnerAdapter;
import com.myproject.offlinebudgettrackerappproject.adapter.CurrencyConverterListViewAdapter;
import com.myproject.offlinebudgettrackerappproject.adapter.CurrencySpinnerAdapter;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerBanking;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerBankingViewModel;
import com.myproject.offlinebudgettrackerappproject.model.Currency;
import com.myproject.offlinebudgettrackerappproject.model.CurrencyConverter;
import com.myproject.offlinebudgettrackerappproject.model.CurrencyConverterViewModel;
import com.myproject.offlinebudgettrackerappproject.util.CurrencyConverterAPIs;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ConverterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConverterFragment extends Fragment {
    private Spinner bankNameSpinner, targetCurrencySpinner;
    TextView bankBalance, currentCurrency, convertedResult;
    Button convertButton, saveButton;
    SharedPreferences sharedPreferences;
    private static final String PREF_CURRENCY_FILENAME = "CURRENCY_SHARED";
    private static final String PREF_CURRENCY_VALUE = "currencyValue";
    private List<BudgetTrackerBanking> bankList;
    private BudgetTrackerBankingViewModel budgetTrackerBankingViewModel;
    private ArrayList<BudgetTrackerBanking> bankArrayList;
    private String spinnerText;
    private String spinnerTargetCurrencyText;
    private Double bankBalanceNum;
    public String targetCurrencyString;
    public String bankNameString;
    public String currentCurrencyString;
    private String convertedCalcResult;
    CurrencyConverterViewModel currencyConverterViewModel;
    CurrencyConverter currencyConverter;
    Double convertedCalcResultDouble = 0.0;
    LiveData<List<CurrencyConverter>> searchLists;
    CurrencyConverterListViewAdapter currencyConverterListViewAdapter;
    private ListView listView;
    Context context;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ConverterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ConverterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ConverterFragment newInstance(String param1, String param2) {
        ConverterFragment fragment = new ConverterFragment();
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
        View view = inflater.inflate(R.layout.fragment_converter, container, false);
        context = container.getContext();

        bankNameSpinner = (Spinner) view.findViewById(R.id.converter_bank_name_spinner);
        targetCurrencySpinner = (Spinner) view.findViewById(R.id.converter_target_currency_spinner);
        bankBalance = (TextView) view.findViewById(R.id.converter_bank_balance_tv);
        currentCurrency = (TextView) view.findViewById(R.id.converter_current_currency_spinner);
        convertedResult = (TextView) view.findViewById(R.id.search_calc_result_txt);
        convertButton = (Button) view.findViewById(R.id.convert_btn);
        saveButton = (Button) view.findViewById(R.id.convert_save_btn);
        sharedPreferences = getActivity().getSharedPreferences(PREF_CURRENCY_FILENAME, 0);

        //選択された通貨表示
        int currentCurrencyNum = sharedPreferences.getInt(PREF_CURRENCY_VALUE, 0);
        Currency currency = Currency.getCurrencyArrayList().get(currentCurrencyNum);

        bankBalance.setCompoundDrawablesWithIntrinsicBounds(currency.getCurrencyImage(), 0, 0, 0);
        currentCurrency.setCompoundDrawablesWithIntrinsicBounds(currency.getCurrencyImage(), 0, 0, 0);
        currentCurrency.setText(currency.getCurrencyString());
//        searchLists = currencyConverterViewModel.getAllCurrencyConverterData();
//        currencyConverterViewModel.getAllCurrencyConverterData().observe(getActivity(), contacts -> {
//            currencyConverterListViewAdapter = new CurrencyConverterListViewAdapter(contacts,context);
//            listView.setAdapter(currencyConverterListViewAdapter);
//
//        });

        budgetTrackerBankingViewModel = new ViewModelProvider(requireActivity()).get(BudgetTrackerBankingViewModel.class);
        bankList = budgetTrackerBankingViewModel.getBankViewModelBankList();
        bankArrayList = new ArrayList<BudgetTrackerBanking>(bankList);
        BankNameSpinnerAdapter bankNameSpinnerAdapter = new BankNameSpinnerAdapter(getActivity(), R.layout.income_spinner_adapter,
                bankArrayList);

        bankNameSpinner.setAdapter(bankNameSpinnerAdapter);
        bankNameSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                BudgetTrackerBanking budgetTrackerBanking = (BudgetTrackerBanking) bankNameSpinner.getSelectedItem();
                spinnerText = budgetTrackerBanking.getBankName();
                bankBalance.setText(String.valueOf(bankBalanceNum = budgetTrackerBanking.getBankBalance()));
                Log.d("TAG_Spinner", "onItemSelected: " + spinnerText);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                spinnerText = null;
            }
        });

        currencyConverterViewModel = new ViewModelProvider(requireActivity()).get(CurrencyConverterViewModel.class);

        CurrencySpinnerAdapter currencySpinnerAdapter = new CurrencySpinnerAdapter(getActivity(), R.layout.currency_spinner_adopter,
                Currency.getCurrencyArrayList());
        targetCurrencySpinner.setAdapter(currencySpinnerAdapter);

        targetCurrencySpinner.setSelection(sharedPreferences.getInt(PREF_CURRENCY_VALUE, 0));
        targetCurrencySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                Currency currency = (Currency) targetCurrencySpinner.getSelectedItem();
                spinnerTargetCurrencyText = currency.getCurrency();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                spinnerText = null;
            }
        });

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //for currency conversion
                Currency currency = (Currency) targetCurrencySpinner.getSelectedItem();
                targetCurrencyString = currency.getCurrencyString();
                String currentCurrencyString = currentCurrency.getText().toString();
                Double bankBalanceNum = Double.parseDouble(bankBalance.getText().toString());


                //for saving data to the db (bankName)


                Log.d("TAG0904", "currentCurrency: " + currentCurrencyString);
                Log.d("TAG0904", "targetCurrency: " + targetCurrencyString);
                Log.d("TAG0904", "bankBalance: " + bankBalanceNum);

                CurrencyConverterAPIs currencyConverterAPIs = new CurrencyConverterAPIs(getActivity());
                try {
                    convertedCalcResult = currencyConverterAPIs.currencyConverter(currentCurrencyString, targetCurrencyString, bankBalanceNum);
                    convertedResult.setText(convertedCalcResult);
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //for saving data to the db (convertedResult String -> Double)
                convertedCalcResultDouble = Double.parseDouble(convertedCalcResult);
                String selectedBankName = bankNameSpinner.getSelectedItem().toString();
                String targetCurrencyStr = targetCurrencySpinner.getSelectedItem().toString();
                String currencyCurrencyStr = currency.getCurrencyString();
                Double bankBalanceNum = Double.parseDouble(bankBalance.getText().toString());

                //for saving data to the db (date)
                Date todayDate = Calendar.getInstance().getTime();
                DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
                String strDate = dateFormat.format(todayDate);

                currencyConverter = new CurrencyConverter(strDate, selectedBankName, currencyCurrencyStr, bankBalanceNum, targetCurrencyStr, convertedCalcResultDouble);
                currencyConverterViewModel.insert(currencyConverter);

            }
        });

//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
//                List<CurrencyConverter> currencyConverterList = (List<CurrencyConverter>) searchLists;
//                int intId = (int) id;
//                CurrencyConverter itemId = currencyConverterList.get(intId);
//                Bundle result = new Bundle();
//                result.putInt("itemId", itemId.getId());
//
//
//            }
//        });



        return view;
    }


}