package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class NewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        TextView genderTxtView = findViewById(R.id.gender);
        TextView nameTxtView = findViewById(R.id.name);

        genderTxtView.setText(getIntent().getStringExtra("radioBtnVal"));
        nameTxtView.setText(getIntent().getStringExtra("inputTxtVal"));
    }
}