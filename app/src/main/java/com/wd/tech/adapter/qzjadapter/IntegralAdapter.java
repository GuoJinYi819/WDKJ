package com.wd.tech.adapter.qzjadapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.like.LikeButton;
import com.wd.tech.R;
import com.wd.tech.bean.qzjbean.detail.DetailBean;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : Quzijun
 * @version 创建时间：2020/4/28 16:55
 * @Description: 用途：完成特定功能
 */
public class IntegralAdapter extends RecyclerView.Adapter<IntegralAdapter.ViewHoder> {
    private DetailBean.ResultBean list = new DetailBean.ResultBean();
    private Context context;

    public IntegralAdapter(DetailBean.ResultBean list, Context context) {
        this.list = list;
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
        holder.img.setImageURI(list.getThumbnail());
        holder.name.setText(list.getTitle());
        holder.nr.setText(list.getSummary());
        holder.gzs.setText(list.getSource());
        long releaseTime = list.getReleaseTime();
        String standardDate = getStandardDate(releaseTime + "");
        holder.sjc.setText(standardDate);
        holder.fxs.setText(list.getShare()+"");
        holder.xhs.setText(list.getPraise()+"");
        holder.likebutton.setVisibility(View.GONE);
        int whetherCollection = list.getWhetherCollection();
        Resources resources = context.getResources();
        if (whetherCollection==1){
            Bitmap bitmap = BitmapFactory.decodeResource(resources, R.mipmap.xhxhxhxh);
            holder.likebutton.setLiked(true);
        }else {
            Bitmap bitmap = BitmapFactory.decodeResource(resources, R.mipmap.xhxh);
           holder.likebutton.setLiked(false);
        }
        Bitmap bitmap = BitmapFactory.decodeResource(resources, R.mipmap.common_icon_collect_n_xxhdpi);
        holder.fx.setImageBitmap(bitmap);
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class ViewHoder extends RecyclerView.ViewHolder {
        private final SimpleDraweeView img;
        private final TextView name;
        private final TextView nr;
        private final TextView gzs;
        private final TextView sjc;
        private final TextView xhs;
        private final TextView fxs;

        private final ImageView fx;
        private final LikeButton likebutton;

        public ViewHoder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            name = itemView.findViewById(R.id.name);
            nr = itemView.findViewById(R.id.nr);
            gzs = itemView.findViewById(R.id.gzs);
            sjc = itemView.findViewById(R.id.sjc);
            xhs = itemView.findViewById(R.id.xhs);
            fxs = itemView.findViewById(R.id.fxs);

            fx = itemView.findViewById(R.id.fx);
            likebutton = itemView.findViewById(R.id.likebutton);
        }
    }
    public String getStandardDate(String timeStr) {

        StringBuffer sb = new StringBuffer();

        long t = Long.parseLong(timeStr);
        long time = System.currentTimeMillis() - (t*1000);
        long mill = (long) Math.ceil(time /1000);//秒前

        long minute = (long) Math.ceil(time/60/1000.0f);// 分钟前

        long hour = (long) Math.ceil(time/60/60/1000.0f);// 小时

        long day = (long) Math.ceil(time/24/60/60/1000.0f);// 天前

        if (day - 1 > 0) {
            sb.append(day + "天");
        } else if (hour - 1 > 0) {
            if (hour >= 24) {
                sb.append("1天");
            } else {
                sb.append(hour + "小时");
            }
        } else if (minute - 1 > 0) {
            if (minute == 60) {
                sb.append("1小时");
            } else {
                sb.append(minute + "分钟");
            }
        } else if (mill - 1 > 0) {
            if (mill == 60) {
                sb.append("1分钟");
            } else {
                sb.append(mill + "秒");
            }
        } else {
            sb.append("刚刚");
        }
        if (!sb.toString().equals("刚刚")) {
            sb.append("前");
        }
        return sb.toString();
    }
}
