package com.wd.tech.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.wd.tech.R;
import com.wd.tech.adapter.qzjadapter.IntegralAdapter;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.qzjbean.detail.DetailBean;
import com.wd.tech.bean.qzjbean.inter.InterBean;
import com.wd.tech.bean.wybean.beanscore.ResultBean;
import com.wd.tech.bean.wybean.beanscore.ScoreBean;
import com.wd.tech.bean.wybean.beanscoredetailed.ScoreDetailedBean;
import com.wd.tech.mvp.qzjmvp.detailmvp.IDetailPresenterImpl;
import com.wd.tech.mvp.qzjmvp.inter.InterConter;
import com.wd.tech.mvp.qzjmvp.inter.InterModelImpl;
import com.wd.tech.mvp.qzjmvp.inter.InterPresenterImpl;
import com.wd.tech.mvp.wymvp.mvpscore.IScoreContract;
import com.wd.tech.mvp.wymvp.mvpscore.ScorePresenterImpl;
import com.wd.tech.net.ApiService;
import com.wd.tech.net.RetrofitUtil;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : Quzijun
 * @version 创建时间：2020/4/28 16:04
 * @Description: 用途：积分兑换 */
public class IntegralActivity extends BaseActivity<InterPresenterImpl> implements InterConter.IScoreView {
    private androidx.recyclerview.widget.RecyclerView re;
    private android.widget.TextView jf;
    private android.widget.TextView wdjf;
    private IntegralAdapter adapter;
    private android.widget.Button bu;
    private int id;
    private android.widget.ImageView fanhui;

    @Override
    public int initLayout() {
        return R.layout.activity_integral;
    }

    @Override
    public void initView() {

        re = findViewById(R.id.re);
        jf = findViewById(R.id.jf);
        wdjf = findViewById(R.id.wdjf);
        bu = findViewById(R.id.bu);
        fanhui = findViewById(R.id.fanhui);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 3);
        int integral = intent.getIntExtra("integral", 3);
        jf.setText(integral+"");
        //请求详情
        presenter.getDetailedScore(id);
        //积分查询
        presenter.getScore();
        //点击兑换
        bu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.getInterDate(id,integral);
            }
        });
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(IntegralActivity.this,ConsultaActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("ismoney",1);
                startActivity(intent1);
            }
        });
    }

    @Override
    public InterPresenterImpl initPresenter() {
        return new InterPresenterImpl();
    }

    @Override
    public void onSuccess(ScoreBean scoreBean) {
        ResultBean result = scoreBean.getResult();
        int amount = result.getAmount();
        wdjf.setText(amount+"");
    }

    @Override
    public void onDetailData(DetailBean detailBean) {
        DetailBean.ResultBean result = detailBean.getResult();
        adapter = new IntegralAdapter(result,IntegralActivity.this);
        StaggeredGridLayoutManager s = new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
        re.setLayoutManager(s);
        re.setAdapter(adapter);
    }

    @Override
    public void onInterDate(InterBean interBean) {
        String message = interBean.getMessage();
        //判断成功与否
        if (message.equals("积分不够,无法兑换")){
            //弹框
            AlertDialog.Builder builder = new AlertDialog.Builder(IntegralActivity.this);
            builder.setTitle("兑换失败");
            builder.setMessage("您目前积分不足");

            //设置正面按钮
            builder.setPositiveButton("去赚积分", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            //设置反面按钮
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }else if (message.equals("兑换成功")){
            AlertDialog.Builder builder = new AlertDialog.Builder(IntegralActivity.this);
            builder.setTitle("兑换成功");
            builder.setMessage("您已兑换了该咨询的阅读权");

            //设置正面按钮
            builder.setPositiveButton("继续阅读", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(IntegralActivity.this, ConsultaActivity.class);
                    intent.putExtra("id",id);
                    intent.putExtra("ismoney",2);
                    startActivity(intent);
                    dialog.dismiss();
                }
            });
            //设置反面按钮
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }else {
            AlertDialog.Builder builder = new AlertDialog.Builder(IntegralActivity.this);
            builder.setTitle("兑换成功");
            builder.setMessage("您已兑换了该咨询的阅读权");

            //设置正面按钮
            builder.setPositiveButton("继续阅读", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(IntegralActivity.this, ConsultaActivity.class);
                    intent.putExtra("id",id);
                    intent.putExtra("ismoney",2);
                    startActivity(intent);
                    finish();
                    dialog.dismiss();
                }
            });
            //设置反面按钮
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

//    DetailBean.ResultBean result = value.getResult();
//    adapter = new IntegralAdapter(result,IntegralActivity.this);
//    StaggeredGridLayoutManager s = new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
//                        re.setLayoutManager(s);
//                        re.setAdapter(adapter);
}
