package com.wd.tech.mvp.gjymvp.newsnotice;

import com.wd.tech.base.BasePresenter;
import com.wd.tech.bean.gjybean.FriendNoticeBean;
import com.wd.tech.bean.gjybean.GroupNoticeBean;

import java.util.Map;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/4/22 0022 10:42
 * @Description: 用途：完成特定功能
 */
public class NewsNoticePresenter extends BasePresenter<INewsNoticeContract.INewsNoticeView> implements INewsNoticeContract.INewsNoticePresenter {
    private NewsNoticeModule module;
    @Override
    public void initModel() {
        module = new NewsNoticeModule();
    }

    @Override
    public void getFriendData() {

    }

    @Override
    public void getFriendNotice(Map<String, String> params) {
        module.getFriendNotice(params, new INewsNoticeContract.INewsNoticeModule.FriendNoticeCallBack() {
            @Override
            public void onFriendNoticeSuccess(FriendNoticeBean bean) {
                iBaseView.onFriendNoticeSuccess(bean);
            }
        });
    }

    @Override
    public void getGroupNotice(Map<String, String> params) {
        module.getGroupNotice(params, new INewsNoticeContract.INewsNoticeModule.GroupNoticeCallBack() {
            @Override
            public void onGroupNoticeSuccess(GroupNoticeBean bean) {
                iBaseView.onGroupNoticeSuccess(bean);
            }
        });
    }
}
