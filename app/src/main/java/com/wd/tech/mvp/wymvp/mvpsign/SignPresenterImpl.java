package com.wd.tech.mvp.wymvp.mvpsign;

import com.wd.tech.base.BasePresenter;
import com.wd.tech.bean.wybean.beansign.SignBean;

/**
 * @author 王阳
 * Class :1708A
 * @description:
 * @date :2020/4/22 13:45
 * @classname :SignPresenterImpl
 */
public class SignPresenterImpl extends BasePresenter<ISignContract.ISignView> implements ISignContract.ISignPresenter {
    private SignModelImpl signModel;
    @Override
    public void initModel() {
        signModel=new SignModelImpl();
    }

    @Override
    public void getSign() {
        signModel.getSign(new ISignContract.ISignModel.DataCallBack() {
            @Override
            public void onSuccess(SignBean sign) {
                iBaseView.onSuccess(sign);
            }

            @Override
            public void onError(String error) {
                iBaseView.onError(error);
            }
        });
    }
}
