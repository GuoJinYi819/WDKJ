package com.wd.tech.mvp.qzjmvp.detailmvp;

import com.wd.tech.bean.qzjbean.addcomment.AddBean;
import com.wd.tech.bean.qzjbean.comment.ConCommentBean;
import com.wd.tech.bean.qzjbean.detail.DetailBean;
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
 * @author 作者 : Quzijun
 * @version 创建时间：2020/4/24 18:11
 * @Description: 用途：完成特定功能
 */
public class IDetailImpl implements IDetailConter.IDetailMoudle {
    @Override
    public void onDetaDate(int id, DetaCallBack callBack) {
        RetrofitUtil instance = RetrofitUtil.getInstance();
        ApiService service = instance.createService();
        Observable<DetailBean> detailsData = service.getDetailsData(id);
        detailsData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DetailBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DetailBean value) {
                        callBack.onDetaSuccess(value);
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
    public void onCommentDate(int infoId, int page, int count, CommentCallBack commentCallBack) {
        RetrofitUtil instance = RetrofitUtil.getInstance();
        ApiService service = instance.createService();
        Observable<ConCommentBean> detailsData = service.getCommentData(infoId,page,count);
        detailsData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ConCommentBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ConCommentBean value) {
                        commentCallBack.onDetaSuccess(value);
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
    public void onAddDate(int infoId, String content, AddCallBack addCallBack) {
        RetrofitUtil instance = RetrofitUtil.getInstance();
        ApiService service = instance.createService();
        Observable<AddBean> detailsData = service.getAddData(infoId,content);
        detailsData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AddBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(AddBean value) {
                        addCallBack.onDetaSuccess(value);
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
