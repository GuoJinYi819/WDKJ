package com.wd.tech.adapter.qzjadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.tech.R;
import com.wd.tech.bean.qzjbean.detail.DetailBean;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : Quzijun
 * @version 创建时间：2020/4/24 19:34
 * @Description: 用途：完成特定功能
 */
public class DetailRecommendAdapter extends RecyclerView.Adapter<DetailRecommendAdapter.ViewHoler> {
    private List<DetailBean.ResultBean.InformationListBean> list = new ArrayList<>();
    private Context context;

    public DetailRecommendAdapter(List<DetailBean.ResultBean.InformationListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_detail_recommend, parent, false);
        ViewHoler viewHoler = new ViewHoler(inflate);
        return viewHoler;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoler holder, int position) {
        holder.img.setImageURI(list.get(position).getThumbnail());
        holder.name.setText(list.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHoler extends RecyclerView.ViewHolder {

        private final SimpleDraweeView img;
        private final TextView name;

        public ViewHoler(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            name = itemView.findViewById(R.id.name);

        }
    }
}
