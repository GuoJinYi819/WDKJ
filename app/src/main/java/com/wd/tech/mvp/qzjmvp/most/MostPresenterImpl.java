package com.wd.tech.mvp.qzjmvp.most;

import com.wd.tech.base.BasePresenter;
import com.wd.tech.bean.qzjbean.most.MostBean;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : Quzijun
 * @version 创建时间：2020/4/27 11:37
 * @Description: 用途：完成特定功能
 */
public class MostPresenterImpl extends BasePresenter<MostConter.IMostView>implements MostConter.IMostPresenter {
   private MostMoudleImpl mostMoudle;
    @Override
    public void initModel() {
        mostMoudle = new MostMoudleImpl();
    }

    @Override
    public void onDate() {
        mostMoudle.onDate(new MostConter.IMostMoudle.MyCallBack() {
            @Override
            public void onSuccess(MostBean mostBean) {
                iBaseView.onSuccess(mostBean);
            }
        });
    }
}
