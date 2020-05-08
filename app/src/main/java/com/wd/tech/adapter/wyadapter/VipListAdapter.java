package com.wd.tech.adapter.wyadapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.tech.R;
import com.wd.tech.activity.BuyVipActivity;
import com.wd.tech.activity.HomeActivity;
import com.wd.tech.activity.MyVIPActivity;
import com.wd.tech.bean.wybean.Event;
import com.wd.tech.bean.wybean.beanselectviplist.ResultBean;
import com.wd.tech.net.EncryptionUtil;
import com.wd.tech.net.SpUtil;

import org.greenrobot.eventbus.EventBus;

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
    private int isClick=-1;
    //控制一段时间只触发一次事件
    private long lastOnClickTime=0;
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
        //缓存  id
        int commodityId = result.get(position).getCommodityId();
        SpUtil instance = SpUtil.getInstance();
        instance.saveInt("commodityId",commodityId);
        //
        if(isClick==position){
            holder.linearVIPWy.setBackgroundColor(Color.RED);
        }else{
            holder.linearVIPWy.setBackgroundColor(Color.WHITE);
        }
        holder.linearVIPWy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isClick=position;
                notifyDataSetChanged();
                //跳转
                ResultBean resultBean = result.get(position);
                EventBus.getDefault().postSticky(resultBean);
                //跳
                Intent intent = new Intent(context, BuyVipActivity.class);
                context.startActivity(intent);
            }
        });
        /*
        签名
        int userId = instance.getSpInt("userId");
        String sessionId = instance.getSpString("sessionId");
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(userId);
        stringBuffer.append(commodityId);
        Log.d("=====", "initData: "+sessionId);
        stringBuffer.append("tech");
        String trim = stringBuffer.toString().trim();
        String s1 = EncryptionUtil.MD5(trim);
        Log.d("=====", "onCreate: "+s1);*/
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
