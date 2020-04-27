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
import com.wd.tech.bean.gjybean.FriendBean;
import com.wd.tech.bean.gjybean.GroupMemberListBean;
import com.wd.tech.net.SpUtil;

import org.greenrobot.eventbus.EventBus;

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
    private int i;

    public GroupFriendChildAdapter(List<GroupMemberListBean.ResultBean> list, Context context, int i) {
        this.list.addAll(list);
        this.i = i;
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


            switch (i){
                case 1:
                    holder.mTvItemGuan.setText("设为管理");
                    holder.mTvItemDelete.setText("踢出");
                    break;
                case 2:
                    holder.mTvItemGuan.setText("取消管理");
                    holder.mTvItemDelete.setText("踢出");
                    break;
                case 3:
                    holder.mTvItemGuan.setText("转让群聊");
                    holder.mTvItemDelete.setText("操作");
                    break;
            }


        holder.mTvItemDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int userId = resultBean.getUserId();
                FriendBean friendBean = new FriendBean();
                friendBean.setUserId(userId);
                EventBus.getDefault().postSticky(friendBean);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyChildHodler extends RecyclerView.ViewHolder {
        private ImageView mIvPic;
        private TextView mTvName;
        public LinearLayout llItem;
        public LinearLayout llHidden;
        private TextView mTvItemGuan;
        private TextView mTvItemDelete;
        public MyChildHodler(@NonNull View itemView) {
            super(itemView);
            mIvPic = itemView.findViewById(R.id.ivPic);
            mTvName = itemView.findViewById(R.id.tvName);
            llItem = itemView.findViewById(R.id.ll_item);
            mTvItemGuan = itemView.findViewById(R.id.tv_item_guan);
            mTvItemDelete = itemView.findViewById(R.id.tv_item_delete);
            llHidden = itemView.findViewById(R.id.ll_hidden);
        }
    }
}
