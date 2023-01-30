package com.myproject.offlinebudgettrackerappproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.myproject.offlinebudgettrackerappproject.model.MysqlInsertViewModel;
import com.myproject.offlinebudgettrackerappproject.model.MysqlRegistration;

public class MysqlRegistrationActivity extends AppCompatActivity {

    EditText enterId, enterPassword;
    Button registerButton;
    MysqlInsertViewModel mysqlInsertViewModel;
    MysqlRegistration mysqlRegistration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mysql_registration);

        enterId = (EditText) findViewById(R.id.mysql_login_register_id);
        enterPassword = (EditText) findViewById(R.id.mysql_login_register_password);
        registerButton = (Button) findViewById(R.id.mysql_registration_btn);

        mysqlInsertViewModel = new ViewModelProvider.AndroidViewModelFactory(MysqlRegistrationActivity.this
                .getApplication())
                .create(MysqlInsertViewModel.class);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userId = enterId.getText().toString();
                String userPassword = enterPassword.getText().toString();
                mysqlRegistration = new MysqlRegistration(userId, userPassword);
                mysqlInsertViewModel.registrationInsert(mysqlRegistration);
            }
        });


    }
}