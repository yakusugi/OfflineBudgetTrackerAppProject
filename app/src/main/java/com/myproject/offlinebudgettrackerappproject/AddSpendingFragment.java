package com.myproject.offlinebudgettrackerappproject;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerSpending;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerSpendingViewModel;
import com.myproject.offlinebudgettrackerappproject.model.Currency;

import java.util.Calendar;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddSpendingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddSpendingFragment extends Fragment {

    EditText enterDate, enterStoreName, enterProductName, enterProductType, enterPrice, enterVatRate, enterNotes;
    RadioGroup radioGroup;
    Button saveButton;
    SharedPreferences sharedPreferences;
    private static final String PREF_CURRENCY_FILENAME = "CURRENCY_SHARED";
    private static final String PREF_CURRENCY_VALUE = "currencyValue";
    BudgetTrackerSpendingViewModel budgetTrackerSpendingViewModel;
    boolean spdBool = false;
    Double vatRate;
    Double price;
    private int searchShopIntentId = 0;
    boolean isEdit = false;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddSpendingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddSpendingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddSpendingFragment newInstance(String param1, String param2) {
        AddSpendingFragment fragment = new AddSpendingFragment();
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
        View view = inflater.inflate(R.layout.fragment_add_spending, container, false);

        enterDate = (EditText) view.findViewById(R.id.spd_enter_date);
        enterStoreName = (EditText) view.findViewById(R.id.spd_enter_store_name);
        enterProductName = (EditText) view.findViewById(R.id.spd_enter_product_name);
        enterProductType = (EditText) view.findViewById(R.id.spd_enter_product_type);
        enterPrice = (EditText) view.findViewById(R.id.spd_enter_price);
        enterVatRate = (EditText) view.findViewById(R.id.spd_vat_rate);
        enterNotes = (EditText) view.findViewById(R.id.spd_notes);
        radioGroup = (RadioGroup) view.findViewById(R.id.spd_radio_group);
        saveButton = (Button) view.findViewById(R.id.spd_save_button);
        sharedPreferences = getActivity().getSharedPreferences(PREF_CURRENCY_FILENAME, 0);

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

        List<BudgetTrackerSpending> budgetTrackerSpending = null;

        budgetTrackerSpendingViewModel = new ViewModelProvider(requireActivity()).get(BudgetTrackerSpendingViewModel.class);

//        todo: July 27th, 2022: Gray out VAT EditText (It disables when yes is chosen)
        if (radioGroup.getCheckedRadioButtonId() == R.id.spd_no) {
            enterVatRate.setEnabled(false);
        } else if (radioGroup.getCheckedRadioButtonId() == R.id.spd_yes) {
            enterVatRate.setEnabled(true);
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
                getActivity().finish();
            }
        });

        Intent searchShopGetIntent = getActivity().getIntent();
        Bundle searchShopGetIntentBundle = searchShopGetIntent.getExtras();
        if (searchShopGetIntentBundle != null) {
            searchShopIntentId = getActivity().getIntent().getIntExtra(SearchFragment.SHOP_SEARCH_ID, 0);
            Log.d("TAG", "shopFragment: " + searchShopIntentId);
            //observe only works when using LiveData
            budgetTrackerSpendingViewModel.getBudgetTrackerSpendingId(searchShopIntentId).observe(getActivity(), new Observer<BudgetTrackerSpending>() {
                @Override
                public void onChanged(BudgetTrackerSpending budgetTrackerSpending) {
                    if (budgetTrackerSpending != null) {
                        enterDate.setText(budgetTrackerSpending.getDate());
                        enterStoreName.setText(budgetTrackerSpending.getStoreName());
                        enterProductName.setText(budgetTrackerSpending.getProductName());
                        enterProductType.setText(budgetTrackerSpending.getProductType());
                        enterPrice.setText(String.valueOf(budgetTrackerSpending.getPrice()));
                        enterVatRate.setText(String.valueOf(budgetTrackerSpending.getTaxRate()));
                        enterNotes.setText(budgetTrackerSpending.getNotes());
                    }
                }

            });
            isEdit = true;
        }




        return view;
    }
}