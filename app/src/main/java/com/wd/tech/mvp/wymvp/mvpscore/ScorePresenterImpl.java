package com.wd.tech.mvp.wymvp.mvpscore;

import com.wd.tech.base.BasePresenter;
import com.wd.tech.bean.wybean.beanscore.ScoreBean;

/**
 * @author 王阳
 * Class :1708A
 * @description:
 * @date :2020/4/25 16:07
 * @classname :ScorePresenterImpl
 */
public class ScorePresenterImpl extends BasePresenter<IScoreContract.IScoreView> implements IScoreContract.IScorePresenter {
    private ScoreModelImpl scoreModel;
    @Override
    public void initModel() {
        scoreModel=new ScoreModelImpl();
    }

    @Override
    public void getScore() {
        scoreModel.getScore(new IScoreContract.IScoreModel.DataCallBack() {
            @Override
            public void onSuccess(ScoreBean scoreBean) {
                iBaseView.onSuccess(scoreBean);
            }

            @Override
            public void onError(String error) {
                iBaseView.onError(error);
            }
        });
    }
}
