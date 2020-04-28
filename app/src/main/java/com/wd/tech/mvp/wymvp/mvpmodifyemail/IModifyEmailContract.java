package com.wd.tech.mvp.wymvp.mvpmodifyemail;

import com.wd.tech.base.IBaseView;
import com.wd.tech.bean.wybean.beanmodifyemail.ModifyEmailBean;

/**
 * @author 王阳
 * Class :1708A
 * @description:
 * @date :2020/4/28 13:57
 * @classname :ModifyEmailBean
 */
public interface IModifyEmailContract {
    //
    interface IModifyEmailView extends IBaseView{
        //
        void onSuccess(ModifyEmailBean modifyEmailBean);
        //
        void onError(String error);
    }
    //
    interface IModifyEmailModel{
        void getModifyEmail(String email,DataCallBack dataCallBack);
        interface DataCallBack{
            //
            void onSuccess(ModifyEmailBean modifyEmailBean);
            //
            void onError(String error);
        }
    }
    //
    interface IModifyEmailPresenter{
        void getModifyEmail(String email);
    }
}
