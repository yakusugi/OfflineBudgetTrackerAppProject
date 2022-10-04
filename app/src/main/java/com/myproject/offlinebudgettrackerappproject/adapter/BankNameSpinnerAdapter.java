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
import com.myproject.offlinebudgettrackerappproject.model.BudgetTrackerBanking;

import java.util.List;

public class BankNameSpinnerAdapter extends ArrayAdapter<BudgetTrackerBanking> {

    LayoutInflater layoutInflater;


    public BankNameSpinnerAdapter(@NonNull Context context, int resource, @NonNull List<BudgetTrackerBanking> banks) {
        super(context, resource, banks);
        layoutInflater = LayoutInflater.from(context);
    }

    public BankNameSpinnerAdapter(Context context, List<BudgetTrackerBanking> budgetTrackerBankingList) {
        super(context, R.layout.income_spinner_adapter, budgetTrackerBankingList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        BudgetTrackerBanking budgetTrackerBanking = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.income_spinner_adapter, parent, false);
        }

        View rowView = layoutInflater.inflate(R.layout.income_spinner_adapter, null, true);
        TextView bankNameRow = (TextView) rowView.findViewById(R.id.bank_spinner_text);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.bank_spinner_image);
        bankNameRow.setText(budgetTrackerBanking.getBankName());
        return rowView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.income_spinner_adapter, parent, false);
        }

        BudgetTrackerBanking budgetTrackerBanking = getItem(position);
        TextView bankNameRow = (TextView) convertView.findViewById(R.id.bank_spinner_text);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.bank_spinner_image);
        bankNameRow.setText(budgetTrackerBanking.getBankName());
        return convertView;
    }
}
