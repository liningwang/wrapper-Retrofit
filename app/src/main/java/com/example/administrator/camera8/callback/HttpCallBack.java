package com.example.administrator.camera8.callback;

/**
 * Created by Administrator on 2017/6/24 0024.
 */

public interface HttpCallBack {
    public void onSuccess(Object o,String method);
    public void onFailed(Object o,String method);
    public void onProgress(int percent,String method);
}
