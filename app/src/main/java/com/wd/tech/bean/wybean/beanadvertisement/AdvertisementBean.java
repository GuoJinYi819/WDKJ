package com.wd.tech.bean.wybean.beanadvertisement;

import java.io.Serializable;

/**
 * @author 王阳
 * Class :1708A
 * @description:
 * @date :2020/4/29 14:18
 * @classname :AdvertisementBean
 */
public class AdvertisementBean implements Serializable {
    private ResultBean result;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }
}
