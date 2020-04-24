package com.wd.tech.mvp.gjymvp.sendgroup;

import com.wd.tech.base.BasePresenter;
import com.wd.tech.bean.gjybean.QueryGroupBean;
import com.wd.tech.bean.gjybean.SendGroupBean;

import java.util.Map;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/4/24 0024 16:41
 * @Description: 用途：完成特定功能
 */
public class SendGroupPresenter extends BasePresenter<ISendGroupContract.ISendGroupView> implements ISendGroupContract.ISendGroupPresenter {
    private SendGroupModule module;
    @Override
    public void initModel() {
        module = new SendGroupModule();
    }

    @Override
    public void getQueryGroup(Map<String, String> params) {
        module.getQueryGroup(params, new ISendGroupContract.ISendGroupModule.QueryGroupCallBack() {
            @Override
            public void onQueryGroupSuccess(QueryGroupBean bean) {
                iBaseView.onQueryGroupSuccess(bean);
            }
        });
    }

    @Override
    public void sendGroup(Map<String, String> params) {
        module.sendGroup(params, new ISendGroupContract.ISendGroupModule.SendGroupCallBack() {
            @Override
            public void onSendGroupSuccess(SendGroupBean bean) {
                iBaseView.onSendGroupSuccess(bean);
            }
        });
    }
}
