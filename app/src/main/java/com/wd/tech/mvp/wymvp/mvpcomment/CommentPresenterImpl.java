package com.wd.tech.mvp.wymvp.mvpcomment;

import com.wd.tech.base.BasePresenter;
import com.wd.tech.bean.wybean.beancomment.CommentBean;
import com.wd.tech.bean.wybean.beandotask.DoTaskBean;
import com.wd.tech.mvp.wymvp.mvpsign.ISignContract;

import okhttp3.MultipartBody;

/**
 * @author 王阳
 * Class :1708A
 * @description:
 * @date :2020/4/19 19:28
 * @classname :CommentPresenterImpl
 */
public class CommentPresenterImpl extends BasePresenter<ICommentContract.ICommentView> implements ICommentContract.ICommentPresenter {
    private CommentModelImpl commentModel;
    @Override
    public void initModel() {
        commentModel=new CommentModelImpl();
    }

    @Override
    public void getComment(String content, MultipartBody.Part file) {
        commentModel.getComment(content, file, new ICommentContract.ICommentModel.DataCallBack() {
            @Override
            public void onSuccess(CommentBean commentBean) {
                iBaseView.onSuccess(commentBean);
            }

            @Override
            public void onError(String msg) {
                iBaseView.onError(msg);
            }
        });
    }

    @Override
    public void getDoTask(int taskId) {
        commentModel.getDoTask(taskId, new ISignContract.ISignModel.DataCallBack2() {
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
