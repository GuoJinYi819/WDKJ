package com.wd.tech.mvp.gjymvp.newsnotice;

import com.wd.tech.base.IBaseView;
import com.wd.tech.bean.gjybean.FriendDataBean;
import com.wd.tech.bean.gjybean.FriendNoticeBean;
import com.wd.tech.bean.gjybean.GroupNoticeBean;

import java.util.Map;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/4/21 0021 21:47
 * @Description: 用途：完成特定功能
 */
public interface INewsNoticeContract {
    interface INewsNoticeView extends IBaseView{
        void onFriendNoticeSuccess(FriendNoticeBean bean);
        void onGroupNoticeSuccess(GroupNoticeBean bean);
    }
    interface INewsNoticeModule{
        void getFriendNotice(Map<String,String> params,FriendNoticeCallBack friendNoticeCallBack);
        void getGroupNotice(Map<String,String> params,GroupNoticeCallBack groupNoticeCallBack);

        interface FriendNoticeCallBack{
            void onFriendNoticeSuccess(FriendNoticeBean bean);
        }
        interface GroupNoticeCallBack{
            void onGroupNoticeSuccess(GroupNoticeBean bean);
        }

    }
    interface INewsNoticePresenter{
        void getFriendNotice(Map<String,String> params);
        void getGroupNotice(Map<String,String> params);
    }

}
