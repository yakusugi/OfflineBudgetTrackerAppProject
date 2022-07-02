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

public class ReplacedListViewAdapter extends ArrayAdapter<BudgetTracker> {

    private static final Object INVALID_POSITION = -1;
    private LiveData<List<BudgetTracker>> budgetTrackerList;
    private Context context;
    private View.OnClickListener listener;

    public ReplacedListViewAdapter(Context context, List<BudgetTracker> budgetTrackerList) {
        super(context, R.layout.replaced_list_item, budgetTrackerList);
    }

    //TODO Someday, I need to adapt LiveData data transactions instead of the traditional List data transactions.
    public ReplacedListViewAdapter(Context context, LiveData<List<BudgetTracker>> budgetTrackerList) {
        super(context, R.layout.replaced_list_item, (List<BudgetTracker>) budgetTrackerList);
    }

    public ReplacedListViewAdapter(Context context, int num) {
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
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.replaced_list_item, parent, false);
        }

        ImageView ReplacedImageViewRow = convertView.findViewById(R.id.replaced_circle_image_view);
        TextView ReplacedStoreNameRow = convertView.findViewById(R.id.replaced_store_name_text_row);
        TextView ReplacedDateRow = convertView.findViewById(R.id.replaced_date_text_row);
        TextView ReplacedProductRow = convertView.findViewById(R.id.replaced_product_name_text_row);
        TextView ReplacedProductTypeRow = convertView.findViewById(R.id.replaced_product_type_text_row);
        TextView ReplacedPriceRow = convertView.findViewById(R.id.replaced_price_text_row);

        if (budgetTracker.getStoreName().equals("Google Store")) {

        }
        ReplacedImageViewRow.setImageResource(R.drawable.replace);
        ReplacedStoreNameRow.setText(budgetTracker.getStoreName());
        ReplacedDateRow.setText(budgetTracker.getDate());
        ReplacedProductRow.setText(budgetTracker.getProductName());
        ReplacedProductTypeRow.setText(budgetTracker.getProductType());
        ReplacedPriceRow.setText(String.valueOf(budgetTracker.getPrice()));

        return convertView;
    }

}
