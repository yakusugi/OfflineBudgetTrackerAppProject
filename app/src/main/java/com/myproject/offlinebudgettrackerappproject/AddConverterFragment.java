package com.myproject.offlinebudgettrackerappproject;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.myproject.offlinebudgettrackerappproject.adapter.BankNameSpinnerAdapter;
import com.myproject.offlinebudgettrackerappproject.adapter.CurrencySpinnerAdapter;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerBanking;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerBankingViewModel;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerConverter;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerConverterViewModel;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerSpending;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerSpendingViewModel;
import com.myproject.offlinebudgettrackerappproject.model.Currency;
import com.myproject.offlinebudgettrackerappproject.util.CurrencyConverterAPIs;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddConverterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddConverterFragment extends Fragment {

    EditText enterDate, enterStoreName, enterProductName, enterProductType, enterVatRate, enterNotes, originalCurrencyPrice;
    TextView targetCurrencyPrice;
    Button saveButton, updateButton, deleteButton, currencyConverterButton;
    SharedPreferences sharedPreferences;
    RadioGroup radioGroup;
    Spinner originalCurrencySpinner, targetCurrencySpinner, addConverterBankSpinner;
    private static final String PREF_CURRENCY_FILENAME = "CURRENCY_SHARED";
    private static final String PREF_CURRENCY_VALUE = "currencyValue";
    private String spinnerTargetCurrencyText;
    private String spinnerText;
    private List<BudgetTrackerBanking> bankList;
    private BudgetTrackerBankingViewModel budgetTrackerBankingViewModel;
    private ArrayList<BudgetTrackerBanking> bankArrayList;
    String foreignCurrencyString;
    String yourCurrencyString;
    private String convertedCalcResult;
    private String convertedDate;
    double vatRate;
    Boolean convertBool = false;
    double vatCalcPrice;
    Double foreignCurrencyPrice = 0.0;
    double convertedYourCurrency;
    BudgetTrackerSpendingViewModel budgetTrackerSpendingViewModel;
    BudgetTrackerConverterViewModel budgetTrackerConverterViewModel;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddConverterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddConverterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddConverterFragment newInstance(String param1, String param2) {
        AddConverterFragment fragment = new AddConverterFragment();
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
        View view = inflater.inflate(R.layout.fragment_add_converter, container, false);

        enterDate = (EditText) view.findViewById(R.id.add_converter_date);
        enterStoreName = (EditText) view.findViewById(R.id.add_converter_store_name);
        enterProductName = (EditText) view.findViewById(R.id.add_converter_product_name);
        enterProductType = (EditText) view.findViewById(R.id.add_converter_product_type);
        enterVatRate = (EditText) view.findViewById(R.id.add_converter_vat_rate);
        enterNotes = (EditText) view.findViewById(R.id.add_converter_notes);
        radioGroup = (RadioGroup) view.findViewById(R.id.add_converter_radio_group);
        originalCurrencySpinner = (Spinner) view.findViewById(R.id.original_currency_spinner);
        originalCurrencyPrice = (EditText) view.findViewById(R.id.original_currency_price);
        targetCurrencySpinner = (Spinner) view.findViewById(R.id.target_currency_spinner);
        targetCurrencyPrice = (TextView) view.findViewById(R.id.target_currency_price);
        saveButton = (Button) view.findViewById(R.id.add_converter_save_button);
        currencyConverterButton = (Button) view.findViewById(R.id.currency_converter_btn);
        updateButton = (Button) view.findViewById(R.id.add_converter_update_btn);
        deleteButton = (Button) view.findViewById(R.id.add_converter_delete_btn);


        addConverterBankSpinner = (Spinner) view.findViewById(R.id.add_converter_bank_spinner);

        sharedPreferences = getActivity().getSharedPreferences(PREF_CURRENCY_FILENAME, 0);

        budgetTrackerSpendingViewModel = new ViewModelProvider(requireActivity()).get(BudgetTrackerSpendingViewModel.class);
        budgetTrackerBankingViewModel = new ViewModelProvider(requireActivity()).get(BudgetTrackerBankingViewModel.class);
        budgetTrackerConverterViewModel = new ViewModelProvider(requireActivity()).get(BudgetTrackerConverterViewModel.class);


        bankList = budgetTrackerBankingViewModel.getBankViewModelBankList();
        bankArrayList = new ArrayList<BudgetTrackerBanking>(bankList);

        BankNameSpinnerAdapter bankNameSpinnerAdapter = new BankNameSpinnerAdapter(getActivity(), R.layout.income_spinner_adapter,
                bankArrayList);

        addConverterBankSpinner.setAdapter(bankNameSpinnerAdapter);
        addConverterBankSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                BudgetTrackerBanking budgetTrackerBanking = (BudgetTrackerBanking) addConverterBankSpinner.getSelectedItem();
                spinnerText = budgetTrackerBanking.getBankName();
                Log.d("TAG_Spinner", "onItemSelected: " + spinnerText);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                spinnerText = null;
            }
        });

        int currentCurrencyNum = sharedPreferences.getInt(PREF_CURRENCY_VALUE, 0);
        Currency currency = Currency.getCurrencyArrayList().get(currentCurrencyNum);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        enterDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        String date = year + "-" + month + "-" + dayOfMonth;
                        enterDate.setText(date);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        targetCurrencyPrice.setCompoundDrawablesWithIntrinsicBounds(currency.getCurrencyImage(), 0, 0, 0);

        CurrencySpinnerAdapter currencySpinnerAdapter = new CurrencySpinnerAdapter(getActivity(), R.layout.currency_spinner_adopter,
                Currency.getCurrencyArrayList());

        originalCurrencySpinner.setAdapter(currencySpinnerAdapter);
        targetCurrencySpinner.setAdapter(currencySpinnerAdapter);

        originalCurrencySpinner.setSelection(sharedPreferences.getInt(PREF_CURRENCY_VALUE, 0));
        originalCurrencySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

        currencyConverterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //for currency conversion
                Currency foreignCurrency = (Currency) originalCurrencySpinner.getSelectedItem();
                foreignCurrencyString = foreignCurrency.getCurrencyString();

                foreignCurrencyPrice = Double.parseDouble(originalCurrencyPrice.getText().toString());
                Currency yourCurrency = (Currency) targetCurrencySpinner.getSelectedItem();
                yourCurrencyString = yourCurrency.getCurrencyString();
                convertedDate = enterDate.getText().toString();

                Double convertedCalcResultDouble = 0.0;

                //for saving data to the db (bankName)

                CurrencyConverterAPIs currencyConverterAPIs = new CurrencyConverterAPIs(getActivity());
                try {
                    convertedCalcResult = currencyConverterAPIs.currencyDateConverter(foreignCurrencyString, yourCurrencyString, foreignCurrencyPrice, convertedDate);
                    targetCurrencyPrice.setText(convertedCalcResult);
                } catch (IOException e) {
                    e.printStackTrace();
                }

//                //for saving data to the db (convertedResult String -> Double)
//                convertedCalcResultDouble = Double.parseDouble(convertedCalcResult);
//
//                //for saving data to the db (date)
//                Date todayDate = Calendar.getInstance().getTime();
//                DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
//                String strDate = dateFormat.format(todayDate);
//
//                currencyConverter = new CurrencyConverter(strDate, selectedBankName, currencyCurrencyStr, bankBalanceNum, targetCurrency, convertedCalcResultDouble);
//                currencyConverterViewModel.insert(currencyConverter);

            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String date = enterDate.getText().toString();
                String storeName = enterStoreName.getText().toString();
                String productName = enterProductName.getText().toString();
                String productType = enterProductType.getText().toString();

                if (radioGroup.getCheckedRadioButtonId() == R.id.add_converter_no) {
                    vatRate = 0.0;
                    enterVatRate.setEnabled(convertBool);
                } else {
                    convertBool = true;
                    enterVatRate.setEnabled(true);
                    vatRate = Double.parseDouble(enterVatRate.getText().toString());
                    vatCalcPrice = vatCalcPrice * vatRate;
                }

                String notes = enterNotes.getText().toString();

                Currency foreignCurrency = (Currency) originalCurrencySpinner.getSelectedItem();
                foreignCurrencyString = foreignCurrency.getCurrencyString();
                vatCalcPrice = Double.parseDouble(originalCurrencyPrice.getText().toString());

                Currency yourCurrency = (Currency) targetCurrencySpinner.getSelectedItem();
                yourCurrencyString = yourCurrency.getCurrencyString();

                convertedYourCurrency = Double.parseDouble(convertedCalcResult);

//                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//                LocalDateTime today = LocalDateTime.now();
//                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//                String strDate = dateFormat.format(today);

                LocalDateTime today = LocalDateTime.now();
//                Date today = Calendar.getInstance().getTime();
                DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
                String strDate = dateFormat.format(today);


                BudgetTrackerSpending budgetTrackerSpending = new BudgetTrackerSpending(date, storeName, productName, productType, convertedYourCurrency, convertBool, vatRate, notes);
                budgetTrackerSpendingViewModel.insert(budgetTrackerSpending);
                if (spinnerText != null) {
                    double spendingNum = budgetTrackerSpending.getPrice();
                    budgetTrackerBankingViewModel.updateSubtraction(spendingNum, spinnerText);
                } else {
                    //Todo Need to make this a snackbar
                    Toast.makeText(getActivity(), "Insert a bank record", Toast.LENGTH_SHORT).show();
                }

                BudgetTrackerConverter budgetTrackerConverter = new BudgetTrackerConverter(date, storeName, productName, productType, convertBool,  vatRate, notes, foreignCurrencyString, vatCalcPrice, yourCurrencyString, convertedYourCurrency, strDate);
                budgetTrackerConverterViewModel.insert(budgetTrackerConverter);
//                if (spinnerText != null) {
//                    double spendingNum = budgetTrackerConverter.getTargetCurrencyPrice();
//                    budgetTrackerBankingViewModel.updateSubtraction(spendingNum, spinnerText);
//                } else {
//                    //Todo Need to make this a snackbar
//                    Toast.makeText(getActivity(), "Insert a bank record", Toast.LENGTH_SHORT).show();
//                }

                getActivity().finish();
            }
        });


        return view;
    }
}