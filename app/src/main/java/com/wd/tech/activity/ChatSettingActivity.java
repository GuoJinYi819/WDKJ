package com.wd.tech.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.base.BasePresenter;

public class ChatSettingActivity extends BaseActivity {

    private android.widget.ImageView mIvBlac;
    private android.widget.ImageView mIvFriendPic;
    private android.widget.TextView mTvName;
    private android.widget.TextView mTvName2;
    private android.widget.RelativeLayout mRelativeFz;
    private android.widget.TextView mTvQurey;
    private android.widget.TextView mTvClose;
    private android.widget.Button mBtnDelete;

    @Override
    public int initLayout() {
        return R.layout.activity_chat_setting;
    }

    @Override
    public void initView() {

        mIvBlac = findViewById(R.id.ivBlac);
        mIvFriendPic = findViewById(R.id.ivFriendPic);
        mTvName = findViewById(R.id.tvName);
        mTvName2 = findViewById(R.id.tvName2);
        mRelativeFz = findViewById(R.id.relativeFz);
        mTvQurey = findViewById(R.id.tvQurey);
        mTvClose = findViewById(R.id.tvClose);
        mBtnDelete = findViewById(R.id.btnDelete);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        String headPic = intent.getStringExtra("headPic");
        Glide.with(ChatSettingActivity.this).load(headPic).into(mIvFriendPic);
        String nickName = intent.getStringExtra("nickName");
        mTvName.setText(nickName);
        mTvName2.setText(nickName);
        int groupId = intent.getIntExtra("groupId", -1);
        int friend = intent.getIntExtra("friend", -1);


    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }
}
