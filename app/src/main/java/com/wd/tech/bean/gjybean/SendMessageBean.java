package com.wd.tech.bean.gjybean;

import java.io.Serializable;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/4/23 0023 15:45
 * @Description: 用途：完成特定功能
 */
public class SendMessageBean implements Serializable {

    private String message;
    private String status;

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }

}
