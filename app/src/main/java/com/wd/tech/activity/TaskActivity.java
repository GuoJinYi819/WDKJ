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
import com.wd.tech.bean.wybean.beanusertasklist.ResultBean;
import com.wd.tech.bean.wybean.beanusertasklist.UserTaskListBean;
import com.wd.tech.mvp.wymvp.mvpusertasklist.IUserTaskListContract;
import com.wd.tech.mvp.wymvp.mvpusertasklist.UserTaskListPresenterImpl;

import java.util.List;

public class TaskActivity extends BaseActivity<UserTaskListPresenterImpl> implements IUserTaskListContract.IUserTaskListView {
    //日常任务页面
    private android.widget.ImageView imgTaskBackWy;
    private android.widget.Button btnToSignWy;
    private Button btnToCommentListWy;
    private Button btnToCommentWy;
    private Button btnToSharedWy;
    private Button btnToAdvertisementWy;
    private Button btnToPerfectWy;
    private Button btnToBindingWy;
    private List<ResultBean> result;

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
                ResultBean resultBean = result.get(0);
                int status = resultBean.getStatus();
                if(status==2){
                    //跳
                    Intent intent = new Intent(TaskActivity.this, SigninActivity.class);
                    startActivity(intent);
                }
            }
        });
        //点击  去评价列表页
        btnToCommentListWy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转   写评论页面
                ResultBean resultBean = result.get(1);
                int status = resultBean.getStatus();
                if(status==2){
                    //跳
                    Intent intent = new Intent(TaskActivity.this, CommentListActivity.class);
                    startActivity(intent);
                }
            }
        });
        //点击  取发帖子
        btnToCommentWy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转   发帖子
                ResultBean resultBean = result.get(2);
                int status = resultBean.getStatus();
                if(status==2){
                    //跳
                    Intent intent = new Intent(TaskActivity.this, CommentActivity.class);
                    startActivity(intent);
                }
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
                ResultBean resultBean = result.get(4);
                int status = resultBean.getStatus();
                if(status==2){
                    //跳
                    Intent intent = new Intent(TaskActivity.this, AdvertisementActivity.class);
                    startActivity(intent);
                }
            }
        });
        //完善个人信息
        btnToPerfectWy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转   完善
                ResultBean resultBean = result.get(5);
                int status = resultBean.getStatus();
                if(status==2){
                    //跳
                    Intent intent = new Intent(TaskActivity.this, ImproveInformationActivity.class);
                    startActivity(intent);
                }
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
    protected void onRestart() {
        super.onRestart();
        //再次请求  刷新数据
        presenter.getUserTaskList();
    }

    @Override
    public void initData() {
        presenter.getUserTaskList();
    }
    @Override
    public UserTaskListPresenterImpl initPresenter() {
        return new UserTaskListPresenterImpl();
    }
    @Override
    public void onSuccess(UserTaskListBean userTaskListBean) {
        result = userTaskListBean.getResult();
        //
        ResultBean resultBean = result.get(0);
        int status = resultBean.getStatus();
        if(status==1) {
            //签到改状态
            btnToSignWy.setTextColor(Color.WHITE);
            btnToSignWy.setBackgroundColor(Color.BLUE);
            btnToSignWy.setText("已完成");
        }

        resultBean = result.get(1);
        status = resultBean.getStatus();
        if(status==1) {
            //评价改状态
            btnToCommentListWy.setTextColor(Color.WHITE);
            btnToCommentListWy.setBackgroundColor(Color.BLUE);
            btnToCommentListWy.setText("已完成");
        }

        resultBean = result.get(2);
        status = resultBean.getStatus();
        if(status==1) {
            //发帖改状态
            btnToCommentWy.setTextColor(Color.WHITE);
            btnToCommentWy.setBackgroundColor(Color.BLUE);
            btnToCommentWy.setText("已完成");
        }

        resultBean = result.get(3);
        status = resultBean.getStatus();
        if(status==1) {
            //分享改状态
            btnToSharedWy.setTextColor(Color.WHITE);
            btnToSharedWy.setBackgroundColor(Color.BLUE);
            btnToSharedWy.setText("已完成");
        }

        resultBean = result.get(4);
        status = resultBean.getStatus();
        if(status==1) {
            //看广告改状态
            btnToAdvertisementWy.setTextColor(Color.WHITE);
            btnToAdvertisementWy.setBackgroundColor(Color.BLUE);
            btnToAdvertisementWy.setText("已完成");
        }

        resultBean = result.get(5);
        status = resultBean.getStatus();
        if(status==1) {
            //完善信息改状态
            btnToPerfectWy.setTextColor(Color.WHITE);
            btnToPerfectWy.setBackgroundColor(Color.BLUE);
            btnToPerfectWy.setText("已完成");
        }

        resultBean = result.get(6);
        status = resultBean.getStatus();
        if(status==1) {
            //绑定微信改状态
            btnToBindingWy.setTextColor(Color.WHITE);
            btnToBindingWy.setBackgroundColor(Color.BLUE);
            btnToBindingWy.setText("已完成");
        }
    }
    @Override
    public void onError(String error) {
    }
}
