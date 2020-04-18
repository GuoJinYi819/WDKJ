package com.wd.tech.adapter.wyadapter;

import com.wd.tech.fragment.wyfragment.Frag_information;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * @author 王阳
 * Class :1708A
 * @description:
 * @date :2020/4/18 12:02
 * @classname :HomeFragmentAdapter
 */
public class HomeFragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments=new ArrayList<>();
    private List<String> strings=new ArrayList<>();

    public HomeFragmentAdapter(FragmentManager fm) {
        super(fm);
        strings.add("资讯");
        strings.add("消息");
        strings.add("社区");
        fragments.add(new Frag_information());
        fragments.add(new Frag_information());
        fragments.add(new Frag_information());
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return strings.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
