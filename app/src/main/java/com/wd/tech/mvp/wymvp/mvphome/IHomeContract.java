package com.wd.tech.mvp.wymvp.mvphome;

import com.wd.tech.base.IBaseView;
import com.wd.tech.bean.wybean.beancommunitycommentList.CommunityCommentListBean;
import com.wd.tech.bean.wybean.beanhome.HomeBean;
import com.wd.tech.bean.wybean.beansendcomment.SendCommentBean;
import com.wd.tech.mvp.wymvp.mvpcommunitycommentList.ICommunityCommentListContract;

/**
 * @author 王阳
 * Class :1708A
 * @description:
 * @date :2020/4/19 14:05
 * @classname :IHomeContract
 */
public interface IHomeContract {
    //
    interface IHomeView extends IBaseView{
        //成功
        void onSuccess(HomeBean homeBean);
        //失败
        void onError(String error);
        //
        void onCommunityCommentListSuccess(CommunityCommentListBean communityCommentListBean);
        //
        void onCommunityCommentListError(String error);
        //
        void onSendCommentSuccess(SendCommentBean sendCommentBean);
        //
        void onSendCommentError(String error);
    }
    //
    interface IHomeModel{
        void getHome(int page,int count,DataCallBack datacallback);
        interface DataCallBack{
            //成功
            void onSuccess(HomeBean homeBean);
            //失败
            void onError(String error);
        }

        void getCommunityCommentList(int communityId, int page, int count,DataCallBack2 dataCallBack2);
        interface DataCallBack2{
            //
            void onCommunityCommentListSuccess(CommunityCommentListBean communityCommentListBean);
            //
            void onCommunityCommentListError(String error);
        }

        void getSendComment(int communityId, String content,DataCallBack3 dataCallBack3);
        interface DataCallBack3{
            //
            void onSendCommentSuccess(SendCommentBean sendCommentBean);
            //
            void onSendCommentError(String error);
        }
    }
    //
    interface IHomePresenter{
        void getHome(int page,int count);
        void getCommunityCommentList(int communityId,int page,int count);
        void getSendComment(int communityId, String content);
    }
}
