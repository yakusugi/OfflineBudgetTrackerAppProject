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
import com.myproject.offlinebudgettrackerappproject.model.CurrencyConverter;

import java.util.List;

public class CurrencyConverterListViewAdapter extends ArrayAdapter<CurrencyConverter> {

    private static final Object INVALID_POSITION = -1;
    private LiveData<List<CurrencyConverter>> currencyConverterList;
    private Context context;
    private View.OnClickListener listener;

//    public SpendingTrackerListViewAdapter(Context context, List<BudgetTrackerSpending> budgetTrackerSpendingList) {
//        super(context, R.layout.spending_tracker_list_item, budgetTrackerSpendingList);
//    }

    //TODO Someday, I need to adapt LiveData data transactions instead of the traditional List data transactions.
    public CurrencyConverterListViewAdapter(List<CurrencyConverter> contactList, Context context) {
        super(context, R.layout.currency_converter_list_item, (List<CurrencyConverter>) contactList);
    }

    public CurrencyConverterListViewAdapter(Context context, int num) {
        super(context, num);
    }


    //When tapped
    public void setOnItemClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        CurrencyConverter currencyConverter = getItem(position);


        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.currency_converter_list_item, parent, false);
        }

        ImageView ConverterImageViewRow = convertView.findViewById(R.id.spending_circle_image_view);
        TextView ConverterBankNameRow = convertView.findViewById(R.id.converter_bank_name_text_row);
        TextView ConverterDateRow = convertView.findViewById(R.id.converter_date_text_row);
        TextView ConverterCurrentNameRow = convertView.findViewById(R.id.converter_original_currency_name_text_row);
        TextView ConverterCurrentBalanceRow = convertView.findViewById(R.id.converter_original_currency_balance_text_row);
        TextView ConverterTargetNameRow = convertView.findViewById(R.id.converter_target_currency_name_text_row);
        TextView ConverterTargetBalanceRow = convertView.findViewById(R.id.converter_target_currency_balance_text_row);

        ConverterBankNameRow.setText(currencyConverter.getBankName());
        ConverterDateRow.setText(currencyConverter.getDate());
        ConverterCurrentNameRow.setText(currencyConverter.getOriginalCurrency());
        ConverterCurrentBalanceRow.setText(String.valueOf(currencyConverter.getOriginalCurrencyBalance()));
        ConverterTargetNameRow.setText(String.valueOf(currencyConverter.getConvertedCurrency()));
        ConverterTargetBalanceRow.setText(String.valueOf(currencyConverter.getConvertedCurrencyBalance()));

        return convertView;
    }
//
//    public interface OnContactClickListener {
//        void onContactClick(int position);
//    }

}
