package com.wd.tech.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.tech.R;
import com.wd.tech.adapter.wyadapter.RecyclerCommentListAdapter;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.base.BasePresenter;
import com.wd.tech.bean.wybean.Event;
import com.wd.tech.bean.wybean.beancommentlist.CommentListBean;
import com.wd.tech.bean.wybean.beancommentlist.ResultBean;
import com.wd.tech.bean.wybean.beandotask.DoTaskBean;
import com.wd.tech.mvp.wymvp.mvpcommentlist.CommentListPresenterImpl;
import com.wd.tech.mvp.wymvp.mvpcommentlist.ICommentListContract;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class CommentListActivity extends BaseActivity<CommentListPresenterImpl> implements ICommentListContract.ICommentListView {
    private android.widget.ImageView imgBackWy;
    private com.facebook.drawee.view.SimpleDraweeView imgListHeadWy;
    private TextView tvListPersonNameWy;
    private TextView tvCommentSum;
    private androidx.recyclerview.widget.RecyclerView recyclerCommentWy;

    //评论列表页
    @Override
    public int initLayout() {
        return R.layout.activity_comment_list;
    }
    @Override
    public void initView() {
        imgBackWy = (ImageView) findViewById(R.id.imgBackWy);
        imgListHeadWy = (SimpleDraweeView) findViewById(R.id.imgListHeadWy);
        tvListPersonNameWy = (TextView) findViewById(R.id.tvListPersonNameWy);
        tvCommentSum = (TextView) findViewById(R.id.tvCommentSum);
        recyclerCommentWy = (RecyclerView) findViewById(R.id.recyclerCommentWy);
        //布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(CommentListActivity.this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerCommentWy.setLayoutManager(linearLayoutManager);
        //订阅
        EventBus.getDefault().register(this);
    }
    @Override
    public void initListener() {
        //返回
        imgBackWy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //返回
                finish();
            }
        });
    }
    @Override
    public void initData() {
    }
    //接收
    @Subscribe(threadMode = ThreadMode.POSTING,sticky = true)
    public void onEvent(Event event){
        int id = event.getId();
        int count = event.getCount();
        String personName = event.getPersonName();
        String head = event.getHead();
        Log.d("==", "onEvent: "+count+personName);
        //设置数据
        Uri uri= Uri.parse(head);
        imgListHeadWy.setImageURI(uri);
        if(!TextUtils.isEmpty(personName)){
            tvListPersonNameWy.setText(personName);
            tvCommentSum.setText(count+"条数据");
        }
        presenter.getCommentList(id,1,10);
        //加分
        presenter.getDoTask(1002);
    }
    @Override
    public CommentListPresenterImpl initPresenter() {
        return new CommentListPresenterImpl();
    }
    @Override
    public void onSuccess(CommentListBean commentListBean) {
        List<ResultBean> result = commentListBean.getResult();
        //适配器
        RecyclerCommentListAdapter recyclerCommentListAdapter = new RecyclerCommentListAdapter(result, CommentListActivity.this);
        recyclerCommentWy.setAdapter(recyclerCommentListAdapter);
    }
    @Override
    public void onError(String error) {
    }
    @Override
    public void onTaskSuccess(DoTaskBean doTaskBean) {
        String message = doTaskBean.getMessage();
        Log.d("==", "onTaskSuccess: "+message);
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
