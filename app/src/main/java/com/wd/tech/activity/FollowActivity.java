package com.wd.tech.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.wd.tech.R;
import com.wd.tech.adapter.wyadapter.FollowAdapter;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.wybean.beanfollow.FollowBean;
import com.wd.tech.bean.wybean.beanfollow.ResultBean;
import com.wd.tech.mvp.wymvp.mvpfollow.FollowPresenterImpl;
import com.wd.tech.mvp.wymvp.mvpfollow.IFollowContract;

import java.util.List;

public class FollowActivity extends BaseActivity<FollowPresenterImpl> implements IFollowContract.IFollowView {
    private android.widget.ImageView imgFollowBackWy;
    private androidx.recyclerview.widget.RecyclerView recyclerFollowWy;

    //关注 页面
    @Override
    public int initLayout() {
        return R.layout.activity_follow;
    }
    @Override
    public void initView() {
        imgFollowBackWy = (ImageView) findViewById(R.id.imgFollowBackWy);
        recyclerFollowWy = (RecyclerView) findViewById(R.id.recyclerFollowWy);
        //布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(FollowActivity.this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerFollowWy.setLayoutManager(linearLayoutManager);
    }
    @Override
    public void initListener() {
        //点击 返回
        imgFollowBackWy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //销毁
                finish();
            }
        });
    }
    @Override
    public void initData() {
        presenter.getFollow(1,10);
    }
    @Override
    public FollowPresenterImpl initPresenter() {
        return new FollowPresenterImpl();
    }
    @Override
    public void onSuccess(FollowBean follow) {
        List<ResultBean> result = follow.getResult();
        //适配器
        FollowAdapter followAdapter = new FollowAdapter(result, FollowActivity.this);
        recyclerFollowWy.setAdapter(followAdapter);
    }
    @Override
    public void onError(String error) {
    }
}
