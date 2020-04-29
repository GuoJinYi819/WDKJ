package com.wd.tech.mvp.qzjmvp.inter;

import com.wd.tech.base.BasePresenter;
import com.wd.tech.bean.qzjbean.detail.DetailBean;
import com.wd.tech.bean.qzjbean.inter.InterBean;
import com.wd.tech.bean.wybean.beanscore.ScoreBean;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : Quzijun
 * @version 创建时间：2020/4/28 19:13
 * @Description: 用途：完成特定功能
 */
public class InterPresenterImpl extends BasePresenter<InterConter.IScoreView> implements InterConter.IScorePresenter {
    private InterModelImpl interModel;
    @Override
    public void initModel() {
        interModel = new InterModelImpl();
    }

    @Override
    public void getScore() {
        interModel.getScore(new InterConter.IScoreModel.DataCallBack() {
            @Override
            public void onSuccess(ScoreBean scoreBean) {
                iBaseView.onSuccess(scoreBean);
            }

            @Override
            public void onError(String error) {

            }
        });
    }

    @Override
    public void getDetailedScore(int id) {
        interModel.getDetailedScore(id, new InterConter.IScoreModel.DataCallBack2() {
            @Override
            public void onDetailedSuccess(DetailBean detailBean) {
                iBaseView.onDetailData(detailBean);
            }

            @Override
            public void onDetailedError(String error) {

            }
        });
    }

    @Override
    public void getInterDate(int infoId, int integralCost) {
        interModel.getInterDate(infoId, integralCost, new InterConter.IScoreModel.DataCallBack3() {
            @Override
            public void onDetailedSuccess(InterBean interBean) {
                iBaseView.onInterDate(interBean);
            }

            @Override
            public void onDetailedError(String error) {

            }
        });
    }
}
