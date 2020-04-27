package com.wd.tech.mvp.gjymvp.deletechat;

import com.wd.tech.base.BasePresenter;
import com.wd.tech.bean.gjybean.DeleteChatBean;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/4/26 0026 11:02
 * @Description: 用途：完成特定功能
 */
public class DeleteChatPresenter extends BasePresenter<IDeleteChatContract.IDeleteChatView> implements IDeleteChatContract.IDeleteChatPresenter {
    private DeleteChatModule module;
    @Override
    public void initModel() {
        module = new DeleteChatModule();
    }

    @Override
    public void deleteChat(int friendUid) {
        module.deleteChat(friendUid, new IDeleteChatContract.IDeleteChatModule.DeleteChatCallBack() {
            @Override
            public void onSuccess(DeleteChatBean bean) {
                iBaseView.onSuccess(bean);
            }

            @Override
            public void onFailed(String error) {
                iBaseView.onFailed(error);
            }
        });
    }
}
