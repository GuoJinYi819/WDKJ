package com.wd.tech.adapter.wyadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.tech.R;
import com.wd.tech.bean.wybean.beanfollow.ResultBean;
import com.wd.tech.net.TimeToUtil;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author 王阳
 * Class :1708A
 * @description:
 * @date :2020/4/22 20:26
 * @classname :FollowAdapter
 */
public class FollowAdapter extends RecyclerView.Adapter<FollowAdapter.FollowViewHolder> {
    private List<ResultBean> result = new ArrayList<>();
    private Context context;

    public FollowAdapter(List<ResultBean> result, Context context) {
        this.result.addAll(result);
        this.context = context;
    }

    @NonNull
    @Override
    public FollowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_follow, parent, false);
        FollowViewHolder followViewHolder = new FollowViewHolder(view);
        return followViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FollowViewHolder holder, int position) {
        holder.imgFollowWy.setImageURI(result.get(position).getHeadPic());
        holder.tvFollowNameWy.setText(result.get(position).getNickName());
        long focusTime = result.get(position).getFocusTime();
        String time = TimeToUtil.getTime(focusTime);
        holder.tvFollowTimeWy.setText(time);
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    class FollowViewHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView imgFollowWy;
        private TextView tvFollowNameWy;
        private TextView tvFollowTimeWy;
        public FollowViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFollowWy = (SimpleDraweeView) itemView.findViewById(R.id.imgFollowWy);
            tvFollowNameWy = (TextView) itemView.findViewById(R.id.tvFollowNameWy);
            tvFollowTimeWy = (TextView) itemView.findViewById(R.id.tvFollowTimeWy);
        }
    }
}
