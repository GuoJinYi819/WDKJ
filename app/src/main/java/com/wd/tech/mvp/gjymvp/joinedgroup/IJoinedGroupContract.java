package com.wd.tech.mvp.gjymvp.joinedgroup;

import com.wd.tech.base.IBaseView;
import com.wd.tech.bean.gjybean.JoinedGroupBean;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/4/21 0021 11:42
 * @Description: 用途：完成特定功能
 */
public interface IJoinedGroupContract {

    interface IJoinedGroupView extends IBaseView{
        void onSuccess(JoinedGroupBean bean);
        void onFailed(String error);
    }

    interface IJoinedGroupModule{
        void getJoinedGroup(JoinedGroupCallBack joinedGroupCallBack);
        //请求全部群组
        interface JoinedGroupCallBack{
            void onSuccess(JoinedGroupBean bean);
            void onFailed(String error);
        }
    }

    interface IJoinedGroupPresenter{
        void getJoinedGroup();
    }
}
