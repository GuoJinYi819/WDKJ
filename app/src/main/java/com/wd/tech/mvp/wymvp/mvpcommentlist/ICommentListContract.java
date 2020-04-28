package com.wd.tech.mvp.wymvp.mvpcommentlist;

import com.wd.tech.base.IBaseView;
import com.wd.tech.bean.wybean.beancommentlist.CommentListBean;
import com.wd.tech.bean.wybean.beandotask.DoTaskBean;
import com.wd.tech.mvp.wymvp.mvpsign.ISignContract;

/**
 * @author 王阳
 * Class :1708A
 * @description:
 * @date :2020/4/20 8:26
 * @classname :ICommentListContract
 */
public interface ICommentListContract {
    //
    interface ICommentListView extends IBaseView {
        //成功
        void onSuccess(CommentListBean commentListBean);
        //失败
        void onError(String error);
        //成功
        void onTaskSuccess(DoTaskBean doTaskBean);
    }
    //
    interface ICommentListModel{
        void getCommentList(int communityId,int page,int count,DataCallBack datacallback);
        interface DataCallBack{
            //成功
            void onSuccess(CommentListBean commentListBean);
            //失败
            void onError(String error);
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
    interface ICommentListPresenter{
        void getCommentList(int communityId,int page,int count);
        void getDoTask(int taskId);
    }
}
