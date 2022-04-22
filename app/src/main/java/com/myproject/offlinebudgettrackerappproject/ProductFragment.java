package com.myproject.offlinebudgettrackerappproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.myproject.offlinebudgettrackerappproject.adapter.ProductListViewAdapter;
import com.myproject.offlinebudgettrackerappproject.adapter.StoreListViewAdapter;
import com.myproject.offlinebudgettrackerappproject.databinding.ActivityMainBinding;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTracker;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProductFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductFragment extends Fragment {

    BudgetTrackerViewModel budgetTrackerViewModel;
    private ProductListViewAdapter productListViewAdapter;
    public static final String PRODUCT_FRAGMENT_ID = "product_fragment_id";
    private ListView productListView;
    private List<BudgetTracker> budgetTrackerList;
    private List<BudgetTracker> budgetTracker;
    List<BudgetTracker> viewModelProductNameLists;
    ActivityMainBinding activityMainBinding;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProductFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProductFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProductFragment newInstance(String param1, String param2) {
        ProductFragment fragment = new ProductFragment();
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
        View view = inflater.inflate(R.layout.fragment_product, container, false);
        EditText enterProductTypeForQuery = (EditText) view.findViewById(R.id.product_search_txt);
        Button productSearchQueryBtn = (Button) view.findViewById(R.id.btn_product_search);
        productListView = (ListView) view.findViewById(R.id.product_listview);
//        AutoCompleteTextView autoCompleteProductTextView  = (AutoCompleteTextView) view.findViewById(R.id.product_search_txt);
//        ArrayAdapter<String> adapter;
//        String [] strings = getResources().getStringArray(R.array.product_type_string_array);

        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());

        BaseAdapter adapter = new StoreListViewAdapter(getActivity(), budgetTrackerList);

        productListViewAdapter = new ProductListViewAdapter(getActivity(), 1);
        productListView.setAdapter(productListViewAdapter);

        productSearchQueryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BudgetTracker budgetTracker;

                //      2022/02/11 追加
                ProductListViewAdapter productListViewAdapter;

                String productType = enterProductTypeForQuery.getText().toString();
                budgetTrackerViewModel = new ViewModelProvider(requireActivity()).get(BudgetTrackerViewModel.class);

                budgetTracker = new BudgetTracker();
                budgetTracker.setProductType(productType);


                viewModelProductNameLists = budgetTrackerViewModel.getProductTypeLists(productType);

                productListViewAdapter = new ProductListViewAdapter(getActivity(), viewModelProductNameLists);
                productListView.setAdapter(productListViewAdapter);

//              Todo  2022/04/10 Tapped modified
                productListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                        String date = adapterView.getItemAtPosition(position).toString();
                        List<BudgetTracker> budgetListItems = viewModelProductNameLists;
                        int intId = (int) id;
                        BudgetTracker productItemId = budgetListItems.get(intId);
                        Intent shopFragmentIntent = new Intent(getActivity(), NewBudgetTracker.class);
                        shopFragmentIntent.putExtra(PRODUCT_FRAGMENT_ID, productItemId.getId());
                        startActivity(shopFragmentIntent);

                        Log.d("TAG", "onItemClick: " + date);
                    }
                });

                Log.d("TAG", "onClick: " + enterProductTypeForQuery.getText().toString());

            }

        });

        return view;
    }
}