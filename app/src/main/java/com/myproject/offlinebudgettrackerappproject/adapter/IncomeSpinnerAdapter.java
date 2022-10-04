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
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerBank;

import java.util.List;

public class IncomeSpinnerAdapter extends ArrayAdapter<BudgetTrackerBank> {

    LayoutInflater layoutInflater;


    public IncomeSpinnerAdapter(@NonNull Context context, int resource, @NonNull List<BudgetTrackerBank> banks) {
        super(context, resource, banks);
        layoutInflater = LayoutInflater.from(context);
    }

    public IncomeSpinnerAdapter(Context context, List<BudgetTrackerBank> budgetTrackerBankList) {
        super(context, R.layout.income_spinner_adapter, budgetTrackerBankList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        BudgetTrackerBank budgetTrackerBank = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.income_spinner_adapter, parent, false);
        }

        View rowView = layoutInflater.inflate(R.layout.income_spinner_adapter, null, true);
        TextView bankNameRow = (TextView) rowView.findViewById(R.id.bank_spinner_text);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.bank_spinner_image);
        bankNameRow.setText(budgetTrackerBank.getBankName());
        return rowView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.income_spinner_adapter, parent, false);
        }

        BudgetTrackerBank budgetTrackerBank = getItem(position);
        TextView bankNameRow = (TextView) convertView.findViewById(R.id.bank_spinner_text);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.bank_spinner_image);
        bankNameRow.setText(budgetTrackerBank.getBankName());
        return convertView;
    }
}
