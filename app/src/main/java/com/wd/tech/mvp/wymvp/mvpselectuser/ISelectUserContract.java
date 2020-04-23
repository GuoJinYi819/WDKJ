package com.wd.tech.mvp.wymvp.mvpselectuser;

import com.wd.tech.base.IBaseView;
import com.wd.tech.bean.wybean.beanselectuser.SelectUserBean;

/**
 * @author 王阳
 * Class :1708A
 * @description:
 * @date :2020/4/23 20:37
 * @classname :ISelectUserContract
 */
public interface ISelectUserContract {
    //
    interface ISelectUserView extends IBaseView{
        //成功
        void onSuccess(SelectUserBean selectUser);
        //失败
        void onError(String error);
    }
    //
    interface ISelectUserModel{
        void getSelectUser(DataCallBack dataCallBack);
        interface DataCallBack{
            //成功
            void onSuccess(SelectUserBean selectUser);
            //失败
            void onError(String error);
        }
    }
    //
    interface ISelectUserPresenter{
        void getSelectUser();
    }
}
