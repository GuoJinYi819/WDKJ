package com.wd.tech.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.wybean.beanadvertisement.AdvertisementBean;
import com.wd.tech.bean.wybean.beanadvertisement.ResultBean;
import com.wd.tech.bean.wybean.beandotask.DoTaskBean;
import com.wd.tech.mvp.wymvp.mvpadvertisement.AdvertisementPresenterImpl;
import com.wd.tech.mvp.wymvp.mvpadvertisement.IAdvertisementContract;

public class AdvertisementActivity extends BaseActivity<AdvertisementPresenterImpl> implements IAdvertisementContract.IAdvertisementView {
    private android.widget.ImageView imgAdvertisementBackWy;
    private android.widget.TextView tvAdvertisementContent;
    private com.facebook.drawee.view.SimpleDraweeView imgAdvertisementPic;
    private android.webkit.WebView webAdvertisementUrl;

    @Override
    public int initLayout() {
        return R.layout.activity_advertisement;
    }
    @Override
    public void initView() {
        imgAdvertisementBackWy = (ImageView) findViewById(R.id.imgAdvertisementBackWy);
        tvAdvertisementContent = (TextView) findViewById(R.id.tvAdvertisementContent);
        imgAdvertisementPic = (SimpleDraweeView) findViewById(R.id.imgAdvertisementPic);
        webAdvertisementUrl = (WebView) findViewById(R.id.webAdvertisementUrl);
    }
    @Override
    public void initListener() {
        //返回
        imgAdvertisementBackWy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    @Override
    public void initData() {
        presenter.getAdvertisement();
        presenter.getDoTask(1005);
    }
    @Override
    public AdvertisementPresenterImpl initPresenter() {
        return new AdvertisementPresenterImpl();
    }
    @Override
    public void onSuccess(AdvertisementBean advertisementBean) {
        ResultBean result = advertisementBean.getResult();
        String content = result.getContent();
        String pic = result.getPic();
        String url = result.getUrl();
        //设置  js交互   在本app展示
        webAdvertisementUrl.getSettings().setJavaScriptEnabled(true);
        webAdvertisementUrl.setWebViewClient(new WebViewClient());
        tvAdvertisementContent.setText(content);
        imgAdvertisementPic.setImageURI(pic);
        webAdvertisementUrl.loadUrl(url);
    }
    @Override
    public void onError(String error) {
    }
    @Override
    public void onTaskSuccess(DoTaskBean doTaskBean) {
        String message = doTaskBean.getMessage();
        Log.d("==", "onTaskSuccess: "+message);
    }
}
