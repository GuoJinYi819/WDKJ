package com.wd.tech.adapter.wyadapter;

import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wd.tech.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author 王阳
 * Class :1708A
 * @description:
 * @date :2020/4/26 21:30
 * @classname :CommentAdapter
 */
public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {
    private List<String> resultList = new ArrayList<>();
    private Context context;
    public CommentAdapter(List<String> resultList, Context context) {
        this.resultList.addAll(resultList);
        this.context = context;
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_comment_wy, parent, false);
        CommentViewHolder commentViewHolder = new CommentViewHolder(view);
        return commentViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        String string = resultList.get(position);
        Log.d("==", "onBindViewHolder: "+string);
        String[] split = string.split("'");
        holder.tvCommNameWy.setText(split[3]);
        holder.tvCommSayWy.setText(split[5]);
    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }

    class CommentViewHolder extends RecyclerView.ViewHolder {
        private TextView tvCommNameWy;
        private TextView tvCommSayWy;
        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCommNameWy = (TextView) itemView.findViewById(R.id.tvCommNameWy);
            tvCommSayWy = (TextView) itemView.findViewById(R.id.tvCommSayWy);
        }
    }
}
