package com.wd.tech.mvp.wymvp.mvphome;

import com.wd.tech.base.BasePresenter;
import com.wd.tech.bean.wybean.beancommunitycommentList.CommunityCommentListBean;
import com.wd.tech.bean.wybean.beanhome.HomeBean;
import com.wd.tech.bean.wybean.beansendcomment.SendCommentBean;

/**
 * @author 王阳
 * Class :1708A
 * @description:
 * @date :2020/4/19 14:14
 * @classname :HomePresenterImpl
 */
public class HomePresenterImpl extends BasePresenter<IHomeContract.IHomeView> implements IHomeContract.IHomePresenter {
    private HomeModelImpl homeModel;
    @Override
    public void initModel() {
        homeModel=new HomeModelImpl();
    }

    @Override
    public void getHome(int page, int count) {
        homeModel.getHome(page, count, new IHomeContract.IHomeModel.DataCallBack() {
            @Override
            public void onSuccess(HomeBean homeBean) {
                iBaseView.onSuccess(homeBean);
            }

            @Override
            public void onError(String error) {
                iBaseView.onError(error);
            }
        });
    }

    @Override
    public void getCommunityCommentList(int communityId, int page, int count) {
        homeModel.getCommunityCommentList(communityId, page, count, new IHomeContract.IHomeModel.DataCallBack2() {
            @Override
            public void onCommunityCommentListSuccess(CommunityCommentListBean communityCommentListBean) {
                iBaseView.onCommunityCommentListSuccess(communityCommentListBean);
            }

            @Override
            public void onCommunityCommentListError(String error) {
                iBaseView.onCommunityCommentListError(error);
            }
        });
    }

    @Override
    public void getSendComment(int communityId, String content) {
        homeModel.getSendComment(communityId,content,new IHomeContract.IHomeModel.DataCallBack3(){
            @Override
            public void onSendCommentSuccess(SendCommentBean sendCommentBean) {
                iBaseView.onSendCommentSuccess(sendCommentBean);
            }

            @Override
            public void onSendCommentError(String error) {
                iBaseView.onSendCommentError(error);
            }
        });
    }
}
