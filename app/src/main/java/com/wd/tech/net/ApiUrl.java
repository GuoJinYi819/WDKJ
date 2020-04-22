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
    //查询用户发布的帖子
    String COMMENTPERSON_URL="community/verify/v1/findUserPostById";
    //模糊搜索联系人
    String FIENDSEACHUSER = "chat/verify/v1/searchFriend";
    //查询所有的群组
    String JOINEDGROUP = "group/verify/v1/findUserJoinedGroup";
    //查询所有的好友
    String FRIENDDATA = "chat/verify/v1/searchFriend?searchName";
    //查询好友通知
    String FRIENDNOTICE = "chat/verify/v1/findFriendNoticePageList";
    //查询群通知
    String GROUPNOTICE = "group/verify/v1/findGroupNoticePageList";
}
