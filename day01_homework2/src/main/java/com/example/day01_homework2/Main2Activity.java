package com.example.day01_homework2;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.day01_homework2.MyAdapter.MyAdapter;
import com.example.day01_homework2.OkHttp.OkHttpUtils;
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
    private MyAdapter myAdapter;
    private AlertDialog dialog;
    private Handler handler = new Handler();
    private String url = "http://192.168.0.166:8080/GaoJi.json";
    private TextView mTextView_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
        initAdapter();
        initListeren();

    }

    private void initListeren() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bean bean = been.get(position);
                Intent intent = new Intent(Main2Activity.this, Main3Activity.class);
                intent.putExtra("info", bean);
                startActivity(intent);
            }
        });
    }

    private void initData() {
        dialog.cancel();
        OkHttpUtils.getInstance(Main2Activity.this).SendGet(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ArrayList<Bean> o = new Gson().fromJson(response.body().string(), new TypeToken<ArrayList<Bean>>() {
                }.getType());
                been.addAll(o);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        myAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }

    private void initAdapter() {
        myAdapter = new MyAdapter(been, this);
        mListView.setAdapter(myAdapter);
        dialog = new AlertDialog.Builder(Main2Activity.this)
                .setTitle("请稍等。。。")
                .create();
        dialog.show();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                initData();
                ;
            }
        }, 2000);
    }

    private void initView() {
        mTextView_title = (TextView) findViewById(R.id.mTextView_title);
        mListView = (ListView) findViewById(R.id.mListView);
        activity_main2 = (LinearLayout) findViewById(R.id.activity_main2);

    }
}
