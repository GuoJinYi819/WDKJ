package com.wd.tech.net;

import com.wd.tech.bean.qzjbean.log.LogBean;
import com.wd.tech.bean.qzjbean.regist.RegBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * @author 王阳
 * Class :1708A
 * @description:
 * @date :2020/4/17 19:22
 * @classname :ApiService
 */
public interface ApiService {
    @POST(ApiUrl.LOGURL)
    @FormUrlEncoded
    Observable<LogBean> getLog(@Field("phone")String phone,@Field("pwd")String pwd);
    @POST(ApiUrl.REGISTER_URL)
    @FormUrlEncoded
    Observable<RegBean> getReg(@Field("phone")String phone, @Field("nickName")String name, @Field("pwd")String pwd);
}
