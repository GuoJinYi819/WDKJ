package com.wd.tech.net;

import com.wd.tech.bean.gjybean.FriendChildListBean;
import com.wd.tech.bean.gjybean.FriendDataBean;
import com.wd.tech.bean.gjybean.FriendGroupListBean;
import com.wd.tech.bean.gjybean.FriendNoticeBean;
import com.wd.tech.bean.gjybean.FriendSeachBean;
import com.wd.tech.bean.gjybean.GroupNoticeBean;
import com.wd.tech.bean.gjybean.JoinedGroupBean;
import com.wd.tech.bean.gjybean.ReviewFriendApplyBean;
import com.wd.tech.bean.qzjbean.consultationlist.ConListBean;
import com.wd.tech.bean.qzjbean.detail.DetailBean;
import com.wd.tech.bean.qzjbean.log.LogBean;
import com.wd.tech.bean.qzjbean.regist.RegBean;
import com.wd.tech.bean.qzjbean.seach.SeachBean;
import com.wd.tech.bean.qzjbean.xbanner.XbBean;
import com.wd.tech.bean.wybean.beancollectionlist.CollectionListBean;
import com.wd.tech.bean.wybean.beancomment.CommentBean;
import com.wd.tech.bean.wybean.beancommentlist.CommentListBean;
import com.wd.tech.bean.wybean.beanfollow.FollowBean;
import com.wd.tech.bean.wybean.beanhome.HomeBean;
import com.wd.tech.bean.wybean.beanperson.PersonBean;
import com.wd.tech.bean.wybean.beansign.SignBean;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
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
    //我的  收藏页
    @GET(ApiUrl.FOLLOW_URL)
    Observable<FollowBean> getFollowData(@Query("page")int page, @Query("count")int count);
    @GET(ApiUrl.DETAILS_URL)
    Observable<DetailBean> getDetailsData(@Query("id")int id);
}
