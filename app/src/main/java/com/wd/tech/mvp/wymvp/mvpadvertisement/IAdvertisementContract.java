package com.wd.tech.mvp.wymvp.mvpadvertisement;

import com.wd.tech.base.IBaseView;
import com.wd.tech.bean.wybean.beanadvertisement.AdvertisementBean;
import com.wd.tech.bean.wybean.beandotask.DoTaskBean;

/**
 * @author 王阳
 * Class :1708A
 * @description:
 * @date :2020/4/29 14:26
 * @classname :IAdvertisementContract
 */
public interface IAdvertisementContract {
    //
    interface IAdvertisementView extends IBaseView {
        //
        void onSuccess(AdvertisementBean advertisementBean);
        //
        void onError(String error);
        //成功
        void onTaskSuccess(DoTaskBean doTaskBean);
    }
    //
    interface IAdvertisementModel{
        void getAdvertisement(DataCallBack dataCallBack);
        interface DataCallBack{
            //
            void onSuccess(AdvertisementBean advertisementBean);
            //
            void onError(String error);
        }

        void getDoTask(int taskId, DataCallBack2 dataCallBack2);
        interface DataCallBack2{
            //成功
            void onSuccess(DoTaskBean doTaskBean);
            //失败
            void onError(String error);
        }
    }
    //
    interface IAdvertisementPresenter{
        void getAdvertisement();
        void getDoTask(int taskId);
    }
}
