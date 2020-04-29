package com.wd.tech.mvp.wymvp.mvpadvertisement;

import com.wd.tech.base.BasePresenter;
import com.wd.tech.bean.wybean.beanadvertisement.AdvertisementBean;
import com.wd.tech.bean.wybean.beandotask.DoTaskBean;

/**
 * @author 王阳
 * Class :1708A
 * @description:
 * @date :2020/4/29 14:32
 * @classname :AdvertisementPresenterImpl
 */
public class AdvertisementPresenterImpl extends BasePresenter<IAdvertisementContract.IAdvertisementView> implements IAdvertisementContract.IAdvertisementPresenter {
    private AdvertisementModelImpl advertisementModel;
    @Override
    public void initModel() {
        advertisementModel=new AdvertisementModelImpl();
    }

    @Override
    public void getAdvertisement() {
        advertisementModel.getAdvertisement(new IAdvertisementContract.IAdvertisementModel.DataCallBack() {
            @Override
            public void onSuccess(AdvertisementBean advertisementBean) {
                iBaseView.onSuccess(advertisementBean);
            }

            @Override
            public void onError(String error) {
                iBaseView.onError(error);

            }
        });
    }

    @Override
    public void getDoTask(int taskId) {
        advertisementModel.getDoTask(taskId, new IAdvertisementContract.IAdvertisementModel.DataCallBack2() {
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
