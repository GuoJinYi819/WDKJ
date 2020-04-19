package com.wd.tech.mvp.wymvp.mvphome;

import com.wd.tech.base.BasePresenter;
import com.wd.tech.bean.wybean.beanhome.HomeBean;

/**
 * @author 王阳
 * Class :1708A
 * @description:
 * @date :2020/4/19 14:14
 * @classname :HomePresenterImpl
 */
public class HomePresenterImpl extends BasePresenter<IHomeContract.IHomeView> implements IHomeContract.IHomePresenter {
    private HomeModelImpl homeModel;
    @Override
    public void initModel() {
        homeModel=new HomeModelImpl();
    }

    @Override
    public void getHome(int page, int count) {
        homeModel.getHome(page, count, new IHomeContract.IHomeModel.DataCallBack() {
            @Override
            public void onSuccess(HomeBean homeBean) {
                iBaseView.onSuccess(homeBean);
            }

            @Override
            public void onError(String error) {
                iBaseView.onError(error);
            }
        });
    }
}
