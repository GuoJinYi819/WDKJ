package com.wd.tech.fragment.gjyfragment.news;

import android.content.Intent;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.wd.tech.R;
import com.wd.tech.activity.GroupActivity;
import com.wd.tech.activity.MyFriendSeachActivity;
import com.wd.tech.activity.QueryFriendActivity;
import com.wd.tech.adapter.gjyadapter.FriendGroupAdapter;
import com.wd.tech.base.BaseFragment;
import com.wd.tech.bean.gjybean.FriendChildListBean;
import com.wd.tech.bean.gjybean.FriendGroupListBean;
import com.wd.tech.mvp.gjymvp.friendlist.FriendPresenter;
import com.wd.tech.mvp.gjymvp.friendlist.IFriendContract;

import java.util.List;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/4/19 0019 20:41
 * @Description: 用途：消息----联系人
 */
public class ContactsFragment extends BaseFragment<FriendPresenter> implements IFriendContract.IFriendView {
    private RelativeLayout mRelativeSeach;
    private LinearLayout mLineGroup;
    private ExpandableListView mExpandableListView;
    private FriendGroupAdapter adapter;
    private boolean isConfig = false;
    private int nummber = 0;
    private int groupid = 0;

    @Override
    public int initLayout() {
        return R.layout.fragment_contacts;
    }

    @Override
    public void initView() {
        //搜索框
        mRelativeSeach = view.findViewById(R.id.relative_Seach);
        //群组
        mLineGroup = view.findViewById(R.id.line_group);
        //二级下拉列表
        mExpandableListView = view.findViewById(R.id.expandable_ListView);

    }

    @Override
    public void initListener() {
        //跳转至搜索界面
        mRelativeSeach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MyFriendSeachActivity.class);
                startActivity(intent);
            }
        });
        //跳转至群组界面
        mLineGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), GroupActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void initData() {
        presenter.getFriendGroupData();
    }

    @Override
    public FriendPresenter initPresenter() {
        return new FriendPresenter();
    }

    @Override
    public void onGroupSuccess(FriendGroupListBean bean) {
        //外层
        String message = bean.getMessage();
        List<FriendGroupListBean.ResultBean> result = bean.getResult();
        if (result != null) {
            adapter = new FriendGroupAdapter(result, getContext());
            mExpandableListView.setAdapter(adapter);

            adapter.onGruopIdListener = new FriendGroupAdapter.OnGruopIdListener() {
                @Override
                public void onGroupId(int id,int groud) {
                    groupid = id;
                    if(!isConfig){
                        nummber = groud;
                        presenter.getFriendChildData(id);
                        isConfig = true;
                    }else {
                        adapter.setListChild(null);
                        //关闭分组
                        mExpandableListView.collapseGroup(groud);
                        isConfig = false;
                    }

                }
            };
        }
    }

    @Override
    public void onChildSuccess(FriendChildListBean bean) {
        //内层
        List<FriendChildListBean.ResultBean> result = bean.getResult();

            if (adapter != null) {
                adapter.setListChild(result);
                //刷新
                List<FriendGroupListBean.ResultBean> list = adapter.getList();
                FriendGroupListBean.ResultBean resultBean = list.get(nummber);
                if (resultBean.getCurrentNumber()>0) {
                    //打开分组
                    mExpandableListView.expandGroup(nummber);
                }else {
                    Toast.makeText(getContext(), "该列表没有联系人", Toast.LENGTH_SHORT).show();
                    isConfig = false;
                }

                adapter.setOnFriendListener(new FriendGroupAdapter.OnFriendListener() {
                    @Override
                    public void onFriendId(int friend) {
                        //跳转界面
                        Intent intent = new Intent(getContext(), QueryFriendActivity.class);
                        intent.putExtra("friend",friend);
                        intent.putExtra("groupId",groupid);
                        startActivity(intent);
                    }
                });

            }

    }

    @Override
    public void onFailed(String error) {

    }
}
