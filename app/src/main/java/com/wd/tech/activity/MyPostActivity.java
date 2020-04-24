package com.wd.tech.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.wd.tech.R;
import com.wd.tech.adapter.wyadapter.PostAdapter;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.base.BasePresenter;
import com.wd.tech.bean.wybean.beanmypost.MyPostBean;
import com.wd.tech.bean.wybean.beanmypost.ResultBean;
import com.wd.tech.mvp.wymvp.mvpmypost.IMyPostContract;
import com.wd.tech.mvp.wymvp.mvpmypost.MyPostPresenterImpl;

import java.util.List;

public class MyPostActivity extends BaseActivity<MyPostPresenterImpl> implements IMyPostContract.IMyPostView {
    private android.widget.ImageView imgPostBackWy;
    private androidx.recyclerview.widget.RecyclerView recyclerPostWy;

    @Override
    public int initLayout() {
        return R.layout.activity_my_post;
    }
    @Override
    public void initView() {
        imgPostBackWy = (ImageView) findViewById(R.id.imgPostBackWy);
        recyclerPostWy = (RecyclerView) findViewById(R.id.recyclerPostWy);
        //布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MyPostActivity.this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerPostWy.setLayoutManager(linearLayoutManager);
    }
    @Override
    public void initListener() {
        //销毁
        imgPostBackWy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    @Override
    public void initData() {
        presenter.getMyPost(1,10);
    }
    @Override
    public MyPostPresenterImpl initPresenter() {
        return new MyPostPresenterImpl();
    }
    @Override
    public void onMyPostSuccess(MyPostBean myPostBean) {
        List<ResultBean> result = myPostBean.getResult();
        //适配器
        PostAdapter postAdapter = new PostAdapter(result, MyPostActivity.this);
        recyclerPostWy.setAdapter(postAdapter);
    }
    @Override
    public void onError(String error) {
    }
}
