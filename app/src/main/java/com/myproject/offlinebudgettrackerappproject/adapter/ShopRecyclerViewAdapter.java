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

public class ShopRecyclerViewAdapter extends RecyclerView.Adapter<ShopRecyclerViewAdapter.ViewHolder> {
    private List<BudgetTracker> budgetTrackerList;
    private Context context;
    private View.OnClickListener listener;

    public ShopRecyclerViewAdapter(List<BudgetTracker> budgetTrackerList, Context context) {
        this.budgetTrackerList = (List<BudgetTracker>) budgetTrackerList;
        this.context = context;
    }

    @NonNull
    @Override
    public ShopRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.budget_tracker_store_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopRecyclerViewAdapter.ViewHolder holder, int position) {
        BudgetTracker budgetTracker = Objects.requireNonNull(budgetTrackerList.get(position));
        if (budgetTracker.getStoreName().equals("Google Store") || budgetTracker.getStoreName().equals("Google")) {
            Drawable myDrawable = context.getResources().getDrawable(R.drawable.search);
            holder.storeImageView.setImageDrawable(myDrawable);
        }
        holder.shopDateRow.setText(budgetTracker.getDate());
        holder.shopStoreNameRow.setText(budgetTracker.getStoreName());
        holder.shopProductNameRow.setText(budgetTracker.getProductName());
        holder.shopProductTypeRow.setText(budgetTracker.getProductType());
        holder.shopPriceRow.setText(String.valueOf(budgetTracker.getPrice()));
//        When tapped


    }

    //     Java 実装 When tapped
    public void setOnItemClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return Objects.requireNonNull(budgetTrackerList.size());
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView storeImageView;
        TextView shopDateRow;
        TextView shopStoreNameRow;
        TextView shopProductNameRow;
        TextView shopProductTypeRow;
        TextView shopPriceRow;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            storeImageView = itemView.findViewById(R.id.store_row_image);
            shopDateRow = itemView.findViewById(R.id.store_row_date);
            shopStoreNameRow = itemView.findViewById(R.id.store_row_store_name);
            shopProductNameRow = itemView.findViewById(R.id.store_row_product_name);
            shopProductTypeRow = itemView.findViewById(R.id.store_row_product_type);
            shopPriceRow = itemView.findViewById(R.id.store_row_price);
        }
    }


}
