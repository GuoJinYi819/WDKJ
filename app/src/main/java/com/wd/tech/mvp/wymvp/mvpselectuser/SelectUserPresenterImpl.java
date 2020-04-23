package com.wd.tech.mvp.wymvp.mvpselectuser;

import com.wd.tech.base.BasePresenter;
import com.wd.tech.bean.wybean.beanselectuser.SelectUserBean;

/**
 * @author 王阳
 * Class :1708A
 * @description:
 * @date :2020/4/23 20:42
 * @classname :SelectUserPresenterImpl
 */
public class SelectUserPresenterImpl extends BasePresenter<ISelectUserContract.ISelectUserView> implements ISelectUserContract.ISelectUserPresenter {
    private SelectUserModelImpl selectUserModel;
    @Override
    public void initModel() {
        selectUserModel=new SelectUserModelImpl();
    }

    @Override
    public void getSelectUser() {
        selectUserModel.getSelectUser(new ISelectUserContract.ISelectUserModel.DataCallBack() {
            @Override
            public void onSuccess(SelectUserBean selectUser) {
                iBaseView.onSuccess(selectUser);
            }

            @Override
            public void onError(String error) {
                iBaseView.onError(error);
            }
        });
    }
}
