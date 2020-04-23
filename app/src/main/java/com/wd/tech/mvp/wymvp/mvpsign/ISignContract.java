package com.wd.tech.mvp.wymvp.mvpsign;

import com.wd.tech.base.IBaseView;
import com.wd.tech.bean.wybean.beandotask.DoTaskBean;
import com.wd.tech.bean.wybean.beansign.SignBean;
import com.wd.tech.mvp.wymvp.mvpdotask.IDoTaskContract;

/**
 * @author 王阳
 * Class :1708A
 * @description:
 * @date :2020/4/22 13:40
 * @classname :ISignContract
 */
public interface ISignContract {
    //
    interface ISignView extends IBaseView{
        //成功
        void onSuccess(SignBean sign);
        //失败
        void onError(String error);

        //成功
        void onTaskSuccess(DoTaskBean doTaskBean);
    }
    //
    interface ISignModel{
        void getSign(DataCallBack dataCallBack);
        interface DataCallBack{
            //成功
            void onSuccess(SignBean sign);
            //失败
            void onError(String error);
        }
        void getDoTask(int taskId,DataCallBack2 dataCallBack2);
        interface DataCallBack2{
            //成功
            void onSuccess(DoTaskBean doTaskBean);
            //失败
            void onError(String error);
        }
    }
    //
    interface ISignPresenter{
        void getSign();
        void getDoTask(int taskId);
    }
}
