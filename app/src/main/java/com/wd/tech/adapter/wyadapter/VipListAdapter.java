package com.wd.tech.adapter.wyadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.tech.R;
import com.wd.tech.bean.wybean.beanselectviplist.ResultBean;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author 王阳
 * Class :1708A
 * @description:
 * @date :2020/4/26 14:52
 * @classname :VipListAdapter
 */
public class VipListAdapter extends RecyclerView.Adapter<VipListAdapter.VipListViewHolder> {
    private List<ResultBean> result = new ArrayList<>();
    private Context context;

    public VipListAdapter(List<ResultBean> result, Context context) {
        this.result.addAll(result);
        this.context = context;
    }

    @NonNull
    @Override
    public VipListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_vip_list, parent, false);
        VipListViewHolder vipListViewHolder = new VipListViewHolder(view);
        return vipListViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull VipListViewHolder holder, int position) {
        holder.imgVIPNameWy.setImageURI(result.get(position).getImageUrl());
        holder.tvVIPNameWy.setText(result.get(position).getCommodityName());
        holder.tvVIPPriceWy.setText("$:"+result.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    class VipListViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout linearVIPWy;
        private SimpleDraweeView imgVIPNameWy;
        private TextView tvVIPNameWy;
        private TextView tvVIPPriceWy;
        public VipListViewHolder(@NonNull View itemView) {
            super(itemView);
            linearVIPWy = (LinearLayout) itemView.findViewById(R.id.linearVIPWy);
            imgVIPNameWy = (SimpleDraweeView) itemView.findViewById(R.id.imgVIPNameWy);
            tvVIPNameWy = (TextView) itemView.findViewById(R.id.tvVIPNameWy);
            tvVIPPriceWy = (TextView) itemView.findViewById(R.id.tvVIPPriceWy);
        }
    }
}
