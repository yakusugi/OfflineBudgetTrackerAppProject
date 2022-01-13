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

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {
    private LiveData<List<BudgetTracker>> budgetTrackerList;
    private Context context;

    public RecycleViewAdapter(LiveData<List<BudgetTracker>> budgetTrackerList, Context context) {
        this.budgetTrackerList = budgetTrackerList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.budget_tracker_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BudgetTracker budgetTracker = Objects.requireNonNull(budgetTrackerList.getValue()).get(position);
        holder.date.setText(budgetTracker.getDate());
        holder.productName.setText(budgetTracker.getProductName());
        holder.price.setText(budgetTracker.getPrice());

    }

    @Override
    public int getItemCount() {
        return Objects.requireNonNull(budgetTrackerList.getValue()).size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView date;
        public TextView productName;
        public TextView price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.row_date_textview);
            productName = itemView.findViewById(R.id.row_product_name_textview);
            price = itemView.findViewById(R.id.row_price_textview);
        }
    }
}
