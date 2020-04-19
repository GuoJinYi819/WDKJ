package com.wd.tech.bean.wybean.beancomment;

import java.io.Serializable;

/**
 * @author 王阳
 * Class :1708A
 * @description:
 * @date :2020/4/19 19:10
 * @classname :CommentBean
 */
public class CommentBean implements Serializable {
    private String status;
    private String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
