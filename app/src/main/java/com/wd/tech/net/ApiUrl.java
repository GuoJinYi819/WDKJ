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
    //咨讯模糊查询
    String SEACH_URL = "information/v1/findInformationByTitle";
    //审核好友
    String REVIEWFRIENDAPPLY = "chat/verify/v1/reviewFriendApply";
    //我的  签到
    String SIGN_URL="user/verify/v1/userSign";
    //审核群通知
    String REVIEWGROUPAPPLY = "group/verify/v1/reviewGroupApply";
    //我的  收藏页
    String COLLECTIONLIST_URL="user/verify/v1/findAllInfoCollection";
    //我的  关注页
    String FOLLOW_URL="user/verify/v1/findFollowUserList";
    //咨讯详情
    String DETAILS_URL="information/v1/findInformationDetails";
    //查询好友信息
    String QUERYFRIEND = "user/verify/v1/queryFriendInformation";
    //查询聊天记录
    String DIALOGUERECORD = "chat/verify/v1/findDialogueRecordPageList";
    //发送消息
    String SENDMESSAGE = "chat/verify/v1/sendMessage";

    //我的   做任务   加分
    String DOTASK_URL="user/verify/v1/doTheTask";
    //根据用户ID查询用户信息
    String SELECT_USER_URL="user/verify/v1/getUserInfoByUserId";
    //我的 帖子
    String MYPOST_URL="community/verify/v1/findMyPostById";
    //查询群聊天记录
    String QUERYGROUP = "group/verify/v1/findGroupChatRecordPage";
    //发送群消息
    String SENDGROUP = "group/verify/v1/sendGroupMessage";
    //咨讯评论
    String CONSULTATION_COMMENT_URL = "information/v1/findAllInfoCommentList";
    //添加评论
    String CONSULTATION_ADDCOMMENT_URL = "information/verify/v1/addInfoComment";
    //咨讯点赞
    String CONSULTATION_GREATRECORD_URL = "information/verify/v1/addGreatRecord";
    //咨讯取消点赞
    String CONSULTATION_CANCELGREAT_URL = "information/verify/v1/cancelGreat";
    //删除帖子  只能是自己的
    String DELETEPOST_URL="community/verify/v1/deletePost";
    //通知
    String NOTICE_URL="tool/verify/v1/findSysNoticeList";
    //积分
    String SCORE_URL="user/verify/v1/findUserIntegral";
    //积分明细
    String SCOREDETAILED_URL="user/verify/v1/findUserIntegralRecord";
    //完善用户 信息
    String IMPROVEINFORMATION_URL="user/verify/v1/perfectUserInfo";
    //添加好友
    String ADDFRIEND = "chat/verify/v1/addFriend";
    //添加群
    String ADDGROUP = "group/verify/v1/applyAddGroup";
    //创建群
    String CREATEGROUP = "group/verify/v1/createGroup";
    //创建自定义分组
    String ADDFRIENDGROUP = "chat/verify/v1/addFriendGroup";
    //转移好友值 其他分组
    String TRANSFERGROUP = "chat/verify/v1/transferFriendGroup";
    //删除好友聊天记录
    String DELETECHAT  = "chat/verify/v1/deleteChatRecord";
    //删除好友
    String DELETEFRIEND = "chat/verify/v1/deleteFriendRelation";
    //查询群组详情
    String FINDGROUPINFO = "group/verify/v1/findGroupInfo";
    //查询群组好友信息
    String GETGROUPMEMBERLIST = "group/verify/v1/findGroupMemberList";
    //修改群简介
    String MODIFYGROUPDESCRIPTION = "group/verify/v1/modifyGroupDescription";
    //查询所有会员商品
    String SELECTVIPLIST_URL="tool/v1/findVipCommodityList";
    //VIP 下单
    String BUYVIP_URL="tool/verify/v1/buyVip";
    //支付
    String BUY_URL="tool/verify/v1/pay";
    //社区评论列表（标签方式返参）
    String COMMUNITYCOMMENTLIST_URL="community/v1/findCommunityCommentList";
    //社区 评论
    String SENDCOMMENT_URL="community/verify/v1/addCommunityComment";
    //用户上传/更换头像
    String MODIFYHEADPIC_URL="user/verify/v1/modifyHeadPic";
}
