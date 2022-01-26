package com.myproject.offlinebudgettrackerappproject.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.myproject.offlinebudgettrackerappproject.R;
import com.myproject.offlinebudgettrackerappproject.model.BudgetTracker;

import java.util.List;
import java.util.Objects;

public class ProductRecyclerViewAdapter extends RecyclerView.Adapter<ProductRecyclerViewAdapter.ViewHolder> {
    private List<BudgetTracker> budgetTrackerList;
    private Context context;

    public ProductRecyclerViewAdapter(List<BudgetTracker> budgetTrackerList, Context context) {
        this.budgetTrackerList = (List<BudgetTracker>) budgetTrackerList;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.budget_tracker_product_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BudgetTracker budgetTracker = Objects.requireNonNull(budgetTrackerList.get(position));
        if (budgetTracker.getProductType() == "gadgets" || budgetTracker.getProductType() == "Gadgets") {
            Drawable myDrawable = context.getResources().getDrawable(R.drawable.gadget);
            holder.productImageView.setImageDrawable(myDrawable);
        }
        holder.productDateRow.setText(budgetTracker.getDate());
        holder.productStoreNameRow.setText(budgetTracker.getStoreName());
        holder.productProductNameRow.setText(budgetTracker.getProductName());
        holder.productProductTypeRow.setText(budgetTracker.getProductType());
        holder.productPriceRow.setText(String.valueOf(budgetTracker.getPrice()));

    }


    @Override
    public int getItemCount() {
        return Objects.requireNonNull(budgetTrackerList.size());
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView productImageView;
        TextView productDateRow;
        TextView productStoreNameRow;
        TextView productProductNameRow;
        TextView productProductTypeRow;
        TextView productPriceRow;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productImageView = itemView.findViewById(R.id.product_row_image);
            productDateRow = itemView.findViewById(R.id.product_row_date);
            productStoreNameRow = itemView.findViewById(R.id.product_row_store_name);
            productProductNameRow = itemView.findViewById(R.id.product_row_product_name);
            productProductTypeRow = itemView.findViewById(R.id.product_row_product_type);
            productPriceRow = itemView.findViewById(R.id.product_row_price);
        }
    }
}
