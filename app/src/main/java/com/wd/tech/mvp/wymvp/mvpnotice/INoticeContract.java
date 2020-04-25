package com.wd.tech.mvp.wymvp.mvpnotice;

import com.wd.tech.base.IBaseView;
import com.wd.tech.bean.wybean.beannotice.NoticeBean;

/**
 * @author 王阳
 * Class :1708A
 * @description:
 * @date :2020/4/25 14:07
 * @classname :INoticeContract
 */
public interface INoticeContract {
    //
    interface INoticeView extends IBaseView{
        //成功
        void onSuccess(NoticeBean noticeBean);
        //失败
        void onError(String error);
    }
    //
    interface INoticeModel{
        void getNotice(int page,int count,DataCallBack dataCallBack);
        interface DataCallBack{
            //成功
            void onSuccess(NoticeBean noticeBean);
            //失败
            void onError(String error);
        }
    }
    //
    interface INoticePresenter{
        void getNotice(int page,int count);
    }
}
