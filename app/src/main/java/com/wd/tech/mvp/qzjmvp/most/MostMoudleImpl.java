package com.wd.tech.mvp.qzjmvp.most;

import com.wd.tech.bean.qzjbean.most.MostBean;
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
 * @version 创建时间：2020/4/27 11:36
 * @Description: 用途：完成特定功能
 */
public class MostMoudleImpl implements MostConter.IMostMoudle {
    @Override
    public void onDate(MyCallBack myCallBack) {
        RetrofitUtil instance = RetrofitUtil.getInstance();
        Observable<MostBean> mostData = instance.createService().getMostData();
        mostData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MostBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MostBean value) {
                        myCallBack.onSuccess(value);
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
