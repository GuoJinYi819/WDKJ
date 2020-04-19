package com.wd.tech.mvp.qzjmvp.regmvp;

import com.wd.tech.base.BasePresenter;
import com.wd.tech.bean.qzjbean.regist.RegBean;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : Quzijun
 * @version 创建时间：2020/4/18 12:59
 * @Description: 用途：完成特定功能
 */
public class RegPresenterImpl extends BasePresenter<RegConter.IRegView> implements RegConter.IRegPresenter {
    private RegMoudleImpl regMoudle;
    @Override
    public void initModel() {
        regMoudle = new RegMoudleImpl();
    }

    @Override
    public void getData(String phone, String name, String pwd) {
        regMoudle.getData(phone, name, pwd, new RegConter.IRegMoudle.MyCallBack() {
            @Override
            public void onSuccess(RegBean regBean) {
                iBaseView.onSuccess(regBean);
            }

            @Override
            public void onError(String error) {
                iBaseView.onError(error);
            }
        });
    }
}
