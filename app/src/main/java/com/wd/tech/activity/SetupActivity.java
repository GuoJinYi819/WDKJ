package com.wd.tech.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.base.BasePresenter;

public class SetupActivity extends BaseActivity {
    private android.widget.ImageView imgSetupBackWy;

    //设置 页面
    @Override
    public int initLayout() {
        return R.layout.activity_setup;
    }
    @Override
    public void initView() {
        imgSetupBackWy = (ImageView) findViewById(R.id.imgSetupBackWy);
    }
    @Override
    public void initListener() {
        //销毁
        imgSetupBackWy.setOnClickListener(new View.OnClickListener() {
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
