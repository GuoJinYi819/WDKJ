package com.wd.tech.mvp.gjymvp.deletechat;

import com.wd.tech.base.IBaseView;
import com.wd.tech.bean.gjybean.DeleteChatBean;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/4/26 0026 10:59
 * @Description: 用途：完成特定功能
 */
public interface IDeleteChatContract {
    interface IDeleteChatView extends IBaseView{
        void onSuccess(DeleteChatBean bean);
        void onFailed(String error);
    }
    interface IDeleteChatModule{
        void deleteChat(int friendUid,DeleteChatCallBack deleteChatCallBack);
        interface DeleteChatCallBack{
            void onSuccess(DeleteChatBean bean);
            void onFailed(String error);
        }
    }
    interface IDeleteChatPresenter{
        void deleteChat(int friendUid);
    }
}
