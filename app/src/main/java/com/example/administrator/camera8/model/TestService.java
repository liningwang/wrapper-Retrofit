package com.example.administrator.camera8.model;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by Administrator on 2017/6/24 0024.
 */

public interface TestService {

    @GET("Camera/servlet/MessageServlet")
    Call<String> getMessage();

    @Streaming
    @GET
    Call<ResponseBody> downApk(@Url String url);

    @Multipart
    @POST("UpdateServlet/servlet/Upload")
    Call<String> uploadFile(@Part MultipartBody.Part file);
}
