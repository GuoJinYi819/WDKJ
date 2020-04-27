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
import com.wd.tech.bean.wybean.beanmodifynickname.ModifyNickNameBean;
import com.wd.tech.mvp.wymvp.mvpmodifynickname.IModifyNickNameContract;
import com.wd.tech.mvp.wymvp.mvpmodifynickname.ModifyNickNamePresenterImpl;

public class ModifyNickName2Activity extends BaseActivity<ModifyNickNamePresenterImpl> implements IModifyNickNameContract.IModifyNickNameView {
    private android.widget.ImageView imgModifyNickNameBackWy;
    private android.widget.EditText etModifyNickNameWy;
    private android.widget.Button btnModifyNickNamBackWy;

    //改用户名 页面
    @Override
    public int initLayout() {
        return R.layout.activity_modify_nick_name2;
    }
    @Override
    public void initView() {
        imgModifyNickNameBackWy = (ImageView) findViewById(R.id.imgModifyNickNameBackWy);
        etModifyNickNameWy = (EditText) findViewById(R.id.etModifyNickNameWy);
        btnModifyNickNamBackWy = (Button) findViewById(R.id.btnModifyNickNamBackWy);
    }
    @Override
    public void initListener() {
        //返回
        imgModifyNickNameBackWy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //提交
        btnModifyNickNamBackWy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string = etModifyNickNameWy.getText().toString().trim();
                if(!TextUtils.isEmpty(string)){
                    presenter.getModifyNickName(string);
                }
            }
        });
    }
    @Override
    public void initData() {
    }
    @Override
    public ModifyNickNamePresenterImpl initPresenter() {
        return new ModifyNickNamePresenterImpl();
    }
    @Override
    public void onSuccess(ModifyNickNameBean modifyNickNameBean) {
        String message = modifyNickNameBean.getMessage();
        Toast.makeText(ModifyNickName2Activity.this,message,Toast.LENGTH_SHORT).show();
        //成功  销毁
        String status = modifyNickNameBean.getStatus();
        if(status.equals("0000")){
            finish();
        }
    }
    @Override
    public void onError(String error) {
    }
}
