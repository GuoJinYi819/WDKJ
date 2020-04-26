package com.wd.tech.mvp.wymvp.mvpimproveinformation;

import com.wd.tech.base.BasePresenter;
import com.wd.tech.bean.wybean.beanimproveinformation.ImproveInformationBean;

/**
 * @author 王阳
 * Class :1708A
 * @description:
 * @date :2020/4/25 19:43
 * @classname :ImproveInformationPresenterImpl
 */
public class ImproveInformationPresenterImpl extends BasePresenter<IImproveInformationContract.IImproveInformationView> implements IImproveInformationContract.IImproveInformationPresenter {
    private ImproveInformationModelImpl improveInformationModel;
    @Override
    public void initModel() {
        improveInformationModel=new ImproveInformationModelImpl();
    }

    @Override
    public void getImproveInformation(String nickName, int sex, String signature, String birthday, String email) {
        improveInformationModel.getImproveInformation(nickName, sex, signature, birthday, email, new IImproveInformationContract.IImproveInformationModel.DataCallBack() {
            @Override
            public void onSuccess(ImproveInformationBean improveInformationBean) {
                iBaseView.onSuccess(improveInformationBean);
            }

            @Override
            public void onError(String error) {
                iBaseView.onError(error);
            }
        });
    }
}
