package com.wd.tech.bean.gjybean;

import java.io.Serializable;
import java.util.List;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/4/21 0021 9:49
 * @Description: 用途：完成特定功能
 */
public class FriendSeachBean implements Serializable {

    private String message;
    private String status;
    private List<ResultBean> result;

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

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * buddySource : 我的好友
         * friendUid : 1404
         * headPic : http://mobile.bwstudent.com/images/tech/default/tech.jpg
         * nickName : 郭金沂二号
         * pwd : b5F0wVV2rlS7HNB2L3+2RyT4EcK+NBXxRzCJPqptcLTpU4u9yPvsihFQxSiNnhOazEKsrvNbbAHkpY1OwKNG/FNMONzGt7QOsdFHBeOA0SO7MUmOdxQ+J6xUa9r3NKLfbTy6oETx6Jrl6AihjdNiow3v0pFjYtIFZqu4pKXUYqk=
         * remarkName : 郭金沂二号
         * userName : l54rFb15621273701
         */

        private String buddySource;
        private int friendUid;
        private String headPic;
        private String nickName;
        private String pwd;
        private String remarkName;
        private String userName;

        public String getBuddySource() {
            return buddySource;
        }

        public void setBuddySource(String buddySource) {
            this.buddySource = buddySource;
        }

        public int getFriendUid() {
            return friendUid;
        }

        public void setFriendUid(int friendUid) {
            this.friendUid = friendUid;
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

        public String getPwd() {
            return pwd;
        }

        public void setPwd(String pwd) {
            this.pwd = pwd;
        }

        public String getRemarkName() {
            return remarkName;
        }

        public void setRemarkName(String remarkName) {
            this.remarkName = remarkName;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
    }
}
