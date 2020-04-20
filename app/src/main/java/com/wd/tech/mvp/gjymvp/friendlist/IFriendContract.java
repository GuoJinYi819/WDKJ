package com.wd.tech.mvp.gjymvp.friendlist;

import com.wd.tech.base.IBaseView;
import com.wd.tech.bean.gjybean.FriendChildListBean;
import com.wd.tech.bean.gjybean.FriendGroupListBean;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/4/20 0020 14:44
 * @Description: 用途：完成特定功能
 */
public interface IFriendContract {
    interface IFriendView extends IBaseView{
        void onGroupSuccess(FriendGroupListBean bean);
        void onChildSuccess(FriendChildListBean bean);
        void onFailed(String error);
    }
    interface IFriendModule{
        void getFriendGroupData(FriendGroupCallBack friendGroupCallBack);
        void getFriendChildData(int groupId,FriendChildCallBack friendChildCallBack);
        interface FriendGroupCallBack{
            void onGroupSuccess(FriendGroupListBean bean);
            void onFailed(String error);
        }
        interface FriendChildCallBack{
            void onChildSuccess(FriendChildListBean bean);
            void onFailed(String error);
        }

    }
    interface IFriendPresenter{
        void getFriendGroupData();
        void getFriendChildData(int groupId);
    }
}
