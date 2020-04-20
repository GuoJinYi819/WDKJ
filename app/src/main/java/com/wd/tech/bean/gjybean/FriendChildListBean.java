package com.wd.tech.bean.gjybean;

import java.io.Serializable;
import java.util.List;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/4/20 0020 14:26
 * @Description: 用途：二级列表 group数据
 */
public class FriendChildListBean implements Serializable {

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
         * friendUid : 1404
         * headPic : http://mobile.bwstudent.com/images/tech/default/tech.jpg
         * nickName : 郭金沂二号
         * remarkName : 郭金沂二号
         * userName : l54rFb15621273701
         * vipFlag : 2
         */

        private int friendUid;
        private String headPic;
        private String nickName;
        private String remarkName;
        private String userName;
        private int vipFlag;
        private String signature;

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public String getSignature() {
            return signature;
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

        public int getVipFlag() {
            return vipFlag;
        }

        public void setVipFlag(int vipFlag) {
            this.vipFlag = vipFlag;
        }
    }
}
