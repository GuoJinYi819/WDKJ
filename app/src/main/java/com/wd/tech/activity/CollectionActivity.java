package com.wd.tech.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.wybean.beancollectionlist.CollectionListBean;
import com.wd.tech.bean.wybean.beancollectionlist.ResultBean;
import com.wd.tech.mvp.wymvp.mvpcollectionlist.CollectionListPresenterImpl;
import com.wd.tech.mvp.wymvp.mvpcollectionlist.ICollectionListContract;

import java.util.List;

public class CollectionActivity extends BaseActivity<CollectionListPresenterImpl> implements ICollectionListContract.ICollectionListView {
    private android.widget.ImageView imgCollectionWty;
    private android.widget.ImageView imgDeleteWy;
    private android.widget.TextView tvCompleteWy;
    private androidx.recyclerview.widget.RecyclerView recyclerCollectionListWy;

    //我的收藏  页面
    @Override
    public int initLayout() {
        return R.layout.activity_collection;
    }
    @Override
    public void initView() {
        imgCollectionWty = (ImageView) findViewById(R.id.imgCollectionWty);
        imgDeleteWy = (ImageView) findViewById(R.id.imgDeleteWy);
        tvCompleteWy = (TextView) findViewById(R.id.tvCompleteWy);
        recyclerCollectionListWy = (RecyclerView) findViewById(R.id.recyclerCollectionListWy);
        //布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(CollectionActivity.this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerCollectionListWy.setLayoutManager(linearLayoutManager);
    }
    @Override
    public void initListener() {
        //点击 删除按钮
        imgDeleteWy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgDeleteWy.setVisibility(View.INVISIBLE);
                tvCompleteWy.setVisibility(View.VISIBLE);
            }
        });
        //点击 完成
        tvCompleteWy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvCompleteWy.setVisibility(View.INVISIBLE);
                imgDeleteWy.setVisibility(View.VISIBLE);
            }
        });
    }
    @Override
    public void initData() {
        presenter.getCollectionList(1,10);
    }
    @Override
    public CollectionListPresenterImpl initPresenter() {
        return new CollectionListPresenterImpl();
    }
    @Override
    public void onSuccess(CollectionListBean collectionList) {
        List<ResultBean> result = collectionList.getResult();
        //适配器
    }
    @Override
    public void onError(String error) {
    }
}
