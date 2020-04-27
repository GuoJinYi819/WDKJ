package com.wd.tech.mvp.gjymvp.addgroup;

import com.wd.tech.base.BasePresenter;
import com.wd.tech.bean.gjybean.AddFriendBean;
import com.wd.tech.mvp.gjymvp.addfriend.AddFriendModule;
import com.wd.tech.mvp.gjymvp.addfriend.IAddFriendContract;

import java.util.Map;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/4/25 0025 14:45
 * @Description: 用途：完成特定功能
 */
public class AddGroupPresenter extends BasePresenter<IAddGroupContract.IAddGroupView> implements IAddGroupContract.IAddGroupPresenter {
    private AddGroupModule module;
    @Override
    public void initModel() {
        module = new AddGroupModule();
    }

    @Override
    public void addGroup(Map<String, String> params) {
        module.addGroup(params, new IAddGroupContract.IAddGroupModule.AddGroupCallBack() {
            @Override
            public void onSuccess(AddFriendBean bean) {
                iBaseView.onSuccess(bean);
            }

            @Override
            public void onFailed(String error) {
                iBaseView.onFailed(error);
            }
        });
    }
}
