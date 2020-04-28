package com.wd.tech.mvp.qzjmvp.most;

import com.wd.tech.base.IBaseView;
import com.wd.tech.bean.qzjbean.most.MostBean;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : Quzijun
 * @version 创建时间：2020/4/27 11:33
 * @Description: 用途：完成特定功能
 */
public interface MostConter {
    interface IMostView extends IBaseView{
        void onSuccess(MostBean mostBean);
    }
    interface IMostMoudle{
        void onDate(MyCallBack myCallBack);
        interface MyCallBack{
            void onSuccess(MostBean mostBean);
        }
    }
    interface IMostPresenter{
        void onDate();
    }
}
