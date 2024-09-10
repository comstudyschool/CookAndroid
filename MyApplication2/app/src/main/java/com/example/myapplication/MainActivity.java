package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView hello2Txt = findViewById(R.id.hello2);
        RadioGroup radioGroup = findViewById(R.id.radioGroup);

        // Javascript의 getElementById()와 유사하다.
        Button clickMeBtn = findViewById(R.id.clickMeBtn);
        clickMeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hello2Txt.setText("Ok버튼 클릭 함!");
                System.out.println("버튼을 눌렀습니다!");
                Log.i("MainActivity", ">>>>>>>> Hello Android - 이것은 Logcat에 출력");
                Log.v("MainActivity", ">>>>>>>> Hello Android - 이것은 Logcat에 출력");
                Log.w("MainActivity", ">>>>>>>> Hello Android - 이것은 Logcat에 출력");
                Log.e("MainActivity", ">>>>>>>> Hello Android - 이것은 Logcat에 출력");
                Log.d("MainActivity", ">>>>>>>> Hello Android - 이것은 Logcat에 출력");
            }
        });

        Button nextBtn = findViewById(R.id.nextBtn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                RadioButton selectedRadioButton = findViewById(selectedId);
                EditText editText = findViewById(R.id.editText);

                String radioBtnVal = "";

                if(selectedRadioButton != null) {
                    radioBtnVal = selectedRadioButton.getText().toString();
                } else {
                    Toast.makeText(MainActivity.this, "옵션을 선택해 주세요.", Toast.LENGTH_SHORT).show();
                }

                String inputTxtVal = editText.getText().toString();

                // 새로운 액티비티를 시작합니다.
                Intent intent = new Intent(MainActivity.this, NewActivity.class);

                intent.putExtra("radioBtnVal", radioBtnVal);
                intent.putExtra("inputTxtVal", inputTxtVal);

                startActivity(intent);
            }
        });

        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<List<Post>> call = apiService.getAllPosts();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (response.isSuccessful()) {
                    List<Post> posts = response.body();
                    for (Post post : posts) {
                        Log.d("RetrofitExample", "Title: " + post.getTitle());
                    }
                }
            }
            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Log.e("RetrofitExample", "Error: " + t.getMessage());
            }
        });
    }
}