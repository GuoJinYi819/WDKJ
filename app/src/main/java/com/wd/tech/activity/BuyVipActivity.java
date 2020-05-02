package com.wd.tech.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.AlipayResultActivity;
import com.alipay.sdk.app.PayResultActivity;
import com.alipay.sdk.app.PayTask;
import com.facebook.drawee.view.SimpleDraweeView;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.gjybean.BuyBean;
import com.wd.tech.bean.wybean.beanbuyvip.BuyVipBean;
import com.wd.tech.bean.wybean.beanselectviplist.ResultBean;
import com.wd.tech.mvp.wymvp.mvpbuy.BuyPresenterImpl;
import com.wd.tech.mvp.wymvp.mvpbuy.IBuyContract;
import com.wd.tech.net.ApiService;
import com.wd.tech.net.EncryptionUtil;
import com.wd.tech.net.RetrofitUtil;
import com.wd.tech.net.SpUtil;
import com.wd.tech.util.WXUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class BuyVipActivity extends BaseActivity<BuyPresenterImpl> implements IBuyContract.IBuyView {
    private static final int SDK_PAY_FLAG = 100;
    private android.widget.ImageView imgVIPBackWy;
    private SimpleDraweeView imgVIPNameWy;
    private android.widget.TextView tvBuyVIPNameWy;
    private android.widget.TextView tvVIPPriceWy;
    private android.widget.Button btnBuyVipWy;
    @SuppressLint("HandlerLeak")
    private Handler mHanlder = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            if (msg.what==SDK_PAY_FLAG) {
                PayTask payTask = new PayTask(BuyVipActivity.this);
                String pay = payTask.pay((String) msg.obj, true);
                Log.i("gjy", "handleMessage: "+pay);
            }
        }
    };

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
        if (message.equals("下单成功")) {
            String orderId = buyVipBean.getOrderId();

            //弹框
            View view = View.inflate(BuyVipActivity.this, R.layout.popup_buy,null);
            PopupWindow popupWindow =new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            popupWindow.setBackgroundDrawable(new BitmapDrawable());
            popupWindow.setFocusable(true);
            popupWindow.setOutsideTouchable(true);
            //动画
            TranslateAnimation animation =new TranslateAnimation(Animation.RELATIVE_TO_PARENT,0, Animation.RELATIVE_TO_PARENT,0,
                    Animation.RELATIVE_TO_PARENT,1, Animation.RELATIVE_TO_PARENT,0);
            animation.setInterpolator(new AccelerateInterpolator());
            animation.setDuration(200);
            popupWindow.showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL,0,0);
            view.startAnimation(animation);

            view.findViewById(R.id.btnWX).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RetrofitUtil instance = RetrofitUtil.getInstance();
                    ApiService apiService = instance.createService();
                    Observable<BuyBean> pay = apiService.pay(orderId, 1);
                    pay.subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Consumer<BuyBean>() {
                                @Override
                                public void accept(BuyBean buyBean) throws Exception {
                                    //启动线程
                                    Runnable runnable = new Runnable() {
                                        @Override
                                        public void run() {
                                            PayReq request = new PayReq();
                                            request.appId= buyBean.getAppId();
                                            request.partnerId = buyBean.getPartnerId();
                                            request.prepayId = buyBean.getPrepayId();
                                            request.packageValue = buyBean.getPackageValue();
                                            request.nonceStr = buyBean.getNonceStr();
                                            request.timeStamp = buyBean.getTimeStamp();
                                            request.sign = buyBean.getSign();
                                            //发送调起微信的请求
                                            WXUtil.api.sendReq(request);

                                        }
                                    };
                                    //启动线程
                                    new Thread(runnable).start();

                                }

                            });
                    popupWindow.dismiss();
                }
            });
            view.findViewById(R.id.btnZFB).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RetrofitUtil instance = RetrofitUtil.getInstance();
                    ApiService apiService = instance.createService();
                    Observable<BuyBean> pay = apiService.pay(orderId, 2);
                    pay.subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Consumer<BuyBean>() {
                                @Override
                                public void accept(BuyBean buyBean) throws Exception {

                                    Runnable runnable = new Runnable() {
                                        @Override
                                        public void run() {
                                            String s = buyBean.getResult();
                                            PayTask alipay = new PayTask(BuyVipActivity.this);
                                            Map<String, String> result = alipay.payV2(orderId, true);
                                            Message msg = new Message();
                                            msg.what = SDK_PAY_FLAG;
                                            msg.obj = s;
                                            mHanlder.sendMessage(msg);

                                        }
                                    };
                                    new Thread(runnable).start();
                                }

                            });
                    popupWindow.dismiss();
                }
            });
            view.findViewById(R.id.btnClone).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popupWindow.dismiss();
                }
            });

        }


    }
    @Override
    public void onError(String error) {
    }
}
