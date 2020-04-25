package com.wd.tech.adapter.wyadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wd.tech.R;
import com.wd.tech.bean.wybean.beannotice.ResultBean;
import com.wd.tech.net.TimeToUtil;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author 王阳
 * Class :1708A
 * @description:
 * @date :2020/4/25 15:12
 * @classname :NoticeAdapter
 */
public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.NoticeViewHolder> {
    private List<ResultBean> result = new ArrayList<>();
    private Context context;

    public NoticeAdapter(List<ResultBean> result, Context context) {
        this.result.addAll(result);
        this.context = context;
    }

    @NonNull
    @Override
    public NoticeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_notice_wy, parent, false);
        NoticeViewHolder noticeViewHolder = new NoticeViewHolder(view);
        return noticeViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NoticeViewHolder holder, int position) {
        long createTime = result.get(position).getCreateTime();
        String time = TimeToUtil.getTime(createTime);
        holder.tvNoticeTimeWy.setText(time);
        //
        holder.tvNoticeMyWy.setText(result.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    class NoticeViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNoticeTimeWy;
        private TextView tvNoticeMyWy;
        public NoticeViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNoticeTimeWy = (TextView) itemView.findViewById(R.id.tvNoticeTimeWy);
            tvNoticeMyWy = (TextView) itemView.findViewById(R.id.tvNoticeMyWy);
        }
    }
}
