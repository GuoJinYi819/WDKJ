package com.wd.tech.mvp.gjymvp.groupmemberlist;

import com.wd.tech.bean.gjybean.GroupMemberListBean;
import com.wd.tech.net.ApiService;
import com.wd.tech.net.RetrofitUtil;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/4/26 0026 20:27
 * @Description: 用途：完成特定功能
 */
public class GroupMemberListModule implements IGroupMemberListContract.IGroupMemberListModule {
    @Override
    public void getGroupMemberList(int groupId, GroupMemberListCallBack groupMemberListCallBack) {
        RetrofitUtil instance = RetrofitUtil.getInstance();
        ApiService apiService = instance.createService();
        Observable<GroupMemberListBean> observable = apiService.getGroupMemberList(groupId);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GroupMemberListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GroupMemberListBean value) {
                        groupMemberListCallBack.onSuccess(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        groupMemberListCallBack.onFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
