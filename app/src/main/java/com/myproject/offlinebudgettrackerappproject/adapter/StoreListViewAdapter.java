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

public class StoreListViewAdapter extends ArrayAdapter<BudgetTracker> {

    private static final Object INVALID_POSITION = -1;
    private LiveData<List<BudgetTracker>> budgetTrackerList;
    private List<BudgetTracker> budgetTrackerRefreshList;
    private Context context;
    private View.OnClickListener listener;

    public StoreListViewAdapter(Context context, List<BudgetTracker> budgetTrackerList) {
        super(context, R.layout.store_list_item, budgetTrackerList);
    }

    //TODO Someday, I need to adapt LiveData data transactions instead of the traditional List data transactions.
    public StoreListViewAdapter(Context context, LiveData<List<BudgetTracker>> budgetTrackerList) {
        super(context, R.layout.store_list_item, (List<BudgetTracker>) budgetTrackerList);
    }

    public StoreListViewAdapter(Context context, int num) {
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
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.store_list_item, parent, false);
        }

        ImageView storeImageViewRow = convertView.findViewById(R.id.store_circle_image_view);
        TextView storeNameRow = convertView.findViewById(R.id.store_name_text_row);
        TextView storeDateRow = convertView.findViewById(R.id.store_date_text_row);
        TextView storeProductRow = convertView.findViewById(R.id.store_product_name_text_row);
        TextView storeProductTypeRow = convertView.findViewById(R.id.store_product_type_text_row);
        TextView storePriceRow = convertView.findViewById(R.id.store_price_text_row);

        storeImageViewRow.setImageResource(R.drawable.store);
        storeNameRow.setText(budgetTracker.getStoreName());
        storeDateRow.setText(budgetTracker.getDate());
        storeProductRow.setText(budgetTracker.getProductName());
        storeProductTypeRow.setText(budgetTracker.getProductType());
        storePriceRow.setText(String.valueOf(budgetTracker.getPrice()));

        return convertView;
    }

    public void refresh(List<BudgetTracker> budgetTrackerRefreshList){
        this.budgetTrackerRefreshList.clear();
        notifyDataSetChanged();
    }

}
