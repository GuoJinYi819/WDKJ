package com.wd.tech.mvp.gjymvp.queryfriend;

import com.wd.tech.base.BasePresenter;
import com.wd.tech.bean.gjybean.QueryFriendBean;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/4/23 0023 14:30
 * @Description: 用途：完成特定功能
 */
public class QueryFriendPresenter extends BasePresenter<IQueryFriendContract.IQueryFriendView> implements IQueryFriendContract.IQueryFriendPresenter{
    private QueryFriendModule module;
    @Override
    public void initModel() {
        module = new QueryFriendModule();
    }

    @Override
    public void queryFriend(int friend) {
        module.queryFriend(friend, new IQueryFriendContract.IQueryFriendModule.QueryFrinedCallBack() {
            @Override
            public void onSuccess(QueryFriendBean bean) {
                iBaseView.onSuccess(bean);
            }

            @Override
            public void onFailed(String error) {
                iBaseView.onFailed(error);
            }
        });
    }
}
