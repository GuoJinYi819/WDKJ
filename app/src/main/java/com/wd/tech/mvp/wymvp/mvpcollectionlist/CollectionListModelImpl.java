package com.wd.tech.mvp.wymvp.mvpcollectionlist;

import com.wd.tech.bean.wybean.beancancelcollection.CancelCollectionBean;
import com.wd.tech.bean.wybean.beancollectionlist.CollectionListBean;
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
 * @date :2020/4/22 14:20
 * @classname :CollectionListModelImpl
 */
public class CollectionListModelImpl implements ICollectionListContract.ICollectionListModel {
    @Override
    public void getCollectionList(int page, int count, DataCallBack dataCallBack) {
        RetrofitUtil instance = RetrofitUtil.getInstance();
        ApiService service = instance.createService();
        Observable<CollectionListBean> collectionListData = service.getCollectionListData(page, count);
        collectionListData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CollectionListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onNext(CollectionListBean value) {
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

    @Override
    public void getCancleCollection(String infoId, DataCallBack2 dataCallBack2) {
        RetrofitUtil instance = RetrofitUtil.getInstance();
        ApiService service = instance.createService();
        Observable<CancelCollectionBean> cancelCollectionData = service.getCancelCollectionData(infoId);
        cancelCollectionData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CancelCollectionBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onNext(CancelCollectionBean value) {
                        dataCallBack2.onCancleCollectionSuccess(value);
                    }
                    @Override
                    public void onError(Throwable e) {
                        dataCallBack2.onError(e.getMessage());
                    }
                    @Override
                    public void onComplete() {
                    }
                });
    }
}
