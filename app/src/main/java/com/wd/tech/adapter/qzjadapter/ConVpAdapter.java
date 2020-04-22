package com.wd.tech.adapter.qzjadapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.wd.tech.fragment.qzjfragment.ChannelFragment;
import com.wd.tech.fragment.qzjfragment.ConFragment;
import com.wd.tech.fragment.qzjfragment.ConSeachFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : Quzijun
 * @version 创建时间：2020/4/22 10:52
 * @Description: 用途：咨讯首页viewpager适配器
 */
public class ConVpAdapter extends FragmentPagerAdapter {
    private List<Fragment> list = new ArrayList<>();
    public ConVpAdapter(FragmentManager fm) {
        super(fm);
        list.add(new ChannelFragment());
        list.add(new ConFragment());
        list.add(new ConSeachFragment());
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
