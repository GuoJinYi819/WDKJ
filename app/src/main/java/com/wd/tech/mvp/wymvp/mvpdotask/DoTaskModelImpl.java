package com.wd.tech.mvp.wymvp.mvpdotask;

import com.wd.tech.bean.wybean.beandotask.DoTaskBean;
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
 * @date :2020/4/23 19:36
 * @classname :DoTaskModelImpl
 */
public class DoTaskModelImpl implements IDoTaskContract.IDoTaskModel {
    @Override
    public void getDoTask(int taskId, DataCallBack dataCallBack) {
        RetrofitUtil instance = RetrofitUtil.getInstance();
        ApiService service = instance.createService();
        Observable<DoTaskBean> doTaskData = service.getDoTaskData(taskId);
        doTaskData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DoTaskBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onNext(DoTaskBean value) {
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
}
