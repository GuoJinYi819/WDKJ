package com.wd.tech.mvp.wymvp.mvpcollectionlist;

import com.google.gson.internal.$Gson$Preconditions;
import com.wd.tech.base.BasePresenter;
import com.wd.tech.bean.wybean.beancancelcollection.CancelCollectionBean;
import com.wd.tech.bean.wybean.beancollectionlist.CollectionListBean;

/**
 * @author 王阳
 * Class :1708A
 * @description:
 * @date :2020/4/22 14:21
 * @classname :CollectionListPresenterImpl
 */
public class CollectionListPresenterImpl extends BasePresenter<ICollectionListContract.ICollectionListView> implements ICollectionListContract.ICollectionListPresenter {
    private CollectionListModelImpl collectionListModel;
    @Override
    public void initModel() {
        collectionListModel=new CollectionListModelImpl();
    }

    @Override
    public void getCollectionList(int page, int count) {
        collectionListModel.getCollectionList(page, count, new ICollectionListContract.ICollectionListModel.DataCallBack() {
            @Override
            public void onSuccess(CollectionListBean collectionList) {
                iBaseView.onSuccess(collectionList);
            }

            @Override
            public void onError(String error) {
                iBaseView.onError(error);
            }
        });
    }

    @Override
    public void getCancleCollection(String infoId) {
        collectionListModel.getCancleCollection(infoId, new ICollectionListContract.ICollectionListModel.DataCallBack2() {
            @Override
            public void onCancleCollectionSuccess(CancelCollectionBean cancelCollectionBean) {
                iBaseView.onCancleCollectionSuccess(cancelCollectionBean);
            }

            @Override
            public void onError(String error) {
                iBaseView.onError(error);
            }
        });
    }
}
