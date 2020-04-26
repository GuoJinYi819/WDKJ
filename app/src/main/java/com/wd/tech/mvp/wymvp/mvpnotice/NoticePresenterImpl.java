package com.wd.tech.mvp.wymvp.mvpnotice;

import com.wd.tech.base.BasePresenter;
import com.wd.tech.bean.wybean.beannotice.NoticeBean;

/**
 * @author 王阳
 * Class :1708A
 * @description:
 * @date :2020/4/25 15:00
 * @classname :NoticePresenterImpl
 */
public class NoticePresenterImpl extends BasePresenter<INoticeContract.INoticeView> implements INoticeContract.INoticePresenter {
    private NoticeModelImpl noticeModel;
    @Override
    public void initModel() {
        noticeModel=new NoticeModelImpl();
    }

    @Override
    public void getNotice(int page, int count) {
        noticeModel.getNotice(page, count, new INoticeContract.INoticeModel.DataCallBack() {
            @Override
            public void onSuccess(NoticeBean noticeBean) {
                iBaseView.onSuccess(noticeBean);
            }

            @Override
            public void onError(String error) {
                iBaseView.onError(error);
            }
        });
    }
}
