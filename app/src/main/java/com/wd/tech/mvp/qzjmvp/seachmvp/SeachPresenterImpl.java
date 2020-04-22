package com.wd.tech.mvp.qzjmvp.seachmvp;

import com.wd.tech.base.BasePresenter;
import com.wd.tech.bean.qzjbean.seach.SeachBean;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : Quzijun
 * @version 创建时间：2020/4/22 16:05
 * @Description: 用途：完成特定功能
 */
public class SeachPresenterImpl extends BasePresenter<SeachConter.ISeachView>implements SeachConter.ISeachPresenter {
    private SeachMoudleImpl moudle;
    @Override
    public void initModel() {
        moudle = new SeachMoudleImpl();
    }

    @Override
    public void getData(String title, int page, int count) {
        moudle.getData(title, page, count, new SeachConter.ISeachMoudle.MyCallBack() {
            @Override
            public void onSuccess(SeachBean seachBean) {
                iBaseView.onSuccess(seachBean);
            }

            @Override
            public void onError(String error) {
iBaseView.onError(error);
            }
        });
    }
}
