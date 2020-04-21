package com.wd.tech.mvp.wymvp.mvpperson;

import com.wd.tech.base.IBaseView;
import com.wd.tech.bean.wybean.beanperson.PersonBean;
import com.wd.tech.bean.wybean.beanperson.ResultBean;

import java.util.List;

/**
 * @author 王阳
 * Class :1708A
 * @description:
 * @date :2020/4/21 10:06
 * @classname :IPersonContract
 */
public interface IPersonContract {
    //
    interface IPersonView extends IBaseView{
        //成功
        void onSuccess(List<ResultBean> result);
        //失败
        void onError(String msg);
    }
    //
    interface IPersonModel{
        void getPerson(int fromUid,int page,int count,DataCallBack datacallback);
        interface DataCallBack{
            //成功
            void onSuccess(List<ResultBean> result);
            //失败
            void onError(String msg);
        }
    }
    //
    interface IPersonPresenter{
        void getPerson(int fromUid,int page,int count);
    }
}
