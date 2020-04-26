package com.wd.tech.net;

import com.wd.tech.bean.gjybean.AddFriendBean;
import com.wd.tech.bean.gjybean.AddFriendGroupBean;
import com.wd.tech.bean.gjybean.CreateGroupBean;
import com.wd.tech.bean.gjybean.DeleteChatBean;
import com.wd.tech.bean.gjybean.DialogueRecordBean;
import com.wd.tech.bean.gjybean.FriendChildListBean;
import com.wd.tech.bean.gjybean.FriendDataBean;
import com.wd.tech.bean.gjybean.FriendGroupListBean;
import com.wd.tech.bean.gjybean.FriendNoticeBean;
import com.wd.tech.bean.gjybean.FriendSeachBean;
import com.wd.tech.bean.gjybean.GroupInfoBean;
import com.wd.tech.bean.gjybean.GroupMemberListBean;
import com.wd.tech.bean.gjybean.GroupNoticeBean;
import com.wd.tech.bean.gjybean.JoinedGroupBean;
import com.wd.tech.bean.gjybean.ModifyGroupDescriptionBean;
import com.wd.tech.bean.gjybean.QueryFriendBean;
import com.wd.tech.bean.gjybean.QueryGroupBean;
import com.wd.tech.bean.gjybean.ReviewFriendApplyBean;
import com.wd.tech.bean.gjybean.SendGroupBean;
import com.wd.tech.bean.gjybean.SendMessageBean;
import com.wd.tech.bean.qzjbean.addcomment.AddBean;
import com.wd.tech.bean.qzjbean.comment.ConCommentBean;
import com.wd.tech.bean.gjybean.TransferFriendBean;
import com.wd.tech.bean.qzjbean.consultationlist.ConListBean;
import com.wd.tech.bean.qzjbean.detail.DetailBean;
import com.wd.tech.bean.qzjbean.great.GreatBean;
import com.wd.tech.bean.qzjbean.log.LogBean;
import com.wd.tech.bean.qzjbean.regist.RegBean;
import com.wd.tech.bean.qzjbean.seach.SeachBean;
import com.wd.tech.bean.qzjbean.xbanner.XbBean;
import com.wd.tech.bean.wybean.beanbuyvip.BuyVipBean;
import com.wd.tech.bean.wybean.beancollectionlist.CollectionListBean;
import com.wd.tech.bean.wybean.beancomment.CommentBean;
import com.wd.tech.bean.wybean.beancommentlist.CommentListBean;
import com.wd.tech.bean.wybean.beandeletepost.DeletePostBean;
import com.wd.tech.bean.wybean.beandotask.DoTaskBean;
import com.wd.tech.bean.wybean.beanfollow.FollowBean;
import com.wd.tech.bean.wybean.beanhome.HomeBean;
import com.wd.tech.bean.wybean.beanimproveinformation.ImproveInformationBean;
import com.wd.tech.bean.wybean.beanmypost.MyPostBean;
import com.wd.tech.bean.wybean.beannotice.NoticeBean;
import com.wd.tech.bean.wybean.beanperson.PersonBean;
import com.wd.tech.bean.wybean.beanscore.ScoreBean;
import com.wd.tech.bean.wybean.beanscoredetailed.ScoreDetailedBean;
import com.wd.tech.bean.wybean.beanselectuser.SelectUserBean;
import com.wd.tech.bean.wybean.beanselectviplist.SelectVipListBean;
import com.wd.tech.bean.wybean.beansign.SignBean;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * @author 王阳
 * Class :1708A
 * @description:
 * @date :2020/4/17 19:22
 * @classname :ApiService
 */
public interface ApiService {
    //登录
    @POST(ApiUrl.LOGURL)
    @FormUrlEncoded
    Observable<LogBean> getLog(@Field("phone")String phone,@Field("pwd")String pwd);
    //注册
    @POST(ApiUrl.REGISTER_URL)
    @FormUrlEncoded
    Observable<RegBean> getReg(@Field("phone")String phone, @Field("nickName")String name, @Field("pwd")String pwd);
    //社区首页列表
    @GET(ApiUrl.COMMUNITY_URL)
    Observable<HomeBean> getHomeData(@Query("page")int page,@Query("count")int count);
    //发布帖子
    @POST(ApiUrl.COMMENT_URL)
    @Multipart
    Observable<CommentBean> getCommentData(@Query("content")String content, @Part MultipartBody.Part file);
    //咨讯Xbanner
    //
    @GET(ApiUrl.CONSULTATION_XBANNER_URL)
    Observable<XbBean> getXbData();
    //社区用户评论（bean方式反参）
    //
    @GET(ApiUrl.COMMENTLIST_URL)
    Observable<CommentListBean> getCommentListData(@Query("communityId")int communityId, @Query("page")int page, @Query("count")int count);
    //
    //咨讯列表
    @GET(ApiUrl.CONSULTATION_LIST_URL)
    //列表方法
    Observable<ConListBean> getListData(@Query("plateId")int plateId, @Query("page")int page, @Query("count")int count);

    //获取二级列表数据
    //
    @GET(ApiUrl.GETFRIENDGROUPLISTDATA)
    Observable<FriendGroupListBean> getFriendGroupList();
    //获取二级子列表数据
    @GET(ApiUrl.GETFRIENDCHILDLISTDATA)
    Observable<FriendChildListBean> getFriendChildList(@Query("groupId") int groupId);
    //查询用户发布的帖子
    //
    @GET(ApiUrl.COMMENTPERSON_URL)
    Observable<PersonBean> getPersonData(@Query("fromUid")int fromUid,@Query("page")int page,@Query("count")int count);
    //搜索联系人
    @GET(ApiUrl.FIENDSEACHUSER)
    Observable<FriendSeachBean> getFriendSeachData(@Query("searchName")String searchName);
    //查询所有的群组
    @GET(ApiUrl.JOINEDGROUP)
    Observable<JoinedGroupBean> joinedGroup();
    //获取所有的好友
    @GET(ApiUrl.FRIENDDATA)
    Observable<FriendDataBean> getFriendData();
    //获取好友通知记录
    @GET(ApiUrl.FRIENDNOTICE)
    Observable<FriendNoticeBean> getFriendNotice(@QueryMap Map<String,String> params);
    //查询群通知
    @GET(ApiUrl.GROUPNOTICE)
    Observable<GroupNoticeBean> getGroupNotice(@QueryMap Map<String,String> params);
    //咨讯模糊查询
    @GET(ApiUrl.SEACH_URL)
    Observable<SeachBean> getSeachData(@Query("title")String title, @Query("page")int page, @Query("count")int count);
    //审核好友
    @GET(ApiUrl.REVIEWFRIENDAPPLY)
    Observable<ReviewFriendApplyBean> getReviewFriendApply(@Query("noticeId")int noticeId,@Query("flag")int flag);
    //我的  签到
    @POST(ApiUrl.SIGN_URL)
    //@FormUrlEncoded   因为没有参数  所以去掉该注解
    Observable<SignBean> getSignData();
    //我的  收藏页
    @GET(ApiUrl.COLLECTIONLIST_URL)
    Observable<CollectionListBean> getCollectionListData(@Query("page")int page,@Query("count")int count);
    //审核群通知
    @PUT(ApiUrl.REVIEWGROUPAPPLY)
    Observable<ReviewFriendApplyBean> getReviewGroupApply(@Query("noticeId")int noticeId,@Query("flag")int flag);
    //我的  收藏页
    @GET(ApiUrl.FOLLOW_URL)
    Observable<FollowBean> getFollowData(@Query("page")int page, @Query("count")int count);
   //咨讯详情
    @GET(ApiUrl.DETAILS_URL)
    Observable<DetailBean> getDetailsData(@Query("id")int id);
    //查询我的好友信息
    @GET(ApiUrl.QUERYFRIEND)
    Observable<QueryFriendBean> queryFriend(@Query("friend") int friend);
    //查询与好友的聊天记录
    @GET(ApiUrl.DIALOGUERECORD)
    Observable<DialogueRecordBean> getDialogueRecord(@QueryMap Map<String,String> params);
    //发送消息
    @POST(ApiUrl.SENDMESSAGE)
    @FormUrlEncoded
    Observable<SendMessageBean> sendMessage(@FieldMap Map<String,String> params);
    //我的  做任务  加分
    @POST(ApiUrl.DOTASK_URL)
    @FormUrlEncoded
    Observable<DoTaskBean> getDoTaskData(@Field("taskId")int taskId);
    //根据用户ID查询用户信息
    @GET(ApiUrl.SELECT_USER_URL)
    Observable<SelectUserBean> getSelectUserData();
    //我的贴子
    @GET(ApiUrl.MYPOST_URL)
    Observable<MyPostBean> getMyPostData(@Query("page")int page, @Query("count")int count);
    //查询群消息
    @GET(ApiUrl.QUERYGROUP)
    Observable<QueryGroupBean> queryGroup(@QueryMap Map<String,String> params);
    //发送群消息
    @POST(ApiUrl.SENDGROUP)
    @FormUrlEncoded
    Observable<SendGroupBean> sendGroup(@FieldMap Map<String,String> params);
    //咨讯评论查询
    @GET(ApiUrl.CONSULTATION_COMMENT_URL)
    Observable<ConCommentBean> getCommentData(@Query("infoId")int infoId,@Query("page")int page,@Query("count")int count);
    //咨讯添加评论
    @POST(ApiUrl.CONSULTATION_ADDCOMMENT_URL)
    @FormUrlEncoded
    Observable<AddBean> getAddData(@Field("infoId") int infoId, @Field("content") String content);
    @POST(ApiUrl.CONSULTATION_GREATRECORD_URL)
    @FormUrlEncoded
    Observable<GreatBean> getDDate(@Field("infoId") int infoId);
    @DELETE(ApiUrl.CONSULTATION_CANCELGREAT_URL)
    Observable<GreatBean> getNDate(@Query("infoId") int infoId);
    //添加好友
    @POST(ApiUrl.ADDFRIEND)
    @FormUrlEncoded
    Observable<AddFriendBean> addFriend(@FieldMap Map<String,String> params);
    //添加群
    @POST(ApiUrl.ADDGROUP)
    @FormUrlEncoded
    Observable<AddFriendBean> addGroup(@FieldMap Map<String,String> params);
    //创建群
    @POST(ApiUrl.CREATEGROUP)
    @FormUrlEncoded
    Observable<CreateGroupBean> createGroup(@FieldMap Map<String,String> params);
    //创建自定义分组
    @POST(ApiUrl.ADDFRIENDGROUP)
    @FormUrlEncoded
    Observable<AddFriendGroupBean> addFriendGroup(@Field("groupName")String groupName);
    //转移好友值 其他分组
    @PUT(ApiUrl.TRANSFERGROUP)
    Observable<TransferFriendBean> transferGroup(@Query("newGroupId")int newGroupId, @Query("friendUid")int friendUid);
    //删除帖子  我的
    @DELETE(ApiUrl.DELETEPOST_URL)
    Observable<DeletePostBean> getDeletePostData(@Query("communityId")String communityId);
    //系统通知
    @GET(ApiUrl.NOTICE_URL)
    Observable<NoticeBean> getNoticeData(@Query("page")int page, @Query("count")int count);
    //用户积分
    @GET(ApiUrl.SCORE_URL)
    Observable<ScoreBean> getScoreData();
    //用户积分明细
    @GET(ApiUrl.SCOREDETAILED_URL)
    Observable<ScoreDetailedBean> getScoreDetailedData(@Query("page")int page, @Query("count")int count);
    //发送群消息
    @POST(ApiUrl.IMPROVEINFORMATION_URL)
    @FormUrlEncoded
    Observable<ImproveInformationBean> getImproveInformationData(@Field("nickName")String nickName,@Field("sex")int sex,@Field("signature")String signature,@Field("birthday")String birthday,@Field("email")String email);
    //vip 商品列表
    @GET(ApiUrl.SELECTVIPLIST_URL)
    Observable<SelectVipListBean> getSelectVipListData();
    //购买  下单
    @POST(ApiUrl.BUYVIP_URL)
    @FormUrlEncoded
    Observable<BuyVipBean> getBuyVipData(@Field("commodityId")int commodityId, @Field("sign")String sign);
    //购买
    /*@POST(ApiUrl.BUY_URL)
    @FormUrlEncoded
    Observable<BuyVipBean> getBuyVipData(@Field("orderId")String orderId, @Field("payType")int payType);*/
    //删除好友的聊天记录
    @DELETE(ApiUrl.DELETECHAT)
    Observable<DeleteChatBean> deleteChatRecord(@Query("friendUid")int friendUid);
    //删除好友
    @DELETE(ApiUrl.DELETEFRIEND)
    Observable<DeleteChatBean> deleteFriend(@Query("friendUid")int friendUid);
    //查询群聊详情页
    @GET(ApiUrl.FINDGROUPINFO)
    Observable<GroupInfoBean> getGroupInfo(@Query("groupId") int groupId);
    //查询群中所有好友
    @GET(ApiUrl.GETGROUPMEMBERLIST)
    Observable<GroupMemberListBean> getGroupMemberList(@Query("groupId")int groupId);
    //修改群简介
    @PUT(ApiUrl.MODIFYGROUPDESCRIPTION)
    Observable<ModifyGroupDescriptionBean> modifyGroupDescription(@Query("groupId")int groupId, @Query("description")String description);

}
