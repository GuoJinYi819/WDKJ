package com.wd.tech.fragment.gjyfragment.news;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.header.TaurusHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.wd.tech.App;
import com.wd.tech.R;
import com.wd.tech.adapter.gjyadapter.NewsAdapter;
import com.wd.tech.base.BaseFragment;
import com.wd.tech.base.BasePresenter;
import com.wd.tech.bean.gjybean.FriendDataBean;
import com.wd.tech.bean.gjybean.FriendNoticeBean;
import com.wd.tech.bean.gjybean.GroupNoticeBean;
import com.wd.tech.bean.gjybean.NewsBean;
import com.wd.tech.bean.wybean.Event;
import com.wd.tech.mvp.gjymvp.newsnotice.INewsNoticeContract;
import com.wd.tech.mvp.gjymvp.newsnotice.NewsNoticePresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/4/19 0019 20:41
 * @Description: 用途：消息----消息
 */
public class NewFragment extends BaseFragment<NewsNoticePresenter> implements INewsNoticeContract.INewsNoticeView {
    private RecyclerView recyclerNews;

    private ArrayList<NewsBean> list = new ArrayList<NewsBean>();
    private NewsAdapter newsAdapter;
    private int help = 0;
    private SmartRefreshLayout refreshLayout;

    @Override
    public int initLayout() {
        return R.layout.fragment_new;
    }

    @Override
    public void initView() {
        boolean registered = EventBus.getDefault().isRegistered(this);

        if (!registered) {
            EventBus.getDefault().register(this);
        }

        recyclerNews = view.findViewById(R.id.recyclerNews);
        refreshLayout = view.findViewById(R.id.refreshLayout);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerNews.setLayoutManager(linearLayoutManager);
        //设置刷新样式
        refreshLayout.setRefreshHeader(new TaurusHeader(App.context));
        refreshLayout.setRefreshFooter(new BallPulseFooter(App.context).setSpinnerStyle(SpinnerStyle.Scale));

    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void onEvent(NewsBean bean){
        if (bean != null) {

            list.add(bean);
            //去重
            for  ( int  i  =   0 ; i  <  list.size()  -   1 ; i ++ )  {
                for  ( int  j  =  list.size()  -   1 ; j  >  i; j -- )  {
                    if  (list.get(j).getUserId().equals(list.get(i).getUserId()))  {
                        list.remove(j);
                    }
                }
            }

            if (newsAdapter != null) {
                newsAdapter.setList(list);
                newsAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void initListener() {
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                Toast toast = new Toast(getContext());
                View inflate = LayoutInflater.from(getContext()).inflate(R.layout.toast_my, null);
                TextView viewById = inflate.findViewById(R.id.tv);
                viewById.setText("刷新完成");
                toast.setView(inflate);
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.show();
                refreshLayout.finishRefresh(true);
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                Toast toast = new Toast(getContext());
                View inflate = LayoutInflater.from(getContext()).inflate(R.layout.toast_my, null);
                TextView viewById = inflate.findViewById(R.id.tv);
                viewById.setText("加载完成");
                toast.setView(inflate);
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.show();
                refreshLayout.finishLoadMore(true);
            }
        });
    }

    @Override
    public void initData() {
        //请求好友通知   群通知
//        HashMap<String, String> hashMap = new HashMap<>();
//        hashMap.put("page","1");
//        hashMap.put("count","10");
//        presenter.getFriendNotice(hashMap);
//        hashMap = new HashMap<>();
//        hashMap.put("page","1");
//        hashMap.put("count","10");
//        presenter.getGroupNotice(hashMap);
        list.add(new NewsBean("null","好友通知","null",""));
        list.add(new NewsBean("null","群通知","nullnull",""));

        newsAdapter = new NewsAdapter(getContext(),list);
        recyclerNews.setAdapter(newsAdapter);

    }

    @Override
    public NewsNoticePresenter initPresenter() {
        return new NewsNoticePresenter();
    }

    @Override
    public void onFriendNoticeSuccess(FriendNoticeBean bean) {
        String message = bean.getMessage();
    }

    @Override
    public void onGroupNoticeSuccess(GroupNoticeBean bean) {
        String message = bean.getMessage();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
