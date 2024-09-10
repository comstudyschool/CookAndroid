package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class NewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        Button showMsgBtn = findViewById(R.id.showMsgBtn);
        // 익명 inner 클래스
        showMsgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 토스트 메세지 표시 (반짝 나타났다가 사라지는 메세지)
                Toast.makeText(NewActivity.this,
                        "클릭되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}