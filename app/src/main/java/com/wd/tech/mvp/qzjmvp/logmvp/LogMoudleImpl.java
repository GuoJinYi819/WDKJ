package com.wd.tech.mvp.qzjmvp.logmvp;

import com.wd.tech.bean.qzjbean.LogBean;
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
 * @version 创建时间：2020/4/17 22:10
 * @Description: 用途：完成特定功能
 */
public class LogMoudleImpl implements LogConter.ILogMoudle {
    @Override
    public void getData(String phone, String pwd, MyCallBack myCallBack) {
        RetrofitUtil instance = RetrofitUtil.getInstance();
        ApiService service = instance.createService();
        Observable<LogBean> log = service.getLog(phone, pwd);
        log.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LogBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LogBean logBean) {
                        myCallBack.onSuccess(logBean);
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
