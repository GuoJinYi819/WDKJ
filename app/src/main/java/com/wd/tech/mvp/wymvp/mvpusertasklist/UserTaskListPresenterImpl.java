package com.wd.tech.mvp.wymvp.mvpusertasklist;

import com.wd.tech.base.BasePresenter;
import com.wd.tech.bean.wybean.beanusertasklist.UserTaskListBean;

/**
 * @author 王阳
 * Class :1708A
 * @description:
 * @date :2020/5/1 16:37
 * @classname :UserTaskListPresenterImpl
 */
public class UserTaskListPresenterImpl extends BasePresenter<IUserTaskListContract.IUserTaskListView> implements IUserTaskListContract.IUserTaskListPresenter {
    private UserTaskListModelImpl userTaskListModel;
    @Override
    public void initModel() {
        userTaskListModel=new UserTaskListModelImpl();
    }

    @Override
    public void getUserTaskList() {
        userTaskListModel.getUserTaskList(new IUserTaskListContract.IUserTaskListModel.DataCallBack() {
            @Override
            public void onSuccess(UserTaskListBean userTaskListBean) {
                iBaseView.onSuccess(userTaskListBean);
            }

            @Override
            public void onError(String error) {
                iBaseView.onError(error);
            }
        });
    }
}
