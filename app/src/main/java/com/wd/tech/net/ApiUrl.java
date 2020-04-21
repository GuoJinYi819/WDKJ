package com.wd.tech.net;

/**
 * @author 王阳
 * Class :1708A
 * @description:
 * @date :2020/4/17 19:29
 * @classname :ApiUrl
 */
public interface ApiUrl {
    String URL="https://mobile.bwstudent.com/techApi/";
    //登录
    String LOGURL="user/v1/login";
    //注册
    String REGISTER_URL="user/v1/register";
    //咨询页 xbanner
    String CONSULTATION_XBANNER_URL="information/v1/bannerShow";
    //消息首页
    String NEWS_URL="";
    //社区首页
    String COMMUNITY_URL="community/v1/findCommunityList";
    //发布帖子
    String COMMENT_URL="community/verify/v1/releasePost";
    //社区用户评论列表（bean方式返参）
    String COMMENTLIST_URL="community/v1/findCommunityUserCommentList";
    //咨讯列表
    String CONSULTATION_LIST_URL="information/v1/infoRecommendList";
    //获取二级列表数据
    String GETFRIENDGROUPLISTDATA = "chat/verify/v1/findFriendGroupList";
    //获取二级子列表数据
    String GETFRIENDCHILDLISTDATA = "chat/verify/v1/findFriendListByGroupId";
    //模糊搜索联系人
    String FIENDSEACHUSER = "chat/verify/v1/searchFriend";
}
