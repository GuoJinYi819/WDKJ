package com.wd.tech.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wd.tech.R;
import com.wd.tech.adapter.wyadapter.AdapterDate;
import com.wd.tech.adapter.wyadapter.AdapterWeek;
import com.wd.tech.net.DateUtil;

import androidx.annotation.Nullable;

/**
 * @author 王阳
 * Class :1708A
 * @description:
 * @date :2020/4/24 13:43
 * @classname :SignTimeView
 */
public class SignTimeView extends LinearLayout {
    private OnSignedSuccess onSignedSuccess;
    private InnerGridView gvSignDateWy;
    private AdapterDate adapterDate;

    public SignTimeView(Context context) {
        super(context);
        init(context);
    }

    public SignTimeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SignTimeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    public void init(Context context){
        View view = View.inflate(context, R.layout.view_sign_time, this);
        TextView tvSignYearWy = view.findViewById(R.id.tvSignYearWy);
        InnerGridView gvSignWeekWy = view.findViewById(R.id.gvSignWeekWy);
        gvSignDateWy = view.findViewById(R.id.gvSignDateWy);
        //
        tvSignYearWy.setText(DateUtil.getCurrentYearAndMonth());
        gvSignWeekWy.setAdapter(new AdapterWeek(context));
        adapterDate = new AdapterDate(getContext());
        gvSignDateWy.setAdapter(adapterDate);
    }
    //接口回调
    public void setOnSignedSuccess(OnSignedSuccess onSignedSuccess) {
        adapterDate.setOnSignedSuccess(onSignedSuccess);
    }

    public interface OnSignedSuccess{
        void OnSignedSuccess();
    }
}
