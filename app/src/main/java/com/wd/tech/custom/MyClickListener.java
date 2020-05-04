package com.wd.tech.custom;

import android.util.Log;
import android.view.View;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/5/4 0004 14:53
 * @Description: 用途：完成特定功能
 */
public abstract class MyClickListener implements View.OnClickListener {
    private long time;
    @Override
    public void onClick(View v) {
        long millis = System.currentTimeMillis();
        if(millis-time>500){
            onMyChilk();
        }else {
            Log.i("mychilk", "防触屏触发");
        }
        time = System.currentTimeMillis();
    }
    public abstract void onMyChilk();
}
