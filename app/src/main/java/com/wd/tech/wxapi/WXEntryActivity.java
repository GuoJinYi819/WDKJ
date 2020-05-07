package com.wd.tech.wxapi;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.wd.tech.bean.gjybean.WxBean;
import com.wd.tech.net.SpUtil;
import com.wd.tech.util.WXUtil;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/4/29 0029 18:14
 * @Description: 用途：完成特定功能
 */
public class WXEntryActivity extends Activity implements IWXAPIEventHandler {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WXUtil.api.handleIntent(getIntent(),this);
    }

    @Override
    public void onReq(BaseReq resp) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        String code = ((SendAuth.Resp) baseResp).code;
        Log.i("gjy", "onResp: ="+code);
        SpUtil instance = SpUtil.getInstance();
        instance.saveString("code",code);

        int userId = instance.getSpInt("wxUserId");
        String sessionId = instance.getSpString("wxSessionId");
        Log.d("gjy==", "onResp: "+userId);
        Log.d("gjy==", "onResp: "+sessionId);

        WXUtil.onWxLoginListener.onCode(code);

        if(baseResp.getType()== ConstantsAPI.COMMAND_PAY_BY_WX){
            if(baseResp.errCode==0){
                Toast.makeText(this, "支付成功", Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(this, "支付失败", Toast.LENGTH_LONG).show();
            }
            finish();
        }
    }
}
