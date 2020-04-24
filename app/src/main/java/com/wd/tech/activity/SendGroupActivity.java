package com.wd.tech.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.base.BasePresenter;

//发送群消息
public class SendGroupActivity extends BaseActivity {

    private android.widget.ImageView mIvBlack;
    private android.widget.TextView mTvName;
    private android.widget.ImageView mIvSetting;
    private androidx.recyclerview.widget.RecyclerView mRecyclerGroup;
    private android.widget.EditText mEditContent;
    private android.widget.TextView mTvSend;

    @Override
    public int initLayout() {
        return R.layout.activity_send_group;
    }

    @Override
    public void initView() {

        mIvBlack = findViewById(R.id.ivBlack);
        mTvName = findViewById(R.id.tvName);
        mIvSetting = findViewById(R.id.ivSetting);
        mRecyclerGroup = findViewById(R.id.recyclerGroup);
        mEditContent = findViewById(R.id.editContent);
        mTvSend = findViewById(R.id.tvSend);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SendGroupActivity.this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        mRecyclerGroup.setLayoutManager(linearLayoutManager);

    }

    @Override
    public void initListener() {
        mIvBlack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        int groupId = intent.getIntExtra("groupId", -1);
        String groupName = intent.getStringExtra("groupName");
        mTvName.setText(groupName);
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }
}
