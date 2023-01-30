package com.myproject.offlinebudgettrackerappproject.model;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.myproject.offlinebudgettrackerappproject.data.MysqlInsertRepository;

public class MysqlInsertViewModel extends AndroidViewModel {
    private MysqlInsertRepository repository;
    private Context context;

    public MysqlInsertViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public void registrationInsert(MysqlRegistration mysqlRegistration) {
        repository = new MysqlInsertRepository(mysqlRegistration, context);
        repository.registrationInsert(mysqlRegistration);
    }

}
