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
import com.wd.tech.bean.wybean.beanimproveinformation.ImproveInformationBean;
import com.wd.tech.mvp.wymvp.mvpimproveinformation.IImproveInformationContract;
import com.wd.tech.mvp.wymvp.mvpimproveinformation.ImproveInformationPresenterImpl;

public class ImproveInformationActivity extends BaseActivity<ImproveInformationPresenterImpl> implements IImproveInformationContract.IImproveInformationView {
    private android.widget.ImageView imgImproveInformationBackWy;
    private android.widget.EditText etImproveNameWy;
    private android.widget.EditText etImproveSexWy;
    private android.widget.EditText etImproveSignatureWy;
    private android.widget.EditText etImproveBirthdayWy;
    private android.widget.EditText etImproveEmailWy;
    private android.widget.Button btnImproveSubmitWy;

    //完善信息页面
    @Override
    public int initLayout() {
        return R.layout.activity_improve_information;
    }
    @Override
    public void initView() {
        imgImproveInformationBackWy = (ImageView) findViewById(R.id.imgImproveInformationBackWy);
        etImproveNameWy = (EditText) findViewById(R.id.etImproveNameWy);
        etImproveSexWy = (EditText) findViewById(R.id.etImproveSexWy);
        etImproveSignatureWy = (EditText) findViewById(R.id.etImproveSignatureWy);
        etImproveBirthdayWy = (EditText) findViewById(R.id.etImproveBirthdayWy);
        etImproveEmailWy = (EditText) findViewById(R.id.etImproveEmailWy);
        btnImproveSubmitWy = (Button) findViewById(R.id.btnImproveSubmitWy);
    }
    @Override
    public void initListener() {
        //返回
        imgImproveInformationBackWy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //销毁
                finish();
            }
        });
        //提交  修改
        btnImproveSubmitWy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //昵称
                String name = etImproveNameWy.getText().toString().trim();
                if(!TextUtils.isEmpty(name)){
                    //性别
                    String sex = etImproveSexWy.getText().toString().trim();
                    if(!TextUtils.isEmpty(sex)){
                        //性别
                        Integer integer = Integer.valueOf(sex);
                        String signature = etImproveSignatureWy.getText().toString().trim();
                        if(!TextUtils.isEmpty(signature)){
                            //生日
                            String birthday = etImproveBirthdayWy.getText().toString().trim();
                            if(!TextUtils.isEmpty(birthday)){
                                //邮箱
                                String email = etImproveEmailWy.getText().toString().trim();
                                if(!TextUtils.isEmpty(email)){
                                    presenter.getImproveInformation(name,integer,signature,birthday,email);
                                }else{
                                    Toast.makeText(ImproveInformationActivity.this,"邮箱不能为空",Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                Toast.makeText(ImproveInformationActivity.this,"出生日期不能为空",Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(ImproveInformationActivity.this,"签名不能为空",Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(ImproveInformationActivity.this,"性别不能为空",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(ImproveInformationActivity.this,"昵称不能为空",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    public void initData() {
    }
    @Override
    public ImproveInformationPresenterImpl initPresenter() {
        return new ImproveInformationPresenterImpl();
    }
    @Override
    public void onSuccess(ImproveInformationBean improveInformationBean) {
        String message = improveInformationBean.getMessage();
        Toast.makeText(ImproveInformationActivity.this,message,Toast.LENGTH_SHORT).show();
        //销毁
        finish();
    }
    @Override
    public void onError(String error) {
    }
}
