package com.wd.tech.mvp.gjymvp.groupmemberlist;

import com.wd.tech.base.IBaseView;
import com.wd.tech.bean.gjybean.GroupMemberListBean;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/4/26 0026 20:26
 * @Description: 用途：完成特定功能
 */
public interface IGroupMemberListContract {
    interface IGroupMemberListView extends IBaseView{
        void onSuccess(GroupMemberListBean bean);
        void onFailed(String error);
    }
    interface IGroupMemberListModule{
        void getGroupMemberList(int groupId,GroupMemberListCallBack groupMemberListCallBack);
        interface GroupMemberListCallBack{
            void onSuccess(GroupMemberListBean bean);
            void onFailed(String error);
        }
    }
    interface IGroupMemberListPresenter{
        void getGroupMemberList(int groupId);
    }
}
