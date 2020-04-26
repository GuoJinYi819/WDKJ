package com.wd.tech.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wd.tech.R;
import com.wd.tech.adapter.wyadapter.VipListAdapter;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.base.BasePresenter;
import com.wd.tech.bean.wybean.beanselectviplist.ResultBean;
import com.wd.tech.bean.wybean.beanselectviplist.SelectVipListBean;
import com.wd.tech.mvp.wymvp.mvpselectviplist.ISelectVipListContract;
import com.wd.tech.mvp.wymvp.mvpselectviplist.SelectVipListPresenterImpl;

import java.util.List;

public class MyVIPActivity extends BaseActivity<SelectVipListPresenterImpl> implements ISelectVipListContract.ISelectVipListView {
    private android.widget.ImageView imgVIPBackWy;
    private androidx.recyclerview.widget.RecyclerView recyclerVipWy;
    /*private android.widget.LinearLayout linearWeekVIPWy;
    private ImageView imgWeekVIPNameWy;
    private android.widget.TextView tvWeekVIPNameWy;
    private android.widget.TextView tvWeekVIPPriceWy;
    private android.widget.LinearLayout linearMonthVIPWy;
    private ImageView imgMonthVIPNameWy;
    private android.widget.TextView tvMonthVIPNameWy;
    private android.widget.TextView tvMonthVIPPriceWy;
    private android.widget.LinearLayout linearMonthsVIPWy;
    private ImageView imgMonthsVIPNameWy;
    private android.widget.TextView tvMonthsVIPNameWy;
    private android.widget.TextView tvMonthsVIPPriceWy;
    private android.widget.LinearLayout linearYearVIPPriceWy;
    private ImageView imgYearVIPNameWy;
    private android.widget.TextView tvYearVIPNameWy;
    private android.widget.TextView tvYearVIPPriceWy;*/

    @Override
    public int initLayout() {
        return R.layout.activity_my_v_i_p;
    }
    @Override
    public void initView() {
        imgVIPBackWy = (ImageView) findViewById(R.id.imgVIPBackWy);
        recyclerVipWy = (RecyclerView) findViewById(R.id.recyclerVipWy);
        //布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MyVIPActivity.this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerVipWy.setLayoutManager(linearLayoutManager);
        /*linearWeekVIPWy = (LinearLayout) findViewById(R.id.linearWeekVIPWy);
        imgWeekVIPNameWy = (ImageView) findViewById(R.id.imgWeekVIPNameWy);
        tvWeekVIPNameWy = (TextView) findViewById(R.id.tvWeekVIPNameWy);
        tvWeekVIPPriceWy = (TextView) findViewById(R.id.tvWeekVIPPriceWy);
        linearMonthVIPWy = (LinearLayout) findViewById(R.id.linearMonthVIPWy);
        imgMonthVIPNameWy = (ImageView) findViewById(R.id.imgMonthVIPNameWy);
        tvMonthVIPNameWy = (TextView) findViewById(R.id.tvMonthVIPNameWy);
        tvMonthVIPPriceWy = (TextView) findViewById(R.id.tvMonthVIPPriceWy);
        linearMonthsVIPWy = (LinearLayout) findViewById(R.id.linearMonthsVIPWy);
        imgMonthsVIPNameWy = (ImageView) findViewById(R.id.imgMonthsVIPNameWy);
        tvMonthsVIPNameWy = (TextView) findViewById(R.id.tvMonthsVIPNameWy);
        tvMonthsVIPPriceWy = (TextView) findViewById(R.id.tvMonthsVIPPriceWy);
        linearYearVIPPriceWy = (LinearLayout) findViewById(R.id.linearYearVIPPriceWy);
        imgYearVIPNameWy = (ImageView) findViewById(R.id.imgYearVIPNameWy);
        tvYearVIPNameWy = (TextView) findViewById(R.id.tvYearVIPNameWy);
        tvYearVIPPriceWy = (TextView) findViewById(R.id.tvYearVIPPriceWy);*/
    }
    @Override
    public void initListener() {
        //点击  返回
        imgVIPBackWy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    @Override
    public void initData() {
        presenter.getSelectVipList();
    }
    @Override
    public SelectVipListPresenterImpl initPresenter() {
        return new SelectVipListPresenterImpl();
    }
    @Override
    public void onSuccess(SelectVipListBean selectVipListBean) {
        //成功
        List<ResultBean> result = selectVipListBean.getResult();
        VipListAdapter vipListAdapter = new VipListAdapter(result, MyVIPActivity.this);
        recyclerVipWy.setAdapter(vipListAdapter);
    }
    @Override
    public void onError(String error) {
    }
}
