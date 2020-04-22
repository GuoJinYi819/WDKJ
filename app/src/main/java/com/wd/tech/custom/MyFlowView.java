package com.wd.tech.custom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wd.tech.App;
import com.wd.tech.R;

public class MyFlowView extends ViewGroup {
    public FlowLenter flowLenter;

    public void setFlowLenter(FlowLenter flowLenter) {
        this.flowLenter = flowLenter;
    }

    public MyFlowView(Context context) {
        super(context);
    }

    public MyFlowView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyFlowView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        int left = 0;
        int right = 0;
        int top = 0;
        int bottom = 0;
        int space = 20;
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            childAt.measure(0,0);
            left = space+right;
            right = left+childAt.getMeasuredWidth();
            if (right>getWidth()){
                left = space;
                top = bottom+space;
            }
            right = left+childAt.getMeasuredWidth();
            bottom = top + childAt.getMeasuredHeight();
            childAt.layout(left,top,right,bottom);
        }
    }
    @SuppressLint("ResourceAsColor")
    public void add(String tag){
        final TextView textView = new TextView(App.context);
        textView.setText(tag);
        textView.setTextSize(20);
        textView.setTextColor(R.color.colorBb);
        addView(textView);
        textView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String trim = textView.getText().toString().trim();
                if (flowLenter!=null){
                    flowLenter.onflowLenter(trim);
                }
            }
        });
    }
    public interface FlowLenter{
        void onflowLenter(String text);
    }
}
