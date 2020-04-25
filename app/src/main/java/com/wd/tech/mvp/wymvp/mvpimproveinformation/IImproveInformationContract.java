package com.wd.tech.mvp.wymvp.mvpimproveinformation;

import com.wd.tech.base.IBaseView;
import com.wd.tech.bean.wybean.beanimproveinformation.ImproveInformationBean;

/**
 * @author 王阳
 * Class :1708A
 * @description:
 * @date :2020/4/25 19:33
 * @classname :IImproveInformationContract
 */
public interface IImproveInformationContract {
    //
    interface IImproveInformationView extends IBaseView{
        //
        void onSuccess(ImproveInformationBean improveInformationBean);
        //
        void onError(String error);
    }
    //
    interface IImproveInformationModel{
        void getImproveInformation(String nickName,int sex,String signature,String birthday,String email,DataCallBack dataCallBack);
        interface DataCallBack{
            //
            void onSuccess(ImproveInformationBean improveInformationBean);
            //
            void onError(String error);
        }
    }
    //
    interface IImproveInformationPresenter{
        void getImproveInformation(String nickName,int sex,String signature,String birthday,String email);
    }
}
