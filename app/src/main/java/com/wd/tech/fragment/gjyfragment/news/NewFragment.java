package com.wd.tech.fragment.gjyfragment.news;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.base.BaseFragment;
import com.wd.tech.base.BasePresenter;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/4/19 0019 20:41
 * @Description: 用途：消息----消息
 */
public class NewFragment extends BaseFragment {
    private RecyclerView mRecyclerFriend;
    private RecyclerView mRecyclerFriendNotice;
    private RecyclerView mRecyclerGroup;

    @Override
    public int initLayout() {
        return R.layout.fragment_new;
    }

    @Override
    public void initView() {

        mRecyclerFriend = view.findViewById(R.id.recyclerFriend);
        mRecyclerFriendNotice = view.findViewById(R.id.recyclerFriendNotice);
        mRecyclerGroup =  view.findViewById(R.id.recyclerGroup);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        mRecyclerFriend.setLayoutManager(linearLayoutManager);

        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        mRecyclerFriendNotice.setLayoutManager(linearLayoutManager);

        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        mRecyclerGroup.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }
}
