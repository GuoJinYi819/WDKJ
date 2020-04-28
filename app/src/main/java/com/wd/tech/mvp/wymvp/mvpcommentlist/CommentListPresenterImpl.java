package com.wd.tech.mvp.wymvp.mvpcommentlist;

import com.wd.tech.base.BasePresenter;
import com.wd.tech.bean.wybean.beancommentlist.CommentListBean;
import com.wd.tech.bean.wybean.beandotask.DoTaskBean;
import com.wd.tech.mvp.wymvp.mvpsign.ISignContract;

/**
 * @author 王阳
 * Class :1708A
 * @description:
 * @date :2020/4/20 8:32
 * @classname :CommentListPresenterImpl
 */
public class CommentListPresenterImpl extends BasePresenter<ICommentListContract.ICommentListView> implements ICommentListContract.ICommentListPresenter {
    private CommentListModelImpl commentListModel;
    @Override
    public void initModel() {
        commentListModel=new CommentListModelImpl();
    }

    @Override
    public void getCommentList(int communityId, int page,int count) {
        commentListModel.getCommentList(communityId, page, count, new ICommentListContract.ICommentListModel.DataCallBack() {
            @Override
            public void onSuccess(CommentListBean commentListBean) {
                iBaseView.onSuccess(commentListBean);
            }

            @Override
            public void onError(String error) {
                iBaseView.onError(error);
            }
        });
    }

    @Override
    public void getDoTask(int taskId) {
        commentListModel.getDoTask(taskId, new ISignContract.ISignModel.DataCallBack2() {
            @Override
            public void onSuccess(DoTaskBean doTaskBean) {
                iBaseView.onTaskSuccess(doTaskBean);
            }

            @Override
            public void onError(String error) {
                iBaseView.onError(error);
            }
        });
    }
}
