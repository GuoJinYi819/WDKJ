package com.wd.tech.mvp.gjymvp.sendnews;

import com.wd.tech.base.IBaseView;
import com.wd.tech.bean.gjybean.DialogueRecordBean;
import com.wd.tech.bean.gjybean.SendMessageBean;

import java.util.Map;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/4/23 0023 16:03
 * @Description: 用途：完成特定功能
 */
public interface ISendNewsContract {
    interface ISendNewsView extends IBaseView{
        void getDialogRecordSuccess(DialogueRecordBean bean);
        void sendMessage(SendMessageBean bean);
    }
    interface ISendNewsModule{
        void getDialogRecordData(Map<String,String> params,DialogRecordCallBack dialogRecordCallBack);
        void sendMessage(Map<String,String> params,SendMessageCallBack sendMessageCallBack);
        interface DialogRecordCallBack{
            void getDialogRecordSuccess(DialogueRecordBean bean);
        }
        interface SendMessageCallBack{
            void sendMessage(SendMessageBean bean);
        };
    }
    interface ISendNewsPresenter{
        void getDialogRecordData(Map<String,String> params);
        void sendMessage(Map<String,String> params);
    }
}
