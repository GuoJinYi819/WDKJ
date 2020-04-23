package com.wd.tech.bean.wybean.beandotask;

import java.io.Serializable;

/**
 * @author 王阳
 * Class :1708A
 * @description:
 * @date :2020/4/23 19:26
 * @classname :DoTaskBean
 */
public class DoTaskBean implements Serializable {
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
