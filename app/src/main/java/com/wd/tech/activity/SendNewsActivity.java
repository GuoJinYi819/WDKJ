package com.wd.tech.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.wd.tech.R;
import com.wd.tech.adapter.gjyadapter.DialogRecordAdapter;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.base.BasePresenter;
import com.wd.tech.bean.gjybean.DialogueRecordBean;
import com.wd.tech.bean.gjybean.SendMessageBean;
import com.wd.tech.mvp.gjymvp.sendnews.ISendNewsContract;
import com.wd.tech.mvp.gjymvp.sendnews.SendNewsPresenter;

import java.util.HashMap;
import java.util.List;

public class SendNewsActivity extends BaseActivity<SendNewsPresenter> implements ISendNewsContract.ISendNewsView {

    private android.widget.ImageView mIvBlack;
    private android.widget.TextView mTvName;
    private android.widget.ImageView mIvSetting;
    private android.widget.EditText mEditContent;
    private android.widget.TextView mTvSend;
    private androidx.recyclerview.widget.RecyclerView mRecyclerNews;

    @Override
    public int initLayout() {
        return R.layout.activity_send_news;
    }

    @Override
    public void initView() {

        mIvBlack = findViewById(R.id.ivBlack);
        mTvName = findViewById(R.id.tvName);
        mIvSetting = findViewById(R.id.ivSetting);
        mEditContent = findViewById(R.id.editContent);
        mTvSend = findViewById(R.id.tvSend);
        mRecyclerNews = findViewById(R.id.recyclerNews);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SendNewsActivity.this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        mRecyclerNews.setLayoutManager(linearLayoutManager);

    }

    @Override
    public void initListener() {
        //关闭当前页面
        mIvBlack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mIvSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //设置
            }
        });
        mTvSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //发送消息
                String content = mEditContent.getText().toString();
                Toast.makeText(SendNewsActivity.this, ""+content, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        mTvName.setText(name);
        int friend = intent.getIntExtra("friend", -1);
        if (friend==-1) {
            finish();
        }else {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("friendUid",friend+"");
            hashMap.put("page","1");
            hashMap.put("count","15");
            presenter.getDialogRecordData(hashMap);
        }
    }

    @Override
    public SendNewsPresenter initPresenter() {
        return new SendNewsPresenter();
    }

    @Override
    public void getDialogRecordSuccess(DialogueRecordBean bean) {
        List<DialogueRecordBean.ResultBean> result = bean.getResult();
        if (result != null) {
            DialogRecordAdapter dialogRecordAdapter = new DialogRecordAdapter(SendNewsActivity.this, result);
            mRecyclerNews.setAdapter(dialogRecordAdapter);
        }
    }

    @Override
    public void sendMessage(SendMessageBean bean) {

    }
}
