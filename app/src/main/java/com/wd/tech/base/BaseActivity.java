package com.wd.tech.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author 王阳
 * Class :1708A
 * @description:
 * @date :2020/4/17 18:32
 * @classname :BaseActivity
 */
public abstract class BaseActivity<p extends BasePresenter> extends AppCompatActivity implements IBaseView{
    public p presenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //
        int initLayout = initLayout();
        setContentView(initLayout);
        presenter=initPresenter();
        if(presenter!=null){
            presenter.attachView(this);
        }
        initView();
        initListener();
        initData();
    }
    //
    public abstract int initLayout();
    public abstract void initView();
    public abstract void initListener();
    public abstract void initData();
    public abstract p initPresenter();
    //销毁
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(presenter!=null){
            presenter.detachView();
        }
    }
}
