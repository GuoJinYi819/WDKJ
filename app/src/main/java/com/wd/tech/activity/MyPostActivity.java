package com.wd.tech.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.base.BasePresenter;

public class MyPostActivity extends BaseActivity {
    private android.widget.ImageView imgPostBackWy;

    @Override
    public int initLayout() {
        return R.layout.activity_my_post;
    }
    @Override
    public void initView() {
        imgPostBackWy = (ImageView) findViewById(R.id.imgPostBackWy);
    }
    @Override
    public void initListener() {
        //销毁
        imgPostBackWy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    @Override
    public void initData() {
    }
    @Override
    public BasePresenter initPresenter() {
        return null;
    }
}
