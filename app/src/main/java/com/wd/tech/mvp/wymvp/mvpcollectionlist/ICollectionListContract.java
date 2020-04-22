package com.wd.tech.mvp.wymvp.mvpcollectionlist;

import com.wd.tech.base.IBaseView;
import com.wd.tech.bean.wybean.beancollectionlist.CollectionListBean;

/**
 * @author 王阳
 * Class :1708A
 * @description:
 * @date :2020/4/22 14:17
 * @classname :ICollectionListContract
 */
public interface ICollectionListContract {
    //
    interface ICollectionListView extends IBaseView {
        //成功
        void onSuccess(CollectionListBean collectionList);
        //失败
        void onError(String error);
    }
    //
    interface ICollectionListModel{
        void getCollectionList(int page,int count,DataCallBack dataCallBack);
        interface DataCallBack{
            //成功
            void onSuccess(CollectionListBean collectionList);
            //失败
            void onError(String error);
        }
    }
    //
    interface ICollectionListPresenter{
        void getCollectionList(int page,int count);
    }
}
