package com.wd.tech.mvp.gjymvp.groupinfo;

import com.wd.tech.bean.gjybean.GroupInfoBean;
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
 * @version 创建时间：2020/4/26 0026 18:17
 * @Description: 用途：完成特定功能
 */
public class GroupInfoModule implements IGroupInfoContract.IGroupInfoModule {
    @Override
    public void getGroupInfo(int groupId, GroupInfoCallBack groupInfoCallBack) {
        RetrofitUtil instance = RetrofitUtil.getInstance();
        ApiService apiService = instance.createService();
        Observable<GroupInfoBean> observable = apiService.getGroupInfo(groupId);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GroupInfoBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GroupInfoBean value) {
                        groupInfoCallBack.onSuccess(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        groupInfoCallBack.onFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
