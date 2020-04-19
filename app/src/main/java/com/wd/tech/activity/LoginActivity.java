package com.wd.tech.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.qzjbean.log.LogBean;
import com.wd.tech.bean.qzjbean.log.LogResultBean;
import com.wd.tech.mvp.qzjmvp.logmvp.LogConter;
import com.wd.tech.mvp.qzjmvp.logmvp.LogPresenterImpl;
import com.wd.tech.net.JudgeUtil;
import com.wd.tech.net.SpUtil;
import com.wd.tech.util.RsaCoder;


/**
 * ClassName: WdDetroy
 *
 * @author 作者 : Quzijun
 * @version 创建时间：2020/4/17 20:22
 * @Description: 用途：登录页面
 */
public class LoginActivity extends BaseActivity<LogPresenterImpl> implements LogConter.ILogView {
    private android.widget.EditText ephone;
    private android.widget.EditText epwd;
    private android.widget.TextView kszc;
    private android.widget.Button dl;
    private String phone;
    private String pwd;

    @Override
    public int initLayout() {
        return R.layout.loginactivity;
    }

    @Override
    public void initView() {

        ephone = (EditText) findViewById(R.id.ephone);
        epwd = (EditText) findViewById(R.id.epwd);
        kszc = (TextView) findViewById(R.id.kszc);
        dl = (Button) findViewById(R.id.dl);
    }

    @Override
    public void initListener() {
        dl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phone = ephone.getText().toString().trim();
                pwd = epwd.getText().toString().trim();
                if (!TextUtils.isEmpty(phone)){
                    if (!TextUtils.isEmpty(pwd)){
                        boolean phone1 = JudgeUtil.isPhone(phone);
                        if (phone1){
                            boolean pwd1 = JudgeUtil.isPwd(pwd);
                            if (pwd1){
                                try {
                                    String s = RsaCoder.encryptByPublicKey(pwd);
                                    Log.d("XX", "onCreate: "+s);
                                    presenter.getData(phone,s);

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }else {
                        Toast.makeText(LoginActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(LoginActivity.this, "账号不能为空", Toast.LENGTH_SHORT).show();
                }

            }
        });
        kszc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void initData() {
        SpUtil instance = SpUtil.getInstance();
        String phon = instance.getSpString("phone");
        String pw = instance.getSpString("pwd");
        if (!TextUtils.isEmpty(phon)&&!TextUtils.isEmpty(pw)){
            Log.d("XXX",phon);
            Log.d("XXX",pw);
            try {
                String s = RsaCoder.encryptByPublicKey(pw);
                Log.d("XX", "onCreate: "+s);
                presenter.getData(phon,s);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public LogPresenterImpl initPresenter() {
        return new LogPresenterImpl();
    }

    @Override
    public void onSuccess(LogBean logBean) {
        String message = logBean.getMessage();
        if (message.equals("登录成功")){
            LogResultBean result = logBean.getResult();
            int userId = result.getUserId();
            String sessionId = result.getSessionId();
            SpUtil instance = SpUtil.getInstance();
            instance.saveInt("userId",userId);
            instance.saveString("sessionId",sessionId);
            instance.saveString("phone",phone);
            instance.saveString("pwd",pwd);
            Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
            startActivity(intent);
            finish();

        }else {
            Toast.makeText(this, "账号或密码错误", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onError(String error) {
        Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
    }
}
