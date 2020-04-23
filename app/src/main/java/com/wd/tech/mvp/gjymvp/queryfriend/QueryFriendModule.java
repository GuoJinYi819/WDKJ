package com.wd.tech.mvp.gjymvp.queryfriend;

import com.wd.tech.bean.gjybean.QueryFriendBean;
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
 * @version 创建时间：2020/4/23 0023 14:28
 * @Description: 用途：完成特定功能
 */
public class QueryFriendModule implements IQueryFriendContract.IQueryFriendModule {
    @Override
    public void queryFriend(int friend, QueryFrinedCallBack queryFrinedCallBack) {
        RetrofitUtil instance = RetrofitUtil.getInstance();
        ApiService apiService = instance.createService();
        Observable<QueryFriendBean> observable = apiService.queryFriend(friend);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<QueryFriendBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(QueryFriendBean value) {
                        queryFrinedCallBack.onSuccess(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        queryFrinedCallBack.onFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
