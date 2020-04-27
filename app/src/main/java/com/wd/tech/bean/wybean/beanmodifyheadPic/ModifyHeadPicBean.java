package com.wd.tech.bean.wybean.beanmodifyheadPic;

import java.io.Serializable;

/**
 * @author 王阳
 * Class :1708A
 * @description:
 * @date :2020/4/27 13:16
 * @classname :ModifyHeadPicBean
 */
public class ModifyHeadPicBean implements Serializable {
    private String result;
    private String message;
    private String status;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
