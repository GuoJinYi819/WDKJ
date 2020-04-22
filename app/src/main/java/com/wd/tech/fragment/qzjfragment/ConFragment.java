package com.wd.tech.fragment.qzjfragment;

import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.bumptech.glide.Glide;
import com.stx.xhb.xbanner.XBanner;
import com.wd.tech.R;
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

    @Override
    public int initLayout() {
        return R.layout.fragment_consultaion;
    }

    @Override
    public void initView() {
        xb = view.findViewById(R.id.xb);
        re = view.findViewById(R.id.re);
    }

    @Override
    public void initListener() {
    }

    @Override
    public void initData() {
        presenter.getData();
        RetrofitUtil instance = RetrofitUtil.getInstance();
        ApiService service = instance.createService();
        Observable<XbBean> commentListData = service.getListData(1, 1, 7);
        commentListData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<XbBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(XbBean conListBean) {
                        List<XbResultBean> result = conListBean.getResult();
                        adapter = new ConListAdapter(result, getActivity());
                        StaggeredGridLayoutManager s = new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
                        re.setLayoutManager(s);
                        re.setAdapter(adapter);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
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
