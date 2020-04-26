package com.wd.tech.mvp.wymvp.mvpbuy;

import com.wd.tech.base.BasePresenter;
import com.wd.tech.bean.wybean.beanbuyvip.BuyVipBean;

/**
 * @author 王阳
 * Class :1708A
 * @description:
 * @date :2020/4/26 16:37
 * @classname :BuyPresenterImpl
 */
public class BuyPresenterImpl extends BasePresenter<IBuyContract.IBuyView> implements IBuyContract.IBuyPresenter {
    private BuyModelImpl buyModel;
    @Override
    public void initModel() {
        buyModel=new BuyModelImpl();
    }

    @Override
    public void getBuy(int commodityId, String sign) {
        buyModel.getBuy(commodityId, sign, new IBuyContract.IBuyModel.DataCallBack() {
            @Override
            public void onSuccess(BuyVipBean buyVipBean) {
                iBaseView.onSuccess(buyVipBean);
            }

            @Override
            public void onError(String error) {
                iBaseView.onError(error);
            }
        });
    }
}
