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

    public List<String> getResult() {
        return result;
    }

    public void setResult(List<String> result) {
        this.result = result;
    }
}
