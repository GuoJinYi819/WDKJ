package com.wd.tech.mvp.qzjmvp.seachmvp;

import com.wd.tech.base.IBaseView;
import com.wd.tech.bean.qzjbean.seach.SeachBean;
import com.wd.tech.bean.qzjbean.seach.SeachResultBean;
import com.wd.tech.bean.qzjbean.xbanner.XbBean;
import com.wd.tech.mvp.qzjmvp.xbannermvp.XbConnter;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : Quzijun
 * @version 创建时间：2020/4/22 15:47
 * @Description: 用途：完成特定功能
 */
public interface SeachConter {
    interface ISeachView extends IBaseView {
        void onSuccess(SeachBean seachBean);
        void onError(String error);
    }
    interface ISeachMoudle{
        void getData(String title,int page,int count,SeachConter.ISeachMoudle.MyCallBack myCallBack);
        interface MyCallBack{
            void onSuccess(SeachBean seachBean);
            void onError(String error);
        }
    }
    interface ISeachPresenter{
        void getData(String title,int page,int count);
    }
}
