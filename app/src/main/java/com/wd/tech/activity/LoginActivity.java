package com.wd.tech.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.base.BasePresenter;
import com.wd.tech.base.IBaseView;
import com.wd.tech.bean.qzjbean.LogBean;
import com.wd.tech.mvp.logmvp.LogConter;
import com.wd.tech.mvp.logmvp.LogPresenterImpl;

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
                Toast.makeText(LoginActivity.this, "登录", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public LogPresenterImpl initPresenter() {
        return new LogPresenterImpl();
    }

    @Override
    public void onSuccess(LogBean logBean) {

    }

    @Override
    public void onError(String error) {

    }
}
