package com.wd.tech.mvp.gjymvp.addfriendgroup;

import com.wd.tech.base.BasePresenter;
import com.wd.tech.bean.gjybean.AddFriendGroupBean;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/4/25 0025 21:47
 * @Description: 用途：完成特定功能
 */
public class AddFriendGroupPresenter extends BasePresenter<IAddFriendGroupContract.IAddFriendGroupView> implements IAddFriendGroupContract.IAddFriendGroupPresenter {
    private AddFriendGroupModule module;
    @Override
    public void initModel() {
        module = new AddFriendGroupModule();
    }

    @Override
    public void addFriendGroup(String groupName) {
        module.addFriendGroup(groupName, new IAddFriendGroupContract.IAddFriendGroupModule.IAddFriendGroupCallBack() {
            @Override
            public void onAddFriendGroupSuccess(AddFriendGroupBean bean) {
                iBaseView.onAddFriendGroupSuccess(bean);
            }

            @Override
            public void onFailed(String error) {
                iBaseView.onFailed(error);
            }
        });
    }
}
