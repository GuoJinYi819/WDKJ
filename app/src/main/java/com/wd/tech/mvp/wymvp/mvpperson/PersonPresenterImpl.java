package com.wd.tech.mvp.wymvp.mvpperson;

import com.wd.tech.base.BasePresenter;
import com.wd.tech.base.IBaseView;
import com.wd.tech.bean.wybean.beanperson.PersonBean;
import com.wd.tech.bean.wybean.beanperson.ResultBean;

import java.util.List;

/**
 * @author 王阳
 * Class :1708A
 * @description:
 * @date :2020/4/21 10:12
 * @classname :PersonPresenterImpl
 */
public class PersonPresenterImpl extends BasePresenter<IPersonContract.IPersonView> implements IPersonContract.IPersonPresenter {
    private PersonModelImpl personModel;
    @Override
    public void initModel() {
        personModel=new PersonModelImpl();
    }

    @Override
    public void getPerson(int fromUid, int page, int count) {
        personModel.getPerson(fromUid, page, count, new IPersonContract.IPersonModel.DataCallBack() {
            @Override
            public void onSuccess(List<ResultBean> result) {
                iBaseView.onSuccess(result);
            }

            @Override
            public void onError(String msg) {
                iBaseView.onError(msg);
            }
        });
    }
}
