package com.wd.tech.mvp.wymvp.mvpsign;

import com.wd.tech.base.BasePresenter;
import com.wd.tech.bean.wybean.beandotask.DoTaskBean;
import com.wd.tech.bean.wybean.beansign.SignBean;
import com.wd.tech.mvp.wymvp.mvpdotask.DoTaskModelImpl;
import com.wd.tech.mvp.wymvp.mvpdotask.IDoTaskContract;

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

    @Override
    public void getDoTask(int taskId) {
        signModel.getDoTask(taskId, new ISignContract.ISignModel.DataCallBack2() {
            @Override
            public void onSuccess(DoTaskBean doTaskBean) {
                iBaseView.onTaskSuccess(doTaskBean);
            }

            @Override
            public void onError(String error) {
                iBaseView.onError(error);
            }
        });
    }
}
