package com.wd.tech.fragment.gjyfragment.news;

import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerNews.setLayoutManager(linearLayoutManager);

    }

    @Override
    public void initListener() {

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
        list.add(new NewsBean("null","好友通知","null","null"));
        list.add(new NewsBean("null","群通知","null","null"));
        list.add(new NewsBean("heihie","三毛","","null"));

        NewsAdapter newsAdapter = new NewsAdapter(getContext(),list);
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
