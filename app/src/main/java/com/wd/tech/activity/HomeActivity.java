package com.wd.tech.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.material.tabs.TabLayout;
import com.wd.tech.R;
import com.wd.tech.adapter.wyadapter.HomeFragmentAdapter;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.base.BasePresenter;
import com.wd.tech.bean.wybean.beansign.SignBean;
import com.wd.tech.mvp.wymvp.mvpsign.ISignContract;
import com.wd.tech.mvp.wymvp.mvpsign.SignPresenterImpl;
import com.wd.tech.net.SpUtil;

public class HomeActivity extends BaseActivity<SignPresenterImpl> implements ISignContract.ISignView {
    private androidx.viewpager.widget.ViewPager viewPager;
    private com.google.android.material.tabs.TabLayout tab;
    //选中数组
    private int [] selection = {R.mipmap.common_tab_informatiion_s_xhdpi,R.mipmap.common_tab_message_s_xhdpi,R.mipmap.common_tab_community_s_xhdpi};
    //未选中数组
    private int [] unchecked = {R.mipmap.common_tab_information_n_xhdpi,R.mipmap.common_tab_message_n_xhdpi,R.mipmap.common_tab_community_n_xhdpi};
    private com.facebook.drawee.view.SimpleDraweeView imgMyTopWy;
    private android.widget.TextView tvMyNameWy;
    private android.widget.TextView tvMySignatureWy;
    private android.widget.LinearLayout linearSignWy;
    private LinearLayout linearCollectionWy;

    @Override
    public int initLayout() {
        return R.layout.activity_home;
    }

    private static final String TAG = "HomeActivity";
    @Override
    public void initView() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tab = (TabLayout) findViewById(R.id.tab);
        //头像  + 名称  +签名
        imgMyTopWy = (SimpleDraweeView) findViewById(R.id.imgMyTopWy);
        tvMyNameWy = (TextView) findViewById(R.id.tvMyNameWy);
        tvMySignatureWy = (TextView) findViewById(R.id.tvMySignatureWy);
        //签到的布局
        linearSignWy = (LinearLayout) findViewById(R.id.linearSignWy);
        //收藏的
        linearCollectionWy = (LinearLayout) findViewById(R.id.linearCollectionWy);
        //取缓  设置头像  名称   签名
        SpUtil instance = SpUtil.getInstance();
        String headPic = instance.getSpString("headPic");
        String nickName = instance.getSpString("nickName");
        String signature = instance.getSpString("signature");
        //判断是否为空
        if(!TextUtils.isEmpty(headPic)){
            imgMyTopWy.setImageURI(headPic);
            tvMyNameWy.setText(nickName);
            tvMySignatureWy.setText(signature);
        }
        //适配器
        HomeFragmentAdapter homeFragmentAdapter = new HomeFragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(homeFragmentAdapter);
        viewPager.setOffscreenPageLimit(3);

        //初始化TabLayout
        TabLayout.Tab t = tab.newTab();
        t.setText("咨询");
        t.setIcon(selection[0]);
        tab.addTab(t);

        t = tab.newTab();
        t.setText("消息");
        t.setIcon(unchecked[1]);
        tab.addTab(t);

        t = tab.newTab();
        t.setText("社区");
        t.setIcon(unchecked[2]);
        tab.addTab(t);

        viewPager.setCurrentItem(0);
    }
    @Override
    public void initListener() {
        //设置首页联动监听

        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab t) {
                //选中
                int position = t.getPosition();
                viewPager.setCurrentItem(position);

                int tabCount = tab.getTabCount();
                for (int i = 0; i < tabCount; i++) {
                    TabLayout.Tab tabAt = tab.getTabAt(i);
                    //选中
                    if(i==position){
                        tabAt.setIcon(selection[i]);
                    }else {
                        tabAt.setIcon(unchecked[i]);
                    }
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //设置TabLayout选中标签
                tab.getTabAt(position).select();

                int tabCount = tab.getTabCount();
                for (int i = 0; i < tabCount; i++) {
                    TabLayout.Tab tabAt = tab.getTabAt(i);
                    //选中
                    if(i==position){
                        tabAt.setIcon(selection[i]);
                    }else {
                        tabAt.setIcon(unchecked[i]);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //点击   签到
        linearSignWy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.getSign();
            }
        });
        //点击   跳转 收藏页
        linearCollectionWy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳
                Intent intent = new Intent(HomeActivity.this, CollectionActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public void initData() {
    }
    @Override
    public SignPresenterImpl initPresenter() {
        return new SignPresenterImpl();
    }
    @Override
    public void onSuccess(SignBean sign) {
        String message = sign.getMessage();
        Toast.makeText(HomeActivity.this,message,Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onError(String error) {
    }
}
