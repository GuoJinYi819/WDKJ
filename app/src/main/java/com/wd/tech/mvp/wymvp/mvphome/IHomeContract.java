package com.wd.tech.mvp.wymvp.mvphome;

import com.wd.tech.base.IBaseView;
import com.wd.tech.bean.wybean.beanhome.HomeBean;

/**
 * @author 王阳
 * Class :1708A
 * @description:
 * @date :2020/4/19 14:05
 * @classname :IHomeContract
 */
public interface IHomeContract {
    //
    interface IHomeView extends IBaseView{
        //成功
        void onSuccess(HomeBean homeBean);
        //失败
        void onError(String error);
    }
    //
    interface IHomeModel{
        void getHome(int page,int count,DataCallBack datacallback);
        interface DataCallBack{
            //成功
            void onSuccess(HomeBean homeBean);
            //失败
            void onError(String error);
        }
    }
    //
    interface IHomePresenter{
        void getHome(int page,int count);
    }
}
