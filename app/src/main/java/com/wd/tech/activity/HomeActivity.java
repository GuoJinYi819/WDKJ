package com.wd.tech.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;

import com.google.android.material.tabs.TabLayout;
import com.wd.tech.R;
import com.wd.tech.adapter.wyadapter.HomeFragmentAdapter;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.base.BasePresenter;

public class HomeActivity extends BaseActivity {
    private androidx.viewpager.widget.ViewPager viewPager;
    private com.google.android.material.tabs.TabLayout tab;
    //选中数组
    private int [] selection = {R.mipmap.common_tab_informatiion_s_xhdpi,R.mipmap.common_tab_message_s_xhdpi,R.mipmap.common_tab_community_s_xhdpi};
    //未选中数组
    private int [] unchecked = {R.mipmap.common_tab_information_n_xhdpi,R.mipmap.common_tab_message_n_xhdpi,R.mipmap.common_tab_community_n_xhdpi};
    @Override
    public int initLayout() {
        return R.layout.activity_home;
    }

    private static final String TAG = "HomeActivity";
    @Override
    public void initView() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tab = (TabLayout) findViewById(R.id.tab);
        //适配器
        HomeFragmentAdapter homeFragmentAdapter = new HomeFragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(homeFragmentAdapter);

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
                tab.setScrollPosition(position,0,false);
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
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
    @Override
    public void initData() {
    }
    @Override
    public BasePresenter initPresenter() {
        return null;
    }
}
