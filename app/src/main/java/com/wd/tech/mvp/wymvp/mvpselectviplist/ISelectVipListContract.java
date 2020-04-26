package com.wd.tech.mvp.wymvp.mvpselectviplist;

import com.wd.tech.base.IBaseView;
import com.wd.tech.bean.wybean.beanselectviplist.SelectVipListBean;

/**
 * @author 王阳
 * Class :1708A
 * @description:
 * @date :2020/4/26 14:34
 * @classname :ISelectVipListContract
 */
public interface ISelectVipListContract {
    //
    interface ISelectVipListView extends IBaseView{
        //
        void onSuccess(SelectVipListBean selectVipListBean);
        //
        void onError(String error);
    }
    //
    interface ISelectVipListModel{
        void getSelectVipList(DataCallBack dataCallBack);
        interface DataCallBack{
            //
            void onSuccess(SelectVipListBean selectVipListBean);
            //
            void onError(String error);
        }
    }
    //
    interface ISelectVipListPresenter{
        void getSelectVipList();
    }
}
