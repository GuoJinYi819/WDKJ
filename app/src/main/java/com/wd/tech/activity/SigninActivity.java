package com.wd.tech.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.wd.tech.R;
import com.wd.tech.adapter.wyadapter.AdapterDate;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.wybean.beandotask.DoTaskBean;
import com.wd.tech.bean.wybean.beansign.SignBean;
import com.wd.tech.bean.wybean.beansignrecording.SignRecordingBean;
import com.wd.tech.mvp.wymvp.mvpsign.ISignContract;
import com.wd.tech.mvp.wymvp.mvpsign.SignPresenterImpl;
import com.wd.tech.view.SignTimeView;

public class SigninActivity extends BaseActivity<SignPresenterImpl> implements ISignContract.ISignView {
    private android.widget.ImageView imgSigninBackWy;
    private com.wd.tech.view.SignTimeView signTimeViewWy;
    private android.widget.Button btnSignWy;
    private boolean isChecked=false;
    //签到页面
    @Override
    public int initLayout() {
        return R.layout.activity_signin;
    }
    @Override
    public void initView() {
        imgSigninBackWy = (ImageView) findViewById(R.id.imgSigninBackWy);
        signTimeViewWy = (SignTimeView) findViewById(R.id.signTimeViewWy);
        btnSignWy = (Button) findViewById(R.id.btnSignWy);
    }
    @Override
    public void initListener() {
        //返回
        imgSigninBackWy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //签到    先不让他点
        signTimeViewWy.setOnSignedSuccess(new SignTimeView.OnSignedSuccess() {
            @Override
            public void OnSignedSuccess() {
                Log.d("==", "OnSignedSuccess: 月  日期");
            }
        });
        //点击签到
        btnSignWy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdapterDate adapterDate = new AdapterDate(SigninActivity.this);
                adapterDate.status();
                //变个色
                isChecked=true;
                if(isChecked){
                    btnSignWy.setBackgroundColor(Color.GREEN);
                    isChecked=false;
                }
                presenter.getSign();
                //加分
                presenter.getDoTask(1001);
            }
        });
    }
    @Override
    public void initData() {
    }
    @Override
    public SignPresenterImpl initPresenter() {
        return new SignPresenterImpl();
    }
    @Override
    public void onSuccess(SignBean sign) {
        String message = sign.getMessage();
        Toast.makeText(SigninActivity.this,message,Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onError(String error) {
    }
    @Override
    public void onTaskSuccess(DoTaskBean doTaskBean) {
        String message = doTaskBean.getMessage();
        Log.d("==", "onTaskSuccess: "+message);
    }

    @Override
    public void onSignRecordingSuccess(SignRecordingBean signRecordingBean) {
        //当月 签到 成功
    }
}
