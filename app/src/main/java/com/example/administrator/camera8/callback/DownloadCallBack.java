package com.example.administrator.camera8.callback;

import android.os.AsyncTask;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/6/27 0027.
 */

public class DownloadCallBack<T> implements Callback<T>{
    private HttpCallBack httpCallBack;
    private File file;
    private String method;
    public DownloadCallBack(HttpCallBack httpCallBack,File file,String method){
        this.httpCallBack = httpCallBack;
        this.file = file;
        this.method = method;

    }
    @Override
    public void onResponse(Call<T> call, final Response<T> response) {
        Log.d("wang","download onResponse");
        MyTask task = new MyTask(response);
        task.execute();
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {

    }
    class MyTask extends AsyncTask<String,Integer,String> {
        Response response;
       public MyTask(Response response){
            this.response = response;
        }
        @Override
        protected String doInBackground(String... params) {
            InputStream is = null;
            try {
                ResponseBody body = (ResponseBody)response.body();
                //文件总长度
                long total = body.contentLength();
                is = body.byteStream();
//                    OutputStream os = new FileOutputStream("/mnt/sdcard/update.apk");
                OutputStream os = new FileOutputStream(file.toString());
                byte[] buf = new byte[1024];
                int len = 0;
                //当前已经下载的长度
                long currLen = 0;
                while ((len = is.read(buf)) != -1) {
                    Log.d("wang","total " + total + " currLen " + currLen);
                    os.write(buf,0,len);
                    currLen += len;
                    int percent = (int)((currLen * 1.0) / total * 100);
                    Log.d("wang","percent  " + percent);
                    publishProgress(percent);
                }

                os.close();
                is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            httpCallBack.onProgress(values[0],method);
        }

    }
}
