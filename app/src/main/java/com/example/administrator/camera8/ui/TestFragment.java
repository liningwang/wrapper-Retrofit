package com.example.administrator.camera8.ui;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.administrator.camera8.R;
import com.example.administrator.camera8.callback.HttpCallBack;
import com.example.administrator.camera8.model.BaseHttp;
import com.example.administrator.camera8.model.TestService;
import com.example.administrator.camera8.model.WelcomMess;

import java.io.File;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;

/**
 * A simple {@link Fragment} subclass.
 */
public class TestFragment extends Fragment implements HttpCallBack{
    WelcomMess welcomMess;

    public TestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_blank, container, false);
        Button bt = (Button) v.findViewById(R.id.mess);
        welcomMess = new WelcomMess(TestFragment.this);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                welcomMess.getWelcomeMess();
                BaseHttp http = new BaseHttp(TestFragment.this);
//                http.downloadFile(TestService.class,new File("/mnt/sdcard/update.apk"),"downApk",
//                        "http://192.168.0.2:8080/DetectApkServlet/update.apk", ResponseBody.class);
                http.uploadFile(TestService.class,new File("/mnt/sdcard/update.apk"),"uploadFile",
                        ResponseBody.class,"pictrue");
            }
        });
        return v;
    }

    @Override
    public void onSuccess(Object o,String method) {
        Toast.makeText(getActivity(),(String)o,Toast.LENGTH_LONG)
                .show();
    }

    @Override
    public void onFailed(Object o,String method) {

    }

    @Override
    public void onProgress(int percent,String method) {

        Log.d("wang","percent is " + percent + " method " + method);
    }
}
