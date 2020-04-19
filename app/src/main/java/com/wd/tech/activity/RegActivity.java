package com.wd.tech.activity;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.qzjbean.regist.RegBean;
import com.wd.tech.mvp.qzjmvp.regmvp.RegConter;
import com.wd.tech.mvp.qzjmvp.regmvp.RegPresenterImpl;
import com.wd.tech.net.JudgeUtil;
import com.wd.tech.util.RsaCoder;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : Quzijun
 * @version 创建时间：2020/4/18 11:31
 * @Description: 用途：完成特定功能
 */
public class RegActivity extends BaseActivity<RegPresenterImpl> implements RegConter.IRegView {
    private android.widget.EditText ename;
    private android.widget.EditText ephone;
    private android.widget.EditText epwd;
    private android.widget.TextView kszc;
    private android.widget.Button dl;

    @Override
    public int initLayout() {
        return R.layout.regactivity;
    }

    @Override
    public void initView() {
        ename = findViewById(R.id.ename);
        ephone = findViewById(R.id.ephone);
        epwd = findViewById(R.id.epwd);
        kszc = findViewById(R.id.kszc);
        dl = findViewById(R.id.dl);
    }
    @Override
    public void initListener() {
        dl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = ephone.getText().toString().trim();
                String name = ename.getText().toString().trim();
                String pwd = epwd.getText().toString().trim();
                if (!TextUtils.isEmpty(phone)){
                    if (!TextUtils.isEmpty(pwd)){
                        boolean phone1 = JudgeUtil.isPhone(phone);
                        if (phone1){
                            boolean pwd1 = JudgeUtil.isPwd(pwd);
                            if (pwd1){
                                try {
                                    String s = RsaCoder.encryptByPublicKey(pwd);
                                    presenter.getData(phone,name,s);
                                    Log.d("==", "onCreate: "+s);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }else {
                        Toast.makeText(RegActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(RegActivity.this, "账号不能为空", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    public void initData() {
    }

    @Override
    public RegPresenterImpl initPresenter() {
        return new RegPresenterImpl();
    }

    @Override
    public void onSuccess(RegBean regBean) {
        String message = regBean.getMessage();
        if (message.equals("注册成功")){
            Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
        } else if(message.equals("注册失败，用户昵称已存在")){
            Toast.makeText(this, "注册失败，用户昵称已存在", Toast.LENGTH_SHORT).show();
        }else if(message.equals("该手机号已注册，不能重复注册")){
            Toast.makeText(this, "该手机号已注册，不能重复注册", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "注册失败", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onError(String error) {

    }
}
