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

public class BankNameListViewAdapter extends ArrayAdapter<BudgetTrackerBank> {

    private List<BudgetTrackerBank> budgetTrackerBankNameList;
    private Context context;
    private View.OnClickListener listener;

    public BankNameListViewAdapter(Context context, List<BudgetTrackerBank> budgetTrackerBankNameList) {
        super(context, R.layout.bank_list_item, budgetTrackerBankNameList);

    }

    //When tapped
    public void setOnItemClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        BudgetTrackerBank budgetTrackerBank = getItem(position);


        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.bank_list_item,parent,false);
        }

//        budgetTracker = new BudgetTracker();

//        budgetTracker = Objects.requireNonNull(budgetTrackerList.get(position));
//        if (budgetTracker.getStoreName().equals("Google Store") || budgetTracker.getStoreName().equals("Google")) {
//            Drawable myDrawable = context.getResources().getDrawable(R.drawable.search);
//            ImageView storeImageViewRow = convertView.findViewById(R.id.store_circle_image_view);
//            storeImageViewRow.setImageDrawable(myDrawable);
//
//        }

        ImageView bankImageViewRow = convertView.findViewById(R.id.bank_circle_image_view);
        TextView bankNameRow = convertView.findViewById(R.id.bank_name_text_row);
        TextView bankBalanceRow = convertView.findViewById(R.id.bank_balance_text_row);

//        bankImageViewRow.setImageResource(budgetTracker.imageId);
        bankNameRow.setText(budgetTrackerBank.getBankName());
        bankBalanceRow.setText(String.valueOf(budgetTrackerBank.getBankBalance()));

        return convertView;
    }
}
