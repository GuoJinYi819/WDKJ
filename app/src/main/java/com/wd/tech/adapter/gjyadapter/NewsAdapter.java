package com.wd.tech.adapter.gjyadapter;

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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.wd.tech.R;
import com.wd.tech.activity.FriendNoticeActivity;
import com.wd.tech.activity.GroupNoticeActivity;
import com.wd.tech.activity.SendNewsActivity;
import com.wd.tech.bean.gjybean.NewsBean;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/4/22 0022 11:25
 * @Description: 用途：完成特定功能
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyNewsHolder> {

    private Context context;
    private ArrayList<NewsBean> list = new ArrayList<NewsBean>();

    public NewsAdapter(Context context,ArrayList<NewsBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyNewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_news, parent, false);
        return new MyNewsHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyNewsHolder holder, int position) {
        NewsBean newsBean = list.get(position);

        String img = newsBean.getImg();
        if (img.equals("null")||img.equals("nullnull")) {
            String name = newsBean.getName();
            holder.mTvNotice.setText(name);
            Resources resources = context.getResources();
            Bitmap bitmap = BitmapFactory.decodeResource(resources, R.mipmap.message_icon_notice_n_xhdpi);
            holder.mIvNoticePic.setImageBitmap(bitmap);
            String time = newsBean.getTime();
            holder.tvTime.setText(time);
        }else {
            String name = newsBean.getName();
            holder.mTvNotice.setText(name);
            String img1 = newsBean.getImg();
            Glide.with(context).load(img1).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(holder.mIvNoticePic);
            String time = newsBean.getTime();
            holder.tvTime.setText(time);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = holder.mTvNotice.getText().toString();
                if (text.contains("好友通知")) {
                    //跳转至 好友通知界面
                    Intent intent = new Intent(context, FriendNoticeActivity.class);
                    context.startActivity(intent);
                }else if (text.contains("群通知")){
                    //跳转至 群通知界面
                    Intent intent = new Intent(context, GroupNoticeActivity.class);
                    context.startActivity(intent);
                }else{
                    Intent intent = new Intent(context, SendNewsActivity.class);
                    String name = newsBean.getName();
                    String userId = newsBean.getUserId();
                    String img1 = newsBean.getImg();
                    intent.putExtra("headPic",img1);
                    intent.putExtra("name",name);
                    intent.putExtra("friend",Integer.parseInt(userId));
                    intent.putExtra("help",1);
                    context.startActivity(intent);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyNewsHolder extends RecyclerView.ViewHolder {
        private ImageView mIvNoticePic;
        private TextView mTvNotice,tvTime;
        public MyNewsHolder(@NonNull View itemView) {
            super(itemView);
            mIvNoticePic = itemView.findViewById(R.id.ivNoticePic);
            mTvNotice = itemView.findViewById(R.id.tvNotice);
            tvTime = itemView.findViewById(R.id.tvTime);
        }
    }

    public void setList(ArrayList<NewsBean> list) {
        this.list = list;
    }
}
