package com.wd.tech.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.PopupWindow;
import android.widget.TextView;


import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.wd.tech.R;
import com.wd.tech.adapter.qzjadapter.DetailAdapter;
import com.wd.tech.adapter.qzjadapter.DetailCommentAdapter;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.base.BasePresenter;
import com.wd.tech.bean.qzjbean.addcomment.AddBean;
import com.wd.tech.bean.qzjbean.comment.ConCommentBean;
import com.wd.tech.bean.qzjbean.detail.DetailBean;
import com.wd.tech.bean.qzjbean.great.GreatBean;
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
    private int ismoney;

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
        ismoney = intent.getIntExtra("ismoney",2);
        id = intent.getIntExtra("id", 3);
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
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ConsultaActivity.this,HomeActivity.class);
                startActivity(intent1);
                finish();
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
        adapter = new DetailAdapter(result,ConsultaActivity.this,ismoney);
        adapter.setGetIdLenter(new DetailAdapter.GetIdLenter() {
            @Override
            public void onId(int id,int integral) {
                initPopWindow(id,integral);
            }
        });
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
            int i = praise + 1;
            dzs.setText(i+"");
        }else {
            Bitmap bitmap1 = BitmapFactory.decodeResource(resources, R.mipmap.bdz);
            dzt.setImageBitmap(bitmap1);
            int praise = list.getPraise();
            dzs.setText(praise+"");
        }
        dzt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int whetherGreat1 = list.getWhetherGreat();
                if (whetherGreat1==1){
                    Bitmap bitmap1 = BitmapFactory.decodeResource(resources, R.mipmap.bdz);
                    dzt.setImageBitmap(bitmap1);
                    int praise = list.getPraise();
                    dzs.setText(praise+"");
                    presenter.onNDate(id);
                    list.setWhetherGreat(2);
                }else {
                    Bitmap bitmap1 = BitmapFactory.decodeResource(resources, R.mipmap.dzdzdz);
                    dzt.setImageBitmap(bitmap1);
                    int praise = list.getPraise();
                    praise++;
                    dzs.setText(praise+"");
                    presenter.onDDate(id);
                    list.setWhetherGreat(1);
                }
            }
        });
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

    @Override
    public void ondSuccess(GreatBean greatBean) {
        String message = greatBean.getMessage();
        Log.d("AAA",message);
    }

    @Override
    public void onnSuccess(GreatBean greatBean) {
        String message = greatBean.getMessage();
        Log.d("AAA",message);
    }
    private void initPopWindow(int id,int integral) {
        View view = LayoutInflater.from(ConsultaActivity.this).inflate(R.layout.item_popwindowssss, null, false);
        View jf = view.findViewById(R.id.jf);
        View vip = view.findViewById(R.id.vip);
        //1.构造一个PopupWindow，参数依次是加载的View，宽高
        final PopupWindow popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setAnimationStyle(R.anim.anim_pop);//设置加载动画
        //点击非PopupWindow区域，PopupWindow会消失的
        popupWindow.setTouchable(true);
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });
        //要为popWindow设置一个背景才有效
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        //设置popupWindow显示的位置，参数依次是参照View，x轴的偏移量，y轴的偏移量
        View inflate = LayoutInflater.from(ConsultaActivity.this).inflate(R.layout.activity_consulation, null);
        popupWindow.showAtLocation(inflate, Gravity.BOTTOM, 0,0);
        //点击事件
        jf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到积分兑换页面
                Intent intent = new Intent(ConsultaActivity.this,IntegralActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("integral",integral);
                startActivity(intent);
                popupWindow.dismiss();
            }
        });
        //点击事件
        vip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }
    // 打开相册
//    public void openGallery(View view) {
//        Intent intent =new Intent(Intent.ACTION_PICK);
//        intent.setType("image/*");
//
//        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_GALLERY
//        startActivityForResult(intent,0);
//    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        // 从相册返回的数据
//        if(requestCode ==0) {
//            if(data !=null) {
//                Uri uri = data.getData();
//                //图片  圆
//                /*CircleCrop circleCrop = new CircleCrop();
//                RequestOptions requestOptions = RequestOptions.bitmapTransform(circleCrop);*/
//                Glide.with(App.context).load(uri).into(imgSetUpHeadWy);
//                //uri转换成file
//                String[] arr = {MediaStore.Images.Media.DATA};
//                Cursor cursor = managedQuery(uri, arr, null, null, null);
//                int img_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//                cursor.moveToFirst();
//                String img_path = cursor.getString(img_index);
//                File file = new File(img_path);
//
//                RequestBody requestBody = RequestBody.create( MediaType.parse( "multipart/form-data"), file );
//                MultipartBody.Part image = MultipartBody.Part.createFormData("image", file.getName(), requestBody);
//                //头像  请求接口
//                presenter.getModifyHeadPic(image);
//            }
//        }
//    }
}
