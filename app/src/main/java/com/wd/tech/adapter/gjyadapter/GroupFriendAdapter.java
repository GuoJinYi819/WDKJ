package com.wd.tech.adapter.gjyadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.tech.R;
import com.wd.tech.bean.gjybean.GroupMemberListBean;
import com.wd.tech.custom.SwipeRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/4/26 0026 20:35
 * @Description: 用途：完成特定功能
 */
public class GroupFriendAdapter extends RecyclerView.Adapter<GroupFriendAdapter.MyGroupFriendHolder> {

    private List<GroupMemberListBean.ResultBean> list = new ArrayList<>();
    private Context context;

    private List<GroupMemberListBean.ResultBean> list3= new ArrayList<>();
    private List<GroupMemberListBean.ResultBean> list2= new ArrayList<>();
    private List<GroupMemberListBean.ResultBean> list1= new ArrayList<>();

    public GroupFriendAdapter(List<GroupMemberListBean.ResultBean> list, Context context) {
        this.list.addAll(list);
        this.context = context;
    }

    @NonNull
    @Override
    public MyGroupFriendHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_group_friend, parent, false);
        return new MyGroupFriendHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyGroupFriendHolder holder, int position) {
        switch (position){
            case 0:{
                holder.mTvGroup.setText("群主");
                for (int i = 0; i < list.size(); i++) {
                    GroupMemberListBean.ResultBean resultBean1 = list.get(i);
                    int role1 = resultBean1.getRole();
                    if (role1==3) {
                        list3.add(resultBean1);
                    }
                }
                GroupFriendChildAdapter groupFriendChildAdapter = new GroupFriendChildAdapter(list3, context,3);
                holder.mRecyclerChild.setAdapter(groupFriendChildAdapter);
                break;}
            case 1:{
                holder.mTvGroup.setText("管理员");
                for (int i = 0; i < list.size(); i++) {
                    GroupMemberListBean.ResultBean resultBean1 = list.get(i);
                    int role1 = resultBean1.getRole();
                    if (role1==2) {
                        list2.add(resultBean1);
                    }
                }
                GroupFriendChildAdapter groupFriendChildAdapter = new GroupFriendChildAdapter(list2, context,2);
                holder.mRecyclerChild.setAdapter(groupFriendChildAdapter);
                break;}
            case 2:{
                holder.mTvGroup.setText("群成员");
                for (int i = 0; i < list.size(); i++) {
                    GroupMemberListBean.ResultBean resultBean1 = list.get(i);
                    int role1 = resultBean1.getRole();
                    if (role1==1) {
                        list1.add(resultBean1);
                    }
                }
                GroupFriendChildAdapter groupFriendChildAdapter = new GroupFriendChildAdapter(list1, context,1);
                holder.mRecyclerChild.setAdapter(groupFriendChildAdapter);
                break;}
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    class MyGroupFriendHolder extends RecyclerView.ViewHolder {
        private TextView mTvGroup;
        private SwipeRecyclerView mRecyclerChild;
        public MyGroupFriendHolder(@NonNull View itemView) {
            super(itemView);
            mTvGroup = itemView.findViewById(R.id.tvGroup);
            mRecyclerChild = itemView.findViewById(R.id.recyclerChild);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
            mRecyclerChild.setLayoutManager(linearLayoutManager);
        }
    }

}
