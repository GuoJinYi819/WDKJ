package com.wd.tech.adapter.gjyadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.wd.tech.R;
import com.wd.tech.bean.gjybean.FriendNoticeBean;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/4/22 0022 15:08
 * @Description: 用途：完成特定功能
 */
public class FriendNoticeAdapter extends RecyclerView.Adapter<FriendNoticeAdapter.MyFriendNoticeHolder> {

    private List<FriendNoticeBean.ResultBean> list = new ArrayList<>();
    private Context context;

    public FriendNoticeAdapter(List<FriendNoticeBean.ResultBean> list, Context context) {
        this.list.addAll(list);
        this.context = context;
    }

    @NonNull
    @Override
    public MyFriendNoticeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_friend_notice, parent, false);
        return new MyFriendNoticeHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MyFriendNoticeHolder holder, int position) {
        FriendNoticeBean.ResultBean resultBean = list.get(position);
        int status = resultBean.getStatus();
        switch (status){
            case 1:
                //待处理
                String fromNickName = resultBean.getFromNickName();
                holder.mTvFromNickName.setText(fromNickName);
                String fromHeadPic = resultBean.getFromHeadPic();
                Glide.with(context).load(fromHeadPic).apply(RequestOptions.bitmapTransform(new CircleCrop()))
                        .into(holder.mIvFromHeadPic);
                String remark = resultBean.getRemark();
                holder.mTvRemark.setText(remark);


                break;
            case 2:
                //通过
                break;
            case 3:
                //拒绝
                break;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyFriendNoticeHolder extends RecyclerView.ViewHolder {
        private TextView mTvNoticeTime;
        private ImageView mIvFromHeadPic;
        private TextView mTvFromNickName;
        private TextView mTvRemark;
        private TextView mTvStatus;
        private LinearLayout mLinearStatus;
        private TextView mTvAgree;
        private TextView mTvRefuse;
        public MyFriendNoticeHolder(@NonNull View itemView) {
            super(itemView);
            mTvNoticeTime = itemView.findViewById(R.id.tvNoticeTime);
            mIvFromHeadPic = itemView.findViewById(R.id.ivFromHeadPic);
            mTvFromNickName = itemView.findViewById(R.id.tvFromNickName);
            mTvRemark = itemView.findViewById(R.id.tvRemark);
            mTvStatus = itemView.findViewById(R.id.tvStatus);
            mLinearStatus = itemView.findViewById(R.id.linear_status);
            mTvAgree = itemView.findViewById(R.id.tvAgree);
            mTvRefuse = itemView.findViewById(R.id.tvRefuse);
        }
    }
}
