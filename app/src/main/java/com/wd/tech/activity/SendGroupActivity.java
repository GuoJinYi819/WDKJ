package com.wd.tech.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.wd.tech.R;
import com.wd.tech.adapter.gjyadapter.SendGroupAdapter;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.base.BasePresenter;
import com.wd.tech.bean.gjybean.QueryGroupBean;
import com.wd.tech.bean.gjybean.SendGroupBean;
import com.wd.tech.mvp.gjymvp.sendgroup.ISendGroupContract;
import com.wd.tech.mvp.gjymvp.sendgroup.SendGroupPresenter;
import com.wd.tech.util.RsaCoder;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

//发送群消息
public class SendGroupActivity extends BaseActivity<SendGroupPresenter> implements ISendGroupContract.ISendGroupView {

    private android.widget.ImageView mIvBlack;
    private android.widget.TextView mTvName;
    private android.widget.ImageView mIvSetting;
    private androidx.recyclerview.widget.RecyclerView mRecyclerGroup;
    private android.widget.EditText mEditContent;
    private android.widget.TextView mTvSend;
    private SendGroupAdapter sendGroupAdapter;

    @Override
    public int initLayout() {
        return R.layout.activity_send_group;
    }

    @Override
    public void initView() {

        mIvBlack = findViewById(R.id.ivBlack);
        mTvName = findViewById(R.id.tvName);
        mIvSetting = findViewById(R.id.ivSetting);
        mRecyclerGroup = findViewById(R.id.recyclerGroup);
        mEditContent = findViewById(R.id.editContent);
        mTvSend = findViewById(R.id.tvSend);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SendGroupActivity.this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        mRecyclerGroup.setLayoutManager(linearLayoutManager);

    }

    @Override
    public void initListener() {
        mIvBlack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mTvSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //发送数据
                String content = mEditContent.getText().toString();
                try {
                    String text = RsaCoder.encryptByPublicKey(content);
                    Intent intent = getIntent();
                    int groupId = intent.getIntExtra("groupId", -1);
                    HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put("content",text);
                    hashMap.put("groupId",groupId+"");
                    presenter.sendGroup(hashMap);
                    mEditContent.setText("");

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        mEditContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sendGroupAdapter != null) {
                    List<QueryGroupBean.ResultBean> list = sendGroupAdapter.getList();
                    mRecyclerGroup.scrollToPosition(list.size()-1);
                }
            }
        });

    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        int groupId = intent.getIntExtra("groupId", -1);
        String groupName = intent.getStringExtra("groupName");
        mTvName.setText(groupName);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("groupId",groupId+"");
        hashMap.put("page","1");
        hashMap.put("count","15");
        presenter.getQueryGroup(hashMap);
    }

    @Override
    public SendGroupPresenter initPresenter() {
        return new SendGroupPresenter();
    }

    @Override
    public void onQueryGroupSuccess(QueryGroupBean bean) {
        List<QueryGroupBean.ResultBean> result = bean.getResult();
        if (result != null) {
            //倒叙
            Collections.reverse(result);
            sendGroupAdapter = new SendGroupAdapter(SendGroupActivity.this, result);
            mRecyclerGroup.setAdapter(sendGroupAdapter);
            mRecyclerGroup.scrollToPosition(result.size()-1);
        }
    }

    @Override
    public void onSendGroupSuccess(SendGroupBean bean) {
        String message = bean.getMessage();
        boolean b = message.equals("发送成功");
        if (b) {
            Intent intent = getIntent();
            int groupId = intent.getIntExtra("groupId", -1);

            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("groupId",groupId+"");
            hashMap.put("page","1");
            hashMap.put("count","15");
            presenter.getQueryGroup(hashMap);
        }
    }
}
