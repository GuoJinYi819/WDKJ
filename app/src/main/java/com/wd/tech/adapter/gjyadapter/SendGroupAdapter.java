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
import com.wd.tech.bean.gjybean.QueryGroupBean;
import com.wd.tech.net.SpUtil;
import com.wd.tech.util.RsaCoder;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/4/24 0024 16:50
 * @Description: 用途：完成特定功能
 */
public class SendGroupAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<QueryGroupBean.ResultBean> list = new ArrayList<>();

    public SendGroupAdapter(Context context, List<QueryGroupBean.ResultBean> list) {
        this.context = context;
        this.list.addAll(list);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 1) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.item_group_right, parent, false);
            MySendGroupRightHolder mySendGroupRightHolder = new MySendGroupRightHolder(inflate);
            return mySendGroupRightHolder;
        } else if (viewType == 2) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.item_group_left, parent, false);
            MySendGroupLeftHolder mySendGroupLeftHolder = new MySendGroupLeftHolder(inflate);
            return mySendGroupLeftHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        QueryGroupBean.ResultBean resultBean = list.get(position);
        if(holder instanceof MySendGroupLeftHolder){
            String nickName = resultBean.getNickName();
            try {

                ((MySendGroupLeftHolder) holder).mTvName.setText(nickName);

                String headPic = resultBean.getHeadPic();
                Glide.with(context).load(headPic).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(((MySendGroupLeftHolder) holder).mIvHeadPic);

                String chatContent = resultBean.getChatContent();
                String decryptByPublicKey = RsaCoder.decryptByPublicKey(chatContent);
                ((MySendGroupLeftHolder) holder).mTvContent.setText(decryptByPublicKey);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        if(holder instanceof MySendGroupRightHolder){
            String nickName = resultBean.getNickName();
            try {

                ((MySendGroupRightHolder) holder).mTvName.setText(nickName);

                String headPic = resultBean.getHeadPic();
                Glide.with(context).load(headPic).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(((MySendGroupRightHolder) holder).mIvHeadPic);

                String chatContent = resultBean.getChatContent();
                String decryptByPublicKey = RsaCoder.decryptByPublicKey(chatContent);
                ((MySendGroupRightHolder) holder).mTvContent.setText(decryptByPublicKey);
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
        QueryGroupBean.ResultBean resultBean = list.get(position);
        int userId = resultBean.getUserId();
        SpUtil instance = SpUtil.getInstance();
        int userId1 = instance.getSpInt("userId");
        if (userId == userId1) {
            return 1;
        } else {
            return 2;
        }

    }

    class MySendGroupLeftHolder extends RecyclerView.ViewHolder {
        private ImageView mIvHeadPic;
        private TextView mTvName;
        private TextView mTvContent;
        public MySendGroupLeftHolder(@NonNull View itemView) {
            super(itemView);
            mIvHeadPic = itemView.findViewById(R.id.ivHeadPic);
            mTvName = itemView.findViewById(R.id.tvName);
            mTvContent = itemView.findViewById(R.id.tvContent);
        }
    }

    class MySendGroupRightHolder extends RecyclerView.ViewHolder {
        private ImageView mIvHeadPic;
        private TextView mTvName;
        private TextView mTvContent;
        public MySendGroupRightHolder(@NonNull View itemView) {
            super(itemView);
            mIvHeadPic = itemView.findViewById(R.id.ivHeadPic);
            mTvName = itemView.findViewById(R.id.tvName);
            mTvContent = itemView.findViewById(R.id.tvContent);
        }
    }
}
