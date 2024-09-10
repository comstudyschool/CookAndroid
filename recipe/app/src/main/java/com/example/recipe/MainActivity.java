package com.example.recipe;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.example.recipe.CarouselAdapter;
import com.example.recipe.CarouselItem;
import com.example.recipe.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.io.File;

public class MainActivity extends AppCompatActivity {

    private List<String> items = Arrays.asList("덮밥", "제육볶음", "곱창", "치킨");

    private RecyclerView carouselRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // RecyclerView 초기화
        carouselRecyclerView = findViewById(R.id.carouselRecyclerView);
        SearchView searchView = findViewById(R.id.searchview);
        searchView.setQueryHint("덮밥 등 음식이름을 입력해주세요");
        TextView resultTextView = findViewById(R.id.TextView);
        resultTextView.setText(getResult());

        // Retrofit을 사용한 파일 업로드
        uploadFileWithRetrofit();

        // 데이터 리스트 생성
        List<CarouselItem> dataList = new ArrayList<>();
        dataList.add(new CarouselItem(R.drawable.kakaotalk_20240103_113417031, "Item 1"));
        dataList.add(new CarouselItem(R.drawable.kakaotalk_20240104_143302521, "Item 2"));
        // 필요한 만큼 데이터를 추가합니다.

        // 어댑터 생성 및 설정
        CarouselAdapter adapter = new CarouselAdapter(dataList);
        carouselRecyclerView.setAdapter(adapter);

        // RecyclerView의 레이아웃 매니저 설정 (수평 스크롤)
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        carouselRecyclerView.setLayoutManager(layoutManager);

        // SearchView의 이벤트 처리(검색어 제출 시 /검색어 변경 시)
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                resultTextView.setText(search(newText));
                return true;
            }
        });
    }

    private void uploadFileWithRetrofit() {
        //Retrofit 인스턴스 생성 시 GsonCinvarterFactory 추가
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2.8080/") // 실제 서버 주소로 변경(다시 변경해야 할수도)
                .addConverterFactory(GsonConverterFactory.create())// GsonConverterFactory 추가
                .build();

        FileUploadService service = retrofit.create(FileUploadService.class);

        File file = new File("C:/Users/유예빈/Pictures/220322 사진/AAMPMGg.jpg");
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestFile);

        Call<ResponseBody> call = service.uploadFile(body);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                // 성공 처리, 예: Toast 메시지 띄우기 등
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                // 실패 처리, 예: Toast 메시지 띄우기 등
            }
        });
    }

    private void downloadFile() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2.8080")
                .addConverterFactory(GsonConverterFactory.create())// GsonConverterFactory 추가
                .build();

        FileDownloadService service = retrofit.create(FileDownloadService.class);

        service.downloadFile().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    // 파일 다운로드에 성공했을 때
                    ResponseBody responseBody = response.body();
                    // 다운로드한 파일을 CarouselItem 리스트로 변환하는 메서드 호출
                    List<CarouselItem> itemList = parseFileToCarouselItemList(responseBody);
                    // RecyclerView에 데이터를 추가
                    updateRecyclerView(itemList);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                // 파일 다운로드 실패 처리
            }
        });
    }

    private List<CarouselItem> parseFileToCarouselItemList(ResponseBody body) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<CarouselItem>>() {}.getType();
        try {
            // ResponseBody를 문자열로 변환
            String json = body.string();
            // JSON을 CarouselItem 리스트로 파싱
            return gson.fromJson(json, type);
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    private void updateRecyclerView(List<CarouselItem> itemList) {
        CarouselAdapter adapter = (CarouselAdapter) carouselRecyclerView.getAdapter();
        if (adapter != null) {
            adapter.setItemList(itemList);
            adapter.notifyDataSetChanged();
        }
    }


    private String search(String query) {
        StringBuilder sb = new StringBuilder();
        for(int i =0; i < items.size();i++) {
            String item = items.get(i);
            if (item.toLowerCase().contains(query.toLowerCase())){
                sb.append(item);
                if (i != items.size() - 1) {
                    sb.append("\n");
                }
            }
        }
        return sb.toString();
    }

    private String getResult() {
        StringBuilder sb = new StringBuilder();
        for(int i =0; i < items.size();i++) {
            String item = items.get(i);
            sb.append(item);
            if (i != items.size() - 1){
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
