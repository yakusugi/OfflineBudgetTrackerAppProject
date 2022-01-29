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

import com.myproject.offlinebudgettrackerappproject.model.BudgetTracker;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DateFragment extends Fragment {

    BudgetTrackerViewModel budgetTrackerViewModel;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DateFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DateFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DateFragment newInstance(String param1, String param2) {
        DateFragment fragment = new DateFragment();
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
        View view = inflater.inflate(R.layout.fragment_date, container, false);
        EditText enterDateForQuery1 = (EditText) view.findViewById(R.id.date_search_txt1);
        EditText enterDateForQuery2 = (EditText) view.findViewById(R.id.date_search_txt2);
        Button dateSearchQueryBtn = (Button) view.findViewById(R.id.btn_date_search);

        dateSearchQueryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BudgetTracker budgetTracker;

                String date1 = enterDateForQuery1.getText().toString();
                String date2 = enterDateForQuery2.getText().toString();
                budgetTrackerViewModel = new ViewModelProvider(requireActivity()).get(BudgetTrackerViewModel.class);
//                budgetTrackerViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(
//                        this.getApplication()).create(CanteensViewModel.class);

                budgetTracker = new BudgetTracker();
//                budgetTracker.setStoreName(productName);

//                budgetTrackerViewModel.getStoreNameLists(storeName);

                List<BudgetTracker> viewModelStoreNameLists = budgetTrackerViewModel.getDateLists(date1, date2);

                Log.d("TAG", "onClick: " + enterDateForQuery1.getText().toString());
                Log.d("TAG", "onClick: " + enterDateForQuery2.getText().toString());

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