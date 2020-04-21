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
import com.wd.tech.R;
import com.wd.tech.bean.gjybean.FriendSeachBean;
import com.wd.tech.net.SpUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/4/21 0021 9:38
 * @Description: 用途：完成特定功能
 */
public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.MyRecordHolder> {
    private List<FriendSeachBean.ResultBean> list = new ArrayList<>();
    private Context context;

    public RecordAdapter(List<FriendSeachBean.ResultBean> list, Context context) {
        this.list.addAll(list);
        this.context = context;
    }

    @NonNull
    @Override
    public MyRecordHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_record, parent, false);
        return new MyRecordHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecordHolder holder, int position) {
        FriendSeachBean.ResultBean resultBean = list.get(position);
        String headPic = resultBean.getHeadPic();
        String nickName = resultBean.getNickName();
        holder.mTvNickName.setText(nickName);
        Glide.with(context).load(headPic).into(holder.mIvHeadPic);
        holder.mIvRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.remove(position);
                SpUtil instance = SpUtil.getInstance();
                String searchName = instance.getSpString("searchName");
                if (nickName.contains(searchName)) {
                    instance.removeKey("searchName");
                }
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class MyRecordHolder extends RecyclerView.ViewHolder {
        private ImageView mIvHeadPic;
        private TextView mTvNickName;
        private ImageView mIvRemove;
        public MyRecordHolder(@NonNull View itemView) {
            super(itemView);
            mIvHeadPic = itemView.findViewById(R.id.ivHeadPic);
            mTvNickName = itemView.findViewById(R.id.tvNickName);
            mIvRemove = itemView.findViewById(R.id.ivRemove);
        }
    }
}
