package com.wd.tech.fragment.wyfragment;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.tech.R;
import com.wd.tech.activity.CommentActivity;
import com.wd.tech.adapter.wyadapter.RecyclerCommunityAdapter;
import com.wd.tech.base.BaseFragment;
import com.wd.tech.bean.wybean.Event;
import com.wd.tech.bean.wybean.beancommunitycommentList.CommunityCommentListBean;
import com.wd.tech.bean.wybean.beanhome.HomeBean;
import com.wd.tech.bean.wybean.beanhome.ResultBean;
import com.wd.tech.mvp.wymvp.mvphome.HomePresenterImpl;
import com.wd.tech.mvp.wymvp.mvphome.IHomeContract;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
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
    private List<String> result2=new ArrayList<>();

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
        //订阅
        EventBus.getDefault().register(this);
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
        //社区页面
        presenter.getHome(1, 10);
        presenter.getCommunityCommentList(1,1,10);
    }
    //接收
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void onEvent(Event event){
        //int id = event.getId();
        //标签   评论
        //presenter.getCommunityCommentList(id,1,10);
        String content = event.getContent();
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
        if(result2!=null){
            recyclerCommunityAdapter.setResultList(result2);
        }
    }
    @Override
    public void onError(String error) {
    }
    @Override
    public void onCommunityCommentListSuccess(CommunityCommentListBean communityCommentListBean) {
        //成功   评论列表
        List<String> result = communityCommentListBean.getResult();
        Log.d("==", "onCommunityCommentListSuccess: "+result);
        result2=result;
    }
    @Override
    public void onCommunityCommentListError(String error) {
    }
    //销毁
    @Override
    public void onDestroy() {
        super.onDestroy();
        boolean registered = EventBus.getDefault().isRegistered(this);
        if(registered){
            EventBus.getDefault().unregister(this);
        }
    }
}
