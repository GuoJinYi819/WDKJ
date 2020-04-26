package com.wd.tech.mvp.gjymvp.groupinfo;

import com.wd.tech.base.BasePresenter;
import com.wd.tech.bean.gjybean.GroupInfoBean;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/4/26 0026 18:18
 * @Description: 用途：完成特定功能
 */
public class GroupInfoPreenter extends BasePresenter<IGroupInfoContract.IGroupInfoView> implements IGroupInfoContract.IGroupInfoPresenter {
    private GroupInfoModule module;
    @Override
    public void initModel() {
        module = new GroupInfoModule();
    }

    @Override
    public void getGroupInfo(int groupId) {
        module.getGroupInfo(groupId, new IGroupInfoContract.IGroupInfoModule.GroupInfoCallBack() {
            @Override
            public void onSuccess(GroupInfoBean bean) {
                iBaseView.onSuccess(bean);
            }

            @Override
            public void onFailed(String error) {
                iBaseView.onFailed(error);
            }
        });
    }
}
