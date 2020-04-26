package com.wd.tech.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.wd.tech.R;
import com.wd.tech.adapter.qzjadapter.DetailAdapter;
import com.wd.tech.adapter.qzjadapter.DetailCommentAdapter;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.base.BasePresenter;
import com.wd.tech.bean.qzjbean.addcomment.AddBean;
import com.wd.tech.bean.qzjbean.comment.ConCommentBean;
import com.wd.tech.bean.qzjbean.detail.DetailBean;
import com.wd.tech.mvp.qzjmvp.detailmvp.IDetailConter;
import com.wd.tech.mvp.qzjmvp.detailmvp.IDetailPresenterImpl;
import com.wd.tech.net.ApiService;
import com.wd.tech.net.RetrofitUtil;

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
 * @version 创建时间：2020/4/23 19:49
 * @Description: 用途：完成特定功能
 */
public class ConsultaActivity extends BaseActivity<IDetailPresenterImpl> implements IDetailConter.IDetaView {
    private androidx.recyclerview.widget.RecyclerView re;
    private DetailAdapter adapter;
    private android.widget.ImageView fanhui;
    private android.widget.EditText etext;
    private android.widget.ImageView plt;
    private android.widget.TextView pls;
    private android.widget.ImageView dzt;
    private android.widget.TextView dzs;
    private android.widget.ImageView sct;
    private android.widget.ImageView fxt;
    private android.widget.TextView fxs;
    private DetailCommentAdapter commentAdapter;
    private List<ConCommentBean.ResultBean> list = new ArrayList<>();
    private int id;

    @Override
    public int initLayout() {
        return R.layout.activity_consulation;
    }

    @Override
    public void initView() {

        re = findViewById(R.id.re);
        fanhui = findViewById(R.id.fanhui);
        etext = findViewById(R.id.etext);
        plt = findViewById(R.id.plt);
        pls = findViewById(R.id.pls);
        dzt = findViewById(R.id.dzt);
        dzs = findViewById(R.id.dzs);
        sct = findViewById(R.id.sct);
        fxt = findViewById(R.id.fxt);
        fxs = findViewById(R.id.fxs);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 3);
        Log.d("XXX", id +"");
        presenter.onDetaDate(id);
        presenter.onCommentDate(id,1,5);
        etext.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND){
                    String trim = etext.getText().toString().trim();
                    presenter.onAddDate(id,trim);
                    InputMethodManager im = (InputMethodManager) ConsultaActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                    im.hideSoftInputFromWindow(ConsultaActivity.this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }
                return true;
            }
        });
    }

    @Override
    public IDetailPresenterImpl initPresenter() {
        return new IDetailPresenterImpl();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onDetaSuccess(DetailBean detailBean) {
        DetailBean.ResultBean result = detailBean.getResult();
        StaggeredGridLayoutManager s = new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
        re.setLayoutManager(s);
        adapter = new DetailAdapter(result,ConsultaActivity.this);
        if (list!=null){
            adapter.setClist(list);
        }
        re.setAdapter(adapter);
        DetailBean.ResultBean list = adapter.getList();
        Resources resources = ConsultaActivity.this.getResources();
        Bitmap bitmap = BitmapFactory.decodeResource(resources, R.mipmap.plplpl);
        plt.setImageBitmap(bitmap);
        pls.setText(list.getComment()+"");
        int whetherGreat = list.getWhetherGreat();
        int whetherCollection = list.getWhetherCollection();
        if (whetherCollection==1){
            Bitmap bitmap1 = BitmapFactory.decodeResource(resources, R.mipmap.xhxhxhxh);
            sct.setImageBitmap(bitmap1);
        }else {
            Bitmap bitmap2 = BitmapFactory.decodeResource(resources, R.mipmap.xhxh);
            sct.setImageBitmap(bitmap2);
        }
        if (whetherGreat==1){
            Bitmap bitmap1 = BitmapFactory.decodeResource(resources, R.mipmap.dzdzdz);
            dzt.setImageBitmap(bitmap1);
            int praise = list.getPraise();
            dzs.setText(praise+"");
        }else {
            Bitmap bitmap1 = BitmapFactory.decodeResource(resources, R.mipmap.bdz);
            dzt.setImageBitmap(bitmap1);
            int praise = list.getPraise();
            dzs.setText(praise+"");
        }
        int share = list.getShare();
        Bitmap bitmap3 = BitmapFactory.decodeResource(resources, R.mipmap.common_icon_collect_n_xxhdpi);
        fxt.setImageBitmap(bitmap3);
        fxs.setText(share+"");
    }

    @Override
    public void onComment(ConCommentBean conCommentBean) {
        List<ConCommentBean.ResultBean> result = conCommentBean.getResult();
        list = result;
    }

    public void refresh() {
        onCreate(null);
    }

    @Override
    public void onAddcomm(AddBean addBean) {
        String message = addBean.getMessage();
        Log.d("TTT",message);
        etext.setText("");
    }
}
