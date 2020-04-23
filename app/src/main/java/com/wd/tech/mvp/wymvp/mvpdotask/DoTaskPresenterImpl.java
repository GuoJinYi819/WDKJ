package com.wd.tech.mvp.wymvp.mvpdotask;

import com.wd.tech.base.BasePresenter;
import com.wd.tech.bean.wybean.beandotask.DoTaskBean;

/**
 * @author 王阳
 * Class :1708A
 * @description:
 * @date :2020/4/23 19:38
 * @classname :DoTaskPresenterImpl
 */
public class DoTaskPresenterImpl extends BasePresenter<IDoTaskContract.IDoTaskView> implements IDoTaskContract.IDoTaskPresenter {
    private DoTaskModelImpl doTaskModel;
    @Override
    public void initModel() {
        doTaskModel=new DoTaskModelImpl();
    }

    @Override
    public void getDoTask(int taskId) {
        doTaskModel.getDoTask(taskId, new IDoTaskContract.IDoTaskModel.DataCallBack() {
            @Override
            public void onSuccess(DoTaskBean doTaskBean) {
                iBaseView.onSuccess(doTaskBean);
            }

            @Override
            public void onError(String error) {
                iBaseView.onError(error);
            }
        });
    }
}
