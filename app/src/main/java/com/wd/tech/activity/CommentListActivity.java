package com.wd.tech.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.base.BasePresenter;

public class CommentListActivity extends BaseActivity {
    //评论列表页
    @Override
    public int initLayout() {
        return R.layout.activity_comment_list;
    }
    @Override
    public void initView() {
    }
    @Override
    public void initListener() {
    }
    @Override
    public void initData() {
    }
    @Override
    public BasePresenter initPresenter() {
        return null;
    }
}
