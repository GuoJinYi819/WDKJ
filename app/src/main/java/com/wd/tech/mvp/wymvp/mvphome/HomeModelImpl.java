package com.wd.tech.mvp.wymvp.mvphome;

import com.wd.tech.bean.wybean.beanhome.HomeBean;
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
 * @date :2020/4/19 14:08
 * @classname :HomeModelImpl
 */
public class HomeModelImpl implements IHomeContract.IHomeModel {
    @Override
    public void getHome(int page, int count, DataCallBack datacallback) {
        RetrofitUtil instance = RetrofitUtil.getInstance();
        //网络判断
        boolean net = instance.net();
        if(!net){
            datacallback.onError("网络异常");
        }
        ApiService service = instance.createService();
        Observable<HomeBean> homeData = service.getHomeData(page, count);
        homeData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onNext(HomeBean value) {
                        //成功
                        datacallback.onSuccess(value);
                    }
                    @Override
                    public void onError(Throwable e) {
                        datacallback.onError(e.getMessage());
                    }
                    @Override
                    public void onComplete() {
                    }
                });
    }
}
