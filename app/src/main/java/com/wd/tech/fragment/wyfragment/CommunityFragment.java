package com.wd.tech.fragment.wyfragment;

import android.content.Intent;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.tech.R;
import com.wd.tech.activity.CommentActivity;
import com.wd.tech.adapter.wyadapter.RecyclerCommunityAdapter;
import com.wd.tech.base.BaseFragment;
import com.wd.tech.bean.wybean.beanhome.HomeBean;
import com.wd.tech.bean.wybean.beanhome.ResultBean;
import com.wd.tech.mvp.wymvp.mvphome.HomePresenterImpl;
import com.wd.tech.mvp.wymvp.mvphome.IHomeContract;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/4/18 0018 22:53
 * @Description: 用途：社区
 */
public class CommunityFragment extends BaseFragment<HomePresenterImpl> implements IHomeContract.IHomeView {
    private RecyclerView recyclerWy;
    private SimpleDraweeView imgWriteWt;
    private RecyclerCommunityAdapter recyclerCommunityAdapter;

    @Override
    public int initLayout() {
        return R.layout.fragment_community;
    }

    @Override
    public void initView() {
        recyclerWy = (RecyclerView) view.findViewById(R.id.recycler_wy);
        //布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerWy.setLayoutManager(linearLayoutManager);
        imgWriteWt = (SimpleDraweeView) view.findViewById(R.id.imgWriteWt);
    }

    @Override
    public void initListener() {
        imgWriteWt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转   写评论页面
                Intent intent = new Intent(getContext(), CommentActivity.class);
                startActivity(intent);
                //刷新
                recyclerCommunityAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void initData() {
        presenter.getHome(1, 10);
    }

    @Override
    public HomePresenterImpl initPresenter() {
        return new HomePresenterImpl();
    }

    @Override
    public void onSuccess(HomeBean homeBean) {
        List<ResultBean> result = homeBean.getResult();
        //适配器
        recyclerCommunityAdapter = new RecyclerCommunityAdapter(result, getContext());
        recyclerWy.setAdapter(recyclerCommunityAdapter);
    }

    @Override
    public void onError(String error) {
    }
}
