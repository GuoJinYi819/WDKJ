package com.wd.tech.bean.gjybean;

import java.io.Serializable;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/5/1 0001 14:13
 * @Description: 用途：完成特定功能
 */
public class BuyBean implements Serializable {
    private String status;
    private String message;
    private String prepayId;
    private String partnerId;
    private String nonceStr;
    private String timeStamp;
    private String sign;
    private String appId;
    private String packageValue;
    private String result;

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getPrepayId() {
        return prepayId;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public String getSign() {
        return sign;
    }

    public String getAppId() {
        return appId;
    }

    public String getPackageValue() {
        return packageValue;
    }

    public String getResult() {
        return result;
    }
}
