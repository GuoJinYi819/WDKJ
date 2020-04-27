package com.wd.tech.mvp.gjymvp.addfriendgroup;

import com.wd.tech.bean.gjybean.AddFriendGroupBean;
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
 * @version 创建时间：2020/4/25 0025 21:46
 * @Description: 用途：完成特定功能
 */
public class AddFriendGroupModule implements IAddFriendGroupContract.IAddFriendGroupModule {
    @Override
    public void addFriendGroup(String groupName, IAddFriendGroupCallBack addFriendGroupCallBack) {
        RetrofitUtil instance = RetrofitUtil.getInstance();
        ApiService apiService = instance.createService();
        Observable<AddFriendGroupBean> observable = apiService.addFriendGroup(groupName);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AddFriendGroupBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(AddFriendGroupBean value) {
                        addFriendGroupCallBack.onAddFriendGroupSuccess(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        addFriendGroupCallBack.onFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
