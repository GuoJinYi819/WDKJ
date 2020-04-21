package com.wd.tech.mvp.gjymvp.joinedgroup;

import com.wd.tech.base.BasePresenter;
import com.wd.tech.bean.gjybean.JoinedGroupBean;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/4/21 0021 11:48
 * @Description: 用途：完成特定功能
 */
public class JoinedGroupPresenter extends BasePresenter<IJoinedGroupContract.IJoinedGroupView> implements IJoinedGroupContract.IJoinedGroupPresenter{
    private JoinedGroupModule module;
    @Override
    public void initModel() {
        module = new JoinedGroupModule();
    }

    @Override
    public void getJoinedGroup() {
        module.getJoinedGroup(new IJoinedGroupContract.IJoinedGroupModule.JoinedGroupCallBack() {
            @Override
            public void onSuccess(JoinedGroupBean bean) {
                iBaseView.onSuccess(bean);
            }

            @Override
            public void onFailed(String error) {
                iBaseView.onFailed(error);
            }
        });
    }
}
