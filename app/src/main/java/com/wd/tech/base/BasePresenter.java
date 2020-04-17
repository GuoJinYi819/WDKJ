package com.wd.tech.base;

/**
 * @author 王阳
 * Class :1708A
 * @description:
 * @date :2020/4/17 18:29
 * @classname :BasePresenter
 */
public abstract class BasePresenter<view extends IBaseView> {
    public view iBaseView;
    public BasePresenter() {
    }

    public void attachView(view iBaseView) {
        this.iBaseView = iBaseView;
    }
    public void detachView(){
        if(iBaseView!=null){
            iBaseView=null;
        }
    }

    public abstract void initModel();
}
