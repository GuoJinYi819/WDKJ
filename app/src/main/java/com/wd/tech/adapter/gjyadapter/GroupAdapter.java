package com.wd.tech.adapter.gjyadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.wd.tech.R;
import com.wd.tech.bean.gjybean.JoinedGroupBean;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/4/21 0021 11:49
 * @Description: 用途：完成特定功能
 */
public class GroupAdapter extends BaseAdapter {
    private List<JoinedGroupBean.ResultBean> list = new ArrayList<>();
    private Context context;

    public GroupAdapter(List<JoinedGroupBean.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyGroupHodler myGroupHodler = null;
        if (convertView==null){
            myGroupHodler = new MyGroupHodler();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_joined_group,parent,false);
            myGroupHodler.ivPic = convertView.findViewById(R.id.ivGroupPic);
            myGroupHodler.tvGroupName = convertView.findViewById(R.id.tvGroupName);
            convertView.setTag(myGroupHodler);
        }else {
            myGroupHodler = (MyGroupHodler) convertView.getTag();
        }
        JoinedGroupBean.ResultBean resultBean = list.get(position);
        String groupImage = resultBean.getGroupImage();
        Glide.with(context).load(groupImage).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(myGroupHodler.ivPic);
        String groupName = resultBean.getGroupName();
        myGroupHodler.tvGroupName.setText(groupName);
        return convertView;
    }

    class MyGroupHodler{
        private ImageView ivPic;
        private TextView tvGroupName;
    }

    public List<JoinedGroupBean.ResultBean> getList() {
        return list;
    }

    public void setList(List<JoinedGroupBean.ResultBean> list) {
        this.list = list;
    }
}
