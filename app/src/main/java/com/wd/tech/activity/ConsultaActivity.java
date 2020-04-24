package com.wd.tech.activity;

import android.content.Intent;
import android.util.Log;

import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.wd.tech.R;
import com.wd.tech.adapter.qzjadapter.DetailAdapter;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.base.BasePresenter;
import com.wd.tech.bean.qzjbean.detail.DetailBean;
import com.wd.tech.mvp.qzjmvp.detailmvp.IDetailConter;
import com.wd.tech.mvp.qzjmvp.detailmvp.IDetailPresenterImpl;
import com.wd.tech.net.ApiService;
import com.wd.tech.net.RetrofitUtil;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : Quzijun
 * @version 创建时间：2020/4/23 19:49
 * @Description: 用途：完成特定功能
 */
public class ConsultaActivity extends BaseActivity<IDetailPresenterImpl> implements IDetailConter.IDetaView {
    private androidx.recyclerview.widget.RecyclerView re;
    private DetailAdapter adapter;
    private android.widget.ImageView fanhui;
    private android.widget.EditText etext;
    private android.widget.ImageView plt;
    private android.widget.TextView pls;
    private android.widget.ImageView dzt;
    private android.widget.TextView dzs;
    private android.widget.ImageView sct;
    private android.widget.ImageView fxt;
    private android.widget.TextView fxs;

    @Override
    public int initLayout() {
        return R.layout.activity_consulation;
    }

    @Override
    public void initView() {

        re = findViewById(R.id.re);
        fanhui = findViewById(R.id.fanhui);
        etext = findViewById(R.id.etext);
        plt = findViewById(R.id.plt);
        pls = findViewById(R.id.pls);
        dzt = findViewById(R.id.dzt);
        dzs = findViewById(R.id.dzs);
        sct = findViewById(R.id.sct);
        fxt = findViewById(R.id.fxt);
        fxs = findViewById(R.id.fxs);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 3);
        Log.d("XXX",id+"");
        presenter.onDetaDate(id);
    }

    @Override
    public IDetailPresenterImpl initPresenter() {
        return new IDetailPresenterImpl();
    }

    @Override
    public void onDetaSuccess(DetailBean detailBean) {
        DetailBean.ResultBean result = detailBean.getResult();
        StaggeredGridLayoutManager s = new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
        re.setLayoutManager(s);
        adapter = new DetailAdapter(result,ConsultaActivity.this);
        re.setAdapter(adapter);
    }
}
