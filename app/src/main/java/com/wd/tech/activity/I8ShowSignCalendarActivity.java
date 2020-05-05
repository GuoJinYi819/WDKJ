package com.wd.tech.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.wybean.beandotask.DoTaskBean;
import com.wd.tech.bean.wybean.beansign.SignBean;
import com.wd.tech.fragment.wyfragment.signcalendar.SignCalendar;
import com.wd.tech.fragment.wyfragment.signcalendar.SignCalendarReq;
import com.wd.tech.mvp.wymvp.mvpsign.ISignContract;
import com.wd.tech.mvp.wymvp.mvpsign.SignPresenterImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class I8ShowSignCalendarActivity extends BaseActivity<SignPresenterImpl> implements ISignContract.ISignView {
    private SignCalendar calendar;
    private String date;
    private TextView btn_sign;
    private TextView tv_sign_year_month;
    private SignCalendarReq signCalendarReq;
    private SignCalendarReq.DataBean dataBean;
    List<String> list = new ArrayList<>();//list中存储的格式为2019-06-02
    private int month;
    private int year;
    private RelativeLayout rlGetGiftData;
    private TextView tvGetSunValue;
    private ImageView ivSun;
    private ImageView ivSunBg;
    private RelativeLayout rlQuedingBtn;
    private RelativeLayout rlBtnSign;
    private ImageView signBack;
    private boolean isSign;
    private boolean isChecked=false;
    @Override
    public int initLayout() {
        return R.layout.activity_sign_calendar;
    }

    @Override
    public void initView() {
        calendar = findViewById(R.id.sc_main);
        btn_sign = findViewById(R.id.btn_sign);
        tv_sign_year_month = findViewById(R.id.tv_sign_year_month);
        rlGetGiftData = findViewById(R.id.rl_get_gift_view);
        tvGetSunValue = findViewById(R.id.tv_text_one);
        ivSun = findViewById(R.id.iv_sun);
        ivSunBg = findViewById(R.id.iv_sun_bg);
        signBack = findViewById(R.id.i8show_attention_back);
        rlQuedingBtn = findViewById(R.id.rl_queding_btn);
        rlBtnSign = findViewById(R.id.rl_btn_sign);

        //接收传递过来的数据
        final SignCalendarReq signCalendarReq = (SignCalendarReq) getIntent().getSerializableExtra("userInfos");

        //获取当前的月份
        month = Calendar.getInstance().get(Calendar.MONTH);
        //获取当前的年份
        year = Calendar.getInstance().get(Calendar.YEAR);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        // 获取当前时间
        Date curDate = new Date(System.currentTimeMillis());
        date = formatter.format(curDate);

        //设置当前日期
        tv_sign_year_month.setText(year + "年" + (month + 1) + "月");

        if (signCalendarReq != null) {
            if (signCalendarReq.getState().getCode() == 1) {//1成功，0失败
                dataBean = signCalendarReq.getData();
                //获取当月已签到的日期
                String signDay = dataBean.getSignDay();
                String[] splitDay = signDay.split(",");

                //list中存储的格式为2019-06-02
                for (int i = 0; i < splitDay.length; i++) {
                    if (Integer.parseInt(splitDay[i]) < 10) {
                        if (month < 10) {
                            list.add(year + "-0" + (month + 1) + "-0" + splitDay[i]);
                        } else {
                            list.add(year + "-" + (month + 1) + "-0" + splitDay[i]);
                        }

                    } else {
                        if (month < 10) {
                            list.add(year + "-0" + (month + 1) + "-" + splitDay[i]);
                        } else {
                            list.add(year + "-" + (month + 1) + "-" + splitDay[i]);
                        }
                    }
                }


                calendar.addMarks(list, 0);

                if (dataBean.getIsSign() == 1) {//1是已签到，0是未签到
                    rlBtnSign.setBackgroundResource(R.drawable.btn_sign_calendar_no);
                    btn_sign.setText("已签到");
                    rlBtnSign.setClickable(false);
                } else {
                    rlBtnSign.setBackgroundResource(R.drawable.btn_sign_calendar);
                    btn_sign.setText("签 到");
                }
            }
        }
    }

    @Override
    public void initListener() {
        btn_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isSign) {
                    signCalendarData();
                }

            }
        });

        rlQuedingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rlGetGiftData.setVisibility(View.GONE);
            }
        });

   /*     signBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });*/
    }

    @Override
    public void initData() {
        //变个色
        /*isChecked=true;
        if(isChecked){
            btn_sign.setBackgroundColor(Color.GREEN);
            isChecked=false;
        }*/
        presenter.getSign();
        //加分
        presenter.getDoTask(1001);
    }
    private void signCalendarData() {
        //模拟请求后台数据签到已成功

        rlGetGiftData.setVisibility(View.VISIBLE);
        rlBtnSign.setBackgroundResource(R.drawable.btn_sign_calendar_no);
        btn_sign.setText("已签到");
        isSign = true;//模拟当天已签到


        ivSun.setImageResource(R.drawable.i8live_sun);
        tvGetSunValue.setText("恭喜获得10个阳光值");


        Animation operatingAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_anim_online_gift);
        LinearInterpolator lin = new LinearInterpolator();
        operatingAnim.setInterpolator(lin);
        ivSunBg.startAnimation(operatingAnim);

        //list.add("2017-11-18");
        list.add(date);
        calendar.addMarks(list, 0);

    }
    @Override
    public SignPresenterImpl initPresenter() {
        return new SignPresenterImpl();
    }
    @Override
    public void onSuccess(SignBean sign) {
        String message = sign.getMessage();
        Toast.makeText(I8ShowSignCalendarActivity.this,message,Toast.LENGTH_SHORT).show();
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
