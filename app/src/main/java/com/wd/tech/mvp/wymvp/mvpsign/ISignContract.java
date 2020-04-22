package com.wd.tech.mvp.wymvp.mvpsign;

import com.wd.tech.base.IBaseView;
import com.wd.tech.bean.wybean.beansign.SignBean;

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
    }
    //
    interface ISignPresenter{
        void getSign();
    }
}
