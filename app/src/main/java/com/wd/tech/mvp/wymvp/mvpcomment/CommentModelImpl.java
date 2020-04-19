package com.wd.tech.mvp.wymvp.mvpcomment;

import com.wd.tech.bean.wybean.beancomment.CommentBean;
import com.wd.tech.net.ApiService;
import com.wd.tech.net.RetrofitUtil;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;

/**
 * @author 王阳
 * Class :1708A
 * @description:
 * @date :2020/4/19 19:18
 * @classname :CommentModelImpl
 */
public class CommentModelImpl implements ICommentContract.ICommentModel {
    @Override
    public void getComment(String content, MultipartBody.Part file, DataCallBack dataCallBack) {
        RetrofitUtil instance = RetrofitUtil.getInstance();
        ApiService service = instance.createService();
        Observable<CommentBean> commentData = service.getCommentData(content, file);
        commentData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CommentBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onNext(CommentBean value) {
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
