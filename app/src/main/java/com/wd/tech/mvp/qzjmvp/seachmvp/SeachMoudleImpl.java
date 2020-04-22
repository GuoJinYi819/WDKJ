package com.wd.tech.mvp.qzjmvp.seachmvp;

import com.wd.tech.bean.qzjbean.seach.SeachBean;
import com.wd.tech.mvp.qzjmvp.xbannermvp.XbConnter;
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
 * @version 创建时间：2020/4/22 15:59
 * @Description: 用途：完成特定功能
 */
public class SeachMoudleImpl implements SeachConter.ISeachMoudle {

    @Override
    public void getData(String title, int page, int count, MyCallBack myCallBack) {
        RetrofitUtil instance = RetrofitUtil.getInstance();
        ApiService service = instance.createService();
        Observable<SeachBean> seachData = service.getSeachData(title, page, count);
        seachData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SeachBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SeachBean seachBean) {
                        myCallBack.onSuccess(seachBean);
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
