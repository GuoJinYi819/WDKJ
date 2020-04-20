package com.wd.tech.mvp.gjymvp.friendlist;

import com.wd.tech.bean.gjybean.FriendChildListBean;
import com.wd.tech.bean.gjybean.FriendGroupListBean;
import com.wd.tech.bean.wybean.beancomment.CommentBean;
import com.wd.tech.net.ApiService;
import com.wd.tech.net.RetrofitUtil;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/4/20 0020 17:32
 * @Description: 用途：完成特定功能
 */
public class FriendMoudle implements IFriendContract.IFriendModule {


    @Override
    public void getFriendGroupData(FriendGroupCallBack friendGroupCallBack) {
        RetrofitUtil instance = RetrofitUtil.getInstance();
        ApiService apiService = instance.createService();
        Observable<FriendGroupListBean> observable = apiService.getFriendGroupList();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FriendGroupListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FriendGroupListBean value) {
                        friendGroupCallBack.onGroupSuccess(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        friendGroupCallBack.onFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getFriendChildData(int groupId, FriendChildCallBack friendChildCallBack) {
        RetrofitUtil instance = RetrofitUtil.getInstance();
        ApiService apiService = instance.createService();
        Observable<FriendChildListBean> observable = apiService.getFriendChildList(groupId);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FriendChildListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FriendChildListBean value) {
                        friendChildCallBack.onChildSuccess(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        friendChildCallBack.onFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
