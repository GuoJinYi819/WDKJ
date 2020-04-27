package com.wd.tech.mvp.gjymvp.addgroup;

import com.wd.tech.base.IBaseView;
import com.wd.tech.bean.gjybean.AddFriendBean;

import java.util.Map;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/4/25 0025 14:40
 * @Description: 用途：完成特定功能
 */
public interface IAddGroupContract {
    interface IAddGroupView extends IBaseView{
        void onSuccess(AddFriendBean bean);
        void onFailed(String error);
    }
    interface IAddGroupModule{
        void addGroup(Map<String, String> params, AddGroupCallBack addGroupCallBack);
        interface AddGroupCallBack{
            void onSuccess(AddFriendBean bean);
            void onFailed(String error);
        }
    }
    interface IAddGroupPresenter{
        void addGroup(Map<String, String> params);
    }
}
