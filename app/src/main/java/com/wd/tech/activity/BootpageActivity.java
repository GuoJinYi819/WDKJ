package com.wd.tech.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.base.BasePresenter;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : Quzijun
 * @version 创建时间：2020/4/17 20:02
 * @Description: 用途：引导页
 */
public class BootpageActivity extends BaseActivity {
    private android.widget.ImageView img;

    @Override
    public int initLayout() {
        return R.layout.bootpageactivity;
    }

    @Override
    public void initView() {

        img = (ImageView) findViewById(R.id.img);
    }

    @Override
    public void initListener() {
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BootpageActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }
}
