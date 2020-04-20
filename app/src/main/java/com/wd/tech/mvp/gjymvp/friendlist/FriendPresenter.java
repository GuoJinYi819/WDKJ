package com.wd.tech.mvp.gjymvp.friendlist;

import com.wd.tech.base.BasePresenter;
import com.wd.tech.bean.gjybean.FriendChildListBean;
import com.wd.tech.bean.gjybean.FriendGroupListBean;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/4/20 0020 17:39
 * @Description: 用途：完成特定功能
 */
public class FriendPresenter extends BasePresenter<IFriendContract.IFriendView> implements IFriendContract.IFriendPresenter {
    private FriendMoudle moudle;
    @Override
    public void initModel() {
        moudle = new FriendMoudle();
    }

    @Override
    public void getFriendGroupData() {
        moudle.getFriendGroupData(new IFriendContract.IFriendModule.FriendGroupCallBack() {
            @Override
            public void onGroupSuccess(FriendGroupListBean bean) {
                iBaseView.onGroupSuccess(bean);
            }

            @Override
            public void onFailed(String error) {

            }
        });
    }

    @Override
    public void getFriendChildData(int groupId) {
        moudle.getFriendChildData(groupId, new IFriendContract.IFriendModule.FriendChildCallBack() {
            @Override
            public void onChildSuccess(FriendChildListBean bean) {
                iBaseView.onChildSuccess(bean);
            }

            @Override
            public void onFailed(String error) {

            }
        });
    }
}
