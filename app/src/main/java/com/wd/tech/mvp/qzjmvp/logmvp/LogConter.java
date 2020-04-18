package com.wd.tech.mvp.qzjmvp.logmvp;

import com.wd.tech.base.IBaseView;
import com.wd.tech.bean.qzjbean.log.LogBean;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : Quzijun
 * @version 创建时间：2020/4/17 21:59
 * @Description: 用途：完成特定功能
 */
public interface LogConter {
    interface ILogView extends IBaseView{
        void onSuccess(LogBean logBean);
        void onError(String error);
    }
    interface ILogMoudle{
        void getData(String phone,String pwd,MyCallBack myCallBack);
        interface MyCallBack{
            void onSuccess(LogBean logBean);
            void onError(String error);
        }
    }
    interface ILogPresenter{
        void getData(String phone,String pwd);
    }
}
