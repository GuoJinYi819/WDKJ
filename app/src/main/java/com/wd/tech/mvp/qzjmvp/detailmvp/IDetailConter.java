package com.wd.tech.mvp.qzjmvp.detailmvp;

import com.wd.tech.base.IBaseView;
import com.wd.tech.bean.qzjbean.addcomment.AddBean;
import com.wd.tech.bean.qzjbean.comment.ConCommentBean;
import com.wd.tech.bean.qzjbean.detail.DetailBean;
import com.wd.tech.bean.qzjbean.great.GreatBean;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : Quzijun
 * @version 创建时间：2020/4/24 18:07
 * @Description: 用途：完成特定功能
 */
public interface IDetailConter {
    interface IDetaView extends IBaseView {
        void onDetaSuccess(DetailBean detailBean);
        void onComment(ConCommentBean conCommentBean);
        void onAddcomm(AddBean addBean);
        void ondSuccess(GreatBean greatBean);
        void onnSuccess(GreatBean greatBean);
    }
    interface IDetailMoudle{
        void onDetaDate(int id,DetaCallBack callBack);
        void onCommentDate(int infoId,int page,int count,CommentCallBack commentCallBack);
        void onAddDate(int infoId,String content,AddCallBack addCallBack);
        void onDDate(int infoId,DCallBack dCallBack);
        void onNDate(int infoId,NCallBack nCallBack);
        interface DetaCallBack{
            void onDetaSuccess(DetailBean detailBean);
        }
        interface CommentCallBack{
            void onDetaSuccess(ConCommentBean conCommentBean);
        }
        interface AddCallBack{
            void onDetaSuccess(AddBean addBean);
        }
        interface DCallBack{
            void onDetaSuccess(GreatBean greatBean);
        }
        interface NCallBack{
            void onDetaSuccess(GreatBean greatBean);
        }
    }
    interface IDetailPresenter{
        void onDetaDate(int id);
        void onCommentDate(int infoId, int page, int count);
        void onAddDate(int infoId, String content);
        void onDDate(int infoId);
        void onNDate(int infoId);
    }
}
