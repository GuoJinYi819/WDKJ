package com.wd.tech.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.wybean.beanmodifyemail.ModifyEmailBean;
import com.wd.tech.mvp.wymvp.mvpmodifyemail.IModifyEmailContract;
import com.wd.tech.mvp.wymvp.mvpmodifyemail.ModifyEmailPresenterImpl;

public class ModifyEmailActivity extends BaseActivity<ModifyEmailPresenterImpl> implements IModifyEmailContract.IModifyEmailView {
    private android.widget.ImageView imgModifyEmailBackWy;
    private android.widget.EditText etModifyEmailWy;
    private android.widget.Button btnModifyEmailWy;

    @Override
    public int initLayout() {
        return R.layout.activity_modify_email;
    }
    @Override
    public void initView() {
        imgModifyEmailBackWy = (ImageView) findViewById(R.id.imgModifyEmailBackWy);
        etModifyEmailWy = (EditText) findViewById(R.id.etModifyEmailWy);
        btnModifyEmailWy = (Button) findViewById(R.id.btnModifyEmailWy);
    }
    @Override
    public void initListener() {
        imgModifyEmailBackWy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //返回
                finish();
            }
        });
        //点击  提交
        btnModifyEmailWy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etModifyEmailWy.getText().toString().trim();
                if(!TextUtils.isEmpty(email)){
                    presenter.getModifyEmail(email);
                }
            }
        });
    }
    @Override
    public void initData() {
    }
    @Override
    public ModifyEmailPresenterImpl initPresenter() {
        return new ModifyEmailPresenterImpl();
    }
    @Override
    public void onSuccess(ModifyEmailBean modifyEmailBean) {
        String message = modifyEmailBean.getMessage();
        Toast.makeText(ModifyEmailActivity.this,message,Toast.LENGTH_SHORT).show();
        //成功  销毁
        String status = modifyEmailBean.getStatus();
        if(status.equals("0000")){
            finish();
        }
    }
    @Override
    public void onError(String error) {
    }
}
