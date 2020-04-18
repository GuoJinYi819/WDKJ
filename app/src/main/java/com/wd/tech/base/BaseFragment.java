package com.wd.tech.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @author 王阳
 * Class :1708A
 * @description:
 * @date :2020/4/17 18:51
 * @classname :BaseFragment
 */
public abstract class BaseFragment<p extends BasePresenter> extends Fragment implements IBaseView {
    public p presenter;
    public View view;
    private boolean isInitData = false;
    private boolean isInitView = false;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int initLayout = initLayout();
        view = View.inflate(getContext(), initLayout, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter=initPresenter();
        if(presenter!=null){
            presenter.attachView(this);
        }
        //
        initView();
        initListener();
        isInitView = true;

        if (getUserVisibleHint()) {
            initData();
            isInitData = true;
        }

    }
    //提前
    public abstract int initLayout();
    public abstract void initView();
    public abstract void initListener();
    public abstract void initData();
    public abstract p initPresenter();

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser&&!isInitData&&isInitView){
            initData();
            isInitData = true;
        }

    }

    //销毁
    @Override
    public void onDestroy() {
        super.onDestroy();
        if(presenter!=null){
            presenter.detachView();
        }
    }
}
