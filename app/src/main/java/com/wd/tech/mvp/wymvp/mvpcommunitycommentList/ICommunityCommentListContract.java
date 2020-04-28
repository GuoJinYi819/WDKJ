package com.wd.tech.mvp.wymvp.mvpcommunitycommentList;

import com.wd.tech.base.IBaseView;
import com.wd.tech.bean.wybean.beancommunitycommentList.CommunityCommentListBean;

/**
 * @author 王阳
 * Class :1708A
 * @description:
 * @date :2020/4/26 20:29
 * @classname :ICommunityCommentListContract
 */
public interface ICommunityCommentListContract {
    //
    interface ICommunityCommentListView extends IBaseView{
        //
        void onSuccess(CommunityCommentListBean communityCommentListBean);
        //
        void onError(String error);
    }
    //
    interface ICommunityCommentListModel{
        void getCommunityCommentList(int communityId,int page,int count,DataCallBack dataCallBack);
        interface DataCallBack{
            //
            void onSuccess(CommunityCommentListBean communityCommentListBean);
            //
            void onError(String error);
        }
    }
    //
    interface ICommunityCommentListPresenter{
        void getCommunityCommentList(int communityId,int page,int count);
    }
}
