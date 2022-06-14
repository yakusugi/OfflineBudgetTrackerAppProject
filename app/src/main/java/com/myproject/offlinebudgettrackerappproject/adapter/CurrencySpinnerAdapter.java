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

import com.myproject.offlinebudgettrackerappproject.Currency;
import com.myproject.offlinebudgettrackerappproject.R;

import java.util.List;

public class CurrencySpinnerAdapter extends ArrayAdapter<Currency> {

    LayoutInflater layoutInflater;

    public CurrencySpinnerAdapter(@NonNull Context context, int resource, @NonNull List<Currency> currencies) {
        super(context, resource, currencies);
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View rowView = layoutInflater.inflate(R.layout.currency_spinner_adopter, null, true);
        Currency currency = getItem(position);
        TextView textView = (TextView) rowView.findViewById(R.id.currency_name_textview);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.currency_symbol);
        textView.setText(currency.getCurrency());
        imageView.setImageResource(currency.getCurrencyImage());
        return rowView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.currency_spinner_adopter, parent, false);
        }
        Currency currency = getItem(position);
        TextView textView = (TextView) convertView.findViewById(R.id.currency_name_textview);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.currency_symbol);
        textView.setText(currency.getCurrency());
        imageView.setImageResource(currency.getCurrencyImage());


        return convertView;
    }
}
