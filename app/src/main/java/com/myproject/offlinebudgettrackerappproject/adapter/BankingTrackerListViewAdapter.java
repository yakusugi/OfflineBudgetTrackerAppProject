package com.myproject.offlinebudgettrackerappproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.myproject.offlinebudgettrackerappproject.R;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerBanking;

import java.util.List;

public class BankingTrackerListViewAdapter extends ArrayAdapter<BudgetTrackerBanking> {

    private static final Object INVALID_POSITION = -1;
    private LiveData<List<BudgetTrackerBanking>> budgetTrackerBankingList;
    private Context context;
    private View.OnClickListener listener;

//    public SpendingTrackerListViewAdapter(Context context, List<BudgetTrackerSpending> budgetTrackerSpendingList) {
//        super(context, R.layout.spending_tracker_list_item, budgetTrackerSpendingList);
//    }

    //TODO Someday, I need to adapt LiveData data transactions instead of the traditional List data transactions.
    public BankingTrackerListViewAdapter(List<BudgetTrackerBanking> contactList, Context context) {
        super(context, R.layout.bank_list_item, (List<BudgetTrackerBanking>) contactList);
    }

//    public SpendingTrackerListViewAdapter(List<BudgetTrackerSpending> contactList, Context context, OnContactClickListener contactClickListener) {
//        super();
//        this.contactList = contactList;
//        this.context = context;
//        this.contactClickListener = contactClickListener;
//    }

    public BankingTrackerListViewAdapter(Context context, int num) {
        super(context, num);
    }


    //When tapped
    public void setOnItemClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        BudgetTrackerBanking budgetTrackerBanking = getItem(position);


        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.bank_list_item, parent, false);
        }

        ImageView SpendingImageViewRow = convertView.findViewById(R.id.bank_circle_image_view);
        TextView BankingNameRow = convertView.findViewById(R.id.bank_name_text_row);
        TextView BankingBalanceRow = convertView.findViewById(R.id.bank_balance_text_row);


        BankingNameRow.setText(budgetTrackerBanking.getBankName());
        BankingBalanceRow.setText(String.valueOf(budgetTrackerBanking.getBankBalance()));

        return convertView;
    }
//
//    public interface OnContactClickListener {
//        void onContactClick(int position);
//    }

}
