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

public class ProductListViewAdapter extends ArrayAdapter<BudgetTracker> {

    private static final Object INVALID_POSITION = -1;
    private LiveData<List<BudgetTracker>> budgetTrackerList;
    private Context context;
    private View.OnClickListener listener;

    public ProductListViewAdapter(Context context, List<BudgetTracker> budgetTrackerList) {
        super(context, R.layout.product_list_item, budgetTrackerList);
    }

    //TODO Someday, I need to adapt LiveData data transactions instead of the traditional List data transactions.
    public ProductListViewAdapter(Context context, LiveData<List<BudgetTracker>> budgetTrackerList) {
        super(context, R.layout.product_list_item, (List<BudgetTracker>) budgetTrackerList);
    }

    public ProductListViewAdapter(Context context, int num) {
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
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.product_list_item, parent, false);
        }

        ImageView productImageViewRow = convertView.findViewById(R.id.product_circle_image_view);
        TextView productStoreNameRow = convertView.findViewById(R.id.product_store_name_text_row);
        TextView productDateRow = convertView.findViewById(R.id.product_date_text_row);
        TextView productProductNameRow = convertView.findViewById(R.id.product_product_name_text_row);
        TextView productProductTypeRow = convertView.findViewById(R.id.product_product_type_text_row);
        TextView productPriceRow = convertView.findViewById(R.id.product_price_text_row);

        productImageViewRow.setImageResource(R.drawable.products);
        productStoreNameRow.setText(budgetTracker.getStoreName());
        productDateRow.setText(budgetTracker.getDate());
        productProductNameRow.setText(budgetTracker.getProductName());
        productProductTypeRow.setText(budgetTracker.getProductType());
        productPriceRow.setText(String.valueOf(budgetTracker.getPrice()));

        return convertView;
    }

}
