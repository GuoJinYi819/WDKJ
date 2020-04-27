package com.wd.tech.mvp.wymvp.mvpmodifysignature;

import com.wd.tech.bean.wybean.beanmodifysignature.ModifySignatureBean;
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
 * @date :2020/4/27 20:18
 * @classname :ModifySignatureModel
 */
public class ModifySignatureModelImpl implements IModifySignatureContract.IModifySignatureModel {
    @Override
    public void getModifySignature(String signature, DataCallBack dataCallBack) {
        RetrofitUtil instance = RetrofitUtil.getInstance();
        ApiService service = instance.createService();
        Observable<ModifySignatureBean> modifySignatureData = service.getModifySignatureData(signature);
        modifySignatureData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ModifySignatureBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onNext(ModifySignatureBean value) {
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
