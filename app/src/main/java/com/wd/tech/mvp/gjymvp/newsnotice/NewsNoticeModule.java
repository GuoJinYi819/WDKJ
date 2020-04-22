package com.wd.tech.mvp.gjymvp.newsnotice;

import com.wd.tech.bean.gjybean.FriendNoticeBean;
import com.wd.tech.bean.gjybean.GroupNoticeBean;
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
 * @version 创建时间：2020/4/21 0021 21:54
 * @Description: 用途：完成特定功能
 */
public class NewsNoticeModule implements INewsNoticeContract.INewsNoticeModule {
    @Override
    public void getFriendData(FriendDataCallBack friendDataCallBack) {
       //暂不需要
    }

    @Override
    public void getFriendNotice(Map<String, String> params, FriendNoticeCallBack friendNoticeCallBack) {
        RetrofitUtil instance = RetrofitUtil.getInstance();
        ApiService apiService = instance.createService();
        Observable<FriendNoticeBean> observable = apiService.getFriendNotice(params);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FriendNoticeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FriendNoticeBean value) {
                        friendNoticeCallBack.onFriendNoticeSuccess(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getGroupNotice(Map<String, String> params, GroupNoticeCallBack groupNoticeCallBack) {
        RetrofitUtil instance = RetrofitUtil.getInstance();
        ApiService apiService = instance.createService();
        Observable<GroupNoticeBean> observable = apiService.getGroupNotice(params);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GroupNoticeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GroupNoticeBean value) {
                        groupNoticeCallBack.onGroupNoticeSuccess(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
