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
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerIncomes;

import java.util.List;

public class IncomeReplacementListViewAdapter extends ArrayAdapter<BudgetTrackerIncomes> {

    private static final Object INVALID_POSITION = -1;
    private LiveData<List<BudgetTrackerIncomes>> budgetTrackerIncomesList;
    private Context context;
    private View.OnClickListener listener;

    public IncomeReplacementListViewAdapter(Context context, List<BudgetTrackerIncomes> budgetTrackerIncomesList) {
        super(context, R.layout.spending_replacement_list_item, budgetTrackerIncomesList);
    }

    //TODO Someday, I need to adapt LiveData data transactions instead of the traditional List data transactions.
    public IncomeReplacementListViewAdapter(Context context, LiveData<List<BudgetTracker>> budgetTrackerIncomesList) {
        super(context, R.layout.incomes_replacement_list_item, (List<BudgetTrackerIncomes>) budgetTrackerIncomesList);
    }

    public IncomeReplacementListViewAdapter(Context context, int num) {
        super(context, num);
    }


    //When tapped
    public void setOnItemClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        BudgetTrackerIncomes budgetTrackerIncomes = getItem(position);


        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.incomes_replacement_list_item, parent, false);
        }

        ImageView ReplacedImageViewRow = convertView.findViewById(R.id.replacement_incomes_circle_image_view);
        TextView ReplaceCategoryNameRow = convertView.findViewById(R.id.replacement_category_name_text_row);
        TextView ReplacedDateRow = convertView.findViewById(R.id.replacement_incomes_date_text_row);
        TextView ReplacedAmountRow = convertView.findViewById(R.id.replacement_amount_text_row);

        ReplacedImageViewRow.setImageResource(R.drawable.replace);
        ReplaceCategoryNameRow.setText(budgetTrackerIncomes.getCategory());
        ReplacedDateRow.setText(budgetTrackerIncomes.getDate());
        ReplacedAmountRow.setText(String.valueOf(budgetTrackerIncomes.getAmount()));

        return convertView;
    }

}
