package com.wd.tech.mvp.wymvp.mvpmodifysignature;

import com.wd.tech.base.IBaseView;
import com.wd.tech.bean.wybean.beanmodifysignature.ModifySignatureBean;

/**
 * @author 王阳
 * Class :1708A
 * @description:
 * @date :2020/4/27 20:13
 * @classname :IModifySignatureContract
 */
public interface IModifySignatureContract {
    //
    interface IModifySignatureView extends IBaseView{
        //
        void onSuccess(ModifySignatureBean modifySignatureBean);
        //
        void onError(String error);
    }
    //
    interface IModifySignatureModel{
        void getModifySignature(String signature,DataCallBack dataCallBack);
        interface DataCallBack{
            //
            void onSuccess(ModifySignatureBean modifySignatureBean);
            //
            void onError(String error);
        }
    }
    //
    interface IModifySignaturePresenter{
        void getModifySignature(String signature);
    }
}
