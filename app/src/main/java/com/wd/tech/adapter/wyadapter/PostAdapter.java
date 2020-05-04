package com.wd.tech.adapter.wyadapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.tech.R;
import com.wd.tech.bean.wybean.Event;
import com.wd.tech.bean.wybean.beanmypost.ResultBean;
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
 * @date :2020/4/24 18:59
 * @classname :PostAdapter
 */
public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {
    private List<ResultBean> result = new ArrayList<>();
    private Context context;
    private int whetherGreat =2;
    public PostAdapter(List<ResultBean> result, Context context) {
        this.result = result;
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
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
        PostViewHolder postViewHolder = new PostViewHolder(view);
        return postViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        holder.tvPost.setText(result.get(position).getContent());
        //时间
        long publishTime = result.get(position).getPublishTime();
        String time = TimeToUtil.getTime(publishTime);
        holder.tvPostTime.setText(time);
        //图片，判断都没有
        String file = result.get(position).getFile();
        if(!TextUtils.isEmpty(file)){
            holder.imgPostWy.setImageURI(file);
        }
        //设置数量
        holder.tvCountPraisePost.setText(""+result.get(position).getPraise());
        holder.tvCountContentPost.setText(""+result.get(position).getComment());
        //点赞
        holder.imgPraisePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (whetherGreat == 1) {
                    //已点赞
                    //获取资源文件
                    Resources resources = context.getResources();
                    Bitmap bitmap = BitmapFactory.decodeResource(resources, R.mipmap.common_icon_prise_n_xhdpi);
                    holder.imgPraisePost.setImageBitmap(bitmap);
                    //设置
                    int praise = result.get(position).getPraise();
                    praise--;
                    if (praise < 0) {
                        praise = 0;
                    }
                    holder.tvCountPraisePost.setText(praise + "");
                    //
                    result.get(position).setPraise(praise);
                    whetherGreat =2;
                } else if (whetherGreat == 2) {
                    //未点赞
                    //获取资源文件
                    Resources resources = context.getResources();
                    Bitmap bitmap = BitmapFactory.decodeResource(resources, R.mipmap.common_icon_praise_s_xhdpi);
                    holder.imgPraisePost.setImageBitmap(bitmap);
                    //设置
                    int praise = result.get(position).getPraise();
                    praise++;
                    holder.tvCountPraisePost.setText(praise + "");
                    //
                    result.get(position).setPraise(praise);
                    whetherGreat =1;
                }
            }
        });
        //删除
        holder.tvPostDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //传值
                int id = result.get(position).getId();
                Event event=new Event();
                event.setId(id);
                EventBus.getDefault().postSticky(event);
            }
        });
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    class PostViewHolder extends RecyclerView.ViewHolder {
        private TextView tvPost;
        private SimpleDraweeView imgPostWy;
        private TextView tvPostTime;
        private TextView tvPostDelete;
        private ImageView imgContentPostWy;
        private TextView tvCountContentPost;
        private ImageView imgPraisePost;
        private TextView tvCountPraisePost;
        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPost = (TextView) itemView.findViewById(R.id.tvPost);
            imgPostWy = itemView.findViewById(R.id.imgPostWy);
            tvPostTime = (TextView) itemView.findViewById(R.id.tvPostTime);
            tvPostDelete = (TextView) itemView.findViewById(R.id.tvPostDelete);
            imgContentPostWy = (ImageView) itemView.findViewById(R.id.imgContentPostWy);
            tvCountContentPost = (TextView) itemView.findViewById(R.id.tvCountContentPost);
            imgPraisePost = (ImageView) itemView.findViewById(R.id.imgPraisePost);
            tvCountPraisePost = (TextView) itemView.findViewById(R.id.tvCountPraisePost);
        }
    }
}
