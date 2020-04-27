package com.wd.tech.mvp.wymvp.mvpselectuser;

import com.wd.tech.bean.wybean.beanmodifyheadPic.ModifyHeadPicBean;
import com.wd.tech.bean.wybean.beanselectuser.SelectUserBean;
import com.wd.tech.net.ApiService;
import com.wd.tech.net.RetrofitUtil;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;

/**
 * @author 王阳
 * Class :1708A
 * @description:
 * @date :2020/4/23 20:41
 * @classname :SelectUserModelImpl
 */
public class SelectUserModelImpl implements ISelectUserContract.ISelectUserModel {
    @Override
    public void getSelectUser(DataCallBack dataCallBack) {
        RetrofitUtil instance = RetrofitUtil.getInstance();
        ApiService service = instance.createService();
        Observable<SelectUserBean> selectUserData = service.getSelectUserData();
        selectUserData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SelectUserBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onNext(SelectUserBean value) {
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
    public void getModifyHeadPic(MultipartBody.Part image, DataCallBack2 dataCallBack2) {
        RetrofitUtil instance = RetrofitUtil.getInstance();
        ApiService service = instance.createService();
        Observable<ModifyHeadPicBean> modifyHeadPicData = service.getModifyHeadPicData(image);
        modifyHeadPicData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ModifyHeadPicBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onNext(ModifyHeadPicBean value) {
                        dataCallBack2.onHeadSuccess(value);
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
