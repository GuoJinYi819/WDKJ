package com.wd.tech.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.wybean.beanbuyvip.BuyVipBean;
import com.wd.tech.bean.wybean.beanselectviplist.ResultBean;
import com.wd.tech.mvp.wymvp.mvpbuy.BuyPresenterImpl;
import com.wd.tech.mvp.wymvp.mvpbuy.IBuyContract;
import com.wd.tech.net.EncryptionUtil;
import com.wd.tech.net.SpUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class BuyVipActivity extends BaseActivity<BuyPresenterImpl> implements IBuyContract.IBuyView {
    private android.widget.ImageView imgVIPBackWy;
    private SimpleDraweeView imgVIPNameWy;
    private android.widget.TextView tvBuyVIPNameWy;
    private android.widget.TextView tvVIPPriceWy;
    private android.widget.Button btnBuyVipWy;

    //购买  下单  页面
    @Override
    public int initLayout() {
        return R.layout.activity_buy_vip;
    }
    @Override
    public void initView() {
        imgVIPBackWy = (ImageView) findViewById(R.id.imgVIPBackWy);
        imgVIPNameWy = (SimpleDraweeView) findViewById(R.id.imgVIPNameWy);
        tvBuyVIPNameWy = (TextView) findViewById(R.id.tvBuyVIPNameWy);
        tvVIPPriceWy = (TextView) findViewById(R.id.tvVIPPriceWy);
        btnBuyVipWy = (Button) findViewById(R.id.btnBuyVipWy);
        //订阅
        EventBus.getDefault().register(this);
    }
    @Override
    public void initListener() {
        imgVIPBackWy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //返回  销毁
                finish();
            }
        });
        //点击
        btnBuyVipWy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //签名
                SpUtil instance = SpUtil.getInstance();
                int userId = instance.getSpInt("userId");
                String sessionId = instance.getSpString("sessionId");
                int commodityId = instance.getSpInt("commodityId");
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(userId);
                stringBuffer.append(commodityId);
                Log.d("=====", "initData: "+sessionId);
                stringBuffer.append("tech");
                String trim = stringBuffer.toString().trim();
                //MD5   签名
                String s1 = EncryptionUtil.MD5(trim);
                Log.d("=====", "onCreate: "+s1);

                //
                presenter.getBuy(commodityId,s1);
            }
        });
    }
    @Override
    public void initData() {
    }
    //接收
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void onEvent(ResultBean resultBean){
        String commodityName = resultBean.getCommodityName();
        String imageUrl = resultBean.getImageUrl();
        double price = resultBean.getPrice();
        //设置数据
        if(!TextUtils.isEmpty(commodityName)){
            imgVIPNameWy.setImageURI(imageUrl);
            tvBuyVIPNameWy.setText(commodityName);
            tvVIPPriceWy.setText("$:"+price);
        }
    }
    @Override
    public BuyPresenterImpl initPresenter() {
        return new BuyPresenterImpl();
    }
    @Override
    public void onSuccess(BuyVipBean buyVipBean) {
        //成功
        String message = buyVipBean.getMessage();
        Toast.makeText(BuyVipActivity.this,message,Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onError(String error) {
    }
}
