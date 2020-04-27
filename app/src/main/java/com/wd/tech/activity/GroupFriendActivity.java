package com.wd.tech.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.wd.tech.R;
import com.wd.tech.adapter.gjyadapter.GroupFriendAdapter;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.base.BasePresenter;
import com.wd.tech.bean.gjybean.DeleteGroupBean;
import com.wd.tech.bean.gjybean.FriendBean;
import com.wd.tech.bean.gjybean.GroupMemberListBean;
import com.wd.tech.bean.wybean.Event;
import com.wd.tech.mvp.gjymvp.groupmemberlist.GroupMemberListPresenter;
import com.wd.tech.mvp.gjymvp.groupmemberlist.IGroupMemberListContract;
import com.wd.tech.net.ApiService;
import com.wd.tech.net.RetrofitUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class GroupFriendActivity extends BaseActivity<GroupMemberListPresenter> implements IGroupMemberListContract.IGroupMemberListView {

    private android.widget.ImageView mIvPic;
    private androidx.recyclerview.widget.RecyclerView mRecyclerGroup;

    @Override
    public int initLayout() {
        return R.layout.activity_group_friend;
    }

    @Override
    public void initView() {

        mIvPic = findViewById(R.id.ivPic);
        mRecyclerGroup = findViewById(R.id.recyclerGroup);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(GroupFriendActivity.this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        mRecyclerGroup.setLayoutManager(linearLayoutManager);

        boolean registered = EventBus.getDefault().isRegistered(this);
        if (!registered) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void initListener() {
        mIvPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void onEvent(FriendBean bean){
        int userId = bean.getUserId();
        Intent intent = getIntent();
        int groupId = intent.getIntExtra("groupId", -1);

        //删除群成员
        RetrofitUtil instance = RetrofitUtil.getInstance();
        ApiService apiService = instance.createService();
        Observable<DeleteGroupBean> observable = apiService.removeGroupMember(groupId, userId);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<DeleteGroupBean>() {
                    @Override
                    public void accept(DeleteGroupBean deleteGroupBean) throws Exception {
                        presenter.getGroupMemberList(groupId);
                    }
                });

    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        int groupId = intent.getIntExtra("groupId", -1);
        presenter.getGroupMemberList(groupId);
    }

    @Override
    public GroupMemberListPresenter initPresenter() {
        return new GroupMemberListPresenter();
    }

    @Override
    public void onSuccess(GroupMemberListBean bean) {
        String message = bean.getMessage();
        List<GroupMemberListBean.ResultBean> result = bean.getResult();
        if (result != null) {
            GroupFriendAdapter groupFriendAdapter = new GroupFriendAdapter(result, GroupFriendActivity.this);
            mRecyclerGroup.setAdapter(groupFriendAdapter);
        }
    }

    @Override
    public void onFailed(String error) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
