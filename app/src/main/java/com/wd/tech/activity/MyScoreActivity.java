package com.wd.tech.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wd.tech.R;
import com.wd.tech.adapter.wyadapter.ScoreAdapter;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.base.BasePresenter;
import com.wd.tech.bean.wybean.Event;
import com.wd.tech.bean.wybean.beanscore.ResultBean;
import com.wd.tech.bean.wybean.beanscore.ScoreBean;
import com.wd.tech.bean.wybean.beanscoredetailed.ScoreDetailedBean;
import com.wd.tech.mvp.wymvp.mvpscore.IScoreContract;
import com.wd.tech.mvp.wymvp.mvpscore.ScorePresenterImpl;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

public class MyScoreActivity extends BaseActivity<ScorePresenterImpl> implements IScoreContract.IScoreView {
    private android.widget.ImageView imgScoreBackWy;
    private android.widget.TextView tvScoreWy;
    private android.widget.TextView tvDayCountWy;
    private androidx.recyclerview.widget.RecyclerView recyclerScoreWy;

    //我的积分
    @Override
    public int initLayout() {
        return R.layout.activity_my_score;
    }
    @Override
    public void initView() {
        imgScoreBackWy = (ImageView) findViewById(R.id.imgScoreBackWy);
        tvScoreWy = (TextView) findViewById(R.id.tvScoreWy);
        tvDayCountWy = (TextView) findViewById(R.id.tvDayCountWy);
        recyclerScoreWy = (RecyclerView) findViewById(R.id.recyclerScoreWy);
        //布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MyScoreActivity.this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerScoreWy.setLayoutManager(linearLayoutManager);
        //
        EventBus.getDefault().register(this);
    }
    @Override
    public void initListener() {
        //点击销毁
        imgScoreBackWy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    @Override
    public void initData() {
        //查 积分
        presenter.getScore();
        //查 积分明细
        presenter.getDetailedScore(1,10);
    }
    //接收
    @Subscribe(threadMode = ThreadMode.POSTING,sticky = true)
    public void onEvent(Event event){
        int id = event.getId();
        tvDayCountWy.setText("您已连续签到"+id+"天");
    }
    @Override
    public ScorePresenterImpl initPresenter() {
        return new ScorePresenterImpl();
    }
    @Override
    public void onSuccess(ScoreBean scoreBean) {
        //积分   成功
        ResultBean result = scoreBean.getResult();
        int amount = result.getAmount();
        tvScoreWy.setText(""+amount);
    }
    @Override
    public void onError(String error) {
    }
    @Override
    public void onDetailedSuccess(ScoreDetailedBean scoreDetailedBean) {
        //积分明细  成功
        List<com.wd.tech.bean.wybean.beanscoredetailed.ResultBean> result = scoreDetailedBean.getResult();
        ScoreAdapter scoreAdapter = new ScoreAdapter(result, MyScoreActivity.this);
        recyclerScoreWy.setAdapter(scoreAdapter);
        /*//
        int count = scoreAdapter.getCount();
        tvDayCountWy.setText("您已连续签到"+count+"天");*/
    }
    @Override
    public void onDetailedError(String error) {
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
