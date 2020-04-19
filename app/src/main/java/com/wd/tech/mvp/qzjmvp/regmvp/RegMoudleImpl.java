package com.wd.tech.mvp.qzjmvp.regmvp;

import com.wd.tech.bean.qzjbean.regist.RegBean;
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
 * @Description: 用途：注册的M层
 */
public class RegMoudleImpl implements RegConter.IRegMoudle {

    @Override
    public void getData(String phone, String name, String pwd, MyCallBack myCallBack) {
        RetrofitUtil instance = RetrofitUtil.getInstance();
        ApiService service = instance.createService();
        Observable<RegBean> reg = service.getReg(phone,name,pwd);
        reg.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RegBean regBean) {
                        myCallBack.onSuccess(regBean);
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
