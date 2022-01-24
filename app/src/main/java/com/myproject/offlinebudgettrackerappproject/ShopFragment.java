package com.myproject.offlinebudgettrackerappproject;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.myproject.offlinebudgettrackerappproject.adapter.ShopRecyclerViewAdapter;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTracker;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShopFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShopFragment extends Fragment {

    BudgetTrackerViewModel budgetTrackerViewModel;
    private ShopRecyclerViewAdapter shopRecyclerViewAdapter;
    private RecyclerView storeRecyclerView;
    private List<BudgetTracker> budgetTracker;


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
        storeRecyclerView = (RecyclerView) view.findViewById(R.id.storeRecyclerView);
        storeRecyclerView.setHasFixedSize(true);
        storeRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        shopRecyclerViewAdapter = new ShopRecyclerViewAdapter(budgetTracker, getActivity());

                storeSearchQueryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BudgetTracker budgetTracker;
                ShopRecyclerViewAdapter shopRecyclerViewAdapter;
                RecyclerView recyclerView = null;

                String storeName = enterStoreNameForQuery.getText().toString();
                budgetTrackerViewModel = new ViewModelProvider(requireActivity()).get(BudgetTrackerViewModel.class);
//                budgetTrackerViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(
//                        this.getApplication()).create(CanteensViewModel.class);

                budgetTracker = new BudgetTracker();
                budgetTracker.setStoreName(storeName);

                budgetTrackerViewModel.getStoreNameLists(storeName);

                List<BudgetTracker> viewModelStoreNameLists = budgetTrackerViewModel.getStoreNameLists(storeName);

//                shopRecyclerViewAdapter = new ShopRecyclerViewAdapter(viewModelStoreNameLists, getActivity());
//                recyclerView.setAdapter(shopRecyclerViewAdapter);

                Log.d("TAG", "onClick: " + enterStoreNameForQuery.getText().toString());

                for(BudgetTracker budgetTrackerList : viewModelStoreNameLists) {
                    Log.d("TAG", "onClick: " + budgetTrackerList.getId());
                    Log.d("TAG", "onClick: " + budgetTrackerList.getDate().toString());
                    Log.d("TAG", "onClick: " + budgetTrackerList.getStoreName().toString());
                    Log.d("TAG", "onClick: " + budgetTrackerList.getProductName().toString());
                    Log.d("TAG", "onClick: " + budgetTrackerList.getProductType().toString());
                    Log.d("TAG", "onClick: " + budgetTrackerList.getPrice());
                }

//                Log.d("TAG", "onClick: " + list);

            }
        });

        return view;
    }

}