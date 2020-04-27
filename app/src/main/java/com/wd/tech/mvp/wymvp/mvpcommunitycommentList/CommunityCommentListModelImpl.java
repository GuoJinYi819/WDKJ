package com.wd.tech.mvp.wymvp.mvpcommunitycommentList;

import com.wd.tech.bean.wybean.beancommunitycommentList.CommunityCommentListBean;
import com.wd.tech.net.ApiService;
import com.wd.tech.net.RetrofitUtil;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author 王阳
 * Class :1708A
 * @description:
 * @date :2020/4/26 20:32
 * @classname :CommunityCommentListModelImpl
 */
public class CommunityCommentListModelImpl implements ICommunityCommentListContract.ICommunityCommentListModel {
    @Override
    public void getCommunityCommentList(int communityId, int page, int count, DataCallBack dataCallBack) {
        RetrofitUtil instance = RetrofitUtil.getInstance();
        ApiService service = instance.createService();
        Observable<CommunityCommentListBean> communityCommentListData = service.getCommunityCommentListData(communityId, page, count);
        communityCommentListData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CommunityCommentListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onNext(CommunityCommentListBean value) {
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
}
