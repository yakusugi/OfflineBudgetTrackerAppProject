package com.myproject.offlinebudgettrackerappproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MysqlLoginActivity extends AppCompatActivity {

    EditText enterId, enterPassword;
    Button loginButton, registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mysql_login);

        enterId = (EditText) findViewById(R.id.mysql_login_id);
        enterPassword = (EditText) findViewById(R.id.mysql_login_password);
        loginButton = (Button) findViewById(R.id.mysql_login_btn);
        registerButton = (Button) findViewById(R.id.mysql_register_btn);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MysqlLoginActivity.this, MysqlRegistrationActivity.class);
                startActivity(intent);
            }
        });


    }
}