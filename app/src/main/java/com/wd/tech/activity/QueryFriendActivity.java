package com.wd.tech.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.base.BasePresenter;
import com.wd.tech.bean.gjybean.QueryFriendBean;
import com.wd.tech.mvp.gjymvp.queryfriend.IQueryFriendContract;
import com.wd.tech.mvp.gjymvp.queryfriend.QueryFriendPresenter;

//查询好友个人资料
public class QueryFriendActivity extends BaseActivity<QueryFriendPresenter> implements IQueryFriendContract.IQueryFriendView {

    private android.widget.TextView mTvEdit;
    private android.widget.ImageView mIvHeadPic;
    private android.widget.TextView mTvSignature;
    private android.widget.TextView mTvSexTime;
    private android.widget.TextView mTvphone,tvName;
    private android.widget.TextView mTvEmail;
    private android.widget.Button mBtnSend;

    @Override
    public int initLayout() {
        return R.layout.activity_query_friend;
    }

    @Override
    public void initView() {
        mTvEdit = findViewById(R.id.tvEdit);
        mIvHeadPic = findViewById(R.id.ivHeadPic);
        mTvSignature = findViewById(R.id.tvSignature);
        mTvSexTime = findViewById(R.id.tvSexTime);
        mTvphone = findViewById(R.id.tvphone);
        mTvEmail = findViewById(R.id.tvEmail);
        mBtnSend = findViewById(R.id.btnSend);
        tvName = new TextView(QueryFriendActivity.this);
    }

    @Override
    public void initListener() {
        //编辑
        mTvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转编辑页面

            }
        });
        //发送信息
        mBtnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                int friend = intent.getIntExtra("friend", -1);
                Intent it = new Intent(QueryFriendActivity.this, SendNewsActivity.class);
                it.putExtra("friend",friend);
                String name = tvName.getText().toString();
                it.putExtra("name",name);
                startActivity(it);
            }
        });
    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        int friend = intent.getIntExtra("friend", -1);
        if(friend==-1){
            finish();
        }else {
            presenter.queryFriend(friend);
        }
    }

    @Override
    public QueryFriendPresenter initPresenter() {
        return new QueryFriendPresenter();
    }

    @Override
    public void onSuccess(QueryFriendBean bean) {
        QueryFriendBean.ResultBean result = bean.getResult();
        String headPic = result.getHeadPic();
        Glide.with(QueryFriendActivity.this).load(headPic)
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(mIvHeadPic);
        String signature = result.getSignature();
        if (!TextUtils.isEmpty(signature)) {
            mTvSignature.setText(signature);
        }else {
            mTvSignature.setText("没有签名信息");
        }
        int sex = result.getSex();
        if (sex==1) {
            mTvSexTime.setText("男 (2020-08-19)");
        }else {
            mTvSexTime.setText("女 (2020-04-28)");
        }

        String phone = result.getPhone();
        String p1 = phone.substring(0, 3);
        String p2 = phone.substring(7, phone.length());
        mTvphone.setText(p1+"****"+p2);

        mTvEmail.setText("暂未开通邮箱");

        String nickName = result.getNickName();
        tvName.setText(nickName);

    }

    @Override
    public void onFailed(String error) {

    }
}
