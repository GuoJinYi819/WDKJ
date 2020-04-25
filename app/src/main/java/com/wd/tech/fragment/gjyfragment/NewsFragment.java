package com.wd.tech.fragment.gjyfragment;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.wd.tech.R;
import com.wd.tech.activity.AddUserActivity;
import com.wd.tech.base.BaseFragment;
import com.wd.tech.base.BasePresenter;
import com.wd.tech.fragment.gjyfragment.news.ContactsFragment;
import com.wd.tech.fragment.gjyfragment.news.NewFragment;

import java.util.ArrayList;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/4/18 0018 22:51
 * @Description: 用途：消息模块
 */
public class NewsFragment extends BaseFragment {
    private TextView mTvNews;
    private TextView mTvContacts;
    private ImageView mIvAdd;
    private ViewPager mNewsViewPager;
    private PopupWindow popupWindow;

    @Override
    public int initLayout() {
        return R.layout.fragment_news;
    }

    @Override
    public void initView() {
        mTvNews = view.findViewById(R.id.tvNews);
        mTvContacts = view.findViewById(R.id.tvContacts);
        mIvAdd = view.findViewById(R.id.ivAdd);
        mNewsViewPager = view.findViewById(R.id.news_viewPager);
        ArrayList<Fragment> list = new ArrayList<>();
        list.add(new NewFragment());
        list.add(new ContactsFragment());
        //设置消息-联系人 fragment
        mNewsViewPager.setOffscreenPageLimit(2);
        mNewsViewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });

    }

    @Override
    public void initListener() {
        //设置联动效果
        //消息
        mTvNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //设置viewPager选中
                mNewsViewPager.setCurrentItem(0);
                //设置选中背景
                mTvNews.setBackgroundResource(R.drawable.text_fillet_select);
                mTvContacts.setBackgroundResource(R.drawable.text_fillet_noselect);
            }
        });
        //联系人
        mTvContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //设置viewPager选中
                mNewsViewPager.setCurrentItem(1);
                //设置选中背景
                mTvContacts.setBackgroundResource(R.drawable.text_fillet_select);
                mTvNews.setBackgroundResource(R.drawable.text_fillet_noselect);
            }
        });
        //viewPager
        mNewsViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    mTvContacts.setBackgroundResource(R.drawable.text_fillet_noselect);
                    mTvNews.setBackgroundResource(R.drawable.text_fillet_select);
                } else if (position == 1) {
                    mTvContacts.setBackgroundResource(R.drawable.text_fillet_select);
                    mTvNews.setBackgroundResource(R.drawable.text_fillet_noselect);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        View inflate = View.inflate(getContext(), R.layout.item_popupwindow, null);
        ImageView mIvFriendPic;
        TextView mTvFriend;
        ImageView mIvGroupPic;
        TextView mTvGroup;
        mIvFriendPic = inflate.findViewById(R.id.ivFriendPic);
        mTvFriend = inflate.findViewById(R.id.tvFriend);
        mIvGroupPic = inflate.findViewById(R.id.ivGroupPic);
        mTvGroup = inflate.findViewById(R.id.tvGroup);

        //设置点击事件
        mIvFriendPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddUserActivity.class);
                startActivity(intent);
            }
        });
        mTvFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddUserActivity.class);
                startActivity(intent);
            }
        });
        mIvGroupPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "创建群聊", Toast.LENGTH_SHORT).show();
            }
        });
        mTvGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "创建群聊", Toast.LENGTH_SHORT).show();
            }
        });

        //测量当前View的宽高
        inflate.measure(0, 0);
        int measuredWidth = inflate.getMeasuredWidth();
        int measuredHeight = inflate.getMeasuredHeight();
        popupWindow = new PopupWindow(inflate, measuredWidth, measuredHeight);
        //设置背景  ---不设置 setOutsideTouchable不好使
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        //点击试图外 关闭popupWindow
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);

        //添加
        mIvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //显示
                popupWindow.showAsDropDown(mIvAdd);
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        popupWindow.dismiss();
    }

    @Override
    public void initData() {

    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }
}
