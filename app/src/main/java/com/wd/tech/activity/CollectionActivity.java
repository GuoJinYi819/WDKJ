package com.wd.tech.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wd.tech.R;
import com.wd.tech.adapter.wyadapter.CollectionListAdapter;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.wybean.Event;
import com.wd.tech.bean.wybean.beancancelcollection.CancelCollectionBean;
import com.wd.tech.bean.wybean.beancollectionlist.CollectionListBean;
import com.wd.tech.bean.wybean.beancollectionlist.ResultBean;
import com.wd.tech.mvp.wymvp.mvpcollectionlist.CollectionListPresenterImpl;
import com.wd.tech.mvp.wymvp.mvpcollectionlist.ICollectionListContract;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

public class CollectionActivity extends BaseActivity<CollectionListPresenterImpl> implements ICollectionListContract.ICollectionListView {
    private android.widget.ImageView imgCollectionBackWy;
    private android.widget.ImageView imgDeleteWy;
    private android.widget.TextView tvCompleteWy;
    private androidx.recyclerview.widget.RecyclerView recyclerCollectionListWy;
    private List<ResultBean> result;
    private CollectionListAdapter collectionListAdapter;
    private String cancleId;

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
        //订阅
        EventBus.getDefault().register(this);
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
                if(!TextUtils.isEmpty(cancleId)){
                    presenter.getCancleCollection(cancleId);
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
    //接收
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void onEvent(Event event){
        cancleId = event.getCancleId();
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
    public void onCancleCollectionSuccess(CancelCollectionBean cancelCollectionBean) {
        //取消收藏
        String message = cancelCollectionBean.getMessage();
        Toast.makeText(CollectionActivity.this,message,Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onError(String error) {
    }
    //销毁
    @Override
    protected void onDestroy() {
        super.onDestroy();
        boolean registered = EventBus.getDefault().isRegistered(this);
        if(registered){
            EventBus.getDefault().unregister(this);
        }
    }
}
