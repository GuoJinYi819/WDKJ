package com.wd.tech.mvp.gjymvp.sendnews;

import com.wd.tech.base.BasePresenter;
import com.wd.tech.bean.gjybean.DialogueRecordBean;
import com.wd.tech.bean.gjybean.SendMessageBean;

import java.util.Map;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/4/23 0023 16:10
 * @Description: 用途：完成特定功能
 */
public class SendNewsPresenter extends BasePresenter<ISendNewsContract.ISendNewsView> implements ISendNewsContract.ISendNewsPresenter {
    private SendNewsModule module;
    @Override
    public void initModel() {
        module = new SendNewsModule();
    }

    @Override
    public void getDialogRecordData(Map<String, String> params) {
        module.getDialogRecordData(params, new ISendNewsContract.ISendNewsModule.DialogRecordCallBack() {
            @Override
            public void getDialogRecordSuccess(DialogueRecordBean bean) {
                iBaseView.getDialogRecordSuccess(bean);
            }
        });
    }

    @Override
    public void sendMessage(Map<String, String> params) {
        module.sendMessage(params, new ISendNewsContract.ISendNewsModule.SendMessageCallBack() {
            @Override
            public void sendMessage(SendMessageBean bean) {
                iBaseView.sendMessage(bean);
            }
        });
    }
}
