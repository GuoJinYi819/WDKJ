package com.wd.tech.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;

import android.os.Bundle;
import android.view.View;

import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.base.BasePresenter;
import com.wd.tech.fragment.gjyfragment.adduser.AddGroupFragment;
import com.wd.tech.fragment.gjyfragment.adduser.AddPersonFragment;

import java.util.ArrayList;
import java.util.List;

public class AddUserActivity extends BaseActivity {

    private android.widget.ImageView mIvBack;
    private com.google.android.material.tabs.TabLayout mAddTabLayout;
    private androidx.viewpager.widget.ViewPager mAddViewPager;
    private List<Fragment> list = new ArrayList<>();
    @Override
    public int initLayout() {
        return R.layout.activity_add_user;
    }

    @Override
    public void initView() {

        mIvBack = findViewById(R.id.ivBack);
        mAddTabLayout = findViewById(R.id.addTabLayout);
        mAddViewPager = findViewById(R.id.addViewPager);

        String [] str = {"找人","找群"};
        list.add(new AddPersonFragment());
        list.add(new AddGroupFragment());

        mAddViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return str[position];
            }
        });
        mAddTabLayout.setupWithViewPager(mAddViewPager);
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

    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }
}
