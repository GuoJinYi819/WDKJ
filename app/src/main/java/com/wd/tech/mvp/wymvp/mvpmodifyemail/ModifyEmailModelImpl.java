package com.wd.tech.mvp.wymvp.mvpmodifyemail;

import com.wd.tech.bean.wybean.beanmodifyemail.ModifyEmailBean;
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
 * @date :2020/4/28 14:07
 * @classname :ModifyEmailModelImpl
 */
public class ModifyEmailModelImpl implements IModifyEmailContract.IModifyEmailModel {
    @Override
    public void getModifyEmail(String email, DataCallBack dataCallBack) {
        RetrofitUtil instance = RetrofitUtil.getInstance();
        ApiService service = instance.createService();
        Observable<ModifyEmailBean> modifyEmailData = service.getModifyEmailData(email);
        modifyEmailData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ModifyEmailBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onNext(ModifyEmailBean value) {
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
