package com.wd.tech.mvp.gjymvp.friendseach;

import com.wd.tech.bean.gjybean.FriendSeachBean;
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
 * @version 创建时间：2020/4/21 0021 9:57
 * @Description: 用途：完成特定功能
 */
public class FriendSeachModule implements IFriendSeachContract.IFriendSeachModule {
    @Override
    public void getFriendSeach(String searchName, FriendSeachCallBack friendSeachCallBack) {
        RetrofitUtil instance = RetrofitUtil.getInstance();
        ApiService apiService = instance.createService();
        Observable<FriendSeachBean> observable = apiService.getFriendSeachData(searchName);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FriendSeachBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FriendSeachBean value) {
                        friendSeachCallBack.onSuccess(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        friendSeachCallBack.onFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
