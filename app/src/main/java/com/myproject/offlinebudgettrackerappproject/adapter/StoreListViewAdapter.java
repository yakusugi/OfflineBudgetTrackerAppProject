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

    private static final Object INVALID_POSITION = -1;
    private List<BudgetTracker> budgetTrackerList;
    private Context context;
    private View.OnClickListener listener;
    private OnItemClickListener onItemClickListener;


    public StoreListViewAdapter(Context context, List<BudgetTracker> budgetTrackerList) {
        super(context, R.layout.store_list_item, budgetTrackerList);

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

        if (budgetTracker.getStoreName().equals("Google Store")) {
            storeImageViewRow.setImageResource(R.drawable.search);
        }
        storeNameRow.setText(budgetTracker.getStoreName());
        storeDateRow.setText(budgetTracker.getDate());
        storeProductRow.setText(budgetTracker.getProductName());
        storeProductTypeRow.setText(budgetTracker.getProductType());
        storePriceRow.setText(String.valueOf(budgetTracker.getPrice()));

        return convertView;
    }


    public static int getItemPosition(StoreListViewAdapter adapter, Object object) {
        return getItemPosition(adapter, INVALID_POSITION);
    }

    //March 28 itemClickListener
    public interface OnItemClickListener {
        void onItemCLick(BudgetTracker budgetTracker);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }
}
