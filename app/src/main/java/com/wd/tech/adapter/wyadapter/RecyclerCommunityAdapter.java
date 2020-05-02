package com.wd.tech.adapter.wyadapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.tech.R;
import com.wd.tech.activity.CommentListActivity;
import com.wd.tech.activity.PersonActivity;
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
    private boolean check=true;

    private List<String> resultList =new ArrayList<>();

    public RecyclerCommunityAdapter(List<ResultBean> result, Context context) {
        this.result.addAll(result);
        this.context = context;
    }

    public void setResultList(List<String> resultList) {
        this.resultList = resultList;
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
        holder.imgContentPersonWy.setOnClickListener(new View.OnClickListener() {
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
        holder.tvContentWy.setOnClickListener(new View.OnClickListener() {
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
        //评论   发评论   点击 评论图片  发评论
        holder.imgCommentWy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //传值
                int id = result.get(position).getId();
                Event eventBus = new Event();
                eventBus.setId(id);

                if(check){
                    check=false;
                    holder.linearCommentWy.setVisibility(View.VISIBLE);
                    RecyclerView recyclerCommentListWy = holder.recyclerCommentListWy;
                    //布局管理器
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
                    linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
                    recyclerCommentListWy.setLayoutManager(linearLayoutManager);
                    //适配器
                    Log.d("==", "onClick: "+resultList);
                    CommentAdapter commentAdapter = new CommentAdapter(resultList, context);
                    recyclerCommentListWy.setAdapter(commentAdapter);
                    //软键盘  发送
                    holder.etShuRu.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                        @Override
                        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                            if(actionId== EditorInfo.IME_ACTION_SEND){
                                String str = holder.etShuRu.getText().toString();
                                if(!TextUtils.isEmpty(str)){
                                    eventBus.setContent(str);
                                    EventBus.getDefault().postSticky(eventBus);
                                }else{
                                    Toast.makeText(context,"评论不能为空",Toast.LENGTH_SHORT).show();
                                }
                            }
                            return false;//隐藏软键盘
                        }
                    });
                    //按钮  发送
                    holder.btnSend.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String str = holder.etShuRu.getText().toString();
                            if(!TextUtils.isEmpty(str)){
                                eventBus.setContent(str);
                                EventBus.getDefault().postSticky(eventBus);
                            }else{
                                Toast.makeText(context,"评论不能为空",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else{
                    check=true;
                    holder.linearCommentWy.setVisibility(View.GONE);
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
        //跳转  个人页面   头像  名字
        holder.imgHeadWy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取用户  ID
                int id = result.get(position).getId();
                Event event=new Event();
                //id
                event.setId(id);
                EventBus.getDefault().postSticky(event);
                //跳转   个人
                Intent intent = new Intent(context, PersonActivity.class);
                context.startActivity(intent);
            }
        });
        holder.tvPersonNameWy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取用户  ID
                int id = result.get(position).getId();
                Event event=new Event();
                //id
                event.setId(id);
                EventBus.getDefault().postSticky(event);
                //跳转   个人
                Intent intent = new Intent(context, PersonActivity.class);
                context.startActivity(intent);
            }
        });
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
        private RecyclerView recyclerCommentListWy;
        private EditText etShuRu;
        private LinearLayout linearCommentWy;
        private Button btnSend;

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
            recyclerCommentListWy=itemView.findViewById(R.id.recyclerCommentListWy);
            etShuRu=itemView.findViewById(R.id.etShuRu);
            linearCommentWy=itemView.findViewById(R.id.linearCommentWy);
            btnSend=itemView.findViewById(R.id.btnSend);
        }
    }
}
