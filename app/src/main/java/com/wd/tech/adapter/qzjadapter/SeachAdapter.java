package com.wd.tech.adapter.qzjadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.tech.R;
import com.wd.tech.bean.qzjbean.consultationlist.ConResultBean;
import com.wd.tech.bean.qzjbean.seach.SeachResultBean;

import java.security.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : Quzijun
 * @version 创建时间：2020/4/21 21:09
 * @Description: 用途：完成特定功能
 */
public class SeachAdapter extends RecyclerView.Adapter<SeachAdapter.ViewHoder> {
    private List<SeachResultBean> list = new ArrayList<>();
    private Context context;

    public SeachAdapter(List<SeachResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_seach_view, parent, false);
        ViewHoder viewHoder = new ViewHoder(inflate);
        return viewHoder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoder holder, int position) {
        holder.name.setText(list.get(position).getTitle());
        holder.nr.setText(list.get(position).getSource());
        String s = stampToDate(list.get(position).getReleaseTime() + "");
        holder.sj.setText(s);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHoder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final TextView nr;
        private final TextView sj;
        public ViewHoder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            nr = itemView.findViewById(R.id.nr);
            sj = itemView.findViewById(R.id.sj);
        }
    }
    public static String stampToDate(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //如果它本来就是long类型的,则不用写这一步
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }
}
