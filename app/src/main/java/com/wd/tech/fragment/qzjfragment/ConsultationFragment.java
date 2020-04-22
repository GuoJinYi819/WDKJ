package com.wd.tech.fragment.qzjfragment;

import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.viewpager.widget.ViewPager;

import com.wd.tech.R;
import com.wd.tech.adapter.qzjadapter.ConVpAdapter;
import com.wd.tech.base.BaseFragment;
import com.wd.tech.bean.qzjbean.xbanner.XbBean;
import com.wd.tech.mvp.qzjmvp.xbannermvp.BannerPresenterImpl;
import com.wd.tech.mvp.qzjmvp.xbannermvp.XbConnter;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/4/18 0018 22:54
 * @Description: 用途：咨询
 */
public class ConsultationFragment extends BaseFragment<BannerPresenterImpl> implements XbConnter.IbannerView {

    private ViewPager vp;
    private RadioGroup rg;
    private ConVpAdapter adapter;
    @Override
    public int initLayout() {
        return R.layout.fragment_con;
    }

    @Override
    public void initView() {
        vp = view.findViewById(R.id.vp);
        rg = view.findViewById(R.id.rg);
    }

    @Override
    public void initListener() {
        adapter = new ConVpAdapter(getChildFragmentManager());
        vp.setAdapter(adapter);
        vp.setCurrentItem(1);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.bu1:
                        vp.setCurrentItem(0);
                        break;
                    case R.id.bu2:
                        vp.setCurrentItem(1);
                        break;
                    case R.id.bu3:
                        vp.setCurrentItem(2);
                        break;
                }
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public BannerPresenterImpl initPresenter() {
        return null;
    }

    @Override
    public void onSuccess(XbBean xbBean) {

    }


    @Override
    public void onError(String error) {

    }
}
