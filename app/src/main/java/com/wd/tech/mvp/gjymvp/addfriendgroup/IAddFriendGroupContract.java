package com.wd.tech.mvp.gjymvp.addfriendgroup;

import com.wd.tech.base.IBaseView;
import com.wd.tech.bean.gjybean.AddFriendGroupBean;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/4/25 0025 21:43
 * @Description: 用途：完成特定功能
 */
public interface IAddFriendGroupContract {
    interface IAddFriendGroupView extends IBaseView{
        void onAddFriendGroupSuccess(AddFriendGroupBean bean);
        void onFailed(String error);
    }
    interface IAddFriendGroupModule{
        void addFriendGroup(String groupName,IAddFriendGroupCallBack addFriendGroupCallBack);
        interface IAddFriendGroupCallBack{
            void onAddFriendGroupSuccess(AddFriendGroupBean bean);
            void onFailed(String error);
        }
    }
    interface IAddFriendGroupPresenter{
        void addFriendGroup(String groupName);
    }
}
