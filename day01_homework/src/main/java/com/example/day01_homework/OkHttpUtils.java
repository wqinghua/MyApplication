package com.example.day01_homework;

import android.content.Context;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by 陌少 on 2017/9/11.
 */

public class OkHttpUtils {
    private static OkHttpUtils okHttpUtils ;
    private OkHttpClient client ;

    private OkHttpUtils(Context context) {
        client = new OkHttpClient();
    }
    public static synchronized OkHttpUtils getInstance(Context context ){
        if (okHttpUtils ==null){
            okHttpUtils = new OkHttpUtils(context);
        }
        return okHttpUtils;
    }

    public void SendGet(String url , Callback callback ){
        Request  request = new Request.Builder().url(url).build();
        Call call = client.newCall(request);
        call.enqueue(callback);
    }
}
