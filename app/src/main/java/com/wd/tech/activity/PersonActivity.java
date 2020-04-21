package com.wd.tech.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.tech.R;
import com.wd.tech.adapter.wyadapter.RecyclerPersonAdapter;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.wybean.Event;
import com.wd.tech.bean.wybean.beanperson.CommunityUserPostVoListBean;
import com.wd.tech.bean.wybean.beanperson.CommunityUserVoBean;
import com.wd.tech.bean.wybean.beanperson.PersonBean;
import com.wd.tech.bean.wybean.beanperson.ResultBean;
import com.wd.tech.mvp.wymvp.mvpperson.IPersonContract;
import com.wd.tech.mvp.wymvp.mvpperson.PersonPresenterImpl;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

public class PersonActivity extends BaseActivity<PersonPresenterImpl> implements IPersonContract.IPersonView {
    private android.widget.ImageView imgShowPersonWy;
    private com.facebook.drawee.view.SimpleDraweeView imgShowPersonHeadWy;
    private android.widget.ImageView imgAddFriendWy;
    private androidx.recyclerview.widget.RecyclerView recyclerPersonWy;
    private int id;
    private android.widget.TextView tvPerNameWy;
    private android.widget.TextView tvPerJjWy;

    //个人的评论页面展示
    @Override
    public int initLayout() {
        return R.layout.activity_person;
    }
    @Override
    public void initView() {
        imgShowPersonWy = (ImageView) findViewById(R.id.imgShowPersonWy);
        imgShowPersonHeadWy = (SimpleDraweeView) findViewById(R.id.imgShowPersonHeadWy);
        imgAddFriendWy = (ImageView) findViewById(R.id.imgAddFriendWy);
        tvPerNameWy = (TextView) findViewById(R.id.tvPerNameWy);
        tvPerJjWy = (TextView) findViewById(R.id.tvPerJjWy);
        recyclerPersonWy = (RecyclerView) findViewById(R.id.recyclerPersonWy);
        //布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(PersonActivity.this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerPersonWy.setLayoutManager(linearLayoutManager);
        //订阅
        EventBus.getDefault().register(this);
    }
    @Override
    public void initListener() {
    }
    @Override
    public void initData() {
    }
    //接收
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void onEvent(Event event){
        id = event.getId();
        presenter.getPerson(1383,1,10);
    }
    @Override
    public PersonPresenterImpl initPresenter() {
        return new PersonPresenterImpl();
    }
    @Override
    public void onSuccess(List<ResultBean> result) {
        //成功
        Log.d("===", "onSuccess: "+result);
        if(result!=null){
            ResultBean resultBean = result.get(0);
            CommunityUserVoBean communityUserVo = resultBean.getCommunityUserVo();
            String headPic = communityUserVo.getHeadPic();
            String nickName = communityUserVo.getNickName();
            String signature = communityUserVo.getSignature();
            //设置
            Glide.with(PersonActivity.this).load(headPic).into(imgShowPersonWy);
            imgShowPersonHeadWy.setImageURI(headPic);
            tvPerNameWy.setText(nickName);
            tvPerJjWy.setText(signature);
            //适配器
            List<CommunityUserPostVoListBean> communityUserPostVoList = resultBean.getCommunityUserPostVoList();
            RecyclerPersonAdapter recyclerPersonAdapter = new RecyclerPersonAdapter(communityUserPostVoList, PersonActivity.this);
            recyclerPersonWy.setAdapter(recyclerPersonAdapter);
        }
    }
    @Override
    public void onError(String msg) {
    }
    //销毁
    @Override
    protected void onDestroy() {
        super.onDestroy();
        boolean registered = EventBus.getDefault().isRegistered(this);
        if(registered){
            EventBus.getDefault().unregister(this);
        }
    }
}
