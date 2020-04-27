package com.wd.tech.mvp.wymvp.mvpmodifynickname;

import com.wd.tech.bean.wybean.beanmodifynickname.ModifyNickNameBean;
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
 * @date :2020/4/27 19:43
 * @classname :ModifyNickNameModelImpl
 */
public class ModifyNickNameModelImpl implements IModifyNickNameContract.IModifyNickNameModel {
    @Override
    public void getModifyNickName(String nickName, DataCallBack dataCallBack) {
        RetrofitUtil instance = RetrofitUtil.getInstance();
        ApiService service = instance.createService();
        Observable<ModifyNickNameBean> modifyNickNameData = service.getModifyNickNameData(nickName);
        modifyNickNameData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ModifyNickNameBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onNext(ModifyNickNameBean value) {
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
