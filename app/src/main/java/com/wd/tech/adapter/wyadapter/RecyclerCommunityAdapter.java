package com.wd.tech.adapter.wyadapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.tech.R;
import com.wd.tech.activity.CommentListActivity;
import com.wd.tech.bean.wybean.Event;
import com.wd.tech.bean.wybean.beanhome.ResultBean;
import com.wd.tech.net.TimeToUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author 王阳
 * Class :1708A
 * @description:
 * @date :2020/4/19 13:38
 * @classname :RecyclerCommunityAdapter
 */
public class RecyclerCommunityAdapter extends RecyclerView.Adapter<RecyclerCommunityAdapter.CommunityViewHolder> {
    private List<ResultBean> result = new ArrayList<>();
    private Context context;
    private boolean check=false;
    public RecyclerCommunityAdapter(List<ResultBean> result, Context context) {
        this.result.addAll(result);
        this.context = context;
    }

    @NonNull
    @Override
    public CommunityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.frag_community_wy, parent, false);
        CommunityViewHolder communityViewHolder = new CommunityViewHolder(view);
        return communityViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CommunityViewHolder holder, int position) {
        holder.imgHeadWy.setImageURI(result.get(position).getHeadPic());
        holder.tvPersonNameWy.setText(result.get(position).getNickName());
        String time = TimeToUtil.getTime(result.get(position).getPublishTime());
        holder.tvTimeWy.setText(time);
        holder.tvSignatureWy.setText(result.get(position).getSignature());
        holder.tvContentWy.setText(result.get(position).getContent());
        holder.imgContentPersonWy.setImageURI(result.get(position).getFile());
        holder.tvCountComment.setText(result.get(position).getComment() + "");
        holder.tvCountPraise.setText(result.get(position).getPraise() + "");
        //评论  跳转
        holder.imgCommentWy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取communityId   传参
                Event event=new Event();
                //id
                event.setId(position);
                //头像
                event.setHead(result.get(position).getHeadPic());
                //名称
                event.setPersonName(result.get(position).getNickName());
                //数量
                event.setCount(result.size());
                EventBus.getDefault().postSticky(event);
                Intent intent = new Intent(context, CommentListActivity.class);
                context.startActivity(intent);
            }
        });
        //评论
        holder.tvCountComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check){
                    check=false;
                    holder.recyclrCommentListWy.setVisibility(View.INVISIBLE);
                    holder.tvWu.setVisibility(View.INVISIBLE);
                    RecyclerView recyclrCommentListWy = holder.recyclrCommentListWy;
                    //布局管理器
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
                    linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
                    recyclrCommentListWy.setLayoutManager(linearLayoutManager);
                    //适配器
                }else{
                    check=true;
                    holder.recyclrCommentListWy.setVisibility(View.GONE);
                    holder.tvWu.setVisibility(View.GONE);
                }
            }
        });
        //点赞
        holder.imgCountPraise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点赞
                //EventBus传值
                //Event event=new Event();
                int whetherGreat = result.get(position).getWhetherGreat();
                if (whetherGreat == 1) {
                    //已点赞
                    //获取资源文件
                    Resources resources = context.getResources();
                    Bitmap bitmap = BitmapFactory.decodeResource(resources, R.mipmap.common_icon_prise_n_xhdpi);
                    holder.imgCountPraise.setImageBitmap(bitmap);
                    //设置
                    int praise = result.get(position).getPraise();
                    praise--;
                    if (praise < 0) {
                        praise = 0;
                    }
                    holder.tvCountPraise.setText(praise + "");
                    //
                    result.get(position).setPraise(praise);
                    result.get(position).setWhetherGreat(2);
                } else if (whetherGreat == 2) {
                    //未点赞
                    //获取资源文件
                    Resources resources = context.getResources();
                    Bitmap bitmap = BitmapFactory.decodeResource(resources, R.mipmap.common_icon_praise_s_xhdpi);
                    holder.imgCountPraise.setImageBitmap(bitmap);
                    //设置
                    int praise = result.get(position).getPraise();
                    praise++;
                    holder.tvCountPraise.setText(praise + "");
                    //
                    result.get(position).setPraise(praise);
                    result.get(position).setWhetherGreat(1);
                }
            }
        });
        //跳转  个人页面
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    class CommunityViewHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView imgHeadWy;
        private TextView tvPersonNameWy;
        private TextView tvTimeWy;
        private TextView tvSignatureWy;
        private TextView tvContentWy;
        private ImageView imgCommentWy;
        private SimpleDraweeView imgContentPersonWy;
        private TextView tvCountComment;
        private ImageView imgCountPraise;
        private TextView tvCountPraise;
        private RecyclerView recyclrCommentListWy;
        private TextView tvWu;

        public CommunityViewHolder(@NonNull View itemView) {
            super(itemView);
            imgHeadWy = (SimpleDraweeView) itemView.findViewById(R.id.imgHeadWy);
            tvPersonNameWy = (TextView) itemView.findViewById(R.id.tvPersonNameWy);
            tvTimeWy = (TextView) itemView.findViewById(R.id.tvTimeWy);
            tvSignatureWy = (TextView) itemView.findViewById(R.id.tvSignatureWy);
            tvContentWy = (TextView) itemView.findViewById(R.id.tvContentWy);
            imgCommentWy = (ImageView) itemView.findViewById(R.id.imgCommentWy);
            imgContentPersonWy = (SimpleDraweeView) itemView.findViewById(R.id.imgContentPersonWy);
            tvCountComment = (TextView) itemView.findViewById(R.id.tvCountComment);
            imgCountPraise = (ImageView) itemView.findViewById(R.id.imgCountPraise);
            tvCountPraise = (TextView) itemView.findViewById(R.id.tvCountPraise);
            recyclrCommentListWy=itemView.findViewById(R.id.recyclrCommentListWy);
            tvWu=itemView.findViewById(R.id.tvWu);
        }
    }
}
