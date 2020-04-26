package com.wd.tech.adapter.gjyadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.wd.tech.R;
import com.wd.tech.bean.gjybean.GroupMemberListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/4/26 0026 20:50
 * @Description: 用途：完成特定功能
 */
public class GroupFriendChildAdapter extends RecyclerView.Adapter<GroupFriendChildAdapter.MyChildHodler> {
    private List<GroupMemberListBean.ResultBean> list = new ArrayList<>();
    private Context context;

    public GroupFriendChildAdapter(List<GroupMemberListBean.ResultBean> list, Context context) {
        this.list.addAll(list);
        this.context = context;
    }

    @NonNull
    @Override
    public MyChildHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_groupfriend_child, parent, false);
        return new MyChildHodler(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MyChildHodler holder, int position) {
        GroupMemberListBean.ResultBean resultBean = list.get(position);
        String headPic = resultBean.getHeadPic();
        Glide.with(context).load(headPic).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(holder.mIvPic);
        String nickName = resultBean.getNickName();
        holder.mTvName.setText(nickName);

        int userId = resultBean.getUserId();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyChildHodler extends RecyclerView.ViewHolder {
        private ImageView mIvPic;
        private TextView mTvName;
        public MyChildHodler(@NonNull View itemView) {
            super(itemView);
            mIvPic = itemView.findViewById(R.id.ivPic);
            mTvName = itemView.findViewById(R.id.tvName);
        }
    }
}
