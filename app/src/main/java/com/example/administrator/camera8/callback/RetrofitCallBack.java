package com.example.administrator.camera8.callback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/6/24 0024.
 */

public class RetrofitCallBack<T> implements Callback<T> {
    private HttpCallBack callBack;
    private String method;
    public RetrofitCallBack(HttpCallBack callBack,String method){
        this.callBack = callBack;
        this.method = method;
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
