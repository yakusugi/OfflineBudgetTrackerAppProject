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
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerSpending;

import java.util.List;

public class SpendingReplacementListViewAdapter extends ArrayAdapter<BudgetTrackerSpending> {

    private static final Object INVALID_POSITION = -1;
    private LiveData<List<BudgetTrackerSpending>> budgetTrackerSpendingList;
    private Context context;
    private View.OnClickListener listener;

    public SpendingReplacementListViewAdapter(Context context, List<BudgetTrackerSpending> budgetTrackerSpendingList) {
        super(context, R.layout.spending_replacement_list_item, budgetTrackerSpendingList);
    }

    //TODO Someday, I need to adapt LiveData data transactions instead of the traditional List data transactions.
    public SpendingReplacementListViewAdapter(Context context, LiveData<List<BudgetTracker>> budgetTrackerList) {
        super(context, R.layout.spending_replacement_list_item, (List<BudgetTrackerSpending>) budgetTrackerList);
    }

    public SpendingReplacementListViewAdapter(Context context, int num) {
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
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.spending_replacement_list_item, parent, false);
        }

        ImageView ReplacedImageViewRow = convertView.findViewById(R.id.replacement_circle_image_view);
        TextView ReplacedStoreNameRow = convertView.findViewById(R.id.replacement_store_name_text_row);
        TextView ReplacedDateRow = convertView.findViewById(R.id.replacement_date_text_row);
        TextView ReplacedProductRow = convertView.findViewById(R.id.replacement_product_name_text_row);
        TextView ReplacedProductTypeRow = convertView.findViewById(R.id.replacement_product_type_text_row);
        TextView ReplacedPriceRow = convertView.findViewById(R.id.replacement_price_text_row);
        TextView ReplacedVatRow = convertView.findViewById(R.id.replacement_vat_text_row);

        ReplacedImageViewRow.setImageResource(R.drawable.replace);
        ReplacedStoreNameRow.setText(budgetTrackerSpending.getStoreName());
        ReplacedDateRow.setText(budgetTrackerSpending.getDate());
        ReplacedProductRow.setText(budgetTrackerSpending.getProductName());
        ReplacedProductTypeRow.setText(budgetTrackerSpending.getProductType());
        ReplacedPriceRow.setText(String.valueOf(budgetTrackerSpending.getPrice()));
        ReplacedVatRow.setText(String.valueOf(budgetTrackerSpending.getTaxRate()));

        return convertView;
    }

}
