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
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerIncomes;

import java.util.List;

public class IncomesTrackerListViewAdapter extends ArrayAdapter<BudgetTrackerIncomes> {

    private static final Object INVALID_POSITION = -1;
    private LiveData<List<BudgetTrackerIncomes>> budgetTrackerIncomesList;
    private Context context;
    private View.OnClickListener listener;

//    public SpendingTrackerListViewAdapter(Context context, List<BudgetTrackerSpending> budgetTrackerSpendingList) {
//        super(context, R.layout.spending_tracker_list_item, budgetTrackerSpendingList);
//    }

    //TODO Someday, I need to adapt LiveData data transactions instead of the traditional List data transactions.
    public IncomesTrackerListViewAdapter(List<BudgetTrackerIncomes> contactList, Context context) {
        super(context, R.layout.income_list_item, (List<BudgetTrackerIncomes>) contactList);
    }

//    public SpendingTrackerListViewAdapter(List<BudgetTrackerSpending> contactList, Context context, OnContactClickListener contactClickListener) {
//        super();
//        this.contactList = contactList;
//        this.context = context;
//        this.contactClickListener = contactClickListener;
//    }

    public IncomesTrackerListViewAdapter(Context context, int num) {
        super(context, num);
    }


    //When tapped
    public void setOnItemClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        BudgetTrackerIncomes budgetTrackerIncomes = getItem(position);


        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.income_list_item, parent, false);
        }

        ImageView IncomesImageViewRow = convertView.findViewById(R.id.income_circle_image_view);
        TextView IncomesCategoryRow = convertView.findViewById(R.id.income_category_text_row);
        TextView IncomesDateRow = convertView.findViewById(R.id.income_date_text_row);
        TextView IncomesAmountRow = convertView.findViewById(R.id.income_amount_text_row);


        IncomesCategoryRow.setText(budgetTrackerIncomes.getCategory());
        IncomesDateRow.setText(budgetTrackerIncomes.getDate());
        IncomesAmountRow.setText(String.valueOf(budgetTrackerIncomes.getAmount()));

        return convertView;
    }
//
//    public interface OnContactClickListener {
//        void onContactClick(int position);
//    }

}
