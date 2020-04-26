package com.wd.tech.bean.gjybean;

import java.io.Serializable;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/4/26 0026 10:58
 * @Description: 用途：完成特定功能
 */
public class DeleteChatBean implements Serializable {
    private String message;
    private String status;

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }
}
