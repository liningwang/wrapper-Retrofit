package com.example.administrator.camera8.model;

import com.example.administrator.camera8.callback.HttpCallBack;
import com.example.administrator.camera8.callback.RetrofitCallBack;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/6/24 0024.
 */

public class WelcomMess {
    private HttpCallBack callBack;
    public WelcomMess(HttpCallBack callBack){
        this.callBack = callBack;
    }
    public void getWelcomeMess(){
        TestService service = HttpUtils.getInstance().createService(TestService.class);
        Call<String> call = service.getMessage();
        call.enqueue(new RetrofitCallBack<String>(callBack,""));
    }
}
