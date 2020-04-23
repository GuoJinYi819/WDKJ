package com.wd.tech.mvp.gjymvp.queryfriend;

import com.wd.tech.base.IBaseView;
import com.wd.tech.bean.gjybean.QueryFriendBean;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/4/23 0023 14:26
 * @Description: 用途：完成特定功能
 */
public interface IQueryFriendContract {
    interface IQueryFriendView extends IBaseView{
        void onSuccess(QueryFriendBean bean);
        void onFailed(String error);
    }
    interface IQueryFriendModule{
        void queryFriend(int friend,QueryFrinedCallBack queryFrinedCallBack);
        interface QueryFrinedCallBack{
            void onSuccess(QueryFriendBean bean);
            void onFailed(String error);
        }
    }
    interface IQueryFriendPresenter{
        void queryFriend(int friend);
    }
}
