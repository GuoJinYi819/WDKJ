package com.wd.tech.adapter.gjyadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/4/22 0022 11:25
 * @Description: 用途：完成特定功能
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyNewsHolder> {

    private String[] str = {"好友通知", "群通知"};
    private Context context;

    public NewsAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyNewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_news, parent, false);
        return new MyNewsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyNewsHolder holder, int position) {
        String text = str[position];
        if (text.contains("好友通知")) {
            holder.mTvNotice.setText(text);
        }else if (text.contains("群通知")){
            holder.mTvNotice.setText(text);
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
    class MyNewsHolder extends RecyclerView.ViewHolder {
        private ImageView mIvNoticePic;
        private TextView mTvNotice;
        public MyNewsHolder(@NonNull View itemView) {
            super(itemView);
            mIvNoticePic = itemView.findViewById(R.id.ivNoticePic);
            mTvNotice = itemView.findViewById(R.id.tvNotice);
        }
    }
}
