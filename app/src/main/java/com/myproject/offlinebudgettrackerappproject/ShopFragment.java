package com.myproject.offlinebudgettrackerappproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.myproject.offlinebudgettrackerappproject.adapter.StoreListViewAdapter;
import com.myproject.offlinebudgettrackerappproject.databinding.ActivityMainBinding;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTracker;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerViewModel;
import com.myproject.offlinebudgettrackerappproject.model.Currency;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShopFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShopFragment extends Fragment {

    private static final String PREF_CURRENCY_FILENAME = "CURRENCY_SHARED";
    private static final String PREF_CURRENCY_VALUE = "currencyValue";
    private static final int RESULT_OK = -1;
    private static final String TAG = "Clicked";
    public static final String SHOP_FRAGMENT_ID = "shop_fragment_id";
    BudgetTrackerViewModel budgetTrackerViewModel;
    private StoreListViewAdapter storeListViewAdapter;
    private ListView storeListView;
    private List<BudgetTracker> budgetTrackerList;
    List<BudgetTracker> viewModelStoreNameLists;
    List<BudgetTracker> storeNameListsReset;
    ActivityMainBinding activityMainBinding;
    String storeName;
    SharedPreferences sharedPreferences;
    TextView totalTv;
    String storeTypeSumStr;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ShopFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ShopFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ShopFragment newInstance(String param1, String param2) {
        ShopFragment fragment = new ShopFragment();
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
        View view = inflater.inflate(R.layout.fragment_shop, container, false);
        EditText enterStoreNameForQuery = (EditText) view.findViewById(R.id.store_search_txt);
        Button storeSearchQueryBtn = (Button) view.findViewById(R.id.btn_store_search);
        storeListView = (ListView) view.findViewById(R.id.store_listview);
        totalTv = (TextView) view.findViewById(R.id.store_name_sum_result_txt);
        TextView storeNameSum = (TextView) view.findViewById(R.id.store_name_sum_result_txt);

        sharedPreferences = getActivity().getSharedPreferences(PREF_CURRENCY_FILENAME, 0);

        //選択された通貨の設定
        int currentCurrencyNum = sharedPreferences.getInt(PREF_CURRENCY_VALUE, 0);
        Currency currency = Currency.getCurrencyArrayList().get(currentCurrencyNum);
        totalTv.setCompoundDrawablesWithIntrinsicBounds(currency.getCurrencyImage(), 0, 0, 0);


//      2022/02/11 追加
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());

//      2022/02/11 追加
//        storeListViewAdapter = new StoreListViewAdapter(getActivity(), budgetTrackerList);
//
//
//        BaseAdapter adapter = new StoreListViewAdapter(getActivity(), budgetTrackerList);

        storeListViewAdapter = new StoreListViewAdapter(getActivity(), 1);
        storeListView.setAdapter(storeListViewAdapter);

        storeSearchQueryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BudgetTracker budgetTracker;

                //      2022/02/11 追加
//                final StoreListViewAdapter[] storeListViewAdapter = new StoreListViewAdapter[1];

                storeName = enterStoreNameForQuery.getText().toString();
                budgetTrackerViewModel = new ViewModelProvider(requireActivity()).get(BudgetTrackerViewModel.class);

                budgetTracker = new BudgetTracker();
                budgetTracker.setStoreName(storeName);

                viewModelStoreNameLists = budgetTrackerViewModel.getStoreNameLists(storeName);

                storeListViewAdapter = new StoreListViewAdapter(getActivity(), viewModelStoreNameLists);
                storeListViewAdapter.notifyDataSetChanged();
                storeListView.setAdapter(storeListViewAdapter);
                storeTypeSumStr = String.valueOf(budgetTrackerViewModel.getStoreNameSumSum(storeName));
                storeNameSum.setText(storeTypeSumStr);

//              Todo  2022/04/10 Tapped modified
                storeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                        String date = adapterView.getItemAtPosition(position).toString();
                        List<BudgetTracker> budgetListItems = viewModelStoreNameLists;
                        int intId = (int) id;
                        BudgetTracker storeItemId = budgetListItems.get(intId);
                        Intent shopFragmentIntent = new Intent(getActivity(), NewBudgetTracker.class);
                        shopFragmentIntent.putExtra(SHOP_FRAGMENT_ID, storeItemId.getId());
                        startActivityForResult(shopFragmentIntent, 1);

                        Log.d(TAG, "onItemClick: " + date);
                    }
                });

                Log.d("TAG-June", "onClick: " + enterStoreNameForQuery.getText().toString());

            }

//            public void refresh(List<BudgetTracker> budgetTrackerList){
//                this.budgetTrackerList=budgetTrackerList;
//                notifyDataSetChanged();
//            }

        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                int result = data.getIntExtra("result", 0);
                viewModelStoreNameLists = budgetTrackerViewModel.getStoreNameLists(storeName);

                storeListViewAdapter = new StoreListViewAdapter(getActivity(), viewModelStoreNameLists);
                storeListViewAdapter.notifyDataSetChanged();
                storeListView.setAdapter(storeListViewAdapter);
            }
        }
    }


}