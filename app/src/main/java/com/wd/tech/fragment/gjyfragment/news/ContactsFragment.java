package com.wd.tech.fragment.gjyfragment.news;

import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.wd.tech.R;
import com.wd.tech.adapter.FriendGroupAdapter;
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
            }

    }

    @Override
    public void onFailed(String error) {

    }
}
