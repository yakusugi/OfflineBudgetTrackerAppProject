package com.myproject.offlinebudgettrackerappproject;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.myproject.offlinebudgettrackerappproject.adapter.ProductRecyclerViewAdapter;
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
    private ProductRecyclerViewAdapter productRecyclerViewAdapter;
    private RecyclerView productRecyclerView;
    private List<BudgetTracker> budgetTracker;

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
        AutoCompleteTextView autoCompleteProductTextView  = (AutoCompleteTextView) view.findViewById(R.id.product_search_txt);
        ArrayAdapter<String> adapter;
        String [] strings = {"Ebook", "Gadget", "Clothing"};

        productRecyclerView = (RecyclerView) view.findViewById(R.id.productRecyclerView);

        productRecyclerView.setHasFixedSize(true);
        productRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        productRecyclerViewAdapter = new ProductRecyclerViewAdapter(budgetTracker, getActivity());

        adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, strings);

        autoCompleteProductTextView.setThreshold(1);
        autoCompleteProductTextView.setAdapter(adapter);

        productSearchQueryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BudgetTracker budgetTracker;
                ProductRecyclerViewAdapter productRecyclerViewAdapter;
                RecyclerView recyclerView = null;
                List<BudgetTracker> viewModelProductTypeLists;
                int viewModelProductTypeSum;

                String productType = autoCompleteProductTextView.getText().toString();
                budgetTrackerViewModel = new ViewModelProvider(requireActivity()).get(BudgetTrackerViewModel.class);

                viewModelProductTypeLists = budgetTrackerViewModel.getProductTypeLists(productType);
                viewModelProductTypeSum = budgetTrackerViewModel.getProductTypeSumNum(productType);
                String viewModelProductTypeSumStr = String.valueOf(viewModelProductTypeSum);
                TextView calcResult = (TextView) view.findViewById(R.id.product_type_sum_result_txt);

                autoCompleteProductTextView.setThreshold(1);
                autoCompleteProductTextView.setAdapter(adapter);



//                TODO: calculated result of all product SQL will be here.
//                calcResult.setText(viewModelProductTypeSumStr);

                productRecyclerViewAdapter = new ProductRecyclerViewAdapter(viewModelProductTypeLists, getActivity());
                productRecyclerView.setAdapter(productRecyclerViewAdapter);

                Log.d("TAG", "onClick: " + autoCompleteProductTextView.getText().toString());
                Log.d("TAG", "onClick: " + viewModelProductTypeSum);

                for(BudgetTracker budgetTrackerList : viewModelProductTypeLists) {
                    Log.d("TAG", "onClick: " + budgetTrackerList.getId());
                    Log.d("TAG", "onClick: " + budgetTrackerList.getDate().toString());
                    Log.d("TAG", "onClick: " + budgetTrackerList.getStoreName().toString());
                    Log.d("TAG", "onClick: " + budgetTrackerList.getProductName().toString());
                    Log.d("TAG", "onClick: " + budgetTrackerList.getProductType().toString());
                    Log.d("TAG", "onClick: " + budgetTrackerList.getPrice());
                }

            }
        });

        return view;
    }
}