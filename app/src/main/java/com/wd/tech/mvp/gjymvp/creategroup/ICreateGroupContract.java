package com.wd.tech.mvp.gjymvp.creategroup;

import com.wd.tech.base.IBaseView;
import com.wd.tech.bean.gjybean.CreateGroupBean;

import java.util.Map;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/4/25 0025 15:44
 * @Description: 用途：完成特定功能
 */
public interface ICreateGroupContract {
    interface ICreateGroupView extends IBaseView{
        void onSuccess(CreateGroupBean bean);
        void onFailed(String error);
    }
    interface ICreateGroupModule{
        void createGroup(Map<String,String> params,CreateGroupCallBack createGroupCallBack);
        interface CreateGroupCallBack{
            void onSuccess(CreateGroupBean bean);
            void onFailed(String error);
        }
    }
    interface ICreateGroupPresenter{
        void createGroup(Map<String,String> params);
    }
}
