package com.wd.tech.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.base.BasePresenter;

public class TaskActivity extends BaseActivity {
    //日常任务页面
    private android.widget.ImageView imgTaskBackWy;
    private android.widget.Button btnToSignWy;
    private Button btnToCommentListWy;
    private Button btnToCommentWy;
    private Button btnToSharedWy;
    private Button btnToAdvertisementWy;
    private Button btnToPerfectWy;
    private Button btnToBindingWy;

    @Override
    public int initLayout() {
        return R.layout.activity_task;
    }
    @Override
    public void initView() {
        imgTaskBackWy = (ImageView) findViewById(R.id.imgTaskBackWy);
        btnToSignWy = (Button) findViewById(R.id.btnToSignWy);
        btnToCommentListWy = (Button) findViewById(R.id.btnToCommentListWy);
        btnToCommentWy = (Button) findViewById(R.id.btnToCommentWy);
        btnToSharedWy = (Button) findViewById(R.id.btnToSharedWy);
        btnToAdvertisementWy = (Button) findViewById(R.id.btnToAdvertisementWy);
        btnToPerfectWy = (Button) findViewById(R.id.btnToPerfectWy);
        btnToBindingWy = (Button) findViewById(R.id.btnToBindingWy);
    }
    @Override
    public void initListener() {
        //点击 返回
        imgTaskBackWy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //销毁
                finish();
            }
        });
        //点击   去签到
        btnToSignWy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳
                Intent intent = new Intent(TaskActivity.this, SigninActivity.class);
                startActivity(intent);
                //改状态
                btnToSignWy.setTextColor(Color.WHITE);
                btnToSignWy.setBackgroundColor(Color.BLUE);
            }
        });
        //点击  去评价列表页
        btnToCommentListWy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转   写评论页面
                Intent intent = new Intent(TaskActivity.this, CommentListActivity.class);
                startActivity(intent);
                //改状态
                btnToCommentListWy.setTextColor(Color.WHITE);
                btnToCommentListWy.setBackgroundColor(Color.BLUE);
            }
        });
        //点击  取发帖子
        btnToCommentWy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转   写评论页面
                Intent intent = new Intent(TaskActivity.this, CommentActivity.class);
                startActivity(intent);
                //改状态
                btnToCommentWy.setTextColor(Color.WHITE);
                btnToCommentWy.setBackgroundColor(Color.BLUE);
            }
        });
        //去 分享
        btnToSharedWy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TaskActivity.this,"分享",Toast.LENGTH_SHORT).show();
            }
        });
        //去看 广告
        btnToAdvertisementWy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转   看广告页面
                Intent intent = new Intent(TaskActivity.this, AdvertisementActivity.class);
                startActivity(intent);
            }
        });
        //完善个人信息
        btnToPerfectWy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转   写评论页面
                Intent intent = new Intent(TaskActivity.this, ImproveInformationActivity.class);
                startActivity(intent);
                //改状态
                btnToPerfectWy.setTextColor(Color.WHITE);
                btnToPerfectWy.setBackgroundColor(Color.BLUE);
            }
        });
        //绑定微信
        btnToBindingWy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TaskActivity.this,"绑定微信",Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void initData() {
    }
    @Override
    public BasePresenter initPresenter() {
        return null;
    }
}
