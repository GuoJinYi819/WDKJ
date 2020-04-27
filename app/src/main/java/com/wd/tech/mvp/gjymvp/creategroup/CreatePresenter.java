package com.wd.tech.mvp.gjymvp.creategroup;

import com.wd.tech.base.BasePresenter;
import com.wd.tech.bean.gjybean.CreateGroupBean;

import java.util.Map;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/4/25 0025 15:47
 * @Description: 用途：完成特定功能
 */
public class CreatePresenter extends BasePresenter<ICreateGroupContract.ICreateGroupView> implements ICreateGroupContract.ICreateGroupPresenter {
    private ICreateGroupModule module;
    @Override
    public void initModel() {
        module = new ICreateGroupModule();
    }

    @Override
    public void createGroup(Map<String, String> params) {
        module.createGroup(params, new ICreateGroupContract.ICreateGroupModule.CreateGroupCallBack() {
            @Override
            public void onSuccess(CreateGroupBean bean) {
                iBaseView.onSuccess(bean);
            }

            @Override
            public void onFailed(String error) {
                iBaseView.onFailed(error);
            }
        });
    }
}
