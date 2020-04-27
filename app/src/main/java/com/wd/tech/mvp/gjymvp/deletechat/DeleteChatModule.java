package com.wd.tech.mvp.gjymvp.deletechat;

import com.wd.tech.bean.gjybean.DeleteChatBean;
import com.wd.tech.net.ApiService;
import com.wd.tech.net.RetrofitUtil;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/4/26 0026 11:01
 * @Description: 用途：完成特定功能
 */
public class DeleteChatModule implements IDeleteChatContract.IDeleteChatModule {
    @Override
    public void deleteChat(int friendUid, DeleteChatCallBack deleteChatCallBack) {
        RetrofitUtil instance = RetrofitUtil.getInstance();
        ApiService apiService = instance.createService();
        Observable<DeleteChatBean> observable = apiService.deleteChatRecord(friendUid);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DeleteChatBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DeleteChatBean value) {
                        deleteChatCallBack.onSuccess(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        deleteChatCallBack.onFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
