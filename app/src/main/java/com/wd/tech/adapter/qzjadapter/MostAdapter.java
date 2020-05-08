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
import com.wd.tech.bean.qzjbean.most.MostBean;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : Quzijun
 * @version 创建时间：2020/4/27 11:39
 * @Description: 用途：完成特定功能
 */
public class MostAdapter extends RecyclerView.Adapter<MostAdapter.ViewHodle> {
    private List<MostBean.ResultBean> list = new ArrayList<>();
    private Context context;
    public MostIdLenter mostIdLenter;

    public void setMostIdLenter(MostIdLenter mostIdLenter) {
        this.mostIdLenter = mostIdLenter;
    }

    public MostAdapter(List<MostBean.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHodle onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.channel_show, parent, false);
        ViewHodle hodle = new ViewHodle(inflate);
        return hodle;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHodle holder, int position) {
        holder.img.setImageURI(list.get(position).getPic());
        holder.name.setText(list.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = list.get(position).getId();
                if (mostIdLenter!=null){
                    mostIdLenter.onMost(id);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHodle extends RecyclerView.ViewHolder {

        private final SimpleDraweeView img;
        private final TextView name;

        public ViewHodle(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            name = itemView.findViewById(R.id.name);
        }
    }
    public interface MostIdLenter{
        void onMost(int id);
    }
}
//