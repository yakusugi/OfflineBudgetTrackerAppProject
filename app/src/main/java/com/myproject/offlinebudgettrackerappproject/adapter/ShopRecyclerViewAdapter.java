package com.myproject.offlinebudgettrackerappproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.myproject.offlinebudgettrackerappproject.R;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTracker;

import java.util.List;
import java.util.Objects;

public class ShopRecyclerViewAdapter extends RecyclerView.Adapter<ShopRecyclerViewAdapter.ViewHolder> {
    private LiveData<List<BudgetTracker>> budgetTrackerList;
    private Context context;

    public ShopRecyclerViewAdapter(LiveData<List<BudgetTracker>> budgetTrackerList, Context context) {
        this.budgetTrackerList = (LiveData<List<BudgetTracker>>) budgetTrackerList;
        this.context = context;
    }

    @NonNull
    @Override
    public ShopRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.budget_tracker_shop_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopRecyclerViewAdapter.ViewHolder holder, int position) {
        BudgetTracker budgetTracker = Objects.requireNonNull(budgetTrackerList.getValue()).get(position);
        holder.shopDateRow.setText(budgetTracker.getDate());
        holder.shopStoreNameRow.setText(budgetTracker.getStoreName());
        holder.shopProductNameRow.setText(budgetTracker.getProductName());
        holder.shopProductTypeRow.setText(budgetTracker.getProductType());
        holder.shopPriceRow.setText(budgetTracker.getPrice());

    }


    @Override
    public int getItemCount() {
        return Objects.requireNonNull(budgetTrackerList.getValue()).size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView shopDateRow;
        TextView shopStoreNameRow;
        TextView shopProductNameRow;
        TextView shopProductTypeRow;
        TextView shopPriceRow;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            shopDateRow = itemView.findViewById(R.id.shop_row_date);
            shopStoreNameRow = itemView.findViewById(R.id.shop_row_store_name);
            shopProductNameRow = itemView.findViewById(R.id.shop_row_product_name);
            shopProductTypeRow = itemView.findViewById(R.id.shop_row_product_type);
            shopPriceRow = itemView.findViewById(R.id.shop_row_price);
        }
    }
}
