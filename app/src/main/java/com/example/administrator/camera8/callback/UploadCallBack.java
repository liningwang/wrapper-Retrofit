package com.example.administrator.camera8.callback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/6/27 0027.
 */

public class UploadCallBack<T> implements Callback<T>{
    HttpCallBack callBack;
    String method;
    public UploadCallBack(HttpCallBack callBack,String method){
        this.callBack = callBack;
        this.method = method;
    }
    public void onLoadding(int percent){
        callBack.onProgress(percent,method);
    }
    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        callBack.onSuccess(response.body(),method);
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        callBack.onFailed(t,method);
    }
}
