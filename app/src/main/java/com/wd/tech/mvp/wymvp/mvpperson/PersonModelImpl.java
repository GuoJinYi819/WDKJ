package com.wd.tech.mvp.wymvp.mvpperson;

import android.util.Log;

import com.wd.tech.bean.wybean.beanperson.PersonBean;
import com.wd.tech.bean.wybean.beanperson.ResultBean;
import com.wd.tech.net.ApiService;
import com.wd.tech.net.RetrofitUtil;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author 王阳
 * Class :1708A
 * @description:
 * @date :2020/4/21 10:10
 * @classname :PersonModelImpl
 */
public class PersonModelImpl implements IPersonContract.IPersonModel {
    @Override
    public void getPerson(int fromUid, int page, int count, DataCallBack datacallback) {
        RetrofitUtil instance = RetrofitUtil.getInstance();
        //网络判断
        /*boolean net = instance.net();
        if(!net){
            datacallback.onError("网络异常");
        }*/
        ApiService service = instance.createService();
        Observable<PersonBean> personData = service.getPersonData(fromUid, page, count);
        personData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PersonBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onNext(PersonBean value) {
                        List<ResultBean> result = value.getResult();
                        Log.d("==", "onNext: "+result);
                        datacallback.onSuccess(result);
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
