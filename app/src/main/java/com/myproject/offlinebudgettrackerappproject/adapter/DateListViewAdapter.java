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
import com.myproject.offlinebudgettrackerappproject.model.BudgetTracker;

import java.util.List;

public class DateListViewAdapter extends ArrayAdapter<BudgetTracker> {

    private static final Object INVALID_POSITION = -1;
    private LiveData<List<BudgetTracker>> budgetTrackerList;
    private Context context;
    private View.OnClickListener listener;

    public DateListViewAdapter(Context context, List<BudgetTracker> budgetTrackerList) {
        super(context, R.layout.date_list_item, budgetTrackerList);
    }

    //TODO Someday, I need to adapt LiveData data transactions instead of the traditional List data transactions.
    public DateListViewAdapter(Context context, LiveData<List<BudgetTracker>> budgetTrackerList) {
        super(context, R.layout.date_list_item, (List<BudgetTracker>) budgetTrackerList);
    }

    public DateListViewAdapter(Context context, int num) {
        super(context, num);
    }

    //When tapped
    public void setOnItemClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        BudgetTracker budgetTracker = getItem(position);


        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.date_list_item, parent, false);
        }

        ImageView dateImageViewRow = convertView.findViewById(R.id.date_circle_image_view);
        TextView dateStoreNameRow = convertView.findViewById(R.id.date_store_name_text_row);
        TextView dateDateRow = convertView.findViewById(R.id.date_date_text_row);
        TextView dateProductRow = convertView.findViewById(R.id.date_product_name_text_row);
        TextView dateProductTypeRow = convertView.findViewById(R.id.date_product_type_text_row);
        TextView datePriceRow = convertView.findViewById(R.id.date_price_text_row);

        if (budgetTracker.getStoreName().equals("Google Store")) {
            dateImageViewRow.setImageResource(R.drawable.search);
        }
        dateStoreNameRow.setText(budgetTracker.getStoreName());
        dateDateRow.setText(budgetTracker.getDate());
        dateProductRow.setText(budgetTracker.getProductName());
        dateProductTypeRow.setText(budgetTracker.getProductType());
        datePriceRow.setText(String.valueOf(budgetTracker.getPrice()));

        return convertView;
    }

}
