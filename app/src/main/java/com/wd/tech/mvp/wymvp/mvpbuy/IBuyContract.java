package com.wd.tech.mvp.wymvp.mvpbuy;

import com.wd.tech.base.IBaseView;
import com.wd.tech.bean.wybean.beanbuyvip.BuyVipBean;

/**
 * @author 王阳
 * Class :1708A
 * @description:
 * @date :2020/4/26 16:27
 * @classname :IBuyContract
 */
public interface IBuyContract {
    //
    interface IBuyView extends IBaseView{
        //
        void onSuccess(BuyVipBean buyVipBean);
        //
        void onError(String error);
    }
    //
    interface IBuyModel{
        void getBuy(int commodityId,String sign,DataCallBack dataCallBack);
        interface DataCallBack{
            //
            void onSuccess(BuyVipBean buyVipBean);
            //
            void onError(String error);
        }
    }
    //
    interface IBuyPresenter{
        void getBuy(int commodityId,String sign);
    }
}
