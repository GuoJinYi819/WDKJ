package com.wd.tech.mvp.wymvp.mvpfollow;

import com.wd.tech.base.IBaseView;
import com.wd.tech.bean.wybean.beanfollow.FollowBean;

/**
 * @author 王阳
 * Class :1708A
 * @description:
 * @date :2020/4/22 20:13
 * @classname :IFollowContract
 */
public interface IFollowContract {
    //
    interface IFollowView extends IBaseView {
        //成功
        void onSuccess(FollowBean follow);
        //失败
        void onError(String error);
    }
    //
    interface IFollowModel{
        void getFollow(int page,int count,DataCallBack dataCallBack);
        interface DataCallBack{
            //成功
            void onSuccess(FollowBean follow);
            //失败
            void onError(String error);
        }
    }
    //
    interface IFollowPresenter{
        void getFollow(int page,int count);
    }
}
