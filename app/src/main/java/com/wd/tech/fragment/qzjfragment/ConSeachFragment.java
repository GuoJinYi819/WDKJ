package com.wd.tech.fragment.qzjfragment;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.wd.tech.R;
import com.wd.tech.adapter.qzjadapter.SeachAdapter;
import com.wd.tech.base.BaseFragment;
import com.wd.tech.base.BasePresenter;
import com.wd.tech.bean.qzjbean.seach.SeachBean;
import com.wd.tech.bean.qzjbean.seach.SeachResultBean;
import com.wd.tech.custom.MyFlowView;
import com.wd.tech.mvp.qzjmvp.seachmvp.SeachConter;
import com.wd.tech.mvp.qzjmvp.seachmvp.SeachPresenterImpl;

import java.util.List;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : Quzijun
 * @version 创建时间：2020/4/22 11:00
 * @Description: 用途：完成特定功能
 */
public class ConSeachFragment extends BaseFragment<SeachPresenterImpl> implements SeachConter.ISeachView {
    private ImageView sousuo;
    private EditText sedit;
    private MyFlowView flow;
    private RecyclerView re;
    private SeachAdapter adapter;

    @Override
    public int initLayout() {
        return R.layout.fragment_con_seach;
    }

    @Override
    public void initView() {

        sousuo = view.findViewById(R.id.sousuo);
        sedit = view.findViewById(R.id.sedit);
        flow = view.findViewById(R.id.flow);
        re = view.findViewById(R.id.re);
    }

    @Override
    public void initListener() {
        sedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flow.setVisibility(View.VISIBLE);
            }
        });
        sousuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flow.setVisibility(View.GONE);
                String trim = sedit.getText().toString().trim();
                flow.add(trim);
                presenter.getData(trim,1,5);
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public SeachPresenterImpl initPresenter() {
        return new SeachPresenterImpl();
    }

    @Override
    public void onSuccess(SeachBean seachBean) {
        List<SeachResultBean> result = seachBean.getResult();
        adapter = new SeachAdapter(result,getActivity());
        StaggeredGridLayoutManager s = new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
        re.setLayoutManager(s);
        re.setAdapter(adapter);
    }

    @Override
    public void onError(String error) {

    }
}
