package com.wd.tech.mvp.gjymvp.groupmemberlist;

import com.wd.tech.base.BasePresenter;
import com.wd.tech.bean.gjybean.GroupMemberListBean;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/4/26 0026 20:28
 * @Description: 用途：完成特定功能
 */
public class GroupMemberListPresenter extends BasePresenter<IGroupMemberListContract.IGroupMemberListView> implements IGroupMemberListContract.IGroupMemberListPresenter {
    private GroupMemberListModule memberListModule;
    @Override
    public void initModel() {
        memberListModule = new GroupMemberListModule();
    }

    @Override
    public void getGroupMemberList(int groupId) {
        memberListModule.getGroupMemberList(groupId, new IGroupMemberListContract.IGroupMemberListModule.GroupMemberListCallBack() {
            @Override
            public void onSuccess(GroupMemberListBean bean) {
                iBaseView.onSuccess(bean);
            }

            @Override
            public void onFailed(String error) {
                iBaseView.onFailed(error);
            }
        });
    }
}
