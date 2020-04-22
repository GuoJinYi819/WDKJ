package com.wd.tech.bean.gjybean;

import java.io.Serializable;
import java.util.List;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/4/21 0021 21:37
 * @Description: 用途：完成特定功能
 */
public class FriendNoticeBean implements Serializable {

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
         * fromHeadPic : http://mobile.bwstudent.com/images/tech/default/tech.jpg
         * fromNickName : 郭金沂三号
         * fromUid : 1405
         * noticeId : 1076
         * noticeTime : 1587467161000
         * receiveUid : 1382
         * remark : 郭金沂七号
         * status : 2
         */

        private String fromHeadPic;
        private String fromNickName;
        private int fromUid;
        private int noticeId;
        private long noticeTime;
        private int receiveUid;
        private String remark;
        private int status;

        public String getFromHeadPic() {
            return fromHeadPic;
        }

        public void setFromHeadPic(String fromHeadPic) {
            this.fromHeadPic = fromHeadPic;
        }

        public String getFromNickName() {
            return fromNickName;
        }

        public void setFromNickName(String fromNickName) {
            this.fromNickName = fromNickName;
        }

        public int getFromUid() {
            return fromUid;
        }

        public void setFromUid(int fromUid) {
            this.fromUid = fromUid;
        }

        public int getNoticeId() {
            return noticeId;
        }

        public void setNoticeId(int noticeId) {
            this.noticeId = noticeId;
        }

        public long getNoticeTime() {
            return noticeTime;
        }

        public void setNoticeTime(long noticeTime) {
            this.noticeTime = noticeTime;
        }

        public int getReceiveUid() {
            return receiveUid;
        }

        public void setReceiveUid(int receiveUid) {
            this.receiveUid = receiveUid;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
