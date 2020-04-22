package com.wd.tech.adapter.qzjadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.tech.R;
import com.wd.tech.bean.qzjbean.consultationlist.ConResultBean;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : Quzijun
 * @version 创建时间：2020/4/21 21:09
 * @Description: 用途：完成特定功能
 */
public class ConListAdapter extends RecyclerView.Adapter<ConListAdapter.ViewHoder> {
    private List<ConResultBean> list = new ArrayList<>();
    private Context context;

    public ConListAdapter(List<ConResultBean> list, Context context) {
        this.list.addAll(list);
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_conlist_nogg, parent, false);
        ViewHoder viewHoder = new ViewHoder(inflate);
        return viewHoder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoder holder, int position) {
//        holder.img.setImageURI(list.get(position).getThumbnail());
//        holder.name.setText(list.get(position).getTitle());
//        holder.nr.setText(list.get(position).getSummary());
//        holder.gzs.setText(list.get(position).getSource());
//        int releaseTime = list.get(position).getReleaseTime();


    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class ViewHoder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView img;
        private final TextView name;
        private final TextView nr;
        private final TextView gzs;
        private final TextView sjc;
        private final TextView xhs;
        private final TextView fxs;
        private final CheckBox xh;
        private final CheckBox fx;

        public ViewHoder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            name = itemView.findViewById(R.id.name);
            nr = itemView.findViewById(R.id.nr);
            gzs = itemView.findViewById(R.id.gzs);
            sjc = itemView.findViewById(R.id.sjc);
            xhs = itemView.findViewById(R.id.xhs);
            fxs = itemView.findViewById(R.id.fxs);
            xh = itemView.findViewById(R.id.xh);
            fx = itemView.findViewById(R.id.fx);
        }
    }
//    public String getStandardDate(String timeStr) {
//
//        StringBuffer sb = new StringBuffer();
//
//        long t = Long.parseLong(timeStr);
//        long time = System.currentTimeMillis() - (t*1000);
//        long mill = (long) Math.ceil(time /1000);//秒前
//
//        long minute = (long) Math.ceil(time/60/1000.0f);// 分钟前
//
//        long hour = (long) Math.ceil(time/60/60/1000.0f);// 小时
//
//        long day = (long) Math.ceil(time/24/60/60/1000.0f);// 天前
//
//        if (day - 1 > 0) {
//            sb.append(day + "天");
//        } else if (hour - 1 > 0) {
//            if (hour >= 24) {
//                sb.append("1天");
//            } else {
//                sb.append(hour + "小时");
//            }
//        } else if (minute - 1 > 0) {
//            if (minute == 60) {
//                sb.append("1小时");
//            } else {
//                sb.append(minute + "分钟");
//            }
//        } else if (mill - 1 > 0) {
//            if (mill == 60) {
//                sb.append("1分钟");
//            } else {
//                sb.append(mill + "秒");
//            }
//        } else {
//            sb.append("刚刚");
//        }
//        if (!sb.toString().equals("刚刚")) {
//            sb.append("前");
//        }
//        return sb.toString();
//    }
}
