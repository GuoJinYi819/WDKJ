package com.wd.tech.mvp.qzjmvp.detailmvp;

import com.wd.tech.base.BasePresenter;
import com.wd.tech.bean.qzjbean.addcomment.AddBean;
import com.wd.tech.bean.qzjbean.comment.ConCommentBean;
import com.wd.tech.bean.qzjbean.detail.DetailBean;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : Quzijun
 * @version 创建时间：2020/4/24 18:13
 * @Description: 用途：完成特定功能
 */
public class IDetailPresenterImpl extends BasePresenter<IDetailConter.IDetaView> implements IDetailConter.IDetailPresenter {
    private IDetailImpl detailMoudle;
    @Override
    public void initModel() {
        detailMoudle = new IDetailImpl();
    }

    @Override
    public void onDetaDate(int id) {
        detailMoudle.onDetaDate(id, new IDetailConter.IDetailMoudle.DetaCallBack() {
            @Override
            public void onDetaSuccess(DetailBean detailBean) {
                iBaseView.onDetaSuccess(detailBean);
            }
        });
    }

    @Override
    public void onCommentDate(int infoId, int page, int count) {
        detailMoudle.onCommentDate(infoId,page,count, new IDetailConter.IDetailMoudle.CommentCallBack() {
            @Override
            public void onDetaSuccess(ConCommentBean conCommentBean) {
                iBaseView.onComment(conCommentBean);
            }
        });

    }

    @Override
    public void onAddDate(int infoId, String content) {
        detailMoudle.onAddDate(infoId, content, new IDetailConter.IDetailMoudle.AddCallBack() {
            @Override
            public void onDetaSuccess(AddBean addBean) {
                iBaseView.onAddcomm(addBean);
            }
        });
    }
}
