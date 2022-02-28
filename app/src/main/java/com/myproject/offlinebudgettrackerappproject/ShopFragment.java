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
public class ShopFragment extends Fragment implements AdapterView.OnItemClickListener {

    private static final String TAG = "Clicked";
    BudgetTrackerViewModel budgetTrackerViewModel;
//    private ShopRecyclerViewAdapter shopRecyclerViewAdapter;
    private StoreListViewAdapter storeListViewAdapter;
//    private RecyclerView storeRecyclerView;
    private ListView storeListView;
    private List<BudgetTracker> budgetTrackerList;
    List<BudgetTracker> viewModelStoreNameLists;
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
        storeListViewAdapter = new StoreListViewAdapter(getActivity(), budgetTrackerList);

        BaseAdapter adapter = new StoreListViewAdapter(getActivity(), budgetTrackerList);



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


                viewModelStoreNameLists = budgetTrackerViewModel.getStoreNameLists(storeName);

                storeListViewAdapter = new StoreListViewAdapter(getActivity(), viewModelStoreNameLists);
                storeListView.setAdapter(storeListViewAdapter);
//                storeListView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Toast.makeText(getActivity(), String.valueOf(viewModelStoreNameLists.get(view.getId())), Toast.LENGTH_SHORT).show();
//                    }
//                });

//                activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());

                //When tapped
                storeListViewAdapter.setOnItemClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        List<BudgetTracker> list = viewModelStoreNameLists;

                        Toast.makeText(getActivity(), String.valueOf(viewModelStoreNameLists.get(view.getId())), Toast.LENGTH_SHORT).show();
                    }
                });

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

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        ListView listView = (ListView) view.findViewById(R.id.store_listview);
//        storeListViewAdapter = new StoreListViewAdapter(getActivity(), viewModelStoreNameLists);
//        listView.setAdapter(storeListViewAdapter);

//        ItemClicked item = storeListViewAdapter.getItemPosition(storeListViewAdapter, position);

        int itemPosition = position;
        String itemValue = (String) listView.getItemAtPosition(position);
        Toast.makeText(getActivity(),
                "Position :"+itemPosition+" ListItem : " +itemValue , Toast.LENGTH_LONG)
                .show();

        BudgetTracker item = storeListViewAdapter.getItem(position);
        Toast.makeText(getActivity(), item + " selected", Toast.LENGTH_LONG).show();

        StoreListViewAdapter storeListViewAdapter = new StoreListViewAdapter(getActivity(), viewModelStoreNameLists);
        listView.setAdapter(storeListViewAdapter);
        storeListViewAdapter.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getActivity(), String.valueOf(viewModelStoreNameLists.get(view.getId())), Toast.LENGTH_SHORT).show();
            }
        });

        Intent intent = new Intent(getActivity(), NewBudgetTracker.class);

        // clickされたpositionのtextとphotoのID
        BudgetTracker budgetTrackerList = (BudgetTracker) viewModelStoreNameLists;
        int listId = budgetTrackerList.getId();
        String listDate = budgetTrackerList.getDate();
        String listStoreName = budgetTrackerList.getStoreName();
        String listProductName = budgetTrackerList.getProductName();
        String listProductType = budgetTrackerList.getProductType();
        int listPrice = budgetTrackerList.getPrice();

        // インテントにセット
        intent.putExtra("list_id", listId);
        intent.putExtra("list_date", listDate);
        intent.putExtra("list_store_name", listStoreName);
        intent.putExtra("list_product_name", listProductName);
        intent.putExtra("list_product_type", listProductType);
        intent.putExtra("list_price", listPrice);

        // NewBudgetTrackerへ遷移
        startActivity(intent);
    }
}