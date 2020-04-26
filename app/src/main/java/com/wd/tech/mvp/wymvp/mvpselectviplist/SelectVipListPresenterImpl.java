package com.wd.tech.mvp.wymvp.mvpselectviplist;

import com.wd.tech.base.BasePresenter;
import com.wd.tech.bean.wybean.beanselectviplist.SelectVipListBean;

/**
 * @author 王阳
 * Class :1708A
 * @description:
 * @date :2020/4/26 14:43
 * @classname :SelectVipListPresenterImpl
 */
public class SelectVipListPresenterImpl extends BasePresenter<ISelectVipListContract.ISelectVipListView> implements ISelectVipListContract.ISelectVipListPresenter {
    private SelectVipListModelImpl selectVipListModel;
    @Override
    public void initModel() {
        selectVipListModel=new SelectVipListModelImpl();
    }

    @Override
    public void getSelectVipList() {
        selectVipListModel.getSelectVipList(new ISelectVipListContract.ISelectVipListModel.DataCallBack() {
            @Override
            public void onSuccess(SelectVipListBean selectVipListBean) {
                iBaseView.onSuccess(selectVipListBean);
            }

            @Override
            public void onError(String error) {
                iBaseView.onError(error);
            }
        });
    }
}
