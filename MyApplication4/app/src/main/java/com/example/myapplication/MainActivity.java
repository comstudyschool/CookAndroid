package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    // RecyclerView의 설정과 데이터 바인딩을 관리.
    private RecyclerView recyclerView;
    // RecyclerView에 표시될 아이템 데이터와 뷰를 연결.
    private MyAdapter adapter;
    // RecyclerView에 표시될 아이템의 데이터가 저장.
    private static List<String> itemList;
    static {
        // itemList 필드에 RecyclerView에 표시될 아이템 데이터를 초기화
        // 나중에 실제로 ArrayList 객체를 new 연산자로 생성 해야 함.
        itemList = new ArrayList<String>();
        itemList.add("할 일 1");
        itemList.add("할 일 2");
        itemList.add("할 일 3");
        itemList.add("할 일 4");
        itemList.add("할 일 5");
        itemList.add("할 일 6");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // activity_main.xml에 정의한 RecyclerView
        recyclerView = findViewById(R.id.recyclerView);

        // MyAdapter 생성자에 itemList를 전달하여 어댑터가 사용할 데이터를 설정
        adapter = new MyAdapter(itemList);
        // RecyclerView의 레이아웃을 선형으로 설정
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        // setLayoutManager 메서드를 사용하여 RecyclerView에 레이아웃 매니저를 설정.
        recyclerView.setLayoutManager(layoutManager);
        // setAdapter 메서드를 사용하여 RecyclerView에 어댑터를 설정.
        // 이로써 RecyclerView는 itemList 데이터를 사용하여 아이템을 표시하게 됨.
        recyclerView.setAdapter(adapter);
    }
}