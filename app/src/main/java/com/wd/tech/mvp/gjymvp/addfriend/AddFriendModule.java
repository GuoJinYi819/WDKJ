package com.wd.tech.mvp.gjymvp.addfriend;

import com.wd.tech.bean.gjybean.AddFriendBean;
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
 * @version 创建时间：2020/4/25 0025 14:44
 * @Description: 用途：完成特定功能
 */
public class AddFriendModule implements IAddFriendContract.IAddFriendModule {
    @Override
    public void addFriend(Map<String, String> params, AddFriendCallBack addFriendCallBack) {
        RetrofitUtil instance = RetrofitUtil.getInstance();
        ApiService apiService = instance.createService();
        Observable<AddFriendBean> observable = apiService.addFriend(params);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AddFriendBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(AddFriendBean value) {
                        addFriendCallBack.onSuccess(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        addFriendCallBack.onFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
