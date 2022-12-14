package com.myproject.offlinebudgettrackerappproject;

import android.annotation.SuppressLint;
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
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.snackbar.Snackbar;
import com.myproject.offlinebudgettrackerappproject.adapter.BankNameSpinnerAdapter;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerBanking;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerBankingViewModel;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerIncomes;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerIncomesViewModel;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerSpending;
import com.myproject.offlinebudgettrackerappproject.model.Currency;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddIncomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddIncomeFragment extends Fragment {

    EditText enterDate, enterCategory, enterAmount, enterNotes;
    Button saveButton, deleteButton, updateButton;
    SharedPreferences sharedPreferences;
    private static final String PREF_CURRENCY_FILENAME = "CURRENCY_SHARED";
    private static final String PREF_CURRENCY_VALUE = "currencyValue";
    BudgetTrackerIncomesViewModel budgetTrackerIncomesViewModel;
    boolean spdBool = false;
    Double vatRate;
    Double amount;
    boolean isEdit = false;
    private List<BudgetTrackerBanking> bankList;
    private BudgetTrackerBankingViewModel budgetTrackerBankingViewModel;
    private ArrayList<BudgetTrackerBanking> bankArrayList;
    private Spinner budgetTrackerSpinner;
    private String spinnerText;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private static final String ARG_REQUESTKEY = "requestKey";
    private static final String ARG_DATA = "data";

    public AddIncomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddIncomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddIncomeFragment newInstance(String param1, String param2) {
        AddIncomeFragment fragment = new AddIncomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static AddIncomeFragment newInstance(String requestKey, BudgetTrackerIncomes incomes) {
        AddIncomeFragment fragment = new AddIncomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_REQUESTKEY, requestKey);
        args.putSerializable(ARG_DATA, incomes);
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

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_income, container, false);

        enterDate = (EditText) view.findViewById(R.id.icm_enter_date);
        enterCategory = (EditText) view.findViewById(R.id.icm_enter_category_name);
        enterAmount = (EditText) view.findViewById(R.id.icm_enter_amount);
        enterNotes = (EditText) view.findViewById(R.id.icm_notes);
        saveButton = (Button) view.findViewById(R.id.icm_save_button);
        deleteButton = (Button) view.findViewById(R.id.icm_delete_button);
        updateButton = (Button) view.findViewById(R.id.icm_update_button);
        sharedPreferences = getActivity().getSharedPreferences(PREF_CURRENCY_FILENAME, 0);
        budgetTrackerSpinner = (Spinner) view.findViewById(R.id.icm_budget_tracker_spinner);

        //選択された通貨表示
        int currentCurrencyNum = sharedPreferences.getInt(PREF_CURRENCY_VALUE, 0);
        Currency currency = Currency.getCurrencyArrayList().get(currentCurrencyNum);

        enterAmount.setCompoundDrawablesWithIntrinsicBounds(currency.getCurrencyImage(), 0, 0, 0);

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

        budgetTrackerIncomesViewModel = new ViewModelProvider(requireActivity()).get(BudgetTrackerIncomesViewModel.class);
        budgetTrackerBankingViewModel = new ViewModelProvider(requireActivity()).get(BudgetTrackerBankingViewModel.class);

        //        TODO: another button press action to confirm there is at least one data in bank table
        bankList = budgetTrackerBankingViewModel.getBankViewModelBankList();
        bankArrayList = new ArrayList<BudgetTrackerBanking>(bankList);

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

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String date = enterDate.getText().toString();
                String categoryName = enterCategory.getText().toString();
                amount = Double.parseDouble(enterAmount.getText().toString());
                String notes = enterNotes.getText().toString();
                BudgetTrackerIncomes budgetTrackerIncomes = new BudgetTrackerIncomes(date, categoryName, amount, notes);
                budgetTrackerIncomesViewModel.insert(budgetTrackerIncomes);
                if (spinnerText != null) {
                    double incomesNum = budgetTrackerIncomes.getAmount();
                    budgetTrackerBankingViewModel.updateAddition(incomesNum, spinnerText);
                } else {
                    //Todo Need to make this a snackbar
                    Toast.makeText(getActivity(), "Insert a bank record", Toast.LENGTH_SHORT).show();
                }
                getActivity().finish();
            }
        });

        // 2022/12/07 received data from list view and put them in textview for update/delete
        String requestKey = getArguments().getString(ARG_REQUESTKEY);
        BudgetTrackerIncomes budgetTrackerIncomes = (BudgetTrackerIncomes)getArguments().getSerializable(ARG_DATA);

        if (budgetTrackerIncomes != null) {
            enterDate.setText(budgetTrackerIncomes.getDate());
            enterCategory.setText(budgetTrackerIncomes.getCategory());
            enterAmount.setText(String.valueOf(budgetTrackerIncomes.getAmount()));
            enterNotes.setText(budgetTrackerIncomes.getNotes());
            isEdit = true;
        }

        //2022/12/14
        FragmentManager fm = getParentFragmentManager();

        deleteButton.setOnClickListener(v -> {
            if (budgetTrackerIncomes == null) {
                return;
            }

            BudgetTrackerIncomesViewModel.deleteBudgetTrackerIncomes(budgetTrackerIncomes);
            FragmentActivity activity = getActivity();
            if (activity != null) {
                activity.getSupportFragmentManager().popBackStack();
            }
//            budgetTrackerSpendingViewModel.
            fm.setFragmentResult(requestKey, new Bundle());
        });

        // 2022/12/07 new update button
        updateButton.setOnClickListener(v -> {
            if (budgetTrackerIncomes == null) {
                return;
            }

            String date = enterDate.getText().toString();
            String categoryName = enterCategory.getText().toString();
            double amount = Double.parseDouble(enterAmount.getText().toString());
            String notes = enterNotes.getText().toString();

            if (TextUtils.isEmpty(date) || TextUtils.isEmpty(categoryName) || TextUtils.isEmpty(String.valueOf(amount)) || TextUtils.isEmpty(notes)) {
                Snackbar.make(enterCategory, R.string.empty, Snackbar.LENGTH_SHORT).show();
            } else {
                BudgetTrackerIncomes newIncomes = new BudgetTrackerIncomes();
                newIncomes.setId(budgetTrackerIncomes.getId());
                newIncomes.setDate(date);
                newIncomes.setCategory(categoryName);
                newIncomes.setAmount(amount);
                newIncomes.setNotes(notes);
                BudgetTrackerIncomesViewModel.updateBudgetTrackerIncomes(newIncomes);

                FragmentActivity activity = getActivity();
                if (activity != null) {
                    activity.getSupportFragmentManager().popBackStack();
                }
            }
            fm.setFragmentResult(requestKey, new Bundle());
        });


        if (isEdit) {
            saveButton.setVisibility(View.GONE);
//            expenseTitleTv.setVisibility(View.GONE);
//            expenseSubTitleTv.setVisibility(View.GONE);
        } else {
            updateButton.setVisibility(View.GONE);
            deleteButton.setVisibility(View.GONE);
        }

        // Inflate the layout for this fragment
        return view;
    }
}