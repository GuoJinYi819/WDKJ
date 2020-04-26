package com.wd.tech.mvp.wymvp.mvpmypost;

import com.wd.tech.base.BasePresenter;
import com.wd.tech.bean.wybean.beandeletepost.DeletePostBean;
import com.wd.tech.bean.wybean.beanmypost.MyPostBean;

/**
 * @author 王阳
 * Class :1708A
 * @description:
 * @date :2020/4/24 15:14
 * @classname :MyPostPresenterImpl
 */
public class MyPostPresenterImpl extends BasePresenter<IMyPostContract.IMyPostView> implements IMyPostContract.IMyPostPresenter {
    private MyPostModelImpl myPostModel;
    @Override
    public void initModel() {
        myPostModel=new MyPostModelImpl();
    }

    @Override
    public void getMyPost(int page, int count) {
        myPostModel.getMyPost(page, count, new IMyPostContract.IMyPostModel.DataCallBack() {
            @Override
            public void onMyPostSuccess(MyPostBean myPostBean) {
                iBaseView.onMyPostSuccess(myPostBean);
            }

            @Override
            public void onError(String error) {
                iBaseView.onError(error);
            }
        });
    }

    @Override
    public void getDeletePost(String communityId) {
        myPostModel.getDeletePost(communityId, new IMyPostContract.IMyPostModel.DataCallBack2() {
            @Override
            public void onDeletePostSuccess(DeletePostBean deletePostBean) {
                iBaseView.onDeletePostSuccess(deletePostBean);
            }

            @Override
            public void onDeleteError(String error) {
                iBaseView.onDeleteError(error);
            }
        });
    }
}
