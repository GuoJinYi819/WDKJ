package com.wd.tech.bean.wybean.beanperson;

import java.io.Serializable;

/**
 * @author 王阳
 * Class :1708A
 * @description:
 * @date :2020/4/21 9:45
 * @classname :CommunityUserVoBean
 */
public class CommunityUserVoBean implements Serializable {
    private String headPic;
    private String nickName;
    private int power;
    private String signature;
    private int userId;
    private int whetherFollow;
    private int whetherMyFriend;

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

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getWhetherFollow() {
        return whetherFollow;
    }

    public void setWhetherFollow(int whetherFollow) {
        this.whetherFollow = whetherFollow;
    }

    public int getWhetherMyFriend() {
        return whetherMyFriend;
    }

    public void setWhetherMyFriend(int whetherMyFriend) {
        this.whetherMyFriend = whetherMyFriend;
    }
}
