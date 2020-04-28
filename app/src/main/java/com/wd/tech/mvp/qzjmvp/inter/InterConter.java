package com.wd.tech.mvp.qzjmvp.inter;

import com.wd.tech.base.IBaseView;
import com.wd.tech.bean.qzjbean.detail.DetailBean;
import com.wd.tech.bean.qzjbean.inter.InterBean;
import com.wd.tech.bean.wybean.beanscore.ScoreBean;
import com.wd.tech.bean.wybean.beanscoredetailed.ScoreDetailedBean;
import com.wd.tech.mvp.wymvp.mvpscore.IScoreContract;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : Quzijun
 * @version 创建时间：2020/4/28 19:04
 * @Description: 用途：完成特定功能
 */
public interface InterConter {
    interface IScoreView extends IBaseView {
        //成功
        void onSuccess(ScoreBean scoreBean);
        //详情
        void onDetailData(DetailBean detailBean);
        //积分兑换
        void onInterDate(InterBean interBean);
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

        void getDetailedScore(int id, DataCallBack2 dataCallBack2);
        interface DataCallBack2{
            //成功
            void onDetailedSuccess(DetailBean detailBean);
            //失败
            void onDetailedError(String error);
        }
        void getInterDate(int infoId,int integralCost,DataCallBack3 dataCallBack3);
        interface DataCallBack3{
            //成功
            void onDetailedSuccess(InterBean interBean);
            //失败
            void onDetailedError(String error);
        }
    }
    //
    interface IScorePresenter{
        void getScore();
        void getDetailedScore(int id);
        void getInterDate(int infoId, int integralCost);
    }
}
