package com.wd.tech.bean.wybean.beanbuyvip;

import java.io.Serializable;

/**
 * @author 王阳
 * Class :1708A
 * @description:
 * @date :2020/4/26 15:33
 * @classname :BuyVipBean
 */
public class BuyVipBean implements Serializable {
    private String orderId;
    private String message;
    private String status;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
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
