package com.myproject.offlinebudgettrackerappproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DashboardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DashboardFragment extends Fragment implements View.OnClickListener {

    private CardView incomeCard, bankCard, settingsCard, aboutCard, replaceCard;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DashboardFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DashboardFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DashboardFragment newInstance(String param1, String param2) {
        DashboardFragment fragment = new DashboardFragment();
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
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        incomeCard = (CardView) view.findViewById(R.id.income_card);
        bankCard = (CardView) view.findViewById(R.id.bank_card);
        settingsCard = (CardView) view.findViewById(R.id.settings_card);
        aboutCard = (CardView) view.findViewById(R.id.about_card);
        replaceCard = (CardView) view.findViewById(R.id.replace_card);

        incomeCard.setOnClickListener(this);
        bankCard.setOnClickListener(this);
        settingsCard.setOnClickListener(this);
        aboutCard.setOnClickListener(this);
        replaceCard.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        Intent i;
        switch (view.getId()) {
            case R.id.income_card:
                i = new Intent(view.getContext(), BudgetTrackerIncomeActivity.class);
                startActivity(i);
                break;
            case R.id.bank_card:
                i = new Intent(view.getContext(), BudgetTrackerBankActivity.class);
                startActivity(i);
                break;
            case R.id.replace_card:
                i = new Intent(view.getContext(), ReplaceActivity.class);
                startActivity(i);
                break;
            case R.id.settings_card:
                i = new Intent(view.getContext(), BudgetTrackerSettingsActivity.class);
                startActivity(i);
                break;
            case R.id.about_card:
                i = new Intent(view.getContext(), AboutActivity.class);
                startActivity(i);
                break;
        }

    }
}