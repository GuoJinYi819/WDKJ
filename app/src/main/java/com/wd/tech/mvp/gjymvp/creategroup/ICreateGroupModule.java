package com.wd.tech.mvp.gjymvp.creategroup;

import com.wd.tech.bean.gjybean.CreateGroupBean;
import com.wd.tech.net.ApiService;
import com.wd.tech.net.RetrofitUtil;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/4/25 0025 15:46
 * @Description: 用途：完成特定功能
 */
public class ICreateGroupModule implements ICreateGroupContract.ICreateGroupModule {
    @Override
    public void createGroup(Map<String, String> params, CreateGroupCallBack createGroupCallBack) {
        RetrofitUtil instance = RetrofitUtil.getInstance();
        ApiService apiService = instance.createService();
        Observable<CreateGroupBean> observable = apiService.createGroup(params);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CreateGroupBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CreateGroupBean value) {
                        createGroupCallBack.onSuccess(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        createGroupCallBack.onFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
