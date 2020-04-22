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
import com.wd.tech.mvp.gjymvp.newsnotice.INewsNoticeContract;
import com.wd.tech.mvp.gjymvp.newsnotice.NewsNoticePresenter;

import java.util.HashMap;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/4/19 0019 20:41
 * @Description: 用途：消息----消息
 */
public class NewFragment extends BaseFragment<NewsNoticePresenter> implements INewsNoticeContract.INewsNoticeView {
    private RecyclerView recyclerNews;

    @Override
    public int initLayout() {
        return R.layout.fragment_new;
    }

    @Override
    public void initView() {

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
        NewsAdapter newsAdapter = new NewsAdapter(getContext());
        recyclerNews.setAdapter(newsAdapter);
    }

    @Override
    public NewsNoticePresenter initPresenter() {
        return new NewsNoticePresenter();
    }

    @Override
    public void onFriendDataSuccessSuccess(FriendDataBean bean) {

    }

    @Override
    public void onFriendNoticeSuccess(FriendNoticeBean bean) {
        String message = bean.getMessage();
    }

    @Override
    public void onGroupNoticeSuccess(GroupNoticeBean bean) {
        String message = bean.getMessage();
    }
}
