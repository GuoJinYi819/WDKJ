package com.wd.tech.fragment.qzjfragment;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.stx.xhb.xbanner.XBanner;
import com.wd.tech.R;
import com.wd.tech.base.BaseFragment;
import com.wd.tech.base.BasePresenter;
import com.wd.tech.bean.qzjbean.xbanner.XbBean;
import com.wd.tech.bean.qzjbean.xbanner.XbResultBean;
import com.wd.tech.mvp.qzjmvp.xbannermvp.BannerMoudleImpl;
import com.wd.tech.mvp.qzjmvp.xbannermvp.BannerPresenterImpl;
import com.wd.tech.mvp.qzjmvp.xbannermvp.XbConnter;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/4/18 0018 22:54
 * @Description: 用途：咨询
 */
public class ConsultationFragment extends BaseFragment<BannerPresenterImpl> implements XbConnter.IbannerView {
    private XBanner xb;
    private List<String> img;
    private List<String> title;

    @Override
    public int initLayout() {
        return R.layout.fragment_consultaion;
    }

    @Override
    public void initView() {
        xb = view.findViewById(R.id.xb);
    }

    @Override
    public void initListener() {
    }

    @Override
    public void initData() {
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
        xb.setData(img,title);

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
