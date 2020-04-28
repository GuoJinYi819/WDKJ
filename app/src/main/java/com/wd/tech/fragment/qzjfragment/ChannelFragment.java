package com.wd.tech.fragment.qzjfragment;

import android.content.Intent;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.wd.tech.R;
import com.wd.tech.activity.MostActivity;
import com.wd.tech.adapter.qzjadapter.MostAdapter;
import com.wd.tech.base.BaseFragment;
import com.wd.tech.bean.qzjbean.most.MostBean;
import com.wd.tech.mvp.qzjmvp.most.MostConter;
import com.wd.tech.mvp.qzjmvp.most.MostPresenterImpl;

import java.util.List;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : Quzijun
 * @version 创建时间：2020/4/22 10:56
 * @Description: 用途：完成特定功能
 */
public class ChannelFragment extends BaseFragment<MostPresenterImpl> implements MostConter.IMostView {
    private RecyclerView rlv;
    private MostAdapter adapter;
    @Override
    public int initLayout() {
        return R.layout.activity_channel_selection;
    }

    @Override
    public void initView() {

        rlv = view.findViewById(R.id.rlv);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        presenter.onDate();
    }

    @Override
    public MostPresenterImpl initPresenter() {
        return new MostPresenterImpl();
    }

    @Override
    public void onSuccess(MostBean mostBean) {
        List<MostBean.ResultBean> result = mostBean.getResult();
        adapter = new MostAdapter(result,getActivity());
        StaggeredGridLayoutManager s = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        rlv.setLayoutManager(s);
        rlv.setAdapter(adapter);
        adapter.setMostIdLenter(new MostAdapter.MostIdLenter() {
            @Override
            public void onMost(int id) {
                Intent intent = new Intent(getActivity(), MostActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
    }
}
