package com.wd.tech.bean.wybean.beanfollow;

import java.io.Serializable;

/**
 * @author 王阳
 * Class :1708A
 * @description:
 * @date :2020/4/22 20:08
 * @classname :ResultBean
 */
public class ResultBean implements Serializable {
    private long focusTime;
    private int focusUid;
    private String headPic;
    private String nickName;
    private int whetherMutualFollow;
    private int whetherVip;

    public long getFocusTime() {
        return focusTime;
    }

    public void setFocusTime(long focusTime) {
        this.focusTime = focusTime;
    }

    public int getFocusUid() {
        return focusUid;
    }

    public void setFocusUid(int focusUid) {
        this.focusUid = focusUid;
    }

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getWhetherMutualFollow() {
        return whetherMutualFollow;
    }

    public void setWhetherMutualFollow(int whetherMutualFollow) {
        this.whetherMutualFollow = whetherMutualFollow;
    }

    public int getWhetherVip() {
        return whetherVip;
    }

    public void setWhetherVip(int whetherVip) {
        this.whetherVip = whetherVip;
    }
}
