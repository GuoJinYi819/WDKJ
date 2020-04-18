package com.wd.tech.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.wd.tech.R;
import com.wd.tech.adapter.wyadapter.HomeFragmentAdapter;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.base.BasePresenter;

public class HomeActivity extends BaseActivity {
    private androidx.viewpager.widget.ViewPager viewPager;
    private com.google.android.material.tabs.TabLayout tab;

    @Override
    public int initLayout() {
        return R.layout.activity_home;
    }
    @Override
    public void initView() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tab = (TabLayout) findViewById(R.id.tab);
        //适配器
        HomeFragmentAdapter homeFragmentAdapter = new HomeFragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(homeFragmentAdapter);
        tab.setupWithViewPager(viewPager);
    }
    @Override
    public void initListener() {
    }
    @Override
    public void initData() {
    }
    @Override
    public BasePresenter initPresenter() {
        return null;
    }
}
