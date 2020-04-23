package com.wd.tech.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.base.BasePresenter;

public class MyScoreActivity extends BaseActivity {
    private android.widget.ImageView imgScoreBackWy;

    //我的积分
    @Override
    public int initLayout() {
        return R.layout.activity_my_score;
    }
    @Override
    public void initView() {
        imgScoreBackWy = (ImageView) findViewById(R.id.imgScoreBackWy);
    }
    @Override
    public void initListener() {
        //点击销毁
        imgScoreBackWy.setOnClickListener(new View.OnClickListener() {
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
