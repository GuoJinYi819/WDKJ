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
}
