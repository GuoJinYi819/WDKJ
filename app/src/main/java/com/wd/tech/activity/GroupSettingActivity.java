package com.wd.tech.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.base.BasePresenter;

public class GroupSettingActivity extends BaseActivity {

    private android.widget.ImageView mIvPic;
    private android.widget.ImageView mIvGroupPic;
    private android.widget.RelativeLayout mRelativeMember;
    private android.widget.TextView mTvParty;
    private android.widget.RelativeLayout mRelativeIntroduce;
    private android.widget.RelativeLayout mRelativeNotice;
    private android.widget.RelativeLayout mRelativeAdministration;
    private android.widget.TextView mTvQuery;
    private android.widget.Button mBtnClone;

    @Override
    public int initLayout() {
        return R.layout.activity_group_setting;
    }

    @Override
    public void initView() {

        mIvPic = findViewById(R.id.ivPic);
        mIvGroupPic = findViewById(R.id.ivGroupPic);
        mRelativeMember = findViewById(R.id.relativeMember);
        mTvParty = findViewById(R.id.tvParty);
        mRelativeIntroduce = findViewById(R.id.relativeIntroduce);
        mRelativeNotice = findViewById(R.id.relativeNotice);
        mRelativeAdministration = findViewById(R.id.relativeAdministration);
        mTvQuery = findViewById(R.id.tvQuery);
        mBtnClone = findViewById(R.id.btnClone);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        int groupId = intent.getIntExtra("groupId", -1);
        Toast.makeText(this, ""+groupId, Toast.LENGTH_SHORT).show();
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }
}
