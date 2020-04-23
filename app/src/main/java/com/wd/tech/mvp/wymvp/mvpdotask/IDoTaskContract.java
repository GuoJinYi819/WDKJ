package com.wd.tech.mvp.wymvp.mvpdotask;

import com.wd.tech.base.IBaseView;
import com.wd.tech.bean.wybean.beandotask.DoTaskBean;

/**
 * @author 王阳
 * Class :1708A
 * @description:
 * @date :2020/4/23 19:29
 * @classname :IDoTaskContract
 */
public interface IDoTaskContract{
    //
    interface IDoTaskView extends IBaseView{
        //成功
        void onSuccess(DoTaskBean doTaskBean);
        //失败
        void onError(String error);
    }
    //
    interface IDoTaskModel{
        void getDoTask(int taskId,DataCallBack dataCallBack);
        interface DataCallBack{
            //成功
            void onSuccess(DoTaskBean doTaskBean);
            //失败
            void onError(String error);
        }
    }
    //
    interface IDoTaskPresenter{
        void getDoTask(int taskId);
    }
}
