package com.wd.tech.mvp.wymvp.mvpmypost;

import com.wd.tech.base.IBaseView;
import com.wd.tech.bean.wybean.beanmypost.MyPostBean;

/**
 * @author 王阳
 * Class :1708A
 * @description:
 * @date :2020/4/24 15:04
 * @classname :IMyPostContract
 */
public interface IMyPostContract {
    //
    interface IMyPostView extends IBaseView{
        //成功
        void onMyPostSuccess(MyPostBean myPostBean);
        //失败
        void onError(String error);
    }
    //
    interface IMyPostModel{
        void getMyPost(int page,int count,DataCallBack dataCallBack);
        interface DataCallBack{
            //成功
            void onMyPostSuccess(MyPostBean myPostBean);
            //失败
            void onError(String error);
        }
    }
    //
    interface IMyPostPresenter{
        void getMyPost(int page,int count);
    }
}
