package com.example.day01_homework2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.List;

public class Main3Activity extends AppCompatActivity {

    private TextView mTextView_title1;
    private Toolbar mToolbar2;
    private Banner banner;
    private TextView mTextView_context1;
    private LinearLayout activity_main3;
    private Bean bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        initView();
        initData();
    }

    private void initData() {
        bean = (Bean) getIntent().getSerializableExtra("info");
        mTextView_title1.setText(bean.getTitle());
        mTextView_context1.setText(bean.getContent());
        List<String> imgs = bean.getImgs();
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
                .setImageLoader(new ImageLoad())
                .setImages(imgs)
                .setBannerAnimation(Transformer.DepthPage)
                .setDelayTime(2000)
                .start();
    }

    private void initView() {
        mTextView_title1 = (TextView) findViewById(R.id.mTextView_title1);
        mToolbar2 = (Toolbar) findViewById(R.id.mToolbar2);
        banner = (Banner) findViewById(R.id.banner);
        mTextView_context1 = (TextView) findViewById(R.id.mTextView_context1);
        activity_main3 = (LinearLayout) findViewById(R.id.activity_main3);
    }
}
