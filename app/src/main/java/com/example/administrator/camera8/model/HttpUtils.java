package com.example.administrator.camera8.model;

import com.example.administrator.camera8.C;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Administrator on 2017/6/24 0024.
 */

public class HttpUtils {
    private static HttpUtils http;
    private Retrofit retrofit;
    private HttpUtils(){
        init();
    }
    public static HttpUtils getInstance() {
        if (http == null) {
            http = new HttpUtils();
            return http;
        } else {
            return http;
        }
    }
    private void init(){
        retrofit = new Retrofit.Builder()
                .baseUrl(C.HOST)
                //支持直接返回字符串,它必须放到gson转换上面
                .addConverterFactory(ScalarsConverterFactory.create())
                //添加json支持，就能自动将json格式的字符串转化成封装类
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public <T> T createService(Class<T> service){
        return retrofit.create(service);
    }
}
