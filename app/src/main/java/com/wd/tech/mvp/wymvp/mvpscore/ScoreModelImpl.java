package com.wd.tech.mvp.wymvp.mvpscore;

import android.util.Log;

import com.wd.tech.bean.wybean.beanscore.ScoreBean;
import com.wd.tech.bean.wybean.beanscoredetailed.ScoreDetailedBean;
import com.wd.tech.net.ApiService;
import com.wd.tech.net.RetrofitUtil;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author 王阳
 * Class :1708A
 * @description:
 * @date :2020/4/25 16:04
 * @classname :ScoreModelImpl
 */
public class ScoreModelImpl implements IScoreContract.IScoreModel {
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
    public void getDetailedScore(int page, int count, DataCallBack2 dataCallBack2) {
        RetrofitUtil instance = RetrofitUtil.getInstance();
        ApiService service = instance.createService();
        Observable<ScoreDetailedBean> scoreDetailedData = service.getScoreDetailedData(page, count);
        scoreDetailedData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ScoreDetailedBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onNext(ScoreDetailedBean value) {
                        dataCallBack2.onDetailedSuccess(value);
                    }
                    @Override
                    public void onError(Throwable e) {
                        Log.d("==", "onError: "+e.getMessage());
                        dataCallBack2.onDetailedError(e.getMessage());
                    }
                    @Override
                    public void onComplete() {
                    }
                });
    }
}
