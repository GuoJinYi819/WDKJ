package com.wd.tech.adapter.wyadapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wd.tech.R;
import com.wd.tech.bean.wybean.Event;
import com.wd.tech.bean.wybean.beanscoredetailed.ResultBean;
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
 * @date :2020/4/25 16:30
 * @classname :ScoreAdapter
 */
public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.ScoreViewHolder> {
    private List<ResultBean> result = new ArrayList<>();
    private Context context;
    private int count=0;
    public ScoreAdapter(List<ResultBean> result, Context context) {
        this.result.addAll(result);
        this.context = context;
    }

    @NonNull
    @Override
    public ScoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_score_wy, parent, false);
        ScoreViewHolder scoreViewHolder = new ScoreViewHolder(view);
        return scoreViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ScoreViewHolder holder, int position) {
        int direction = result.get(position).getDirection();
        if(direction==1){
            //收入
            holder.tvScoreCountWy.setText("+"+result.get(position).getAmount());
            //
            int type = result.get(position).getType();
            switch (type){
                case 1:
                    holder.tvScoreTypeWy.setText("签到成功");
                    count++;
                    break;
                case 2:
                    holder.tvScoreTypeWy.setText("发布评论");
                    break;
                case 3:
                    holder.tvScoreTypeWy.setText("分享成功");
                    break;
                case 4:
                    holder.tvScoreTypeWy.setText("发布帖子");
                    break;
                case 5:
                    holder.tvScoreTypeWy.setText("抽奖获得");
                    break;
                case 8:
                    holder.tvScoreTypeWy.setText("完善个人信息");
                    break;
                case 9:
                    holder.tvScoreTypeWy.setText("查看广告");
                    break;
                case 10:
                    holder.tvScoreTypeWy.setText("绑定第三方");
                    break;
            }
        }else if(direction==2){
            //支出
            holder.tvScoreCountWy.setTextColor(Color.BLUE);
            holder.tvScoreCountWy.setText("-"+result.get(position).getAmount());
            //
            int type = result.get(position).getType();
            switch (type){
                case 6:
                    holder.tvScoreTypeWy.setText("兑换付费资讯");
                    break;
                case 7:
                    holder.tvScoreTypeWy.setText("兑换抽奖");
                    break;
            }
        }
        //时间
        long createTime = result.get(position).getCreateTime();
        String time = TimeToUtil.getTime(createTime);
        holder.tvScoreTimeWy.setText(time);
        //传值  签到天数
        Event event = new Event();
        event.setId(count);
        EventBus.getDefault().postSticky(event);
    }

    public int getCount() {
        return count;
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    class ScoreViewHolder extends RecyclerView.ViewHolder {
        private TextView tvScoreTypeWy;
        private TextView tvScoreTimeWy;
        private TextView tvScoreCountWy;
        public ScoreViewHolder(@NonNull View itemView) {
            super(itemView);
            tvScoreTypeWy = (TextView) itemView.findViewById(R.id.tvScoreTypeWy);
            tvScoreTimeWy = (TextView) itemView.findViewById(R.id.tvScoreTimeWy);
            tvScoreCountWy = (TextView) itemView.findViewById(R.id.tvScoreCountWy);
        }
    }
}
