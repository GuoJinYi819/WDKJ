package com.wd.tech.fragment.qzjfragment;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.bumptech.glide.Glide;
import com.scwang.smartrefresh.header.DropBoxHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.stx.xhb.xbanner.XBanner;
import com.wd.tech.App;
import com.wd.tech.R;
import com.wd.tech.activity.ConsultaActivity;
import com.wd.tech.adapter.qzjadapter.ConListAdapter;
import com.wd.tech.base.BaseFragment;
import com.wd.tech.bean.qzjbean.consultationlist.ConListBean;
import com.wd.tech.bean.qzjbean.consultationlist.ConResultBean;
import com.wd.tech.bean.qzjbean.xbanner.XbBean;
import com.wd.tech.bean.qzjbean.xbanner.XbResultBean;
import com.wd.tech.mvp.qzjmvp.xbannermvp.BannerPresenterImpl;
import com.wd.tech.mvp.qzjmvp.xbannermvp.XbConnter;
import com.wd.tech.net.ApiService;
import com.wd.tech.net.RetrofitUtil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/4/18 0018 22:54
 * @Description: 用途：咨询
 */
public class ConFragment extends BaseFragment<BannerPresenterImpl> implements XbConnter.IbannerView {
    private XBanner xb;
    private List<String> img;
    private List<String> title;
    private ConListAdapter adapter;
    private RecyclerView re;
    private SmartRefreshLayout smartRefresh;

    @Override
    public int initLayout() {
        return R.layout.fragment_consultaion;
    }

    @Override
    public void initView() {
        xb = view.findViewById(R.id.xb);
        re = view.findViewById(R.id.rere);
        smartRefresh = view.findViewById(R.id.SmartRefresh);

        smartRefresh.setRefreshHeader(new DropBoxHeader(App.context));
        smartRefresh.setRefreshFooter(new BallPulseFooter(App.context).setSpinnerStyle( SpinnerStyle.Scale));

    }

    @Override
    public void initListener() {

        smartRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                smartRefresh.finishRefresh(true);
            }
        });

        smartRefresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMore(true);
            }
        });

        RetrofitUtil instance = RetrofitUtil.getInstance();
        ApiService service = instance.createService();
        Observable<ConListBean> listData = service.getListData(1, 1, 10);
        listData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ConListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }
                    @Override
                    public void onNext(ConListBean value) {
                        List<ConResultBean> result = value.getResult();
                        adapter = new ConListAdapter(result,getActivity());
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
                        re.setLayoutManager(linearLayoutManager);
                        re.setAdapter(adapter);
                        adapter.setAdapterCallBack(new ConListAdapter.AdapterCallBack() {

                            @Override
                            public void onGiveId(int id, int ismoney) {
                                Intent intent = new Intent(getActivity(), ConsultaActivity.class);
                                intent.putExtra("id",id);
                                intent.putExtra("ismoney",ismoney);
                                startActivity(intent);
                            }
                        });
                    }
                    @Override
                    public void onError(Throwable e) {
                        String message = e.getMessage();
                        Log.d("XXX",message);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Override
    public void initData() {
        //调用P层
        presenter.getData();
    }

    @Override
    public BannerPresenterImpl initPresenter() {
        return new BannerPresenterImpl();
    }

    @Override
    public void onSuccess(XbBean xbBean) {
        img = new ArrayList<>();
        title = new ArrayList<>();
        List<XbResultBean> result = xbBean.getResult();
        for (int i = 0; i < result.size(); i++) {
            img.add(result.get(i).getImageUrl());
            title.add(result.get(i).getTitle());
        }
        xb.setData(img, title);

        xb.setmAdapter(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {

                Glide.with(getActivity()).load(img.get(position)).into((ImageView) view);
            }
        });
    }


    @Override
    public void onError(String error) {

    }
}
