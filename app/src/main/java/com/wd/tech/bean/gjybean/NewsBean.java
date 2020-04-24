package com.wd.tech.bean.gjybean;

import java.io.Serializable;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/4/24 0024 14:08
 * @Description: 用途：完成特定功能
 */
public class NewsBean implements Serializable {
    private String img;
    private String name;
    private String userId;
    private String time;

    public NewsBean(String img, String name, String userId,String time) {
        this.img = img;
        this.name = name;
        this.userId = userId;
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
