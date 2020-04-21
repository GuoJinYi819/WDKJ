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
import com.wd.tech.activity.PersonActivity;
import com.wd.tech.bean.wybean.Event;
import com.wd.tech.bean.wybean.beanperson.CommunityUserPostVoListBean;

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
 * @date :2020/4/21 17:17
 * @classname :RecyclerPersonAdapter
 */
public class RecyclerPersonAdapter extends RecyclerView.Adapter<RecyclerPersonAdapter.PersonViewHolder> {
    private List<CommunityUserPostVoListBean> communityUserPostVoList = new ArrayList<>();
    private Context context;
    private boolean check=false;
    public RecyclerPersonAdapter(List<CommunityUserPostVoListBean> communityUserPostVoList, Context context) {
        this.communityUserPostVoList.addAll(communityUserPostVoList);
        this.context = context;
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.frag_community2_wy, parent, false);
        PersonViewHolder personViewHolder = new PersonViewHolder(view);
        return personViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder holder, int position) {
        holder.tvContent2Wy.setText(communityUserPostVoList.get(position).getContent());
        holder.imgContentPerson2Wy.setImageURI(communityUserPostVoList.get(position).getFile());
        holder.tvCountComment2.setText(communityUserPostVoList.get(position).getComment() + "");
        holder.tvCountPraise2.setText(communityUserPostVoList.get(position).getPraise() + "");
        //评论
        holder.tvCountComment2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check){
                    check=false;
                    holder.recyclrCommentList2Wy.setVisibility(View.INVISIBLE);
                    holder.tvWu2.setVisibility(View.INVISIBLE);
                    RecyclerView recyclrCommentListWy = holder.recyclrCommentList2Wy;
                    //布局管理器
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
                    linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
                    recyclrCommentListWy.setLayoutManager(linearLayoutManager);
                    //适配器
                }else{
                    check=true;
                    holder.recyclrCommentList2Wy.setVisibility(View.GONE);
                    holder.tvWu2.setVisibility(View.GONE);
                }
            }
        });
        //点赞
        holder.imgCountPraise2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点赞
                //EventBus传值
                //Event event=new Event();
                int whetherGreat = communityUserPostVoList.get(position).getWhetherGreat();
                if (whetherGreat == 1) {
                    //已点赞
                    //获取资源文件
                    Resources resources = context.getResources();
                    Bitmap bitmap = BitmapFactory.decodeResource(resources, R.mipmap.common_icon_prise_n_xhdpi);
                    holder.imgCountPraise2.setImageBitmap(bitmap);
                    //设置
                    int praise = communityUserPostVoList.get(position).getPraise();
                    praise--;
                    if (praise < 0) {
                        praise = 0;
                    }
                    holder.tvCountPraise2.setText(praise + "");
                    //
                    communityUserPostVoList.get(position).setPraise(praise);
                    communityUserPostVoList.get(position).setWhetherGreat(2);
                } else if (whetherGreat == 2) {
                    //未点赞
                    //获取资源文件
                    Resources resources = context.getResources();
                    Bitmap bitmap = BitmapFactory.decodeResource(resources, R.mipmap.common_icon_praise_s_xhdpi);
                    holder.imgCountPraise2.setImageBitmap(bitmap);
                    //设置
                    int praise = communityUserPostVoList.get(position).getPraise();
                    praise++;
                    holder.tvCountPraise2.setText(praise + "");
                    //
                    communityUserPostVoList.get(position).setPraise(praise);
                    communityUserPostVoList.get(position).setWhetherGreat(1);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return communityUserPostVoList.size();
    }

    class PersonViewHolder extends RecyclerView.ViewHolder {
        private TextView tvContent2Wy;
        private SimpleDraweeView imgContentPerson2Wy;
        private ImageView imgComment2Wy;
        private TextView tvCountComment2;
        private ImageView imgCountPraise2;
        private TextView tvCountPraise2;
        private RecyclerView recyclrCommentList2Wy;
        private TextView tvWu2;
        public PersonViewHolder(@NonNull View itemView) {
            super(itemView);
            tvContent2Wy = (TextView) itemView.findViewById(R.id.tvContent2Wy);
            imgContentPerson2Wy = (SimpleDraweeView) itemView.findViewById(R.id.imgContentPerson2Wy);
            imgComment2Wy = (ImageView) itemView.findViewById(R.id.imgComment2Wy);
            tvCountComment2 = (TextView) itemView.findViewById(R.id.tvCountComment2);
            imgCountPraise2 = (ImageView) itemView.findViewById(R.id.imgCountPraise2);
            tvCountPraise2 = (TextView) itemView.findViewById(R.id.tvCountPraise2);
            recyclrCommentList2Wy = (RecyclerView) itemView.findViewById(R.id.recyclrCommentList2Wy);
            tvWu2 = (TextView) itemView.findViewById(R.id.tvWu2);
        }
    }
}
