package com.example.administrator.camera8.model;

import com.example.administrator.camera8.callback.DownloadCallBack;
import com.example.administrator.camera8.callback.HttpCallBack;
import com.example.administrator.camera8.callback.RetrofitCallBack;
import com.example.administrator.camera8.callback.UploadCallBack;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;

/**
 * Created by Administrator on 2017/6/26 0026.
 */

public class BaseHttp<S> {
    private HttpCallBack callBack;
    public BaseHttp(HttpCallBack callBack){
        this.callBack = callBack;
    }
    public <T> void httpOperate(Class<S> service,String methodName,Map map,Class<T> cls){
        Call<T> call = null;
        S service1 = HttpUtils.getInstance().createService(service);
        try {
            if(map != null) {
            Method method = service.getDeclaredMethod(methodName, Map.class);

                call = (Call<T>) method.invoke(service1, map);
            } else {
                Method method = service.getDeclaredMethod(methodName);
                call = (Call<T>) method.invoke(service1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        call.enqueue(new RetrofitCallBack<T>(callBack,methodName));
    }
    public <T> void downloadFile(Class<S> service, File file, String methodName, String url, Class<T> cls){
        Call<T> call = null;
        S service1 = HttpUtils.getInstance().createService(service);
        try {
            Method method = service.getDeclaredMethod(methodName, String.class);
            call = (Call<T>) method.invoke(service1, url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        call.enqueue(new DownloadCallBack<T>(callBack,file,methodName));
    }

    public <T> void uploadFile(Class<S> service,File file,String methodName,Class<T> cls,String picName){
        Call<T> call = null;
        UploadCallBack<T> callback = new UploadCallBack<T>(callBack,methodName);
        S service1 = HttpUtils.getInstance().createService(service);
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        FileRequestBody body = new FileRequestBody(requestFile,callback);
        MultipartBody.Part part = MultipartBody.Part.createFormData(picName,file.getName(),body);
        try {
            Method method = service.getDeclaredMethod(methodName, MultipartBody.Part.class);
            call = (Call<T>) method.invoke(service1, part);
        } catch (Exception e) {
            e.printStackTrace();
        }
        call.enqueue(callback);
    }
}
