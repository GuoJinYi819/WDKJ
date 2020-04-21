package com.wd.tech.bean.wybean.beanperson;

import java.io.Serializable;

/**
 * @author 王阳
 * Class :1708A
 * @description:
 * @date :2020/4/21 9:45
 * @classname :CommunityUserPostVoListBean
 */
public class CommunityUserPostVoListBean implements Serializable {
    private String content;
    private String file;
    private int comment;
    private int id;
    private int praise;
    private int whetherGreat;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPraise() {
        return praise;
    }

    public void setPraise(int praise) {
        this.praise = praise;
    }

    public int getWhetherGreat() {
        return whetherGreat;
    }

    public void setWhetherGreat(int whetherGreat) {
        this.whetherGreat = whetherGreat;
    }
}
