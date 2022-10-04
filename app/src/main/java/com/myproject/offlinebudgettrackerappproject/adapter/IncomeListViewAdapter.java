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
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerIncome;

import java.util.List;

public class IncomeListViewAdapter extends ArrayAdapter<BudgetTrackerIncome> {

    private List<BudgetTrackerIncome> budgetTrackerIncomeCategoryList;
    private Context context;
    private View.OnClickListener listener;

    public IncomeListViewAdapter(Context context, List<BudgetTrackerIncome> budgetTrackerIncomeCategoryList) {
        super(context, R.layout.income_list_item, budgetTrackerIncomeCategoryList);

    }

    //When tapped
    public void setOnItemClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        BudgetTrackerIncome budgetTrackerIncome = getItem(position);


        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.income_list_item,parent,false);
        }

//        budgetTracker = new BudgetTracker();

//        budgetTracker = Objects.requireNonNull(budgetTrackerList.get(position));
//        if (budgetTracker.getStoreName().equals("Google Store") || budgetTracker.getStoreName().equals("Google")) {
//            Drawable myDrawable = context.getResources().getDrawable(R.drawable.search);
//            ImageView storeImageViewRow = convertView.findViewById(R.id.store_circle_image_view);
//            storeImageViewRow.setImageDrawable(myDrawable);
//
//        }

        ImageView incomeImageViewRow = convertView.findViewById(R.id.income_circle_image_view);
        TextView incomeCategoryRow = convertView.findViewById(R.id.income_category_text_row);
        TextView incomeDateRow = convertView.findViewById(R.id.income_date_text_row);
        TextView incomeAmountRow = convertView.findViewById(R.id.income_amount_text_row);

//        storeImageViewRow.setImageResource(budgetTracker.imageId);
        incomeCategoryRow.setText(budgetTrackerIncome.getCategory());
        incomeDateRow.setText(budgetTrackerIncome.getDate());
        incomeAmountRow.setText(String.valueOf(budgetTrackerIncome.getAmount()));

        return convertView;
    }
}
