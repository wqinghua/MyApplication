package com.example.day01_homework;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class Main2Activity extends AppCompatActivity {

    private ListView mListView;
    private LinearLayout activity_main2;
    private ArrayList<Bean> been = new ArrayList<>();
    private MyBaseAdapter myBaseAdapter;
    private View inflate;
    private Handler handler = new Handler();
    private int time = 3;
    private PopupWindow popupWindow;
    private String url = "http://172.16.44.6:8080/GaoJi.json";

;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
        initListeren();


    }

    private void initListeren() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bean bean = been.get(i);
                Intent intent = new Intent(Main2Activity.this,Main3Activity.class);
                intent.putExtra("bean",bean);
                startActivity(intent);
            }
        });

    }

    private void initData() {
        popupWindow.dismiss();



        OkHttpUtils.getInstance(Main2Activity.this).SendGet(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
//                String s = new String(string.getBytes(),"utf-8");
                Gson gson = new Gson();
                ArrayList<Bean> o = gson.fromJson(string, new TypeToken<ArrayList<Bean>>() {
                }.getType());
                been.addAll(o);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                      myBaseAdapter.notifyDataSetChanged();

                    }
                });


            }
        });


    }

    private void initView() {
        mListView = (ListView) findViewById(R.id.mListView);
        activity_main2 = (LinearLayout) findViewById(R.id.activity_main2);
         myBaseAdapter = new MyBaseAdapter(been,this);
        mListView.setAdapter(myBaseAdapter);



         inflate = LayoutInflater.from(this).inflate(R.layout.popu_item, null);


        popupWindow = new PopupWindow(inflate, ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);

        popupWindow.setBackgroundDrawable(new BitmapDrawable());

        popupWindow.setTouchable(true);
        popupWindow.setFocusable(true);
//        popupWindow.showAtLocation(activity_main2, Gravity.CENTER,0,0);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                initData();
            }
        },3000);


    }
}
