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
import com.wd.tech.adapter.wyadapter.NoticeAdapter;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.base.BasePresenter;
import com.wd.tech.bean.wybean.beannotice.NoticeBean;
import com.wd.tech.bean.wybean.beannotice.ResultBean;
import com.wd.tech.mvp.wymvp.mvpnotice.INoticeContract;
import com.wd.tech.mvp.wymvp.mvpnotice.NoticePresenterImpl;

import java.util.List;

public class MyNoticeActivity extends BaseActivity<NoticePresenterImpl> implements INoticeContract.INoticeView {
    private android.widget.ImageView imgNoticeBackWy;
    private androidx.recyclerview.widget.RecyclerView recyclerNoticeWy;
    private android.widget.TextView tvIsNotice;

    @Override
    public int initLayout() {
        return R.layout.activity_my_notice;
    }
    @Override
    public void initView() {
        imgNoticeBackWy = (ImageView) findViewById(R.id.imgNoticeBackWy);
        recyclerNoticeWy = (RecyclerView) findViewById(R.id.recyclerNoticeWy);
        //布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MyNoticeActivity.this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerNoticeWy.setLayoutManager(linearLayoutManager);
        tvIsNotice = (TextView) findViewById(R.id.tvIsNotice);
    }
    @Override
    public void initListener() {
        //销毁
        imgNoticeBackWy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    @Override
    public void initData() {
        presenter.getNotice(1,10);
    }
    @Override
    public NoticePresenterImpl initPresenter() {
        return new NoticePresenterImpl();
    }
    @Override
    public void onSuccess(NoticeBean noticeBean) {
        //成功
        String message = noticeBean.getMessage();
        Toast.makeText(MyNoticeActivity.this,message,Toast.LENGTH_SHORT).show();
        //
        List<ResultBean> result = noticeBean.getResult();
        if(result.size()!=0){
            //适配器
            tvIsNotice.setText("");
            NoticeAdapter noticeAdapter = new NoticeAdapter(result, MyNoticeActivity.this);
            recyclerNoticeWy.setAdapter(noticeAdapter);
        }else{
            tvIsNotice.setText("暂无通知");
        }
    }
    @Override
    public void onError(String error) {
    }
}
