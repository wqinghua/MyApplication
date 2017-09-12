package com.example.day01_homework;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTextView;
    private RelativeLayout activity_main;
    int time = 3;
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {

            handler.postDelayed(runnable,1000);
            time--;
            if (time==0){
                startActivity(new Intent(MainActivity.this,Main2Activity.class));
            }else {
                handler.postDelayed(runnable,1000);
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        handler.postDelayed(runnable,1000);
    }

    private void initView() {
        mTextView = (TextView) findViewById(R.id.mTextView);
        activity_main = (RelativeLayout) findViewById(R.id.activity_main);
    }
}
