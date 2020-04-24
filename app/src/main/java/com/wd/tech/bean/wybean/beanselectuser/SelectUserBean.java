package com.wd.tech.bean.wybean.beanselectuser;

import java.io.Serializable;

/**
 * @author 王阳
 * Class :1708A
 * @description:
 * @date :2020/4/23 20:31
 * @classname :SelectUserBean
 */
public class SelectUserBean implements Serializable {
    private ResultBean result;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }
}
