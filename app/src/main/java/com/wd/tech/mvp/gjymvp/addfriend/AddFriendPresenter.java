package com.wd.tech.mvp.gjymvp.addfriend;

import com.wd.tech.base.BasePresenter;
import com.wd.tech.bean.gjybean.AddFriendBean;

import java.util.Map;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/4/25 0025 14:45
 * @Description: 用途：完成特定功能
 */
public class AddFriendPresenter extends BasePresenter<IAddFriendContract.IAddFriendView> implements IAddFriendContract.IAddFriendPresenter {
    private AddFriendModule module;
    @Override
    public void initModel() {
        module = new AddFriendModule();
    }

    @Override
    public void addFriend(Map<String, String> params) {
        module.addFriend(params, new IAddFriendContract.IAddFriendModule.AddFriendCallBack() {
            @Override
            public void onSuccess(AddFriendBean bean) {
                iBaseView.onSuccess(bean);
            }

            @Override
            public void onFailed(String error) {
                iBaseView.onFailed(error);
            }
        });
    }
}
