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
import com.wd.tech.bean.qzjbean.comment.ConCommentBean;
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
    private List<ConCommentBean.ResultBean> clist = new ArrayList<>();
    private Context context;
    private DetailRecommendAdapter adapter;
    private String mmk="";
    private DetailCommentAdapter cadapter;
    private int ismoney;
    public GetIdLenter getIdLenter;

    public void setGetIdLenter(GetIdLenter getIdLenter) {
        this.getIdLenter = getIdLenter;
    }

    public DetailBean.ResultBean getList() {
        return list;
    }

    public void setClist(List<ConCommentBean.ResultBean> clist) {
        this.clist = clist;
    }

    public DetailAdapter(DetailBean.ResultBean list, Context context, int ismoney) {
        this.list = list;
        this.context = context;
        this.ismoney = ismoney;
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
        if (ismoney==2){
            Resources resources = context.getResources();
            Bitmap bitmap = BitmapFactory.decodeResource(resources, R.mipmap.c4);
            holder.pdt.setImageURI(list.getThumbnail());
            List<DetailBean.ResultBean.InformationListBean> informationList = list.getInformationList();
            adapter = new DetailRecommendAdapter(informationList,context);
            StaggeredGridLayoutManager ss = new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL);
            holder.rere.setLayoutManager(ss);
            holder.tj.setText("推荐");
            List<DetailBean.ResultBean.PlateBean> plate = list.getPlate();
            for (int i = 0; i < plate.size(); i++) {
                mmk = mmk + plate.get(i).getName()+" ";
                holder.mk.setText("模块: "+mmk);
            }
            cadapter = new DetailCommentAdapter(clist,context);
            StaggeredGridLayoutManager ta = new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
            holder.plre.setLayoutManager(ta);
            holder.plre.setAdapter(cadapter);

            holder.rere.setAdapter(adapter);
        }else {
            Resources resources = context.getResources();
            Bitmap bitmap = BitmapFactory.decodeResource(resources, R.mipmap.c5);
            holder.pdt.setImageBitmap(bitmap);
            cadapter = new DetailCommentAdapter(clist,context);
            StaggeredGridLayoutManager ta = new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
            holder.plre.setLayoutManager(ta);
            holder.plre.setAdapter(cadapter);
            holder.pdt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id = list.getId();
                    int integralCost = list.getIntegralCost();
                    if (getIdLenter!=null){
                        getIdLenter.onId(id,integralCost);
                    }
                }
            });
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
        private final TextView tj;
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
            tj = itemView.findViewById(R.id.tj);
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
    public interface GetIdLenter{
        void onId(int id,int integral);
    }
}
//
