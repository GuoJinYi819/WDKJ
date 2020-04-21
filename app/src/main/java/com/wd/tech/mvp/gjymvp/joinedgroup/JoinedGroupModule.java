package com.wd.tech.mvp.gjymvp.joinedgroup;

import com.wd.tech.bean.gjybean.JoinedGroupBean;
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
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/4/21 0021 11:46
 * @Description: 用途：完成特定功能
 */
public class JoinedGroupModule implements IJoinedGroupContract.IJoinedGroupModule {
    @Override
    public void getJoinedGroup(JoinedGroupCallBack joinedGroupCallBack) {
        RetrofitUtil instance = RetrofitUtil.getInstance();
        ApiService apiService = instance.createService();
        Observable<JoinedGroupBean> observable = apiService.joinedGroup();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JoinedGroupBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(JoinedGroupBean value) {
                        joinedGroupCallBack.onSuccess(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        joinedGroupCallBack.onFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
