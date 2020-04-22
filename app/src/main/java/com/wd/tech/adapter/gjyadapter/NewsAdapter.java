package com.wd.tech.adapter.gjyadapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/4/22 0022 11:25
 * @Description: 用途：完成特定功能
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyNewsHolder> {

    @NonNull
    @Override
    public MyNewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MyNewsHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class MyNewsHolder extends RecyclerView.ViewHolder{

        public MyNewsHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
