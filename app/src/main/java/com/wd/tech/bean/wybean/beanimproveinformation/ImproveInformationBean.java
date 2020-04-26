package com.wd.tech.bean.wybean.beanimproveinformation;

import java.io.Serializable;

/**
 * @author 王阳
 * Class :1708A
 * @description:
 * @date :2020/4/25 19:22
 * @classname :ImproveInformationBean
 */
public class ImproveInformationBean implements Serializable {
    private String message;
    private String status;

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
