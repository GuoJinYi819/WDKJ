package com.wd.tech.adapter.wyadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.tech.R;
import com.wd.tech.bean.wybean.beancommentlist.ResultBean;
import com.wd.tech.net.TimeToUtil;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author 王阳
 * Class :1708A
 * @description:
 * @date :2020/4/20 18:43
 * @classname :RecyclerCommentListAdapter
 */
public class RecyclerCommentListAdapter extends RecyclerView.Adapter<RecyclerCommentListAdapter.CommentViewHolder> {
    private List<ResultBean> result = new ArrayList<>();
    private Context context;

    public RecyclerCommentListAdapter(List<ResultBean> result, Context context) {
        this.result.addAll(result);
        this.context = context;
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_commentlist, parent, false);
        CommentViewHolder commentViewHolder = new CommentViewHolder(view);
        return commentViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        holder.imgCommentListWy.setImageURI(result.get(position).getHeadPic());
        holder.tvCommentNameWy.setText(result.get(position).getNickName());
        long commentTime = result.get(position).getCommentTime();
        String time = TimeToUtil.getTime(commentTime);
        holder.tvCommentTimeWy.setText(time);
        holder.tvCommentTextWy.setText(result.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    class CommentViewHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView imgCommentListWy;
        private TextView tvCommentNameWy;
        private TextView tvCommentTimeWy;
        private TextView tvCommentTextWy;
        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);
            imgCommentListWy = (SimpleDraweeView) itemView.findViewById(R.id.imgCommentListWy);
            tvCommentNameWy = (TextView) itemView.findViewById(R.id.tvCommentNameWy);
            tvCommentTimeWy = (TextView) itemView.findViewById(R.id.tvCommentTimeWy);
            tvCommentTextWy = (TextView) itemView.findViewById(R.id.tvCommentTextWy);
        }
    }
}
