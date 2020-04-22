package com.wd.tech.mvp.wymvp.mvpfollow;

import com.wd.tech.base.BasePresenter;
import com.wd.tech.bean.wybean.beanfollow.FollowBean;

/**
 * @author 王阳
 * Class :1708A
 * @description:
 * @date :2020/4/22 20:17
 * @classname :FollowPresenterImpl
 */
public class FollowPresenterImpl extends BasePresenter<IFollowContract.IFollowView> implements IFollowContract.IFollowPresenter {
    private FollowModelImpl followModel;
    @Override
    public void initModel() {
        followModel=new FollowModelImpl();
    }

    @Override
    public void getFollow(int page, int count) {
        followModel.getFollow(page, count, new IFollowContract.IFollowModel.DataCallBack() {
            @Override
            public void onSuccess(FollowBean follow) {
                iBaseView.onSuccess(follow);
            }

            @Override
            public void onError(String error) {
                iBaseView.onError(error);
            }
        });
    }
}
