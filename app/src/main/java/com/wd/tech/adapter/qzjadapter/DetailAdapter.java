package com.wd.tech.adapter.qzjadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.bean.qzjbean.detail.DetailBean;
import com.wd.tech.bean.qzjbean.xbanner.XbBean;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : Quzijun
 * @version 创建时间：2020/4/24 14:02
 * @Description: 用途：完成特定功能
 */
public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.ViewHoder> {
    private List<DetailBean.ResultBean> list = new ArrayList<>();
    private Context context;
    private List<XbBean> xbBeanList = new ArrayList<>();

    public void setXbBeanList(List<XbBean> xbBeanList) {
        this.xbBeanList = xbBeanList;
    }

    public DetailAdapter(List<DetailBean.ResultBean> list, Context context) {
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

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHoder extends RecyclerView.ViewHolder {

        private final RecyclerView rere;
        private final RecyclerView plre;
        private final TextView name;
        private final TextView laiyuan;
        private final TextView shijian;
        private final TextView nr;
        private final TextView mk;
        private final ImageView fanhui;
        private final ImageView etext;
        private final TextView pls;
        private final TextView dzs;
        private final TextView fxs;
        private final ImageView plt;
        private final ImageView dzt;
        private final ImageView sct;
        private final ImageView fxt;

        public ViewHoder(@NonNull View itemView) {
            super(itemView);
            rere = itemView.findViewById(R.id.rere);
            plre = itemView.findViewById(R.id.plre);
            name = itemView.findViewById(R.id.name);
            laiyuan = itemView.findViewById(R.id.laiyuan);
            shijian = itemView.findViewById(R.id.shijian);
            nr = itemView.findViewById(R.id.nr);
            mk = itemView.findViewById(R.id.mk);
            fanhui = itemView.findViewById(R.id.fanhui);
            etext = itemView.findViewById(R.id.etext);
            pls = itemView.findViewById(R.id.pls);
            dzs = itemView.findViewById(R.id.dzs);
            fxs = itemView.findViewById(R.id.fxs);
            plt = itemView.findViewById(R.id.plt);
            dzt = itemView.findViewById(R.id.dzt);
            sct = itemView.findViewById(R.id.sct);
            fxt = itemView.findViewById(R.id.fxt);
        }
    }
}
