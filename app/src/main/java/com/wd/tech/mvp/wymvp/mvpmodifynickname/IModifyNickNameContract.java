package com.wd.tech.mvp.wymvp.mvpmodifynickname;

import com.wd.tech.base.IBaseView;
import com.wd.tech.bean.wybean.beanmodifynickname.ModifyNickNameBean;

/**
 * @author 王阳
 * Class :1708A
 * @description:
 * @date :2020/4/27 19:41
 * @classname :IModifyNickNameContract
 */
public interface IModifyNickNameContract {
    //
    interface IModifyNickNameView extends IBaseView{
        //
        void onSuccess(ModifyNickNameBean modifyNickNameBean);
        //
        void onError(String error);
    }
    //
    interface IModifyNickNameModel{
        void getModifyNickName(String nickName,DataCallBack dataCallBack);
        interface DataCallBack{
            //
            void onSuccess(ModifyNickNameBean modifyNickNameBean);
            //
            void onError(String error);
        }
    }
    //
    interface IModifyNickNamePresenter{
        void getModifyNickName(String nickName);
    }
}
