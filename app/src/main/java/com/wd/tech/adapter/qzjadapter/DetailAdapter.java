package com.wd.tech.adapter.qzjadapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.tech.R;
import com.wd.tech.bean.qzjbean.detail.DetailBean;
import com.wd.tech.bean.qzjbean.xbanner.XbBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : Quzijun
 * @version 创建时间：2020/4/24 14:02
 * @Description: 用途：完成特定功能
 */
public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.ViewHoder> {
    private DetailBean.ResultBean list = new DetailBean.ResultBean();
    private Context context;
    private DetailRecommendAdapter adapter;

    public DetailAdapter(DetailBean.ResultBean list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_consulta_detail, parent, false);
        ViewHoder viewHoder = new ViewHoder(inflate);
        return viewHoder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoder holder, int position) {
        holder.name.setText(list.getTitle());
        holder.laiyuan.setText(list.getSource());
        String s = stampToDate(list.getReleaseTime() + "");
        holder.shijian.setText(s);
        String content = list.getContent();
        if (!TextUtils.isEmpty(content)){
            holder.nr.setText(Html.fromHtml(content));
        }


        int readPower = list.getReadPower();
        if (readPower==1){
            Resources resources = context.getResources();
            Bitmap bitmap = BitmapFactory.decodeResource(resources, R.mipmap.c4);
            holder.pdt.setImageBitmap(bitmap);
            List<DetailBean.ResultBean.InformationListBean> informationList = list.getInformationList();
            adapter = new DetailRecommendAdapter(informationList,context);
            StaggeredGridLayoutManager ss = new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL);
            holder.rere.setLayoutManager(ss);
            holder.rere.setAdapter(adapter);
        }else {
            Resources resources = context.getResources();
            Bitmap bitmap = BitmapFactory.decodeResource(resources, R.mipmap.c4);
            holder.pdt.setImageBitmap(bitmap);
        }
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class ViewHoder extends RecyclerView.ViewHolder {

        private final RecyclerView rere;
        private final RecyclerView plre;
        private final TextView name;
        private final TextView laiyuan;
        private final TextView shijian;
        private final TextView nr;
        private final TextView mk;
        private final SimpleDraweeView pdt;

        public ViewHoder(@NonNull View itemView) {
            super(itemView);
            rere = itemView.findViewById(R.id.rere);
            plre = itemView.findViewById(R.id.plre);
            name = itemView.findViewById(R.id.name);
            laiyuan = itemView.findViewById(R.id.laiyuan);
            shijian = itemView.findViewById(R.id.shijian);
            nr = itemView.findViewById(R.id.nr);
            mk = itemView.findViewById(R.id.mk);
            pdt = itemView.findViewById(R.id.pdt);
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
