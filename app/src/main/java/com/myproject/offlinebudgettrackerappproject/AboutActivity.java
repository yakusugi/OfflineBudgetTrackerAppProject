package com.myproject.offlinebudgettrackerappproject;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        TextView englishText = (TextView) findViewById(R.id.english_text_view);

//        englishText.setText("My Text" + "\n" + "Test");
    }


}