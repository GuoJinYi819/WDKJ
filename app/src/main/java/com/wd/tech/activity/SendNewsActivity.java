package com.wd.tech.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.base.BasePresenter;

public class SendNewsActivity extends BaseActivity {

    private android.widget.ImageView mIvBlack;
    private android.widget.TextView mTvName;
    private android.widget.ImageView mIvSetting;
    private android.widget.EditText mEditContent;
    private android.widget.TextView mTvSend;
    private androidx.recyclerview.widget.RecyclerView mRecyclerNews;

    @Override
    public int initLayout() {
        return R.layout.activity_send_news;
    }

    @Override
    public void initView() {

        mIvBlack = findViewById(R.id.ivBlack);
        mTvName = findViewById(R.id.tvName);
        mIvSetting = findViewById(R.id.ivSetting);
        mEditContent = findViewById(R.id.editContent);
        mTvSend = findViewById(R.id.tvSend);
        mRecyclerNews = findViewById(R.id.recyclerNews);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SendNewsActivity.this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        mRecyclerNews.setLayoutManager(linearLayoutManager);

    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");

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
