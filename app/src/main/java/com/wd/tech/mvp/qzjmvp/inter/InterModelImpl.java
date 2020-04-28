package com.wd.tech.mvp.qzjmvp.inter;

import android.util.Log;

import com.wd.tech.bean.qzjbean.detail.DetailBean;
import com.wd.tech.bean.qzjbean.inter.InterBean;
import com.wd.tech.bean.wybean.beanscore.ScoreBean;
import com.wd.tech.bean.wybean.beanscoredetailed.ScoreDetailedBean;
import com.wd.tech.mvp.wymvp.mvpscore.IScoreContract;
import com.wd.tech.net.ApiService;
import com.wd.tech.net.RetrofitUtil;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author quzijun
 * Class :1708A
 * @description:
 * @date :2020/4/25 16:04
 * @classname :ScoreModelImpl
 */
public class InterModelImpl implements InterConter.IScoreModel {
    @Override
    public void getScore(DataCallBack dataCallBack) {
        RetrofitUtil instance = RetrofitUtil.getInstance();
        ApiService service = instance.createService();
        Observable<ScoreBean> scoreData = service.getScoreData();
        scoreData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ScoreBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onNext(ScoreBean value) {
                        dataCallBack.onSuccess(value);
                    }
                    @Override
                    public void onError(Throwable e) {
                        dataCallBack.onError(e.getMessage());
                    }
                    @Override
                    public void onComplete() {
                    }
                });
    }

    @Override
    public void getDetailedScore(int id, DataCallBack2 dataCallBack2) {
        RetrofitUtil instance = RetrofitUtil.getInstance();
        ApiService service = instance.createService();
        Observable<DetailBean> detailsData = service.getDetailsData(id);
        detailsData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DetailBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DetailBean value) {
                        dataCallBack2.onDetailedSuccess(value);
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
    public void getInterDate(int infoId, int integralCost, DataCallBack3 dataCallBack3) {
        RetrofitUtil instance = RetrofitUtil.getInstance();
        ApiService service = instance.createService();
        Observable<InterBean> detailsData = service.getInterData(infoId,integralCost);
        detailsData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<InterBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(InterBean value) {
                        dataCallBack3.onDetailedSuccess(value);
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
