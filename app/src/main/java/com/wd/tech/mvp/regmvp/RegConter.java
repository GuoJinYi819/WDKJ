package com.wd.tech.mvp.regmvp;

import com.wd.tech.base.IBaseView;
import com.wd.tech.bean.qzjbean.log.LogBean;
import com.wd.tech.bean.qzjbean.regist.RegBean;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : Quzijun
 * @version 创建时间：2020/4/17 21:59
 * @Description: 用途：完成特定功能
 */
public interface RegConter {
    interface IRegView extends IBaseView{
        void onSuccess(RegBean regBean);
        void onError(String error);
    }
    interface IRegMoudle{
        void getData(String phone,String name, String pwd, MyCallBack myCallBack);
        interface MyCallBack{
            void onSuccess(RegBean regBean);
            void onError(String error);
        }
    }
    interface IRegPresenter{
        void getData(String phone,String name, String pwd);
    }
}
