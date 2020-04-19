package com.wd.tech.mvp.wymvp.mvpcomment;

import com.wd.tech.base.IBaseView;
import com.wd.tech.bean.wybean.beancomment.CommentBean;

import okhttp3.MultipartBody;

/**
 * @author 王阳
 * Class :1708A
 * @description:
 * @date :2020/4/19 19:16
 * @classname :ICommentContract
 */
public interface ICommentContract {
    //
    interface ICommentView extends IBaseView {
        //成功
        void onSuccess(CommentBean commentBean);
        //失败
        void onError(String msg);
    }
    //
    interface ICommentModel{
        void getComment(String content,MultipartBody.Part file, DataCallBack dataCallBack);
        interface DataCallBack{
            //成功
            void onSuccess(CommentBean commentBean);
            //失败
            void onError(String msg);
        }
    }
    //
    interface ICommentPresenter{
        void getComment(String content,MultipartBody.Part file);
    }
}
