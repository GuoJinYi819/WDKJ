package com.wd.tech.mvp.wymvp.mvpcommunitycommentList;

import com.wd.tech.base.BasePresenter;
import com.wd.tech.bean.wybean.beancommunitycommentList.CommunityCommentListBean;

/**
 * @author 王阳
 * Class :1708A
 * @description:
 * @date :2020/4/26 20:33
 * @classname :CommunityCommentListPresenterImpl
 */
public class CommunityCommentListPresenterImpl extends BasePresenter<ICommunityCommentListContract.ICommunityCommentListView> implements ICommunityCommentListContract.ICommunityCommentListPresenter {
    private CommunityCommentListModelImpl communityCommentListModel;
    @Override
    public void initModel() {
        communityCommentListModel=new CommunityCommentListModelImpl();
    }

    @Override
    public void getCommunityCommentList(int communityId, int page, int count) {
        communityCommentListModel.getCommunityCommentList(communityId, page, count, new ICommunityCommentListContract.ICommunityCommentListModel.DataCallBack() {
            @Override
            public void onSuccess(CommunityCommentListBean communityCommentListBean) {
                iBaseView.onSuccess(communityCommentListBean);
            }
            @Override
            public void onError(String error) {
                iBaseView.onError(error);
            }
        });
    }
}
