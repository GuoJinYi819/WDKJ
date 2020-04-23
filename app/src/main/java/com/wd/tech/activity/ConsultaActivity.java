package com.wd.tech.activity;

import android.content.Intent;

import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.base.BasePresenter;
import com.wd.tech.bean.qzjbean.detail.DetailBean;
import com.wd.tech.net.ApiService;
import com.wd.tech.net.RetrofitUtil;

import io.reactivex.Observable;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : Quzijun
 * @version 创建时间：2020/4/23 19:49
 * @Description: 用途：完成特定功能
 */
public class ConsultaActivity extends BaseActivity {
    private androidx.recyclerview.widget.RecyclerView re;

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
        RetrofitUtil instance = RetrofitUtil.getInstance();
        ApiService service = instance.createService();
        Observable<DetailBean> detailsData = service.getDetailsData(id);
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }
}
