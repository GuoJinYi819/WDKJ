package com.wd.tech.bean.wybean.beancommunitycommentList;

import java.io.Serializable;
import java.util.List;

/**
 * @author 王阳
 * Class :1708A
 * @description:
 * @date :2020/4/26 20:20
 * @classname :CommunityCommentListBean
 */
public class CommunityCommentListBean implements Serializable {
    private List<String> result;

    public List<String> getResult() {
        return result;
    }

    public void setResult(List<String> result) {
        this.result = result;
    }
}
