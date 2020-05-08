package com.wd.tech.fragment.wyfragment;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.scwang.smartrefresh.header.DropBoxHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.wd.tech.App;
import com.wd.tech.R;
import com.wd.tech.activity.CommentActivity;
import com.wd.tech.adapter.wyadapter.RecyclerCommunityAdapter;
import com.wd.tech.base.BaseFragment;
import com.wd.tech.bean.wybean.Event;
import com.wd.tech.bean.wybean.beancommunitycommentList.CommunityCommentListBean;
import com.wd.tech.bean.wybean.beanhome.HomeBean;
import com.wd.tech.bean.wybean.beanhome.ResultBean;
import com.wd.tech.bean.wybean.beansendcomment.SendCommentBean;
import com.wd.tech.mvp.wymvp.mvphome.HomePresenterImpl;
import com.wd.tech.mvp.wymvp.mvphome.IHomeContract;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
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
    private List<String> result2 = new ArrayList<>();
    //刷新次数
    private int count = 10;
    private int page = 1;
    private SmartRefreshLayout communitySmartWy;
    private List<ResultBean> result;

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
        communitySmartWy = (SmartRefreshLayout) view.findViewById(R.id.communitySmartWy);
        communitySmartWy.setRefreshHeader(new DropBoxHeader(App.context));
        communitySmartWy.setRefreshFooter(new BallPulseFooter(App.context).setSpinnerStyle( SpinnerStyle.Scale));
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
        //上拉  下拉
        communitySmartWy.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                //刷新
                count=10;
                presenter.getHome(1, count);
                presenter.getCommunityCommentList(1, 1, count);
                //
                recyclerCommunityAdapter.onRefresh(result);
                //
                communitySmartWy.finishRefresh();
            }
        });

        communitySmartWy.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                //加载
                page++;
                count++;
                presenter.getHome(page, count);
                presenter.getCommunityCommentList(1, 1, count);
                //
                recyclerCommunityAdapter.onLoadMore(result);
                //
                communitySmartWy.finishLoadMore();
            }
        });
        /*communitySmartWy.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                //加载
                page++;
                count++;
                presenter.getHome(1, count);
                presenter.getCommunityCommentList(1, 1, count);
                //
                recyclerCommunityAdapter.onLoadMore(result);
                //
                communitySmartWy.finishLoadMore();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                //刷新
                count=10;
                presenter.getHome(1, count);
                presenter.getCommunityCommentList(1, 1, count);
                //
                recyclerCommunityAdapter.onRefresh(result);
                //
                communitySmartWy.finishRefresh();
            }
        });*/
    }

    //刷新
    @Override
    public void onResume() {
        super.onResume();
        //社区页面
        presenter.getHome(1, 10);
        presenter.getCommunityCommentList(1, 1, 10);
    }

    @Override
    public void initData() {
        //社区页面
        presenter.getHome(1, 10);
        presenter.getCommunityCommentList(1, 1, 10);
    }

    //接收
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onEvent(Event event) {
        //int id = event.getId();
        //标签   评论
        //presenter.getCommunityCommentList(id,1,10);
        String content = event.getContent();
        //发  评论
        if (!TextUtils.isEmpty(content)) {
            presenter.getSendComment(1, content);
        }
    }

    @Override
    public HomePresenterImpl initPresenter() {
        return new HomePresenterImpl();
    }

    @Override
    public void onSuccess(HomeBean homeBean) {
        result = homeBean.getResult();
        //适配器
        recyclerCommunityAdapter = new RecyclerCommunityAdapter(result, getContext());
        recyclerWy.setAdapter(recyclerCommunityAdapter);
        if (result2 != null) {
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
        Log.d("==", "onCommunityCommentListSuccess: " + result);
        result2 = result;
    }

    @Override
    public void onCommunityCommentListError(String error) {
    }

    @Override
    public void onSendCommentSuccess(SendCommentBean sendCommentBean) {
        //成功  发表评论
        String message = sendCommentBean.getMessage();
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        String status = sendCommentBean.getStatus();
        if(status.equals("0000")){
            //刷新
            count=10;
            presenter.getHome(1, count);
            presenter.getCommunityCommentList(1, 1, count);
            //
            recyclerCommunityAdapter.onRefresh(result);
            //
            communitySmartWy.finishRefresh();
        }
    }

    @Override
    public void onSendCommentError(String error) {
    }

    //销毁
    @Override
    public void onDestroy() {
        super.onDestroy();
        boolean registered = EventBus.getDefault().isRegistered(this);
        if (registered) {
            EventBus.getDefault().unregister(this);
        }
    }
}
