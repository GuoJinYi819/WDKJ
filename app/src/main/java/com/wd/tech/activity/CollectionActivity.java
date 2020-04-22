package com.wd.tech.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wd.tech.R;
import com.wd.tech.adapter.wyadapter.CollectionListAdapter;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.wybean.beancollectionlist.CollectionListBean;
import com.wd.tech.bean.wybean.beancollectionlist.ResultBean;
import com.wd.tech.mvp.wymvp.mvpcollectionlist.CollectionListPresenterImpl;
import com.wd.tech.mvp.wymvp.mvpcollectionlist.ICollectionListContract;

import java.util.List;

public class CollectionActivity extends BaseActivity<CollectionListPresenterImpl> implements ICollectionListContract.ICollectionListView {
    private android.widget.ImageView imgCollectionBackWy;
    private android.widget.ImageView imgDeleteWy;
    private android.widget.TextView tvCompleteWy;
    private androidx.recyclerview.widget.RecyclerView recyclerCollectionListWy;
    private List<ResultBean> result;
    private CollectionListAdapter collectionListAdapter;

    //我的收藏  页面
    @Override
    public int initLayout() {
        return R.layout.activity_collection;
    }
    @Override
    public void initView() {
        imgCollectionBackWy = (ImageView) findViewById(R.id.imgCollectionBackWy);
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
                for(int i=0;i<result.size();i++){
                    result.get(i).setDelete(true);
                }
                //刷新适配器
                collectionListAdapter.notifyDataSetChanged();
            }
        });
        //点击 完成
        tvCompleteWy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvCompleteWy.setVisibility(View.INVISIBLE);
                imgDeleteWy.setVisibility(View.VISIBLE);
                for(int i=0;i<result.size();i++){
                    result.get(i).setDelete(false);
                }
                //刷新适配器
                collectionListAdapter.notifyDataSetChanged();
            }
        });
        //点击  返回
        imgCollectionBackWy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //销毁
                finish();
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
        result = collectionList.getResult();
        //适配器
        collectionListAdapter = new CollectionListAdapter(result, CollectionActivity.this);
        recyclerCollectionListWy.setAdapter(collectionListAdapter);
    }
    @Override
    public void onError(String error) {
    }
}
