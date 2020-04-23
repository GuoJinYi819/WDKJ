package com.wd.tech.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.base.BasePresenter;

public class TaskActivity extends BaseActivity {
    //日常任务页面
    private android.widget.ImageView imgTaskBackWy;
    @Override
    public int initLayout() {
        return R.layout.activity_task;
    }
    @Override
    public void initView() {
        imgTaskBackWy = (ImageView) findViewById(R.id.imgTaskBackWy);
    }
    @Override
    public void initListener() {
        //点击 返回
        imgTaskBackWy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //销毁
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
