package com.wd.tech.mvp.wymvp.mvpmodifyemail;

import com.wd.tech.base.BasePresenter;
import com.wd.tech.bean.wybean.beanmodifyemail.ModifyEmailBean;

/**
 * @author 王阳
 * Class :1708A
 * @description:
 * @date :2020/4/28 14:10
 * @classname :ModifyEmailPresenterImpl
 */
public class ModifyEmailPresenterImpl extends BasePresenter<IModifyEmailContract.IModifyEmailView> implements IModifyEmailContract.IModifyEmailPresenter {
    private ModifyEmailModelImpl modifyEmailModel;
    @Override
    public void initModel() {
        modifyEmailModel=new ModifyEmailModelImpl();
    }

    @Override
    public void getModifyEmail(String email) {
        modifyEmailModel.getModifyEmail(email, new IModifyEmailContract.IModifyEmailModel.DataCallBack() {
            @Override
            public void onSuccess(ModifyEmailBean modifyEmailBean) {
                iBaseView.onSuccess(modifyEmailBean);
            }

            @Override
            public void onError(String error) {
                iBaseView.onError(error);
            }
        });
    }
}
