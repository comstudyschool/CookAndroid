package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<Post> call = apiService.getPostById(1);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(response.isSuccessful()) {
                    Post post = response.body();
                    Log.d("Retrofit >>> ", post.toString());
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Log.e("RetrofitExample", "Error: " + t.getMessage());
            }
        });

//        ApiService apiService = ApiClient.getClient().create(ApiService.class);
//        Call<List<Post>> call = apiService.getAllPosts();
//        call.enqueue(new Callback<List<Post>>() {
//            @Override
//            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
//                if (response.isSuccessful()) {
//                    List<Post> posts = response.body();
//                    for (Post post : posts) {
//                        Log.d("RetrofitExample", "Title: " + post.getTitle());
//                    }
//                }
//            }
//            @Override
//            public void onFailure(Call<List<Post>> call, Throwable t) {
//                Log.e("RetrofitExample", "Error: " + t.getMessage());
//            }
//        });
    }
}