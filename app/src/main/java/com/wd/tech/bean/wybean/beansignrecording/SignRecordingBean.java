package com.wd.tech.bean.wybean.beansignrecording;

import java.io.Serializable;
import java.util.List;

/**
 * @author 王阳
 * Class :1708A
 * @description:
 * @date :2020/5/5 9:25
 * @classname :SignRecordingBean
 */
public class SignRecordingBean implements Serializable {
    private List<String> result;
    private String message;
    private String status;
    private int conSign;
    private int isSign=1;
    private int code;
    private String[] SignDay;
    public List<String> getResult() {
        return result;
    }

    public void setResult(List<String> result) {
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

    public int getConSign() {
        return conSign;
    }

    public void setConSign(int conSign) {
        this.conSign = conSign;
    }

    public int getIsSign() {
        return isSign;
    }

    public void setIsSign(int isSign) {
        this.isSign = isSign;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String[] getSignDay() {
        return SignDay;
    }

    public void setSignDay(String[] signDay) {
        SignDay = signDay;
    }
}
