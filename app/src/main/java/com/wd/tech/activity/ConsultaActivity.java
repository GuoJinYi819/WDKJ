package com.wd.tech.activity;

import android.content.Intent;

import com.wd.tech.R;
import com.wd.tech.adapter.qzjadapter.DetailAdapter;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.base.BasePresenter;
import com.wd.tech.bean.qzjbean.detail.DetailBean;
import com.wd.tech.net.ApiService;
import com.wd.tech.net.RetrofitUtil;

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
public class ConsultaActivity extends BaseActivity {
    private androidx.recyclerview.widget.RecyclerView re;
    private DetailAdapter adapter;

    @Override
    public int initLayout() {
        return R.layout.activity_consulation;
    }

    @Override
    public void initView() {

        re = findViewById(R.id.re);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 3);

    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }
}
