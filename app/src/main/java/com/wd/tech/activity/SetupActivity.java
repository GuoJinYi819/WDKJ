package com.wd.tech.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.base.BasePresenter;
import com.wd.tech.bean.wybean.beanselectuser.ResultBean;
import com.wd.tech.bean.wybean.beanselectuser.SelectUserBean;
import com.wd.tech.mvp.wymvp.mvpselectuser.ISelectUserContract;
import com.wd.tech.mvp.wymvp.mvpselectuser.SelectUserPresenterImpl;
import com.wd.tech.net.TimeToUtil;

public class SetupActivity extends BaseActivity<SelectUserPresenterImpl> implements ISelectUserContract.ISelectUserView {
    private android.widget.ImageView imgSetupBackWy;
    private com.facebook.drawee.view.SimpleDraweeView imgSetUpHeadWy;
    private android.widget.TextView tvSetUpNameWy;
    private android.widget.TextView tvSetUpSexWy;
    private android.widget.TextView tvSetUpSignatureWy;
    private android.widget.TextView tvSetUpBirthdayWy;
    private android.widget.TextView tvSetUpPhotoWy;
    private android.widget.TextView tvSetUpEmailWy;
    private android.widget.TextView tvSetUpintegralWy;
    private android.widget.TextView tvSetUpWhetherVipWy;
    private android.widget.TextView tvSetUpWhetherFaceIdWy;

    //设置 页面
    @Override
    public int initLayout() {
        return R.layout.activity_setup;
    }
    @Override
    public void initView() {
        imgSetupBackWy = (ImageView) findViewById(R.id.imgSetupBackWy);
        imgSetUpHeadWy = (SimpleDraweeView) findViewById(R.id.imgSetUpHeadWy);
        tvSetUpNameWy = (TextView) findViewById(R.id.tvSetUpNameWy);
        tvSetUpSexWy = (TextView) findViewById(R.id.tvSetUpSexWy);
        tvSetUpSignatureWy = (TextView) findViewById(R.id.tvSetUpSignatureWy);
        tvSetUpBirthdayWy = (TextView) findViewById(R.id.tvSetUpBirthdayWy);
        tvSetUpPhotoWy = (TextView) findViewById(R.id.tvSetUpPhotoWy);
        tvSetUpEmailWy = (TextView) findViewById(R.id.tvSetUpEmailWy);
        tvSetUpintegralWy = (TextView) findViewById(R.id.tvSetUpintegralWy);
        tvSetUpWhetherVipWy = (TextView) findViewById(R.id.tvSetUpWhetherVipWy);
        tvSetUpWhetherFaceIdWy = (TextView) findViewById(R.id.tvSetUpWhetherFaceIdWy);
    }
    @Override
    public void initListener() {
        //销毁
        imgSetupBackWy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    @Override
    public void initData() {
        presenter.getSelectUser();
    }
    @Override
    public SelectUserPresenterImpl initPresenter() {
        return new SelectUserPresenterImpl();
    }
    @Override
    public void onSuccess(SelectUserBean selectUser) {
        //成功
        ResultBean result = selectUser.getResult();
        //取值
        String headPic = result.getHeadPic();
        String nickName = result.getNickName();
        int sex = result.getSex();
        String signature = result.getSignature();
        long birthday = result.getBirthday();
        String phone = result.getPhone();
        String email = result.getEmail();
        int integral = result.getIntegral();
        int whetherVip = result.getWhetherVip();
        int whetherFaceId = result.getWhetherFaceId();
        //设置
        imgSetUpHeadWy.setImageURI(headPic);
        tvSetUpNameWy.setText(nickName);
        //性别
        if(sex==1){
            tvSetUpSexWy.setText("男");
        }else if(sex==2){
            tvSetUpSexWy.setText("女");
        }
        tvSetUpSignatureWy.setText(signature);
        //时间  生日
        String time = TimeToUtil.getTime(birthday);
        tvSetUpBirthdayWy.setText(time);
        tvSetUpPhotoWy.setText(phone);
        tvSetUpEmailWy.setText(email);
        tvSetUpintegralWy.setText(""+integral);
        //VIP
        if(whetherVip==1){
            tvSetUpWhetherVipWy.setText("是");
        }else if(whetherVip==2){
            tvSetUpWhetherVipWy.setText("否");
        }
        //绑定
        if(whetherFaceId==1){
            tvSetUpWhetherFaceIdWy.setText("解除绑定");
        }else if(whetherFaceId==2){
            tvSetUpWhetherFaceIdWy.setText("立即绑定");
        }
    }
    @Override
    public void onError(String error) {
    }
}
