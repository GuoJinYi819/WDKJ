package com.wd.tech.mvp.wymvp.mvpmypost;

import com.wd.tech.bean.wybean.beanmypost.MyPostBean;
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
 * @date :2020/4/24 15:09
 * @classname :MyPostModelImpl
 */
public class MyPostModelImpl implements IMyPostContract.IMyPostModel {
    @Override
    public void getMyPost(int page, int count, DataCallBack dataCallBack) {
        RetrofitUtil instance = RetrofitUtil.getInstance();
        ApiService service = instance.createService();
        Observable<MyPostBean> myPostData = service.getMyPostData(page, count);
        myPostData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MyPostBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onNext(MyPostBean value) {
                        dataCallBack.onMyPostSuccess(value);
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
