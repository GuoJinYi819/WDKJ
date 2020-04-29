package com.wd.tech.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.List;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/4/29 0029 18:13
 * @Description: 用途：完成特定功能
 */
public class WXUtil {
    //APP_ID

    private static final String APP_ID ="wx4c96b6b8da494224";//就是自己申请的ID

// IWXAPI 是第三方app和微信通信的openApi接口

    public static IWXAPI api;

    public static OnWxLoginListener onWxLoginListener;

    public static void regToWx(Context c) {

// 通过WXAPIFactory工厂，获取IWXAPI的实例

        api = WXAPIFactory.createWXAPI(c,APP_ID,true);

// 将应用的appId注册到微信

        api.registerApp(APP_ID);

    }
    //唤起微信
    public static void callWX(){

        SendAuth.Req req =new SendAuth.Req();  //在开放平台文档里复制
        req.scope ="snsapi_userinfo";
        req.state ="wechat_sdk_demo_test";
        api.sendReq(req);

    }

    public interface OnWxLoginListener{
        void onCode(String code);
    }

}
