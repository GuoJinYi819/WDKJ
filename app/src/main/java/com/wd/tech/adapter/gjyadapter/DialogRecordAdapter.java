package com.wd.tech.adapter.gjyadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.wd.tech.R;
import com.wd.tech.bean.gjybean.DialogueRecordBean;
import com.wd.tech.util.RsaCoder;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/4/23 0023 16:19
 * @Description: 用途：完成特定功能
 */
public class DialogRecordAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<DialogueRecordBean.ResultBean> list = new ArrayList<>();

    public DialogRecordAdapter(Context context, List<DialogueRecordBean.ResultBean> list) {
        this.context = context;
        this.list.addAll(list);
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 1) {
            //我发送的消息
            View inflate = LayoutInflater.from(context).inflate(R.layout.item_send_right, parent, false);
            MyDialogRightHolder myDialogRightHolder = new MyDialogRightHolder(inflate);
            return myDialogRightHolder;
        } else if(viewType==2){
            //好友发送的消息
            View inflate = LayoutInflater.from(context).inflate(R.layout.item_send_left, parent, false);
            MyDialogLeftHolder myDialogLeftHolder = new MyDialogLeftHolder(inflate);
            return myDialogLeftHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        DialogueRecordBean.ResultBean resultBean = list.get(position);
        //我发送的
        if( holder instanceof  MyDialogRightHolder){
            String headPic = resultBean.getHeadPic();
            String content = resultBean.getContent();
            Glide.with(context)
                    .load(headPic)
                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                    .into(((MyDialogRightHolder) holder).mIvHeadPic);
            //解密
            try {
                String c = RsaCoder.decryptByPublicKey(content);
                ((MyDialogRightHolder) holder).mTvContent.setText(c);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //别人发送的
        if(holder instanceof  MyDialogLeftHolder){
            String headPic = resultBean.getHeadPic();
            String content = resultBean.getContent();
            Glide.with(context)
                    .load(headPic)
                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                    .into(((MyDialogLeftHolder) holder).mIvHeadPic);
            //解密
            try {
                String c = RsaCoder.decryptByPublicKey(content);
                ((MyDialogLeftHolder) holder).mTvContent.setText(c);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        DialogueRecordBean.ResultBean resultBean = list.get(position);
        int direction = resultBean.getDirection();
        return direction;
    }

    class MyDialogLeftHolder extends RecyclerView.ViewHolder {
        private ImageView mIvHeadPic;
        private TextView mTvContent;
        public MyDialogLeftHolder(@NonNull View itemView) {
            super(itemView);
            mIvHeadPic = itemView.findViewById(R.id.ivHeadPic);
            mTvContent = itemView.findViewById(R.id.tvContent);
        }
    }

    class MyDialogRightHolder extends RecyclerView.ViewHolder {
        private ImageView mIvHeadPic;
        private TextView mTvContent;
        public MyDialogRightHolder(@NonNull View itemView) {
            super(itemView);
            mIvHeadPic = itemView.findViewById(R.id.ivHeadPic);
            mTvContent = itemView.findViewById(R.id.tvContent);
        }
    }

    public List<DialogueRecordBean.ResultBean> getList() {
        return list;
    }
}
