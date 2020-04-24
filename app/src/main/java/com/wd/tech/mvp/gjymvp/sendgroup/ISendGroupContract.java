package com.wd.tech.mvp.gjymvp.sendgroup;

import com.wd.tech.base.IBaseView;
import com.wd.tech.bean.gjybean.QueryGroupBean;
import com.wd.tech.bean.gjybean.SendGroupBean;

import java.util.Map;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/4/24 0024 16:33
 * @Description: 用途：完成特定功能
 */
public interface ISendGroupContract {
    interface ISendGroupView extends IBaseView{
        void onQueryGroupSuccess(QueryGroupBean bean);
        void onSendGroupSuccess(SendGroupBean bean);
    }
    interface ISendGroupModule{
        void getQueryGroup(Map<String,String> params,QueryGroupCallBack queryGroupCallBack);
        void sendGroup(Map<String,String> params,SendGroupCallBack sendGroupCallBack);
        interface QueryGroupCallBack{
            void onQueryGroupSuccess(QueryGroupBean bean);
        }
        interface SendGroupCallBack{
            void onSendGroupSuccess(SendGroupBean bean);
        }
    }
    interface ISendGroupPresenter{
        void getQueryGroup(Map<String,String> params);
        void sendGroup(Map<String,String> params);
    }
}
