package com.wd.tech.mvp.qzjmvp.logmvp;

import com.wd.tech.base.BasePresenter;
import com.wd.tech.bean.qzjbean.LogBean;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : Quzijun
 * @version 创建时间：2020/4/17 22:23
 * @Description: 用途：完成特定功能
 */
public class LogPresenterImpl extends BasePresenter<LogConter.ILogView>implements LogConter.ILogPresenter {
    private LogMoudleImpl logMoudle;
    @Override
    public void initModel() {
        logMoudle = new LogMoudleImpl();
    }

    @Override
    public void getData(String phone, String pwd) {
        logMoudle.getData(phone, pwd, new LogConter.ILogMoudle.MyCallBack() {
            @Override
            public void onSuccess(LogBean logBean) {
                iBaseView.onSuccess(logBean);
            }

            @Override
            public void onError(String error) {
                iBaseView.onError(error);
            }
        });
    }
}
