package com.wd.tech.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wd.tech.App;
import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.gjybean.WxBean;
import com.wd.tech.bean.qzjbean.log.LogBean;
import com.wd.tech.bean.qzjbean.log.LogResultBean;
import com.wd.tech.mvp.qzjmvp.logmvp.LogConter;
import com.wd.tech.mvp.qzjmvp.logmvp.LogPresenterImpl;
import com.wd.tech.net.ApiService;
import com.wd.tech.net.JudgeUtil;
import com.wd.tech.net.RetrofitUtil;
import com.wd.tech.net.SpUtil;
import com.wd.tech.util.RsaCoder;
import com.wd.tech.util.WXUtil;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


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
    private String s;
    private ImageView ivwxLogin;

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
        ivwxLogin = findViewById(R.id.ivWxLogin);
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

        ivwxLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean weixinAvilible = WXUtil.isWeixinAvilible(App.context);
                if (weixinAvilible){
                    WXUtil.callWX();
                    WXUtil.onWxLoginListener = new WXUtil.OnWxLoginListener() {
                        @Override
                        public void onCode(String code) {
                            RetrofitUtil instance = RetrofitUtil.getInstance();
                            ApiService apiService = instance.createService();
                            Observable<WxBean> observable = apiService.wxLogin(code);
                            observable.subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(new Consumer<WxBean>() {
                                        @Override
                                        public void accept(WxBean wxBean) throws Exception {
                                            String message = wxBean.getMessage();
                                            if (message.equals("登陆成功")) {
                                                WxBean.ResultBean result = wxBean.getResult();
                                                int userId = result.getUserId();
                                                String sessionId = result.getSessionId();
                                                //缓存数据  头像 名称 签名
                                                String headPic = result.getHeadPic();
                                                String nickName = result.getNickName();
                                                //     String signature = result.getSignature();
                                                //是否为vip   是否为绑定faceId
                                                int whetherVip = result.getWhetherVip();
                                                int whetherFaceId = result.getWhetherFaceId();
                                                //设置
                                                SpUtil instance = SpUtil.getInstance();
                                                instance.saveInt("userId",userId);
                                                instance.saveString("sessionId",sessionId);
                                                instance.saveString("phone",phone);
                                                instance.saveString("pwd",pwd);
                                                //个人 数据
                                                instance.saveString("headPic",headPic);
                                                instance.saveString("nickName",nickName);
                                                instance.saveString("signature","没有签名信息");
                                                instance.saveInt("whetherVip",whetherVip);
                                                instance.saveInt("whetherFaceId",whetherFaceId);
                                                Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                                                startActivity(intent);
                                                finish();
                                            }
                                        }
                                    });

                        }
                    };
                }else {
                    Toast.makeText(LoginActivity.this, "请先安装微信", Toast.LENGTH_SHORT).show();
                }



            }
        });
    }

    @Override
    public void initData() {
        SpUtil instance = SpUtil.getInstance();
        String phon = instance.getSpString("phone");
        String pw = instance.getSpString("pwd");

        if (!TextUtils.isEmpty(phon)&&!TextUtils.isEmpty(pw)){

            ephone.setText(phon);
            epwd.setText(pw);
            try {
                s = RsaCoder.encryptByPublicKey(pw);
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
            //缓存数据  头像 名称 签名
            String headPic = result.getHeadPic();
            String nickName = result.getNickName();
            String signature = result.getSignature();
            //是否为vip   是否为绑定faceId
            int whetherVip = result.getWhetherVip();
            int whetherFaceId = result.getWhetherFaceId();
            //设置
            SpUtil instance = SpUtil.getInstance();
            instance.saveInt("userId",userId);
            instance.saveString("sessionId",sessionId);
            instance.saveString("phone",phone);
            instance.saveString("pwd",pwd);
            //个人 数据
            instance.saveString("headPic",headPic);
            instance.saveString("nickName",nickName);
            instance.saveString("signature",signature);
            instance.saveInt("whetherVip",whetherVip);
            instance.saveInt("whetherFaceId",whetherFaceId);
            Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
            startActivity(intent);
            finish();

        }
        Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onError(String error) {
        Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
    }
}
