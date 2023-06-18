package com.myproject.offlinebudgettrackerappproject.model;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.myproject.offlinebudgettrackerappproject.data.MysqlRegistrationRepository;

import java.util.List;

public class MysqlRegistrationViewModel extends AndroidViewModel {
    private MysqlRegistrationRepository repository;
    private Context context;

    public MysqlRegistrationViewModel(@NonNull Application application) {
        super(application);
        repository = new MysqlRegistrationRepository();
    }

    public void sendSearchedSpendingList(List<BudgetTrackerSpending> spendingList) {
        repository.sendSearchedSpendingList(spendingList);
    }
}
