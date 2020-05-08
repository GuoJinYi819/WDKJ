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
import com.wd.tech.bean.qzjbean.comment.ConCommentBean;
import com.wd.tech.bean.qzjbean.detail.DetailBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : Quzijun
 * @version 创建时间：2020/4/24 19:34
 * @Description: 用途：完成特定功能
 */
public class DetailCommentAdapter extends RecyclerView.Adapter<DetailCommentAdapter.ViewHoler> {
    private List<ConCommentBean.ResultBean> list = new ArrayList<>();
    private Context context;

    public DetailCommentAdapter(List<ConCommentBean.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_consault_comment, parent, false);
        ViewHoler viewHoler = new ViewHoler(inflate);
        return viewHoler;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoler holder, int position) {
        holder.img.setImageURI(list.get(position).getHeadPic());
        holder.name.setText(list.get(position).getNickName());
        long commentTime = list.get(position).getCommentTime();
        String s = stampToDate(commentTime + "");
        holder.shijian.setText(s);
        holder.name.setText(list.get(position).getNickName());
        holder.nr.setText(list.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHoler extends RecyclerView.ViewHolder {

        private final SimpleDraweeView img;
        private final TextView name;
        private final TextView shijian;
        private final TextView nr;

        public ViewHoler(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            name = itemView.findViewById(R.id.name);
            shijian = itemView.findViewById(R.id.shijian);
            nr = itemView.findViewById(R.id.nr);

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
//