package com.wd.tech.mvp.wymvp.mvpimproveinformation;

import com.wd.tech.bean.wybean.beandotask.DoTaskBean;
import com.wd.tech.bean.wybean.beanimproveinformation.ImproveInformationBean;
import com.wd.tech.mvp.wymvp.mvpsign.ISignContract;
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
 * @date :2020/4/25 19:42
 * @classname :ImproveInformationModelImpl
 */
public class ImproveInformationModelImpl implements IImproveInformationContract.IImproveInformationModel {
    @Override
    public void getImproveInformation(String nickName, int sex, String signature, String birthday, String email, DataCallBack dataCallBack) {
        RetrofitUtil instance = RetrofitUtil.getInstance();
        ApiService service = instance.createService();
        Observable<ImproveInformationBean> improveInformationData = service.getImproveInformationData(nickName, sex, signature, birthday, email);
        improveInformationData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ImproveInformationBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onNext(ImproveInformationBean value) {
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
    public void getDoTask(int taskId, ISignContract.ISignModel.DataCallBack2 dataCallBack2) {
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
                        dataCallBack2.onSuccess(value);
                    }
                    @Override
                    public void onError(Throwable e) {
                        dataCallBack2.onError(e.getMessage());
                    }
                    @Override
                    public void onComplete() {
                    }
                });
    }
}
