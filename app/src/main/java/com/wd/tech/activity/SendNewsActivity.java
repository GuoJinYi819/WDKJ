package com.wd.tech.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.wd.tech.R;
import com.wd.tech.adapter.gjyadapter.DialogRecordAdapter;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.base.BasePresenter;
import com.wd.tech.bean.gjybean.DialogueRecordBean;
import com.wd.tech.bean.gjybean.NewsBean;
import com.wd.tech.bean.gjybean.QueryFriendBean;
import com.wd.tech.bean.gjybean.QueryGroupBean;
import com.wd.tech.bean.gjybean.SendMessageBean;
import com.wd.tech.mvp.gjymvp.sendnews.ISendNewsContract;
import com.wd.tech.mvp.gjymvp.sendnews.SendNewsPresenter;
import com.wd.tech.net.ApiService;
import com.wd.tech.net.RetrofitUtil;
import com.wd.tech.util.RsaCoder;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

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
                //查询好友信息
                Intent intent = getIntent();
                int groupId = intent.getIntExtra("groupId", -1);
                int friend = intent.getIntExtra("friend", -1);
                RetrofitUtil instance = RetrofitUtil.getInstance();
                ApiService apiService = instance.createService();
                Observable<QueryFriendBean> observable = apiService.queryFriend(friend);
                observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<QueryFriendBean>() {
                            @Override
                            public void accept(QueryFriendBean bean) throws Exception {
                                String message = bean.getMessage();
                                if (message.equals("查询成功")) {
                                    Intent it = new Intent(SendNewsActivity.this, ChatSettingActivity.class);
                                    QueryFriendBean.ResultBean result = bean.getResult();
                                    String headPic = result.getHeadPic();
                                    it.putExtra("headPic",headPic);
                                    String nickName = result.getNickName();
                                    it.putExtra("nickName",nickName);
                                    it.putExtra("groupId",groupId);
                                    it.putExtra("friend",friend);
                                    startActivityForResult(it,100);

                                }
                            }
                        });


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

        mEditContent.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect rect = new Rect();
                SendNewsActivity.this.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);//获取当前界面可视部分
                int screenHeight = SendNewsActivity.this.getWindow().getDecorView().getRootView().getHeight();//获取屏幕高度
                int heiDifference = screenHeight - rect.bottom;//获取键盘高度，键盘没有弹出时，高度为0，键盘弹出时，高度为正数
                if (heiDifference == 0) {
                    //todo:键盘没有弹出时
                } else {
                    //todo：键盘弹出时
                    if (dialogRecordAdapter != null) {
                        List<DialogueRecordBean.ResultBean> list = dialogRecordAdapter.getList();
                        mRecyclerNews.scrollToPosition(list.size()-1);

                    }
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==100&&resultCode==100){
            finish();
        }
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
