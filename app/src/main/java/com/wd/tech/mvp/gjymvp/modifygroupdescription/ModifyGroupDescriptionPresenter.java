package com.wd.tech.mvp.gjymvp.modifygroupdescription;

import com.wd.tech.base.BasePresenter;
import com.wd.tech.bean.gjybean.ModifyGroupDescriptionBean;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/4/26 0026 21:49
 * @Description: 用途：完成特定功能
 */
public class ModifyGroupDescriptionPresenter extends BasePresenter<IModifyGroupDescriptionContract.IModifyGroupDescriptionView> implements IModifyGroupDescriptionContract.IModifyGroupDescriptionPresenter {
    private ModifyGroupDescriptionModule modifyGroupDescriptionModule;
    @Override
    public void initModel() {
        modifyGroupDescriptionModule = new ModifyGroupDescriptionModule();
    }

    @Override
    public void setModifyGroupDescription(int id, String content) {
        modifyGroupDescriptionModule.setModifyGroupDescription(id, content, new IModifyGroupDescriptionContract.IModifyGroupDescriptionModule.ModifyGroupDescriptionCallBack() {
            @Override
            public void onSuccess(ModifyGroupDescriptionBean bean) {
                iBaseView.onSuccess(bean);
            }

            @Override
            public void onFailed(String error) {
                iBaseView.onFailed(error);
            }
        });
    }
}
