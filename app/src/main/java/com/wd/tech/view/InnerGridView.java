package com.wd.tech.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * @author 王阳
 * Class :1708A
 * @description:
 * @date :2020/4/24 13:53
 * @classname :InnerGridView
 */
public class InnerGridView extends GridView {

    public InnerGridView(Context context) {
        super(context);
    }

    public InnerGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public InnerGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 3, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}