package com.myproject.offlinebudgettrackerappproject;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
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
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.snackbar.Snackbar;
import com.myproject.offlinebudgettrackerappproject.adapter.BankNameSpinnerAdapter;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerBanking;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerBankingViewModel;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerSpending;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerSpendingViewModel;
import com.myproject.offlinebudgettrackerappproject.model.Currency;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddSpendingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddSpendingFragment extends Fragment {

    EditText enterDate, enterStoreName, enterProductName, enterProductType, enterPrice, enterVatRate, enterNotes;
    TextView expenseTitleTv, expenseSubTitleTv;
    RadioGroup radioGroup;
    Button saveButton, updateButton, deleteButton;
    SharedPreferences sharedPreferences;
    private static final String PREF_CURRENCY_FILENAME = "CURRENCY_SHARED";
    private static final String PREF_CURRENCY_VALUE = "currencyValue";
    BudgetTrackerSpendingViewModel budgetTrackerSpendingViewModel;
    boolean spdBool = false;
    Double price;
    private int searchShopIntentId = 0;
    private int searchShopId = 0;
    boolean isEdit = false;
    private List<BudgetTrackerBanking> bankList;
    private BudgetTrackerBankingViewModel budgetTrackerBankingViewModel;
    private ArrayList<BudgetTrackerBanking> bankArrayList;
    private Spinner budgetTrackerSpinner;
    private String spinnerText;
    double vatRate;

    private static final String ARG_SPENDING = "spending";

    private BudgetTrackerSpending mSpending;

    public AddSpendingFragment() {
        // Required empty public constructor
    }

    public static AddSpendingFragment newInstance(BudgetTrackerSpending spending) {
        AddSpendingFragment fragment = new AddSpendingFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_SPENDING, (Serializable) spending);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mSpending = (BudgetTrackerSpending) getArguments().getSerializable(ARG_SPENDING);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_spending, container, false);

        expenseTitleTv = (TextView) view.findViewById(R.id.title_spending);
        expenseSubTitleTv = (TextView) view.findViewById(R.id.sub_title_spending);
        enterDate = (EditText) view.findViewById(R.id.spd_enter_date);
        enterStoreName = (EditText) view.findViewById(R.id.spd_enter_store_name);
        enterProductName = (EditText) view.findViewById(R.id.spd_enter_product_name);
        enterProductType = (EditText) view.findViewById(R.id.spd_enter_product_type);
        enterPrice = (EditText) view.findViewById(R.id.spd_enter_price);
        enterVatRate = (EditText) view.findViewById(R.id.spd_vat_rate);
        enterNotes = (EditText) view.findViewById(R.id.spd_notes);
        radioGroup = (RadioGroup) view.findViewById(R.id.spd_radio_group);
        saveButton = (Button) view.findViewById(R.id.spd_save_button);
        updateButton = (Button) view.findViewById(R.id.spd_update_btn);
        deleteButton = (Button) view.findViewById(R.id.spd_delete_btn);
        sharedPreferences = getActivity().getSharedPreferences(PREF_CURRENCY_FILENAME, 0);
        budgetTrackerSpinner = (Spinner) view.findViewById(R.id.icm_budget_tracker_spinner);

        //選択された通貨表示
        int currentCurrencyNum = sharedPreferences.getInt(PREF_CURRENCY_VALUE, 0);
        Currency currency = Currency.getCurrencyArrayList().get(currentCurrencyNum);

        enterPrice.setCompoundDrawablesWithIntrinsicBounds(currency.getCurrencyImage(), 0, 0, 0);
        enterVatRate.setCompoundDrawablesWithIntrinsicBounds(currency.getCurrencyImage(), 0, 0, 0);

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


        budgetTrackerSpendingViewModel = new ViewModelProvider(requireActivity()).get(BudgetTrackerSpendingViewModel.class);
        budgetTrackerBankingViewModel = new ViewModelProvider(requireActivity()).get(BudgetTrackerBankingViewModel.class);

//        todo: July 27th, 2022: Gray out VAT EditText (It disables when yes is chosen)
        if (radioGroup.getCheckedRadioButtonId() == R.id.spd_no) {
            enterVatRate.setEnabled(false);
        } else if (radioGroup.getCheckedRadioButtonId() == R.id.spd_yes) {
            enterVatRate.setEnabled(true);
        }

        //        TODO: another button press action to confirm there is at least one data in bank table
        bankList = budgetTrackerBankingViewModel.getBankViewModelBankList();
        bankArrayList = new ArrayList<>(bankList);

        BankNameSpinnerAdapter bankNameSpinnerAdapter = new BankNameSpinnerAdapter(getActivity(), R.layout.income_spinner_adapter,
                bankArrayList);

        budgetTrackerSpinner.setAdapter(bankNameSpinnerAdapter);
        budgetTrackerSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                BudgetTrackerBanking budgetTrackerBanking = (BudgetTrackerBanking) budgetTrackerSpinner.getSelectedItem();
                spinnerText = budgetTrackerBanking.getBankName();
                Log.d("TAG_Spinner", "onItemSelected: " + spinnerText);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                spinnerText = null;
            }
        });

        if (mSpending != null) {
            enterDate.setText(mSpending.getDate());
            enterStoreName.setText(mSpending.getStoreName());
            enterProductName.setText(mSpending.getProductName());
            enterProductType.setText(mSpending.getProductType());
            enterPrice.setText(String.valueOf(mSpending.getPrice()));
            enterVatRate.setText(String.valueOf(mSpending.getTaxRate()));
            enterNotes.setText(mSpending.getNotes());
            isEdit = true;
        }

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String date = enterDate.getText().toString();
                String storeName = enterStoreName.getText().toString();
                String productName = enterProductName.getText().toString();
                String productType = enterProductType.getText().toString();
                price = Double.parseDouble(enterPrice.getText().toString());
                if (radioGroup.getCheckedRadioButtonId() == R.id.spd_no) {
                    vatRate = 0.0;
                    enterVatRate.setEnabled(false);
                } else {
                    spdBool = true;
                    vatRate = Double.parseDouble(enterVatRate.getText().toString());
                    price = price * vatRate;
                }
                String notes = enterNotes.getText().toString();
                BudgetTrackerSpending budgetTrackerSpending = new BudgetTrackerSpending(date, storeName, productName, productType, price, spdBool, vatRate, notes);
                budgetTrackerSpendingViewModel.insert(budgetTrackerSpending);
                if (spinnerText != null) {
                    double spendingNum = budgetTrackerSpending.getPrice();
                    budgetTrackerBankingViewModel.updateSubtraction(spendingNum, spinnerText);
                } else {
                    //Todo Need to make this a snackbar
                    Toast.makeText(getActivity(), "Insert a bank record", Toast.LENGTH_SHORT).show();
                }
                getActivity().finish();
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mSpending == null) {
                    return;
                }

                String date = enterDate.getText().toString();
                String storeName = enterStoreName.getText().toString();
                String productName = enterProductName.getText().toString();
                String productType = enterProductType.getText().toString();
                double price = Double.parseDouble(enterPrice.getText().toString());
                if (enterVatRate.getText().toString().trim().length() > 0) {
                    Log.d("09120222", "onClick: " + enterVatRate.getText().toString());
                    enterVatRate.setText(null);
                } else {
                    vatRate = Double.parseDouble(enterVatRate.getText().toString());
                }

                String notes = enterNotes.getText().toString();

                Log.d("noifuji", date + "," + storeName + "," + productName);
                if (TextUtils.isEmpty(date) || TextUtils.isEmpty(storeName) || TextUtils.isEmpty(productName) || TextUtils.isEmpty(productType) || TextUtils.isEmpty(String.valueOf(price)) || TextUtils.isEmpty(String.valueOf(vatRate)) || TextUtils.isEmpty(notes)) {
                    Snackbar.make(enterProductName, R.string.empty, Snackbar.LENGTH_SHORT).show();
                } else {
                    BudgetTrackerSpending newSpending = new BudgetTrackerSpending();
                    newSpending.setId(mSpending.getId());
                    newSpending.setDate(date);
                    newSpending.setStoreName(storeName);
                    newSpending.setProductName(productName);
                    newSpending.setProductType(productType);
                    newSpending.setPrice(price);
                    newSpending.setTaxRate(vatRate);
                    newSpending.setNotes(notes);
                    BudgetTrackerSpendingViewModel.updateBudgetTrackerSpending(newSpending);

                    FragmentActivity activity = getActivity();
                    if (activity != null) {
                        activity.getSupportFragmentManager().popBackStack();
                    }
                }
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mSpending == null) {
                    return;
                }

                BudgetTrackerSpendingViewModel.deleteBudgetTrackerSpending(mSpending);
                FragmentActivity activity = getActivity();
                if (activity != null) {
                    activity.getSupportFragmentManager().popBackStack();
                }
            }
        });


        if (isEdit) {
            saveButton.setVisibility(View.GONE);
            expenseTitleTv.setVisibility(View.GONE);
            expenseSubTitleTv.setVisibility(View.GONE);
        } else {
            updateButton.setVisibility(View.GONE);
            deleteButton.setVisibility(View.GONE);
        }

        return view;
    }
}