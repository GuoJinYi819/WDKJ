package com.wd.tech.adapter.qzjadapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.tech.R;
import com.wd.tech.bean.gjybean.WxBean;
import com.wd.tech.bean.qzjbean.consultationlist.ConResultBean;
import com.wd.tech.bean.qzjbean.great.GreatBean;
import com.wd.tech.net.RetrofitUtil;
import com.wd.tech.util.WXUtil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : Quzijun
 * @version 创建时间：2020/4/21 21:09
 * @Description: 用途：完成特定功能
 */
public class ConListAdapter extends RecyclerView.Adapter<ConListAdapter.ViewHoder> {
    private List<ConResultBean> list = new ArrayList<>();
    private Context context;
    public AdapterCallBack adapterCallBack;

    public void setAdapterCallBack(AdapterCallBack adapterCallBack) {
        this.adapterCallBack = adapterCallBack;
    }

    public ConListAdapter(List<ConResultBean> list, Context context) {
        this.list.addAll(list);
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_conlist_nogg, parent, false);
        ViewHoder viewHoder = new ViewHoder(inflate);
        return viewHoder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoder holder, int position) {
        holder.img.setImageURI(list.get(position).getThumbnail());
        holder.name.setText(list.get(position).getTitle());
        holder.nr.setText(list.get(position).getSummary());
        holder.gzs.setText(list.get(position).getSource());
        long releaseTime = list.get(position).getReleaseTime();
        String standardDate = getStandardDate(releaseTime + "");
        holder.sjc.setText(standardDate);
        holder.fxs.setText(list.get(position).getShare()+"");
        holder.xhs.setText(list.get(position).getCollection()+"");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = list.get(position).getId();
                int whetherPay = list.get(position).getWhetherPay();
                if (adapterCallBack!=null){
                    adapterCallBack.onGiveId(id,whetherPay);
                }
            }
        });
        int whetherCollection = list.get(position).getWhetherCollection();
        Resources resources = context.getResources();
        if (whetherCollection==1){
            Bitmap bitmap = BitmapFactory.decodeResource(resources, R.mipmap.xhxhxhxh);
            holder.xh.setImageBitmap(bitmap);
        }else {
            Bitmap bitmap = BitmapFactory.decodeResource(resources, R.mipmap.xhxh);
            holder.xh.setImageBitmap(bitmap);
        }
        holder.xh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int whetherCollection1 = list.get(position).getWhetherCollection();
                if (whetherCollection1==1){
                    Bitmap bitmap = BitmapFactory.decodeResource(resources, R.mipmap.xhxh);
                    holder.xh.setImageBitmap(bitmap);
                    list.get(position).setWhetherCollection(2);
                    String trim = holder.xhs.getText().toString().trim();
                    Integer integer = Integer.valueOf(trim);
                    integer--;
                    holder.xhs.setText(integer+"");
                    RetrofitUtil instance = RetrofitUtil.getInstance();
                    int id = list.get(position).getId();
                    Observable<GreatBean> delete = instance.createService().getDelete(id);
                    delete.subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Observer<GreatBean>() {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @Override
                                public void onNext(GreatBean value) {
                                    String message = value.getMessage();
                                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onError(Throwable e) {

                                }

                                @Override
                                public void onComplete() {

                                }
                            });

                }else if (whetherCollection1==2){
                    Bitmap bitmap = BitmapFactory.decodeResource(resources, R.mipmap.xhxhxhxh);
                    holder.xh.setImageBitmap(bitmap);
                    list.get(position).setWhetherCollection(1);
                    String trim = holder.xhs.getText().toString().trim();
                    Integer integer = Integer.valueOf(trim);
                    integer++;
                    holder.xhs.setText(integer+"");
                    RetrofitUtil instance = RetrofitUtil.getInstance();
                    int id = list.get(position).getId();
                    Observable<GreatBean> delete = instance.createService().getAdd(id);
                    delete.subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Observer<GreatBean>() {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @Override
                                public void onNext(GreatBean value) {
                                    String message = value.getMessage();
                                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onError(Throwable e) {

                                }

                                @Override
                                public void onComplete() {

                                }
                            });
                }
            }
        });
//        if (whetherCollection==1){
//
//            Bitmap bitmap = BitmapFactory.decodeResource(resources, R.mipmap.xhxhxhxh);
//            holder.xh.setImageBitmap(bitmap);
//            holder.xh.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    list.get(position).setWhetherCollection(2);
//                    Bitmap bitmap = BitmapFactory.decodeResource(resources, R.mipmap.xhxh);
//                    holder.xh.setImageBitmap(bitmap);
//                }
//            });
//        }else {
//            Bitmap bitmap = BitmapFactory.decodeResource(resources, R.mipmap.xhxh);
//            holder.xh.setImageBitmap(bitmap);
//            holder.xh.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    list.get(position).setWhetherCollection(1);
//                    Bitmap bitmap = BitmapFactory.decodeResource(resources, R.mipmap.xhxhxhxh);
//                    holder.xh.setImageBitmap(bitmap);
//                }
//            });
//        }
        Bitmap bitmap = BitmapFactory.decodeResource(resources, R.mipmap.common_icon_collect_n_xxhdpi);
        holder.fx.setImageBitmap(bitmap);
        holder.fx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String trim = holder.fxs.getText().toString().trim();
//                Integer integer = Integer.valueOf(trim);
//                integer++;
//                holder.fxs.setText(integer+"");
//                Toast.makeText(context, "分享成功", Toast.LENGTH_SHORT).show();
                //微信分享
                ConResultBean conResultBean = list.get(position);

                WXUtil.wechatShare(1,"a");
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHoder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView img;
        private final TextView name;
        private final TextView nr;
        private final TextView gzs;
        private final TextView sjc;
        private final TextView xhs;
        private final TextView fxs;
        private final ImageView xh;
        private final ImageView fx;

        public ViewHoder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            name = itemView.findViewById(R.id.name);
            nr = itemView.findViewById(R.id.nr);
            gzs = itemView.findViewById(R.id.gzs);
            sjc = itemView.findViewById(R.id.sjc);
            xhs = itemView.findViewById(R.id.xhs);
            fxs = itemView.findViewById(R.id.fxs);
            xh = itemView.findViewById(R.id.xh);
            fx = itemView.findViewById(R.id.fx);
        }
    }
    public String getStandardDate(String timeStr) {

        StringBuffer sb = new StringBuffer();

        long t = Long.parseLong(timeStr);
        long time = System.currentTimeMillis() - (t*1000);
        long mill = (long) Math.ceil(time /1000);//秒前

        long minute = (long) Math.ceil(time/60/1000.0f);// 分钟前

        long hour = (long) Math.ceil(time/60/60/1000.0f);// 小时

        long day = (long) Math.ceil(time/24/60/60/1000.0f);// 天前

        if (day - 1 > 0) {
            sb.append(day + "天");
        } else if (hour - 1 > 0) {
            if (hour >= 24) {
                sb.append("1天");
            } else {
                sb.append(hour + "小时");
            }
        } else if (minute - 1 > 0) {
            if (minute == 60) {
                sb.append("1小时");
            } else {
                sb.append(minute + "分钟");
            }
        } else if (mill - 1 > 0) {
            if (mill == 60) {
                sb.append("1分钟");
            } else {
                sb.append(mill + "秒");
            }
        } else {
            sb.append("刚刚");
        }
        if (!sb.toString().equals("刚刚")) {
            sb.append("前");
        }
        return sb.toString();
    }
    public interface AdapterCallBack{
        void onGiveId(int id,int ismoney);
    }
}
