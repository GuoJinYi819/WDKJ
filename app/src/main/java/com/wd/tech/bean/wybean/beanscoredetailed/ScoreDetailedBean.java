package com.wd.tech.bean.wybean.beanscoredetailed;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 王阳
 * Class :1708A
 * @description:
 * @date :2020/4/25 15:56
 * @classname :ScoreDetailedBean
 */
public class ScoreDetailedBean implements Serializable {
    private ArrayList<ResultBean> result;

    public ArrayList<ResultBean> getResult() {
        return result;
    }

    public void setResult(ArrayList<ResultBean> result) {
        this.result = result;
    }
}
