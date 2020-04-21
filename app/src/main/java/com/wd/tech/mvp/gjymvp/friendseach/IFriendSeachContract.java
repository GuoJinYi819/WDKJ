package com.wd.tech.mvp.gjymvp.friendseach;

import com.wd.tech.base.IBaseView;
import com.wd.tech.bean.gjybean.FriendSeachBean;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/4/21 0021 9:52
 * @Description: 用途：完成特定功能
 */
public interface IFriendSeachContract {
    interface IFriendSeachView extends IBaseView{
        void onSuccess(FriendSeachBean bean);
        void onFailed(String error);
    }
    interface IFriendSeachModule{
        void getFriendSeach(String searchName,FriendSeachCallBack friendSeachCallBack);
        interface FriendSeachCallBack{
            void onSuccess(FriendSeachBean bean);
            void onFailed(String error);
        }
    }
    interface IFriendSeachPresenter{
        void getFriendSeach(String searchName);
    }
}
