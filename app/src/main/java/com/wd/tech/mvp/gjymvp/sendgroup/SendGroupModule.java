package com.wd.tech.mvp.gjymvp.sendgroup;

import com.wd.tech.bean.gjybean.QueryGroupBean;
import com.wd.tech.bean.gjybean.SendGroupBean;
import com.wd.tech.net.ApiService;
import com.wd.tech.net.RetrofitUtil;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/4/24 0024 16:37
 * @Description: 用途：完成特定功能
 */
public class SendGroupModule implements ISendGroupContract.ISendGroupModule {
    @Override
    public void getQueryGroup(Map<String, String> params, QueryGroupCallBack queryGroupCallBack) {
        RetrofitUtil instance = RetrofitUtil.getInstance();
        ApiService apiService = instance.createService();
        Observable<QueryGroupBean> observable = apiService.queryGroup(params);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<QueryGroupBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(QueryGroupBean value) {
                        queryGroupCallBack.onQueryGroupSuccess(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void sendGroup(Map<String, String> params, SendGroupCallBack sendGroupCallBack) {
        RetrofitUtil instance = RetrofitUtil.getInstance();
        ApiService apiService = instance.createService();
        Observable<SendGroupBean> observable = apiService.sendGroup(params);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SendGroupBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SendGroupBean value) {
                        sendGroupCallBack.onSendGroupSuccess(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
