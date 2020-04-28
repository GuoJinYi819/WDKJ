package com.wd.tech.mvp.wymvp.mvpmodifysignature;

import com.wd.tech.base.BasePresenter;
import com.wd.tech.bean.wybean.beanmodifysignature.ModifySignatureBean;

/**
 * @author 王阳
 * Class :1708A
 * @description:
 * @date :2020/4/27 20:20
 * @classname :ModifySignaturePresenterImpl
 */
public class ModifySignaturePresenterImpl extends BasePresenter<IModifySignatureContract.IModifySignatureView> implements IModifySignatureContract.IModifySignaturePresenter {
    private ModifySignatureModelImpl modifySignatureModel;
    @Override
    public void initModel() {
        modifySignatureModel=new ModifySignatureModelImpl();
    }

    @Override
    public void getModifySignature(String signature) {
        modifySignatureModel.getModifySignature(signature, new IModifySignatureContract.IModifySignatureModel.DataCallBack() {
            @Override
            public void onSuccess(ModifySignatureBean modifySignatureBean) {
                iBaseView.onSuccess(modifySignatureBean);
            }

            @Override
            public void onError(String error) {
                iBaseView.onError(error);
            }
        });
    }
}
