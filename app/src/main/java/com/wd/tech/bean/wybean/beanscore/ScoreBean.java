package com.wd.tech.bean.wybean.beanscore;

import java.io.Serializable;

/**
 * @author 王阳
 * Class :1708A
 * @description:
 * @date :2020/4/25 15:52
 * @classname :ScoreBean
 */
public class ScoreBean implements Serializable {
    private ResultBean result;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }
}
