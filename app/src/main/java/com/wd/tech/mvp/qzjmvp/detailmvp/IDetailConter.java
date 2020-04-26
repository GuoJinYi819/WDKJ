package com.wd.tech.mvp.qzjmvp.detailmvp;

import com.wd.tech.base.IBaseView;
import com.wd.tech.bean.qzjbean.detail.DetailBean;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : Quzijun
 * @version 创建时间：2020/4/24 18:07
 * @Description: 用途：完成特定功能
 */
public interface IDetailConter {
    interface IDetaView extends IBaseView {
        void onDetaSuccess(DetailBean detailBean);
    }
    interface IDetailMoudle{
        void onDetaDate(int id,DetaCallBack callBack);
        interface DetaCallBack{
            void onDetaSuccess(DetailBean detailBean);
        }
    }
    interface IDetailPresenter{
        void onDetaDate(int id);
    }
}
