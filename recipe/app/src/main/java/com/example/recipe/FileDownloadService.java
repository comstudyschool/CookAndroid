package com.example.recipe;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface FileDownloadService {
    @GET("C:/Users/유예빈/Pictures/")
    Call<ResponseBody> downloadFile();
}
