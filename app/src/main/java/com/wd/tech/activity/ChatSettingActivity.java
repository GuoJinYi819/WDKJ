package com.wd.tech.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.base.BasePresenter;
import com.wd.tech.bean.gjybean.DeleteChatBean;
import com.wd.tech.mvp.gjymvp.deletechat.DeleteChatPresenter;
import com.wd.tech.mvp.gjymvp.deletechat.IDeleteChatContract;
import com.wd.tech.net.ApiService;
import com.wd.tech.net.RetrofitUtil;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ChatSettingActivity extends BaseActivity<DeleteChatPresenter> implements IDeleteChatContract.IDeleteChatView {

    private android.widget.ImageView mIvBlac;
    private android.widget.ImageView mIvFriendPic;
    private android.widget.TextView mTvName;
    private android.widget.TextView mTvName2;
    private android.widget.RelativeLayout mRelativeFz;
    private android.widget.TextView mTvQurey;
    private android.widget.TextView mTvClose;
    private android.widget.Button mBtnDelete;

    @Override
    public int initLayout() {
        return R.layout.activity_chat_setting;
    }

    @Override
    public void initView() {

        mIvBlac = findViewById(R.id.ivBlac);
        mIvFriendPic = findViewById(R.id.ivFriendPic);
        mTvName = findViewById(R.id.tvName);
        mTvName2 = findViewById(R.id.tvName2);
        mRelativeFz = findViewById(R.id.relativeFz);
        mTvQurey = findViewById(R.id.tvQurey);
        mTvClose = findViewById(R.id.tvClose);
        mBtnDelete = findViewById(R.id.btnDelete);
    }

    @Override
    public void initListener() {
        mRelativeFz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                int groupId = intent.getIntExtra("groupId", -1);
                int friend = intent.getIntExtra("friend", -1);
                Intent it = new Intent(ChatSettingActivity.this, SelectGroupActivity.class);
                it.putExtra("groupId",groupId);
                it.putExtra("friend",friend);
                startActivity(it);
            }
        });
        mIvBlac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mTvQurey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ChatSettingActivity.this, "已跳转聊天记录页", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        mTvClose.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                //弹框
                View view = View.inflate(ChatSettingActivity.this, R.layout.pupup_closemessage, null);
                PopupWindow popupWindow = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT,
                        WindowManager.LayoutParams.WRAP_CONTENT);
                popupWindow.setBackgroundDrawable(new BitmapDrawable());
                popupWindow.setFocusable(true);
                popupWindow.setOutsideTouchable(true);

                //动画
                TranslateAnimation animation = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT, 0,
                        Animation.RELATIVE_TO_PARENT, 1, Animation.RELATIVE_TO_PARENT, 0);
                animation.setInterpolator(new AccelerateInterpolator());
                animation.setDuration(200);
                popupWindow.showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                view.startAnimation(animation);

                view.findViewById(R.id.tvQ).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //删除聊天记录
                        Intent intent = getIntent();
                        int friend = intent.getIntExtra("friend", -1);
                        presenter.deleteChat(friend);
                        popupWindow.dismiss();
                    }
                });
                view.findViewById(R.id.tvC).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
            }
        });
        mBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //弹框
                View view = View.inflate(ChatSettingActivity.this, R.layout.pupup_deletefriend, null);
                PopupWindow popupWindow = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT,
                        WindowManager.LayoutParams.WRAP_CONTENT);
                popupWindow.setBackgroundDrawable(new BitmapDrawable());
                popupWindow.setFocusable(true);
                popupWindow.setOutsideTouchable(true);

                //动画
                TranslateAnimation animation = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT, 0,
                        Animation.RELATIVE_TO_PARENT, 1, Animation.RELATIVE_TO_PARENT, 0);
                animation.setInterpolator(new AccelerateInterpolator());
                animation.setDuration(200);
                popupWindow.showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                view.startAnimation(animation);

                TextView tvText = view.findViewById(R.id.tvText);
                String text = mTvName.getText().toString();
                tvText.setText("将联系人（"+text+")删除，同时删除与联系人的聊天记录");

                view.findViewById(R.id.tvQ).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = getIntent();
                        int friend = intent.getIntExtra("friend", -1);
                        //删除聊天记录
                        presenter.deleteChat(friend);
                        //删除好友
                        RetrofitUtil instance = RetrofitUtil.getInstance();
                        ApiService apiService = instance.createService();
                        Observable<DeleteChatBean> observable = apiService.deleteFriend(friend);
                        observable.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Consumer<DeleteChatBean>() {
                                    @Override
                                    public void accept(DeleteChatBean bean) throws Exception {
                                        setResult(100,null);
                                        finish();
                                    }
                                });

                        popupWindow.dismiss();
                    }
                });
                view.findViewById(R.id.tvC).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
            }
        });
    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        String headPic = intent.getStringExtra("headPic");
        Glide.with(ChatSettingActivity.this).load(headPic).into(mIvFriendPic);
        String nickName = intent.getStringExtra("nickName");
        mTvName.setText(nickName);
        mTvName2.setText(nickName);
        int groupId = intent.getIntExtra("groupId", -1);
        int friend = intent.getIntExtra("friend", -1);


    }

    @Override
    public DeleteChatPresenter initPresenter() {
        return new DeleteChatPresenter();
    }

    @Override
    public void onSuccess(DeleteChatBean bean) {
        //Toast.makeText(this, ""+bean.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailed(String error) {

    }
}
