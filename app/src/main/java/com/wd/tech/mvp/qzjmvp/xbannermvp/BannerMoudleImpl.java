package com.wd.tech.mvp.qzjmvp.xbannermvp;

import com.wd.tech.bean.qzjbean.xbanner.XbBean;
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
 * @version 创建时间：2020/4/19 20:50
 * @Description: 用途：完成特定功能
 */
public class BannerMoudleImpl implements XbConnter.IbannerMoudle {
    @Override
    public void getData(MyCallBack myCallBack) {
        RetrofitUtil instance = RetrofitUtil.getInstance();
        Observable<XbBean> xbData = instance.createService().getXbData();
        xbData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<XbBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(XbBean xbBean) {
                        myCallBack.onSuccess(xbBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        String message = e.getMessage();
                        myCallBack.onError(message);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
