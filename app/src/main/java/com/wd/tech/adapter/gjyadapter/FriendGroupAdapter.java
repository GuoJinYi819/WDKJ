package com.wd.tech.adapter.gjyadapter;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.wd.tech.R;
import com.wd.tech.bean.gjybean.FriendChildListBean;
import com.wd.tech.bean.gjybean.FriendGroupListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/4/20 0020 17:43
 * @Description: 用途：完成特定功能
 */
public class FriendGroupAdapter extends BaseExpandableListAdapter {
    private List<FriendGroupListBean.ResultBean> list = new ArrayList<>();
    private List<FriendChildListBean.ResultBean> listChild;
    private Context context;
    private TextView mTvGroupName;
    private TextView mTvGroupNumber;

    public OnGruopIdListener onGruopIdListener;
    private ImageView mIvHeadPic;
    private TextView mTvRemarkName;
    private TextView mTvSignature;

    public OnFriendListener onFriendListener;

    public FriendGroupAdapter(List<FriendGroupListBean.ResultBean> list, Context context) {
        this.list.addAll(list);
        this.context = context;
    }

    public void setOnFriendListener(OnFriendListener onFriendListener) {
        this.onFriendListener = onFriendListener;
    }

    public void setListChild(List<FriendChildListBean.ResultBean> listChild) {
        this.listChild = listChild;
    }

    @Override
    public int getGroupCount() {
        return list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if (listChild != null) {
            return listChild.size();
        }
        return 0;
    }

    @Override
    public Object getGroup(int groupPosition) {
        FriendGroupListBean.ResultBean resultBean = list.get(groupPosition);
        return resultBean;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        if (listChild != null) {
            FriendChildListBean.ResultBean resultBean = listChild.get(childPosition);
            return resultBean;
        }
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return groupPosition+childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_friend_group, parent, false);
        mTvGroupName = view.findViewById(R.id.tvGroupName);
        mTvGroupNumber = view.findViewById(R.id.tvGroupNumber);

        FriendGroupListBean.ResultBean resultBean = list.get(groupPosition);
        String groupName = resultBean.getGroupName();
        mTvGroupName.setText(groupName);
        int currentNumber = resultBean.getCurrentNumber();
        mTvGroupNumber.setText(currentNumber + "/10");
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int groupId = resultBean.getGroupId();
                onGruopIdListener.onGroupId(groupId,groupPosition);
            }
        });
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        if (listChild != null) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_friend_child, parent, false);
            mIvHeadPic = view.findViewById(R.id.ivHeadPic);
            mTvRemarkName = view.findViewById(R.id.tvRemarkName);
            mTvSignature = view.findViewById(R.id.tvSignature);
            FriendChildListBean.ResultBean resultBean = listChild.get(childPosition);
            //头像
            String headPic = resultBean.getHeadPic();
            Glide.with(context).load(headPic).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(mIvHeadPic);
            //备注名
            String remarkName = resultBean.getRemarkName();
            mTvRemarkName.setText(remarkName);
            //获取签名
            String signature = resultBean.getSignature();
            if (!TextUtils.isEmpty(signature)) {
                //还没有设置签名
                mTvSignature.setText("该用户没有设置签名");
            } else {
                mTvSignature.setText(signature);
            }
            //回调ID
            int friendUid = resultBean.getFriendUid();
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    onFriendListener.onFriendId(friendUid);
                }
            });

            return view;
        }

        return null;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public interface OnGruopIdListener {
        void onGroupId(int id,int group);
    }

    public interface OnFriendListener {
        void onFriendId(int friend);
    }

    public List<FriendGroupListBean.ResultBean> getList() {
        return list;
    }


}
