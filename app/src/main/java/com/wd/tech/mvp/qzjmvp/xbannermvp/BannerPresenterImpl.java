package com.wd.tech.mvp.qzjmvp.xbannermvp;

import com.wd.tech.base.BasePresenter;
import com.wd.tech.bean.qzjbean.xbanner.XbBean;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : Quzijun
 * @version 创建时间：2020/4/20 13:40
 * @Description: 用途：完成特定功能
 */
public class BannerPresenterImpl extends BasePresenter<XbConnter.IbannerView>implements XbConnter.IbannerPresenter {
    private BannerMoudleImpl bannerMoudle;
    @Override
    public void initModel() {
        bannerMoudle = new BannerMoudleImpl();
    }

    @Override
    public void getData() {
        bannerMoudle.getData(new XbConnter.IbannerMoudle.MyCallBack() {
            @Override
            public void onSuccess(XbBean xbBean) {
                iBaseView.onSuccess(xbBean);
            }

            @Override
            public void onError(String error) {
                iBaseView.onError(error);
            }
        });
    }
}
