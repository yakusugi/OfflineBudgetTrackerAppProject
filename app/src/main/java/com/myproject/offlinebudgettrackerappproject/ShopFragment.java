package com.myproject.offlinebudgettrackerappproject;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.myproject.offlinebudgettrackerappproject.adapter.StoreListViewAdapter;
import com.myproject.offlinebudgettrackerappproject.databinding.ActivityMainBinding;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTracker;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShopFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShopFragment extends Fragment {

    private static final String TAG = "Clicked";
    BudgetTrackerViewModel budgetTrackerViewModel;
//    private ShopRecyclerViewAdapter shopRecyclerViewAdapter;
    private StoreListViewAdapter storeListViewAdapter;
//    private RecyclerView storeRecyclerView;
    private ListView storeListView;
    private List<BudgetTracker> budgetTracker;
    ActivityMainBinding activityMainBinding;


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
//        RecyclerViewを削除
//        storeRecyclerView = (RecyclerView) view.findViewById(R.id.storeRecyclerView);
        storeListView = (ListView) view.findViewById(R.id.store_listview);
//        storeRecyclerView.setHasFixedSize(true);
//        storeRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//      2022/02/11 追加
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
//      RecyclerVieなので不要
//        shopRecyclerViewAdapter = new ShopRecyclerViewAdapter(budgetTracker, getActivity());
//      2022/02/11 追加
        storeListViewAdapter = new StoreListViewAdapter(getActivity(), budgetTracker);

        storeSearchQueryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BudgetTracker budgetTracker;
//                ShopRecyclerViewAdapter shopRecyclerViewAdapter;
                //      2022/02/11 追加
                StoreListViewAdapter storeListViewAdapter;
//                RecyclerView recyclerView = null;

                String storeName = enterStoreNameForQuery.getText().toString();
                budgetTrackerViewModel = new ViewModelProvider(requireActivity()).get(BudgetTrackerViewModel.class);

                budgetTracker = new BudgetTracker();
                budgetTracker.setStoreName(storeName);


                List<BudgetTracker> viewModelStoreNameLists = budgetTrackerViewModel.getStoreNameLists(storeName);

                storeListViewAdapter = new StoreListViewAdapter(getActivity(), viewModelStoreNameLists);
                storeListView.setAdapter(storeListViewAdapter);

//                activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());

                storeListViewAdapter.setOnItemClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        List<BudgetTracker> list = viewModelStoreNameLists;

//                        for (BudgetTracker budgetTrackerList : list) {
//                            int index = 0;
//                            Log.d("TAG", "Current index is: " + budgetTrackerList + "," + index++ + "," + budgetTrackerList.getId());
//                        }

                        Toast.makeText(getActivity(), String.valueOf(viewModelStoreNameLists.get(view.getId())), Toast.LENGTH_SHORT).show();
                    }
                });


//                for (BudgetTracker budgetTrackerList : viewModelStoreNameLists) {
//                    Log.d("TAG", "onClick: " + budgetTrackerList.getId());
//                }

//                RecyclerViewの機能
//                //Tap された際の呼び出し
//                shopRecyclerViewAdapter.setOnItemClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        List<BudgetTracker> list = viewModelStoreNameLists;
//
//                        for (BudgetTracker budgetTrackerList : list) {
//                            int index = 0;
//                            Log.d("TAG", "Current index is: " + budgetTrackerList + "," + index++ + "," + budgetTrackerList.getId());
//                        }
//                        Toast.makeText(getActivity(), String.valueOf(viewModelStoreNameLists.get(view.getId())), Toast.LENGTH_SHORT).show();
//                    }
//                });

                Log.d("TAG", "onClick: " + enterStoreNameForQuery.getText().toString());

                for (BudgetTracker budgetTrackerList : viewModelStoreNameLists) {
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