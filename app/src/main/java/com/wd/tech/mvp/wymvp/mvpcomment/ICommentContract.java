package com.wd.tech.mvp.wymvp.mvpcomment;

import com.wd.tech.base.IBaseView;
import com.wd.tech.bean.wybean.beancomment.CommentBean;
import com.wd.tech.bean.wybean.beandotask.DoTaskBean;
import com.wd.tech.mvp.wymvp.mvpsign.ISignContract;

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
        //成功
        void onTaskSuccess(DoTaskBean doTaskBean);
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

        void getDoTask(int taskId, ISignContract.ISignModel.DataCallBack2 dataCallBack2);
        interface DataCallBack2{
            //成功
            void onSuccess(DoTaskBean doTaskBean);
            //失败
            void onError(String error);
        }
    }
    //
    interface ICommentPresenter{
        void getComment(String content,MultipartBody.Part file);
        void getDoTask(int taskId);
    }
}
