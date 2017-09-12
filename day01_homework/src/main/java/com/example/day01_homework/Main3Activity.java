package com.example.day01_homework;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.List;

public class Main3Activity extends AppCompatActivity {

    private TextView mTextView_title1;
    private Banner banner;
    private TextView mTextView_context1;
    private LinearLayout activity_main3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        initView();
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        Bean bean = (Bean) intent.getSerializableExtra("bean");
        mTextView_title1.setText(bean.getTitle());
        mTextView_context1.setText(bean.getContent());
        List<String> imgs = bean.getImgs();
        banner.setImages(imgs)//添加图片集合或图片url集合
                .setDelayTime(2000)//设置轮播时间
                .setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
                .setImageLoader(new GlideImage())//加载图片
                .setIndicatorGravity(BannerConfig.CENTER)//设置指示器位置
                .start();
    }

    private void initView() {
        mTextView_title1 = (TextView) findViewById(R.id.mTextView_title1);
        banner = (Banner) findViewById(R.id.banner);
        mTextView_context1 = (TextView) findViewById(R.id.mTextView_context1);
        activity_main3 = (LinearLayout) findViewById(R.id.activity_main3);
    }
}
