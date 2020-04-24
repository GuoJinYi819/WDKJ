package com.wd.tech.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.wd.tech.R;
import com.wd.tech.adapter.gjyadapter.DialogRecordAdapter;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.base.BasePresenter;
import com.wd.tech.bean.gjybean.DialogueRecordBean;
import com.wd.tech.bean.gjybean.NewsBean;
import com.wd.tech.bean.gjybean.SendMessageBean;
import com.wd.tech.mvp.gjymvp.sendnews.ISendNewsContract;
import com.wd.tech.mvp.gjymvp.sendnews.SendNewsPresenter;
import com.wd.tech.util.RsaCoder;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class SendNewsActivity extends BaseActivity<SendNewsPresenter> implements ISendNewsContract.ISendNewsView {

    private android.widget.ImageView mIvBlack;
    private android.widget.TextView mTvName;
    private android.widget.ImageView mIvSetting;
    private android.widget.EditText mEditContent;
    private android.widget.TextView mTvSend;
    private androidx.recyclerview.widget.RecyclerView mRecyclerNews;
    private DialogRecordAdapter dialogRecordAdapter;

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
                try {
                    Intent intent = getIntent();
                    int friend = intent.getIntExtra("friend", -1);
                    String name = intent.getStringExtra("name");

                    String c = RsaCoder.encryptByPublicKey(content);
                    HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put("receiveUid",friend+"");
                    hashMap.put("content",c);
                    presenter.sendMessage(hashMap);

                    int help = intent.getIntExtra("help", -1);
                    if(help==1){

                    }else if (help==-1){
                        String headPic = intent.getStringExtra("headPic");
                        //发送消息
                        Calendar instance = Calendar.getInstance();
                        //获取时
                        int hour = instance.get(Calendar.HOUR_OF_DAY);
                        int minute = instance.get(Calendar.MINUTE);
                        String time = hour+":"+minute;
                        NewsBean newsBean = new NewsBean(headPic, name, friend + "",time);

                        EventBus.getDefault().postSticky(newsBean);

                    }

                    mEditContent.setText("");


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        mEditContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialogRecordAdapter != null) {
                    List<DialogueRecordBean.ResultBean> list = dialogRecordAdapter.getList();
                    // if (result.size()>7) {
                    mRecyclerNews.scrollToPosition(list.size()-1);
                    //}
                }
            }
        });
       mEditContent.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence s, int start, int count, int after) {
               if (dialogRecordAdapter != null) {
                   List<DialogueRecordBean.ResultBean> list = dialogRecordAdapter.getList();
                   // if (result.size()>7) {
                   mRecyclerNews.scrollToPosition(list.size()-1);
                   //}
               }
           }

           @Override
           public void onTextChanged(CharSequence s, int start, int before, int count) {

           }

           @Override
           public void afterTextChanged(Editable s) {

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
            Collections.reverse(result);
            dialogRecordAdapter = new DialogRecordAdapter(SendNewsActivity.this, result);
            mRecyclerNews.setAdapter(dialogRecordAdapter);
            if (result.size()>7) {
                mRecyclerNews.scrollToPosition(result.size()-1);
            }
        }
    }

    @Override
    public void sendMessage(SendMessageBean bean) {
        String message = bean.getMessage();
        if (message.equals("发送成功")) {
            Intent intent = getIntent();
            int friend = intent.getIntExtra("friend", -1);
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("friendUid",friend+"");
                hashMap.put("page","1");
                hashMap.put("count","15");
                presenter.getDialogRecordData(hashMap);

        }
    }
}
