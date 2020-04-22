package com.wd.tech.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.base.BasePresenter;
import com.wd.tech.bean.gjybean.FriendNoticeBean;
import com.wd.tech.bean.gjybean.GroupNoticeBean;
import com.wd.tech.mvp.gjymvp.newsnotice.INewsNoticeContract;
import com.wd.tech.mvp.gjymvp.newsnotice.NewsNoticePresenter;

import java.util.HashMap;
import java.util.List;

//好友通知
public class FriendNoticeActivity extends BaseActivity<NewsNoticePresenter> implements INewsNoticeContract.INewsNoticeView {

    private android.widget.ImageView mIvBack;
    private androidx.recyclerview.widget.RecyclerView mRecyclerFriendNotice;

    @Override
    public int initLayout() {
        return R.layout.activity_friend_notice;
    }

    @Override
    public void initView() {
        mIvBack = findViewById(R.id.ivBack);
        mRecyclerFriendNotice = findViewById(R.id.recyclerFriendNotice);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(FriendNoticeActivity.this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        mRecyclerFriendNotice.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void initListener() {
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void initData() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("page","1");
        hashMap.put("count","10");
        presenter.getFriendNotice(hashMap);
    }

    @Override
    public NewsNoticePresenter initPresenter() {
        return new NewsNoticePresenter();
    }

    @Override
    public void onFriendNoticeSuccess(FriendNoticeBean bean) {
        List<FriendNoticeBean.ResultBean> result = bean.getResult();
        if (result != null) {

        }
    }

    @Override
    public void onGroupNoticeSuccess(GroupNoticeBean bean) {

    }
}
