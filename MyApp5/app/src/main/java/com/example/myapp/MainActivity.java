package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int cnt = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("MainActivity", ">>>>>>>> Hello Android - 이것은 Logcat에 출력");

        Button button = findViewById(R.id.button);
        TextView textView = findViewById(R.id.textView);
        Button startButton = findViewById(R.id.startButton);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 내부 클래스에서 외부클래스의 this 접근
                Intent intent = new Intent(MainActivity.this, NewActivity.class);
                startActivity(intent);
            }
        });

        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(view.getAlpha() == 0.5F) {
                    view.setAlpha(1.0F);
                    button.setText("클릭 하세요!");
                    textView.setText("Hello world!" + cnt++ );
                } else {
                    view.setAlpha(0.5F);
                    button.setText("다시 클릭 하세요!");
                    textView.setText("버튼이 클릭 되었습니다." + cnt++ );
                }
            }
        });
    }
}