package com.wd.tech.mvp.qzjmvp.xbannermvp;

import com.wd.tech.base.IBaseView;
import com.wd.tech.bean.qzjbean.regist.RegBean;
import com.wd.tech.bean.qzjbean.xbanner.XbBean;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : Quzijun
 * @version 创建时间：2020/4/19 20:43
 * @Description: 用途：Xbanner契约类
 */
public interface XbConnter {
    interface IbannerView extends IBaseView{
        void onSuccess(XbBean xbBean);
        void onError(String error);
    }
    interface IbannerMoudle{
        void getData(MyCallBack myCallBack);
        interface MyCallBack{
            void onSuccess(XbBean xbBean);
            void onError(String error);
        }
    }
    interface IbannerPresenter{
        void getData();
    }
}
