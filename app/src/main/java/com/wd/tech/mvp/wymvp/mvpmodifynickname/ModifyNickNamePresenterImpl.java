package com.wd.tech.mvp.wymvp.mvpmodifynickname;

import com.wd.tech.base.BasePresenter;
import com.wd.tech.bean.wybean.beanmodifynickname.ModifyNickNameBean;

/**
 * @author 王阳
 * Class :1708A
 * @description:
 * @date :2020/4/27 19:49
 * @classname :ModifyNickNamePresenterImpl
 */
public class ModifyNickNamePresenterImpl extends BasePresenter<IModifyNickNameContract.IModifyNickNameView> implements IModifyNickNameContract.IModifyNickNamePresenter {
    private ModifyNickNameModelImpl modifyNickNameModel;
    @Override
    public void initModel() {
        modifyNickNameModel=new ModifyNickNameModelImpl();
    }

    @Override
    public void getModifyNickName(String nickName) {
        modifyNickNameModel.getModifyNickName(nickName, new IModifyNickNameContract.IModifyNickNameModel.DataCallBack() {
            @Override
            public void onSuccess(ModifyNickNameBean modifyNickNameBean) {
                iBaseView.onSuccess(modifyNickNameBean);
            }

            @Override
            public void onError(String error) {
                iBaseView.onError(error);
            }
        });
    }
}
