package com.wd.tech.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.base.BasePresenter;

public class SendNewsActivity extends BaseActivity {

    @Override
    public int initLayout() {
        return R.layout.activity_send_news;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        int friend = intent.getIntExtra("friend", -1);
        if (friend==-1) {
            finish();
        }else {
            Toast.makeText(this, ""+friend, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }
}
