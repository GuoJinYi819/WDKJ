package com.wd.tech.adapter.wyadapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.tech.R;
import com.wd.tech.activity.ConsultaActivity;
import com.wd.tech.bean.wybean.Event;
import com.wd.tech.bean.wybean.beancollectionlist.ResultBean;
import com.wd.tech.net.TimeToUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author 王阳
 * Class :1708A
 * @description:
 * @date :2020/4/22 16:45
 * @classname :CollectionAdapter
 */
public class CollectionListAdapter extends RecyclerView.Adapter<CollectionListAdapter.CollectionViewHolder> {
    private List<ResultBean> result = new ArrayList<>();
    private Context context;
    private int isChecked=-1;
    public CollectionListAdapter(List<ResultBean> result, Context context) {
        this.result.addAll(result);
        this.context = context;
    }

    //刷新
    public void onRefresh(List<ResultBean> result){
        this.result.clear();
        this.result.addAll(result);
        notifyDataSetChanged();
    }
    //加载
    public void onLoadMore(List<ResultBean> result){
        this.result.addAll(result);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CollectionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_collectionlist, parent, false);
        CollectionViewHolder collectionViewHolder = new CollectionViewHolder(view);
        return collectionViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CollectionViewHolder holder, int position) {
        holder.imgCollectionListWy.setImageURI(result.get(position).getThumbnail());
        holder.tvCommentionListTitleWy.setText(result.get(position).getTitle());
        long createTime = result.get(position).getCreateTime();
        String time = TimeToUtil.getTime(createTime);
        holder.tvCollectionListTimeWy.setText(time);
        holder.ckCollectionListWy.setVisibility(View.GONE);
        boolean delete = result.get(position).isDelete();
        //拼接
        StringBuffer stringBuffer = new StringBuffer();
        if(delete){
            holder.ckCollectionListWy.setVisibility(View.VISIBLE);
        }
        //
        holder.ckCollectionListWy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //取出 选中的ID
                boolean checked = holder.ckCollectionListWy.isChecked();
                if(checked){
                    int infoId = result.get(position).getInfoId();
                    stringBuffer.append(infoId+",");
                }
                //转
                String string = stringBuffer.toString().trim();
                Event event = new Event();
                event.setCancleId(string);
                EventBus.getDefault().postSticky(event);
            }
        });
        //变色
        if(isChecked==position){
            holder.itemView.setBackgroundColor(Color.GREEN);
        }else{
            holder.itemView.setBackgroundColor(Color.WHITE);
        }
        //点击  跳转 资讯详情页面
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isChecked=position;
                notifyDataSetChanged();
                Intent intent = new Intent(context, ConsultaActivity.class);
                int infoId = result.get(position).getInfoId();
                intent.putExtra("id",infoId);
                intent.putExtra("ismoney",2);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    class CollectionViewHolder extends RecyclerView.ViewHolder {
        private CheckBox ckCollectionListWy;
        private SimpleDraweeView imgCollectionListWy;
        private TextView tvCommentionListTitleWy;
        private TextView tvCollectionListTimeWy;
        public CollectionViewHolder(@NonNull View itemView) {
            super(itemView);
            ckCollectionListWy = (CheckBox) itemView.findViewById(R.id.ckCollectionListWy);
            imgCollectionListWy = (SimpleDraweeView) itemView.findViewById(R.id.imgCollectionListWy);
            tvCommentionListTitleWy = (TextView) itemView.findViewById(R.id.tvCommentionListTitleWy);
            tvCollectionListTimeWy = (TextView) itemView.findViewById(R.id.tvCollectionListTimeWy);
        }
    }
}
