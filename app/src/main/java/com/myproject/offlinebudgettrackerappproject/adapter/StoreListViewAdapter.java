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

import com.myproject.offlinebudgettrackerappproject.R;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTracker;

import java.util.List;

public class StoreListViewAdapter extends ArrayAdapter<BudgetTracker> {

    private List<BudgetTracker> budgetTrackerList;
    private Context context;
    private View.OnClickListener listener;

    public StoreListViewAdapter(Context context, List<BudgetTracker> budgetTrackerList) {
        super(context, R.layout.store_list_item, budgetTrackerList);

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
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.store_list_item,parent,false);
        }

//        budgetTracker = new BudgetTracker();

//        budgetTracker = Objects.requireNonNull(budgetTrackerList.get(position));
//        if (budgetTracker.getStoreName().equals("Google Store") || budgetTracker.getStoreName().equals("Google")) {
//            Drawable myDrawable = context.getResources().getDrawable(R.drawable.search);
//            ImageView storeImageViewRow = convertView.findViewById(R.id.store_circle_image_view);
//            storeImageViewRow.setImageDrawable(myDrawable);
//
//        }

        ImageView storeImageViewRow = convertView.findViewById(R.id.store_circle_image_view);
        TextView storeNameRow = convertView.findViewById(R.id.store_name_text_row);
        TextView storeDateRow = convertView.findViewById(R.id.store_date_text_row);
        TextView storeProductRow = convertView.findViewById(R.id.store_product_name_text_row);
        TextView storePriceRow = convertView.findViewById(R.id.store_price_text_row);

//        storeImageViewRow.setImageResource(budgetTracker.imageId);
        storeNameRow.setText(budgetTracker.getStoreName());
        storeDateRow.setText(budgetTracker.getDate());
        storeProductRow.setText(budgetTracker.getProductName());
        storePriceRow.setText(String.valueOf(budgetTracker.getPrice()));

        return convertView;
    }
}
