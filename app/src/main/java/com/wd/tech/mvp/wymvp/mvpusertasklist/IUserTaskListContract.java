package com.wd.tech.mvp.wymvp.mvpusertasklist;

import com.wd.tech.base.IBaseView;
import com.wd.tech.bean.wybean.beanusertasklist.UserTaskListBean;

/**
 * @author 王阳
 * Class :1708A
 * @description:
 * @date :2020/5/1 16:27
 * @classname :IUserTaskListContract
 */
public interface IUserTaskListContract {
    //
    interface IUserTaskListView extends IBaseView{
        //
        void onSuccess(UserTaskListBean userTaskListBean);
        //
        void onError(String error);
    }
    //
    interface IUserTaskListModel{
        void getUserTaskList(DataCallBack dataCallBack);
        interface DataCallBack{
            //
            void onSuccess(UserTaskListBean userTaskListBean);
            //
            void onError(String error);
        }
    }
    //
    interface IUserTaskListPresenter{
        void getUserTaskList();
    }
}
