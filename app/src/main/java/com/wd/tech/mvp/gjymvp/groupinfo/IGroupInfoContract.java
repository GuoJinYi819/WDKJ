package com.wd.tech.mvp.gjymvp.groupinfo;

import com.wd.tech.base.IBaseView;
import com.wd.tech.bean.gjybean.GroupInfoBean;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/4/26 0026 18:15
 * @Description: 用途：完成特定功能
 */
public interface IGroupInfoContract {
    interface IGroupInfoView extends IBaseView {
        void onSuccess(GroupInfoBean bean);
        void onFailed(String error);
    }
    interface IGroupInfoModule{
        void getGroupInfo(int groupId,GroupInfoCallBack groupInfoCallBack);
        interface GroupInfoCallBack{
            void onSuccess(GroupInfoBean bean);
            void onFailed(String error);
        }
    }
    interface IGroupInfoPresenter{
        void getGroupInfo(int groupId);
    }


}
