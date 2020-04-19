package com.wd.tech.net;

import com.wd.tech.bean.qzjbean.log.LogBean;
import com.wd.tech.bean.qzjbean.regist.RegBean;
import com.wd.tech.bean.qzjbean.xbanner.XbBean;
import com.wd.tech.bean.wybean.beancomment.CommentBean;
import com.wd.tech.bean.wybean.beanhome.HomeBean;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

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
}
