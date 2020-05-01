package com.wd.tech.bean.wybean.beanusertasklist;

import java.io.Serializable;
import java.util.List;

/**
 * @author 王阳
 * Class :1708A
 * @description:
 * @date :2020/5/1 16:24
 * @classname :UserTaskListBean
 */
public class UserTaskListBean implements Serializable {
    private List<ResultBean> result;

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }
}
