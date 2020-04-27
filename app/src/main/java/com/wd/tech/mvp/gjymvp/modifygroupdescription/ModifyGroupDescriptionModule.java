package com.wd.tech.mvp.gjymvp.modifygroupdescription;

import com.wd.tech.bean.gjybean.ModifyGroupDescriptionBean;
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
 * @version 创建时间：2020/4/26 0026 21:48
 * @Description: 用途：完成特定功能
 */
public class ModifyGroupDescriptionModule implements IModifyGroupDescriptionContract.IModifyGroupDescriptionModule {
    @Override
    public void setModifyGroupDescription(int id, String content, ModifyGroupDescriptionCallBack modifyGroupDescription) {
        RetrofitUtil instance = RetrofitUtil.getInstance();
        ApiService apiService = instance.createService();
        Observable<ModifyGroupDescriptionBean> observable = apiService.modifyGroupDescription(id, content);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ModifyGroupDescriptionBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ModifyGroupDescriptionBean value) {
                        modifyGroupDescription.onSuccess(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        modifyGroupDescription.onFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
