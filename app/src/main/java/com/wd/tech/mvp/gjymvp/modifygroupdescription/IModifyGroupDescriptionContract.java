package com.wd.tech.mvp.gjymvp.modifygroupdescription;

import com.wd.tech.base.IBaseView;
import com.wd.tech.bean.gjybean.ModifyGroupDescriptionBean;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/4/26 0026 21:45
 * @Description: 用途：完成特定功能
 */
public interface IModifyGroupDescriptionContract {
    interface IModifyGroupDescriptionView extends IBaseView{
        void onSuccess(ModifyGroupDescriptionBean bean);
        void onFailed(String error);
    }
    interface IModifyGroupDescriptionModule{
        void setModifyGroupDescription(int id,String content,ModifyGroupDescriptionCallBack modifyGroupDescription);
        interface ModifyGroupDescriptionCallBack{
            void onSuccess(ModifyGroupDescriptionBean bean);
            void onFailed(String error);
        }
    }
    interface IModifyGroupDescriptionPresenter{
        void setModifyGroupDescription(int id,String content);
    }
}
