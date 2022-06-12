package com.myproject.offlinebudgettrackerappproject;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.snackbar.Snackbar;
import com.myproject.offlinebudgettrackerappproject.adapter.IncomeSpinnerAdapter;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTracker;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerBank;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerBankViewModel;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerViewModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class NewBudgetTracker extends AppCompatActivity {

    private EditText enterDate;
    private EditText enterStoreName;
    private EditText enterProductName;
    private EditText enterProductType;
    private EditText enterPrice;
    private Button saveInfoButton;
    private Button updateButton;
    private Button deleteButton;
    private int shopFragmentIntentId = 0;
    private int productFragmentIntentId = 0;
    private int dateFragmentIntentId = 0;
    boolean isEdit = false;
    LiveData<List<BudgetTracker>> viewModelStoreNameLists;
    private ArrayList<BudgetTrackerBank> bankArrayList;
    private List<BudgetTrackerBank> bankList;
    private Spinner budgetTrackerSpinner;
    private String spinnerText;
    SharedPreferences sharedPreferences;

    private BudgetTrackerViewModel budgetTrackerViewModel;
    private BudgetTrackerBankViewModel budgetTrackerBankViewModel;

    public ArrayList<BudgetTrackerBank> getBankArrayList() {
        return bankArrayList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_budget_tracker);

        enterDate = findViewById(R.id.enter_date);
        enterStoreName = findViewById(R.id.enter_store_name);
        enterProductName = findViewById(R.id.enter_product_name);
        enterProductType = findViewById(R.id.enter_product_type);
        enterPrice = findViewById(R.id.enter_price);
        saveInfoButton = findViewById(R.id.saveIndoButton);
        updateButton = findViewById(R.id.update_btn);
        deleteButton = findViewById(R.id.delete_btn);
        budgetTrackerSpinner = (Spinner) findViewById(R.id.budget_tracker_spinner);
        sharedPreferences = getSharedPreferences("CURRENCY_SHARED",0);

        Integer selectedCurrencyNum = sharedPreferences.getInt("PREF_CURRENCY_VALUE", 0);

        if (selectedCurrencyNum.equals(0)) {
            enterPrice.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_money, 0, 0, 0);
        } else if (selectedCurrencyNum.equals(1)) {
            enterPrice.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_yen, 0, 0, 0);
        } else if (selectedCurrencyNum.equals(2)) {
            enterPrice.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_euro, 0, 0, 0);
        }

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        enterDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        NewBudgetTracker.this, new DatePickerDialog.OnDateSetListener() {
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

        budgetTrackerViewModel = new ViewModelProvider.AndroidViewModelFactory(NewBudgetTracker.this
                .getApplication())
                .create(BudgetTrackerViewModel.class);

        budgetTrackerBankViewModel = new ViewModelProvider.AndroidViewModelFactory(NewBudgetTracker.this
                .getApplication())
                .create(BudgetTrackerBankViewModel.class);

        //        TODO: another button press action to confirm there is at least one data in bank table
        bankList = budgetTrackerBankViewModel.getBankViewModelBankList();
        bankArrayList = new ArrayList<BudgetTrackerBank>(bankList);

        IncomeSpinnerAdapter bankSpinnerAdapter = new IncomeSpinnerAdapter(this, R.layout.income_spinner_adapter,
                bankArrayList);

        budgetTrackerSpinner.setAdapter(bankSpinnerAdapter);
        budgetTrackerSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                BudgetTrackerBank budgetTrackerBank = (BudgetTrackerBank) budgetTrackerSpinner.getSelectedItem();
                spinnerText = budgetTrackerBank.getBankName();
                Log.d("TAG_Spinner", "onItemSelected: " + spinnerText);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                spinnerText = null;
            }
        });

        saveInfoButton.setOnClickListener(view -> {

            if (!TextUtils.isEmpty(enterDate.getText())
                    && !TextUtils.isEmpty(enterStoreName.getText())
                    && !TextUtils.isEmpty(enterProductName.getText())
                    && !TextUtils.isEmpty(enterProductType.getText())
                    && !TextUtils.isEmpty(enterPrice.getText())) {
                String date = enterDate.getText().toString();
                String storeName = enterStoreName.getText().toString();
                String productName = enterProductName.getText().toString();
                String productType = enterProductType.getText().toString();
                int price = Integer.parseInt(enterPrice.getText().toString());

                BudgetTracker budgetTracker = new BudgetTracker(date, storeName, productName, productType, price);
                budgetTrackerViewModel.insert(budgetTracker);
                //updating bank record
                if (spinnerText != null) {
                    int spendingNum = budgetTracker.getPrice();
                    budgetTrackerBankViewModel.updateSubtraction(spendingNum, spinnerText);
                } else {
                    //Todo Need to make this a snackbar
                    Toast.makeText(this, "Insert a bank record", Toast.LENGTH_SHORT).show();
                }

            }
//            else {
//                setResult(RESULT_CANCELED, replyIntent);
//            }
            finish();

        });

//  Get intent from ShopFragment
        Intent shopFragmentGetIntent = getIntent();
        Bundle shopFragmentGetIntentBundle = shopFragmentGetIntent.getExtras();
        if (shopFragmentGetIntentBundle != null) {
            shopFragmentIntentId = getIntent().getIntExtra(ShopFragment.SHOP_FRAGMENT_ID, 0);
            Log.d("TAG", "shopFragment: " + shopFragmentIntentId);
            //observe only works when using LiveData
            budgetTrackerViewModel.getBudgetTrackerId(shopFragmentIntentId).observe(this, new Observer<BudgetTracker>() {
                @Override
                public void onChanged(BudgetTracker budgetTracker) {
                    if (budgetTracker != null) {
                        enterDate.setText(budgetTracker.getDate());
                        enterStoreName.setText(budgetTracker.getStoreName());
                        enterProductName.setText(budgetTracker.getProductName());
                        enterProductType.setText(budgetTracker.getProductType());
                        enterPrice.setText(String.valueOf(budgetTracker.getPrice()));
                    }
                }
            });
            isEdit = true;
        }

//  Get intent from ProductFragment
        Intent productFragmentGetIntent = getIntent();
        Bundle productFragmentGetIntentBundle = productFragmentGetIntent.getExtras();
        if (productFragmentGetIntentBundle != null) {
            productFragmentIntentId = getIntent().getIntExtra(ProductFragment.PRODUCT_FRAGMENT_ID, 0);
            Log.d("TAG", "productFragment: " + productFragmentIntentId);
            //observe only works when using LiveData
            budgetTrackerViewModel.getBudgetTrackerId(productFragmentIntentId).observe(this, new Observer<BudgetTracker>() {
                @Override
                public void onChanged(BudgetTracker budgetTracker) {
                    if (budgetTracker != null) {
                        enterDate.setText(budgetTracker.getDate());
                        enterStoreName.setText(budgetTracker.getStoreName());
                        enterProductName.setText(budgetTracker.getProductName());
                        enterProductType.setText(budgetTracker.getProductType());
                        enterPrice.setText(String.valueOf(budgetTracker.getPrice()));
                    }
                }
            });
            isEdit = true;
        }

        //  Get intent from DateFragment
        Intent dateFragmentGetIntent = getIntent();
        Bundle dateFragmentGetIntentBundle = dateFragmentGetIntent.getExtras();
        if (dateFragmentGetIntentBundle != null) {
            dateFragmentIntentId = getIntent().getIntExtra(DateFragment.DATE_FRAGMENT_ID, 0);
            Log.d("TAG", "dateFragment: " + dateFragmentIntentId);
            //observe only works when using LiveData
            budgetTrackerViewModel.getBudgetTrackerId(dateFragmentIntentId).observe(this, new Observer<BudgetTracker>() {
                @Override
                public void onChanged(BudgetTracker budgetTracker) {
                    if (budgetTracker != null) {
                        enterDate.setText(budgetTracker.getDate());
                        enterStoreName.setText(budgetTracker.getStoreName());
                        enterProductName.setText(budgetTracker.getProductName());
                        enterProductType.setText(budgetTracker.getProductType());
                        enterPrice.setText(String.valueOf(budgetTracker.getPrice()));
                    }
                }
            });
            isEdit = true;
        }


        //Delete button
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (shopFragmentIntentId != 0 || productFragmentIntentId != 0 || dateFragmentIntentId != 0) {
                    int idStore = shopFragmentIntentId;
                    int idProduct = productFragmentIntentId;
                    int idDate = dateFragmentIntentId;
                    String date = enterDate.getText().toString();
                    String storeName = enterStoreName.getText().toString();
                    String productName = enterProductName.getText().toString();
                    String productType = enterProductType.getText().toString();
                    int price = Integer.parseInt(enterPrice.getText().toString());

                    if (TextUtils.isEmpty(date) || TextUtils.isEmpty(storeName) || TextUtils.isEmpty(productName) || TextUtils.isEmpty(productType) || TextUtils.isEmpty(String.valueOf(price))) {
                        Snackbar.make(enterProductName, R.string.empty, Snackbar.LENGTH_SHORT).show();
                    } else {
                        BudgetTracker budgetTracker = new BudgetTracker();
                        if (shopFragmentIntentId != 0) {
                            budgetTracker.setId(idStore);
                        } else if (productFragmentIntentId != 0) {
                            budgetTracker.setId(idProduct);
                        } else if (dateFragmentIntentId != 0) {
                            budgetTracker.setId(idDate);
                        }

                        budgetTracker.setDate(date);
                        budgetTracker.setStoreName(storeName);
                        budgetTracker.setProductName(productName);
                        budgetTracker.setProductType(productType);
                        budgetTracker.setPrice(price);
                        BudgetTrackerViewModel.deleteBudgetTracker(budgetTracker);
                        budgetTrackerViewModel.getAllBudgetTrackerLists();
                        finish();
                    }
                }
            }
        });

        //Update button
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (shopFragmentIntentId != 0 || productFragmentIntentId != 0 || dateFragmentIntentId != 0) {
                    int idStore = shopFragmentIntentId;
                    int idProduct = productFragmentIntentId;
                    int idDate = dateFragmentIntentId;
                    String date = enterDate.getText().toString();
                    String storeName = enterStoreName.getText().toString();
                    String productName = enterProductName.getText().toString();
                    String productType = enterProductType.getText().toString();
                    int price = Integer.parseInt(enterPrice.getText().toString());

                    if (TextUtils.isEmpty(date) || TextUtils.isEmpty(storeName) || TextUtils.isEmpty(productName) || TextUtils.isEmpty(productType) || TextUtils.isEmpty(String.valueOf(price))) {
                        Snackbar.make(enterProductName, R.string.empty, Snackbar.LENGTH_SHORT).show();
                    } else {
                        BudgetTracker budgetTracker = new BudgetTracker();
                        if (shopFragmentIntentId != 0) {
                            budgetTracker.setId(idStore);
                        } else if (productFragmentIntentId != 0) {
                            budgetTracker.setId(idProduct);
                        } else if (dateFragmentIntentId != 0) {
                            budgetTracker.setId(idDate);
                        }
                        budgetTracker.setDate(date);
                        budgetTracker.setStoreName(storeName);
                        budgetTracker.setProductName(productName);
                        budgetTracker.setProductType(productType);
                        budgetTracker.setPrice(price);
                        BudgetTrackerViewModel.updateBudgetTracker(budgetTracker);
//                         Todo Automatic search after updated an item
//                        viewModelStoreNameLists = budgetTrackerViewModel.getAllBudgetTrackerLists();
//                        StoreListViewAdapter storeListViewAdapter;
//
//                        storeListViewAdapter = new StoreListViewAdapter(this, viewModelStoreNameLists);
//                        storeListView.setAdapter(storeListViewAdapter);
                        finish();
                    }
                }

            }
        });

        if (isEdit) {
            saveInfoButton.setVisibility(View.GONE);
        } else {
            updateButton.setVisibility(View.GONE);
            deleteButton.setVisibility(View.GONE);
        }

    }
}