package com.wd.tech.mvp.gjymvp.friendseach;

import com.wd.tech.base.BasePresenter;
import com.wd.tech.bean.gjybean.FriendSeachBean;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/4/21 0021 9:59
 * @Description: 用途：完成特定功能
 */
public class FriendSeachPresenter extends BasePresenter<IFriendSeachContract.IFriendSeachView> implements IFriendSeachContract.IFriendSeachPresenter {
    private FriendSeachModule module;
    @Override
    public void initModel() {
        module = new FriendSeachModule();
    }

    @Override
    public void getFriendSeach(String searchName) {
        module.getFriendSeach(searchName, new IFriendSeachContract.IFriendSeachModule.FriendSeachCallBack() {
            @Override
            public void onSuccess(FriendSeachBean bean) {
                iBaseView.onSuccess(bean);
            }

            @Override
            public void onFailed(String error) {
                iBaseView.onFailed(error);
            }
        });
    }
}
