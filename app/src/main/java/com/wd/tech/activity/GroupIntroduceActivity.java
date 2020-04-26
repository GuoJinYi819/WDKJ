package com.wd.tech.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.base.BasePresenter;

public class GroupIntroduceActivity extends BaseActivity {

    private android.widget.TextView mTvConfirm;
    private android.widget.EditText mEditContent;

    @Override
    public int initLayout() {
        return R.layout.activity_group_introduce;
    }

    @Override
    public void initView() {

        mTvConfirm = findViewById(R.id.tvConfirm);
        mEditContent = findViewById(R.id.editContent);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        String description = intent.getStringExtra("description");
        mEditContent.setText(description);
        int groupId = intent.getIntExtra("groupId", -1);
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }
}
