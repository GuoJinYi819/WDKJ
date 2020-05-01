package com.wd.tech.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;

import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXTextObject;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.wd.tech.R;

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

    public static boolean isWeixinAvilible(Context context) {
        final PackageManager packageManager = context.getPackageManager();// 获取packagemanager
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals("com.tencent.mm")) {
                    return true;
                }
            }
        }
        return false;
    }
    public static void wechatShare(int flag,String path){
        WXWebpageObject webpage = new WXWebpageObject();
        webpage.webpageUrl = "这里填写链接url";
        WXMediaMessage msg = new WXMediaMessage(webpage);
        msg.title = "标题";
        msg.description = "内容";
        //这里替换一张自己工程里的图片资源

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = String.valueOf(System.currentTimeMillis());
        req.message = msg;
        req.scene = flag==0?SendMessageToWX.Req.WXSceneSession:SendMessageToWX.Req.WXSceneTimeline;
        api.sendReq(req);
    }
}
