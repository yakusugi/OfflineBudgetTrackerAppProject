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
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerSpending;

import java.util.List;

public class SpendingTrackerListViewAdapter extends ArrayAdapter<BudgetTrackerSpending> {

    private static final Object INVALID_POSITION = -1;
    private LiveData<List<BudgetTrackerSpending>> budgetTrackerSpendingList;
    private Context context;
    private View.OnClickListener listener;

//    public SpendingTrackerListViewAdapter(Context context, List<BudgetTrackerSpending> budgetTrackerSpendingList) {
//        super(context, R.layout.spending_tracker_list_item, budgetTrackerSpendingList);
//    }

    //TODO Someday, I need to adapt LiveData data transactions instead of the traditional List data transactions.
    public SpendingTrackerListViewAdapter(List<BudgetTrackerSpending> contactList, Context context) {
        super(context, R.layout.spending_tracker_list_item, (List<BudgetTrackerSpending>) contactList);
    }

//    public SpendingTrackerListViewAdapter(List<BudgetTrackerSpending> contactList, Context context, OnContactClickListener contactClickListener) {
//        super();
//        this.contactList = contactList;
//        this.context = context;
//        this.contactClickListener = contactClickListener;
//    }

    public SpendingTrackerListViewAdapter(Context context, int num) {
        super(context, num);
    }


    //When tapped
    public void setOnItemClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        BudgetTrackerSpending budgetTrackerSpending = getItem(position);


        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.spending_tracker_list_item, parent, false);
        }

        ImageView SpendingImageViewRow = convertView.findViewById(R.id.spending_circle_image_view);
        TextView SpendingStoreNameRow = convertView.findViewById(R.id.spending_store_name_text_row);
        TextView SpendingDateRow = convertView.findViewById(R.id.spending_date_text_row);
        TextView SpendingProductRow = convertView.findViewById(R.id.spending_product_name_text_row);
        TextView SpendingProductTypeRow = convertView.findViewById(R.id.spending_product_type_text_row);
        TextView SpendingPriceRow = convertView.findViewById(R.id.spending_price_text_row);
        TextView SpendingVatRow = convertView.findViewById(R.id.spending_vat_text_row);
        
        SpendingStoreNameRow.setText(budgetTrackerSpending.getStoreName());
        SpendingDateRow.setText(budgetTrackerSpending.getDate());
        SpendingProductRow.setText(budgetTrackerSpending.getProductName());
        SpendingProductTypeRow.setText(budgetTrackerSpending.getProductType());
        SpendingPriceRow.setText(String.valueOf(budgetTrackerSpending.getPrice()));
        SpendingVatRow.setText(String.valueOf(budgetTrackerSpending.getTaxRate()));

        return convertView;
    }
//
//    public interface OnContactClickListener {
//        void onContactClick(int position);
//    }

}
