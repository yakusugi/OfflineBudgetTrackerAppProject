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

public class ProductNameSearchListViewAdapter extends ArrayAdapter<BudgetTrackerSpending> {

    private static final Object INVALID_POSITION = -1;
    private LiveData<List<BudgetTrackerSpending>> budgetTrackerSpendingList;
    private Context context;
    private View.OnClickListener listener;

    public ProductNameSearchListViewAdapter(Context context, List<BudgetTrackerSpending> budgetTrackerSpendingList) {
        super(context, R.layout.search_product_name_list_item, budgetTrackerSpendingList);
    }

    //TODO Someday, I need to adapt LiveData data transactions instead of the traditional List data transactions.
    public ProductNameSearchListViewAdapter(Context context, LiveData<List<BudgetTrackerSpending>> budgetTrackerSpendingList) {
        super(context, R.layout.search_product_name_list_item, (List<BudgetTrackerSpending>) budgetTrackerSpendingList);
    }

    public ProductNameSearchListViewAdapter(Context context, int num) {
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
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.search_product_name_list_item, parent, false);
        }

        ImageView searchImageViewRow = convertView.findViewById(R.id.product_name_circle_image_view);
        TextView searchProductRow = convertView.findViewById(R.id.product_name_product_name_text_row);
        TextView searchProductTypeRow = convertView.findViewById(R.id.product_name_product_type_text_row);
        TextView searchDateRow = convertView.findViewById(R.id.product_name_date_text_row);
        TextView searchStoreNameRow = convertView.findViewById(R.id.product_name_store_name_text_row);
        TextView searchPriceRow = convertView.findViewById(R.id.product_name_price_text_row);
        TextView searchVatRow = convertView.findViewById(R.id.product_name_vat_text_row);

        searchImageViewRow.setImageResource(R.drawable.products);
        searchStoreNameRow.setText(budgetTrackerSpending.getStoreName());
        searchDateRow.setText(budgetTrackerSpending.getDate());
        searchProductRow.setText(budgetTrackerSpending.getProductName());
        searchProductTypeRow.setText(budgetTrackerSpending.getProductType());
        searchPriceRow.setText(String.valueOf(budgetTrackerSpending.getPrice()));
        searchVatRow.setText(String.valueOf(budgetTrackerSpending.getTaxRate()));

        return convertView;
    }

}
