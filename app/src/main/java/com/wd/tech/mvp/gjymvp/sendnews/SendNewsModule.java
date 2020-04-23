package com.wd.tech.mvp.gjymvp.sendnews;

import com.wd.tech.bean.gjybean.DialogueRecordBean;
import com.wd.tech.bean.gjybean.SendMessageBean;
import com.wd.tech.net.ApiService;
import com.wd.tech.net.RetrofitUtil;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/4/23 0023 16:08
 * @Description: 用途：完成特定功能
 */
public class SendNewsModule implements ISendNewsContract.ISendNewsModule {
    @Override
    public void getDialogRecordData(Map<String, String> params, DialogRecordCallBack dialogRecordCallBack) {
        RetrofitUtil instance = RetrofitUtil.getInstance();
        ApiService apiService = instance.createService();
        Observable<DialogueRecordBean> observable = apiService.getDialogueRecord(params);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<DialogueRecordBean>() {
                    @Override
                    public void accept(DialogueRecordBean bean) throws Exception {
                        dialogRecordCallBack.getDialogRecordSuccess(bean);
                    }
                });
    }

    @Override
    public void sendMessage(Map<String, String> params, SendMessageCallBack sendMessageCallBack) {
        RetrofitUtil instance = RetrofitUtil.getInstance();
        ApiService apiService = instance.createService();
        Observable<SendMessageBean> observable = apiService.sendMessage(params);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SendMessageBean>() {
                    @Override
                    public void accept(SendMessageBean bean) throws Exception {
                        sendMessageCallBack.sendMessage(bean);
                    }
                });
    }
}
