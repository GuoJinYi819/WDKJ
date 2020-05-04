package com.wd.tech.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.wd.tech.R;
import com.wd.tech.adapter.wyadapter.PostAdapter;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.base.BasePresenter;
import com.wd.tech.bean.wybean.Event;
import com.wd.tech.bean.wybean.beandeletepost.DeletePostBean;
import com.wd.tech.bean.wybean.beanmypost.MyPostBean;
import com.wd.tech.bean.wybean.beanmypost.ResultBean;
import com.wd.tech.mvp.wymvp.mvpmypost.IMyPostContract;
import com.wd.tech.mvp.wymvp.mvpmypost.MyPostPresenterImpl;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

public class MyPostActivity extends BaseActivity<MyPostPresenterImpl> implements IMyPostContract.IMyPostView {
    private android.widget.ImageView imgPostBackWy;
    private androidx.recyclerview.widget.RecyclerView recyclerPostWy;
    private PostAdapter postAdapter;
    private com.scwang.smartrefresh.layout.SmartRefreshLayout postSmartWy;
    //刷新次数
    private int count = 10;
    private List<ResultBean> result;

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
        //EventBus   订阅
        EventBus.getDefault().register(this);
        postSmartWy = (SmartRefreshLayout) findViewById(R.id.postSmartWy);
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
        //上拉  下拉
        postSmartWy.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                //加载
                count++;
                presenter.getMyPost(1,count);
                postAdapter.onLoadMore(result);
                postSmartWy.finishLoadMore();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                //刷新
                count=10;
                presenter.getMyPost(1,count);
                postAdapter.onRefresh(result);
                postSmartWy.finishRefresh();
            }
        });
    }
    @Override
    public void initData() {
        presenter.getMyPost(1,10);
    }
    //接收
    @Subscribe(threadMode = ThreadMode.POSTING,sticky = true)
    public void onEvent(Event event){
        //删除帖子
        int id = event.getId();
        if(id!=-1){
            presenter.getDeletePost(""+id);
        }
    }
    @Override
    public MyPostPresenterImpl initPresenter() {
        return new MyPostPresenterImpl();
    }
    //帖子数据  成功
    @Override
    public void onMyPostSuccess(MyPostBean myPostBean) {
        result = myPostBean.getResult();
        //适配器
        postAdapter = new PostAdapter(result, MyPostActivity.this);
        recyclerPostWy.setAdapter(postAdapter);
    }
    //删除 的  成功
    @Override
    public void onDeletePostSuccess(DeletePostBean deletePostBean) {
        //成功   吐司
        String message = deletePostBean.getMessage();
        Log.d("==", "onDeletePostSuccess: "+message);
        Toast.makeText(MyPostActivity.this,message,Toast.LENGTH_SHORT).show();
        /*//刷新
        postAdapter.notifyDataSetChanged();*/
        //删除成功   刷新
        //再次请求  刷新数据
        //presenter.getMyPost(1,10);
    }
    @Override
    public void onError(String error) {
    }
    @Override
    public void onDeleteError(String error) {
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
