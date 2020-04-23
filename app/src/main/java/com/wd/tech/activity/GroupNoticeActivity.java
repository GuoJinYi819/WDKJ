package com.wd.tech.activity;

import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.adapter.gjyadapter.FriendNoticeAdapter;
import com.wd.tech.adapter.gjyadapter.GroupNoticeAdapter;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.gjybean.FriendNoticeBean;
import com.wd.tech.bean.gjybean.GroupNoticeBean;
import com.wd.tech.bean.gjybean.ReviewFriendApplyBean;
import com.wd.tech.mvp.gjymvp.newsnotice.INewsNoticeContract;
import com.wd.tech.mvp.gjymvp.newsnotice.NewsNoticePresenter;
import com.wd.tech.net.ApiService;
import com.wd.tech.net.RetrofitUtil;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

//好友通知
public class GroupNoticeActivity extends BaseActivity<NewsNoticePresenter> implements INewsNoticeContract.INewsNoticeView {

    private android.widget.ImageView mIvBack;
    private RecyclerView mRecyclerGroupNotice;

    @Override
    public int initLayout() {
        return R.layout.activity_group_notice;
    }

    @Override
    public void initView() {
        mIvBack = findViewById(R.id.ivBackc);
        mRecyclerGroupNotice = findViewById(R.id.recyclerGroupNotice);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(GroupNoticeActivity.this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        mRecyclerGroupNotice.setLayoutManager(linearLayoutManager);

    }

    @Override
    public void initListener() {
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void initData() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("page","1");
        hashMap.put("count","10");
        presenter.getGroupNotice(hashMap);
    }

    @Override
    public NewsNoticePresenter initPresenter() {
        return new NewsNoticePresenter();
    }

    @Override
    public void onFriendNoticeSuccess(FriendNoticeBean bean) {

    }

    @Override
    public void onGroupNoticeSuccess(GroupNoticeBean bean) {
        List<GroupNoticeBean.ResultBean> result = bean.getResult();
        if (result != null) {
            GroupNoticeAdapter groupNoticeAdapter = new GroupNoticeAdapter(result, GroupNoticeActivity.this);
            mRecyclerGroupNotice.setAdapter(groupNoticeAdapter);
            //监听
            groupNoticeAdapter.onStatusListener=new GroupNoticeAdapter.OnStatusListener() {
                @Override
                public void onAgree(int noticeId, int flag) {
                    //审核群通知
                    RetrofitUtil instance = RetrofitUtil.getInstance();
                    ApiService apiService = instance.createService();
                    Observable<ReviewFriendApplyBean> observable = apiService.getReviewGroupApply(noticeId, flag);
                    observable.subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Consumer<ReviewFriendApplyBean>() {
                                @Override
                                public void accept(ReviewFriendApplyBean reviewFriendApplyBean) throws Exception {
                                    String message = reviewFriendApplyBean.getMessage();
                                    Toast.makeText(GroupNoticeActivity.this, ""+message, Toast.LENGTH_SHORT).show();
                                    groupNoticeAdapter.notifyDataSetChanged();
                                }
                            });
                }
            };
        }
    }
}
