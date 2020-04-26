package com.wd.tech.mvp.wymvp.mvpscore;

import com.wd.tech.base.IBaseView;
import com.wd.tech.bean.wybean.beanscore.ScoreBean;
import com.wd.tech.bean.wybean.beanscoredetailed.ScoreDetailedBean;

/**
 * @author 王阳
 * Class :1708A
 * @description:
 * @date :2020/4/25 15:59
 * @classname :IScoreContract
 */
public interface IScoreContract {
    //
    interface IScoreView extends IBaseView{
        //成功
        void onSuccess(ScoreBean scoreBean);
        //失败
        void onError(String error);
        //成功
        void onDetailedSuccess(ScoreDetailedBean scoreDetailedBean);
        //失败
        void onDetailedError(String error);
    }
    //
    interface IScoreModel{
        void getScore(DataCallBack dataCallBack);
        interface DataCallBack{
            //成功
            void onSuccess(ScoreBean scoreBean);
            //失败
            void onError(String error);
        }

        void getDetailedScore(int page,int count,DataCallBack2 dataCallBack2);
        interface DataCallBack2{
            //成功
            void onDetailedSuccess(ScoreDetailedBean scoreDetailedBean);
            //失败
            void onDetailedError(String error);
        }
    }
    //
    interface IScorePresenter{
        void getScore();
        void getDetailedScore(int page,int count);
    }
}
