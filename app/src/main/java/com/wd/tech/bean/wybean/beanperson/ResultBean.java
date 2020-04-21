package com.wd.tech.bean.wybean.beanperson;

import java.io.Serializable;
import java.util.List;

/**
 * @author 王阳
 * Class :1708A
 * @description:
 * @date :2020/4/21 9:44
 * @classname :ResultBean
 */
public class ResultBean implements Serializable {
    private List<CommunityUserPostVoListBean> communityUserPostVoList;
    private CommunityUserVoBean communityUserVo;

    public List<CommunityUserPostVoListBean> getCommunityUserPostVoList() {
        return communityUserPostVoList;
    }

    public void setCommunityUserPostVoList(List<CommunityUserPostVoListBean> communityUserPostVoList) {
        this.communityUserPostVoList = communityUserPostVoList;
    }

    public CommunityUserVoBean getCommunityUserVo() {
        return communityUserVo;
    }

    public void setCommunityUserVo(CommunityUserVoBean communityUserVo) {
        this.communityUserVo = communityUserVo;
    }
}
