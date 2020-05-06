package com.wd.tech.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.material.tabs.TabLayout;
import com.wd.tech.R;
import com.wd.tech.adapter.wyadapter.HomeFragmentAdapter;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.base.BasePresenter;
import com.wd.tech.bean.wybean.beandotask.DoTaskBean;
import com.wd.tech.bean.wybean.beansign.SignBean;
import com.wd.tech.fragment.wyfragment.signcalendar.SignCalendarReq;
import com.wd.tech.mvp.wymvp.mvpsign.ISignContract;
import com.wd.tech.mvp.wymvp.mvpsign.SignPresenterImpl;
import com.wd.tech.net.EncryptionUtil;
import com.wd.tech.net.SpUtil;

import java.io.Serializable;

public class HomeActivity extends BaseActivity{
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
    private LinearLayout linearFollowWy;
    private LinearLayout linearTaskWy;
    private LinearLayout linearScoreWy;
    private LinearLayout linearPostWy;
    private LinearLayout linearNoticeWy;
    private LinearLayout linearSetupWy;
    private android.widget.ImageView imgVIPWy;
    private LinearLayout linearLeft;
    private LinearLayout linearLeftChild;

    private SignCalendarReq signCalendarReq;
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
        //关注的
        linearFollowWy = (LinearLayout) findViewById(R.id.linearFollowWy);
        //我的帖子
        linearPostWy = (LinearLayout) findViewById(R.id.linearPostWy);
        //通知
        linearNoticeWy = (LinearLayout) findViewById(R.id.linearNoticeWy);
        //积分
        linearScoreWy = (LinearLayout) findViewById(R.id.linearScoreWy);
        //任务的
        linearTaskWy = (LinearLayout) findViewById(R.id.linearTaskWy);
        //设置页面的
        linearSetupWy = (LinearLayout) findViewById(R.id.linearSetupWy);
        //点击VIP图标   开通VIP
        imgVIPWy = (ImageView) findViewById(R.id.imgVIPWy);
        //取缓  设置头像  名称   签名
        SpUtil instance = SpUtil.getInstance();
        String headPic = instance.getSpString("headPic");
        String nickName = instance.getSpString("nickName");
        String signature = instance.getSpString("signature");

        linearLeft = findViewById(R.id.linearLeft);
        linearLeftChild = findViewById(R.id.linearLeftChild);


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

        //模拟请求后台返回初始化数据
        signCalendarReq = new SignCalendarReq();

        SignCalendarReq.StateBean state = new SignCalendarReq.StateBean();
        state.setCode(1);
        state.setMsg("成功");
        signCalendarReq.setState(state);

        SignCalendarReq.DataBean data = new SignCalendarReq.DataBean();
        data.setConSign(1);
        data.setIsSign(0);
        data.setSignDay("1,2");
        data.setUid("3347922");
        signCalendarReq.setData(data);
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
                //跳
                Intent intent = new Intent(HomeActivity.this, I8ShowSignCalendarActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("userInfos", (Serializable) signCalendarReq);
                intent.putExtras(bundle);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        //点击  VIP
        imgVIPWy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳
                Intent intent = new Intent(HomeActivity.this, MyVIPActivity.class);
                startActivity(intent);
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
        //点击  关注页面
        linearFollowWy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳
                Intent intent = new Intent(HomeActivity.this,FollowActivity.class);
                startActivity(intent);
            }
        });
        //点击  帖子页面
        linearPostWy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳
                Intent intent = new Intent(HomeActivity.this,MyPostActivity.class);
                startActivity(intent);
            }
        });
        //点击  通知页面
        linearNoticeWy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳
                Intent intent = new Intent(HomeActivity.this,MyNoticeActivity.class);
                startActivity(intent);
            }
        });
        //点击  积分
        linearScoreWy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳
                Intent intent = new Intent(HomeActivity.this,MyScoreActivity.class);
                startActivity(intent);
            }
        });
        //点击  任务
        linearTaskWy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳
                Intent intent = new Intent(HomeActivity.this,TaskActivity.class);
                startActivity(intent);
            }
        });
        //点击  设置页
        linearSetupWy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳
                Intent intent = new Intent(HomeActivity.this,SetupActivity.class);
                startActivity(intent);
            }
        });
        linearLeftChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    @Override
    public void initData() {
        Intent intent = getIntent();
        int help = intent.getIntExtra("help", -1);
        if(help==1){
            //显示
            linearLeftChild.setVisibility(View.VISIBLE);
            SpUtil instance = SpUtil.getInstance();
            instance.cloneUser();
        }
    }
    @Override
    public BasePresenter initPresenter() {
        return null;
    }
}
