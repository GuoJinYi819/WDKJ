package com.wd.tech.adapter.wyadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.tech.R;
import com.wd.tech.bean.wybean.beancollectionlist.ResultBean;
import com.wd.tech.net.TimeToUtil;

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

    public CollectionListAdapter(List<ResultBean> result, Context context) {
        this.result.addAll(result);
        this.context = context;
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
        if(delete){
            holder.ckCollectionListWy.setVisibility(View.VISIBLE);
        }
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
