package com.wd.tech.bean.wybean.beancommentlist;

import java.io.Serializable;
import java.util.List;

/**
 * @author 王阳
 * Class :1708A
 * @description:
 * @date :2020/4/20 8:17
 * @classname :CommentListBean
 */
public class CommentListBean implements Serializable {
    private List<ResultBean> result;

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }
}
