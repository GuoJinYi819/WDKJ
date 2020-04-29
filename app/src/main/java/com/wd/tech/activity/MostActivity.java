package com.wd.tech.activity;

import android.content.Intent;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.adapter.qzjadapter.ConListAdapter;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.base.BasePresenter;
import com.wd.tech.bean.qzjbean.consultationlist.ConListBean;
import com.wd.tech.bean.qzjbean.consultationlist.ConResultBean;
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
 * @version 创建时间：2020/4/28 14:46
 * @Description: 用途：模块列表
 */
public class MostActivity extends BaseActivity {
    private androidx.recyclerview.widget.RecyclerView re;
    private ConListAdapter adapter;

    @Override
    public int initLayout() {
        return R.layout.activity_most;
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
        Observable<ConListBean> listData = service.getListData(id, 1, 7);
        listData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ConListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onNext(ConListBean value) {
                        List<ConResultBean> result = value.getResult();
                        adapter = new ConListAdapter(result,MostActivity.this);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MostActivity.this);
                        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
                        re.setLayoutManager(linearLayoutManager);
                        re.setAdapter(adapter);
                        adapter.setAdapterCallBack(new ConListAdapter.AdapterCallBack() {
                            @Override
                            public void onGiveId(int id, int ismoney) {
                                Intent intent = new Intent(MostActivity.this, ConsultaActivity.class);
                                intent.putExtra("id",id);
                                intent.putExtra("ismoney",ismoney);
                                startActivity(intent);
                            }
                        });
                    }
                    @Override
                    public void onError(Throwable e) {
                        String message = e.getMessage();
                        Log.d("XXX",message);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }
}
