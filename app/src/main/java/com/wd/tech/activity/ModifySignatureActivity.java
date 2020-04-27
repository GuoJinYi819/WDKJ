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
import com.wd.tech.bean.wybean.beanmodifysignature.ModifySignatureBean;
import com.wd.tech.mvp.wymvp.mvpmodifysignature.IModifySignatureContract;
import com.wd.tech.mvp.wymvp.mvpmodifysignature.ModifySignaturePresenterImpl;

public class ModifySignatureActivity extends BaseActivity<ModifySignaturePresenterImpl> implements IModifySignatureContract.IModifySignatureView {
    private android.widget.ImageView imgModifySignatureBackWy;
    private android.widget.EditText etModifySignatureWy;
    private android.widget.Button btnModifySignatureWy;

    @Override
    public int initLayout() {
        return R.layout.activity_modify_signature;
    }
    @Override
    public void initView() {
        imgModifySignatureBackWy = (ImageView) findViewById(R.id.imgModifySignatureBackWy);
        etModifySignatureWy = (EditText) findViewById(R.id.etModifySignatureWy);
        btnModifySignatureWy = (Button) findViewById(R.id.btnModifySignatureWy);
    }
    @Override
    public void initListener() {
        //返回
        imgModifySignatureBackWy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //提交
        btnModifySignatureWy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string = etModifySignatureWy.getText().toString().trim();
                if(!TextUtils.isEmpty(string)){
                    presenter.getModifySignature(string);
                }
            }
        });
    }
    @Override
    public void initData() {
    }
    @Override
    public ModifySignaturePresenterImpl initPresenter() {
        return new ModifySignaturePresenterImpl();
    }
    @Override
    public void onSuccess(ModifySignatureBean modifySignatureBean) {
        String message = modifySignatureBean.getMessage();
        Toast.makeText(ModifySignatureActivity.this,message,Toast.LENGTH_SHORT).show();
        //成功  销毁
        String status = modifySignatureBean.getStatus();
        if(status.equals("0000")){
            finish();
        }
    }
    @Override
    public void onError(String error) {
    }
}
