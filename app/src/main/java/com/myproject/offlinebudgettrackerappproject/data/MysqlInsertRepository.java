package com.myproject.offlinebudgettrackerappproject.data;

import android.content.Context;

import com.myproject.offlinebudgettrackerappproject.model.MysqlRegistration;

public class MysqlInsertRepository {
    private MysqlInsertDao mysqlInsertDao;
    private final String userId;
    private final String userPassword;
    private Context context;

    public MysqlInsertRepository(MysqlRegistration mysqlRegistration, Context context) {
        this.userId = mysqlRegistration.getUserId();
        this.userPassword = mysqlRegistration.getPassword();
        this.context = context;
        mysqlInsertDao = new MysqlInsertDao();
    }

    public void registrationInsert(MysqlRegistration mysqlRegistration) {
        mysqlInsertDao.mySqlRegistrationInsert(mysqlRegistration);
    }

}