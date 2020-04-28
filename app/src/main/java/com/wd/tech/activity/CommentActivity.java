package com.wd.tech.activity;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.wd.tech.App;
import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.wybean.beancomment.CommentBean;
import com.wd.tech.bean.wybean.beandotask.DoTaskBean;
import com.wd.tech.mvp.wymvp.mvpcomment.CommentPresenterImpl;
import com.wd.tech.mvp.wymvp.mvpcomment.ICommentContract;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommentActivity extends BaseActivity<CommentPresenterImpl> implements ICommentContract.ICommentView {
    //发表评论页面
    private android.widget.TextView tvCancleWy;
    private android.widget.TextView tvPublishWy;
    private android.widget.EditText etPublishWy;
    private android.widget.TextView tvPublishNumWy;
    //限制的最大字数
    private int max=300;
    private android.widget.ImageView imgAddWy;
    private MultipartBody.Part formData;

    @Override
    public int initLayout() {
        return R.layout.activity_comment;
    }
    @Override
    public void initView() {
        tvCancleWy = (TextView) findViewById(R.id.tvCancleWy);
        tvPublishWy = (TextView) findViewById(R.id.tvPublishWy);
        etPublishWy = (EditText) findViewById(R.id.etPublishWy);
        tvPublishNumWy = (TextView) findViewById(R.id.tvPublishNumWy);

        //输入框字数限制
        InputFilter[] filters={chineseFilter()};
        etPublishWy.addTextChangedListener(passwordListener());
        //超过300字就不能再输入了
        etPublishWy.setFilters(new InputFilter[]{new InputFilter.LengthFilter(max)});
        imgAddWy = (ImageView) findViewById(R.id.imgAddWy);
    }
    /**
     * 输入框
     * @return
     */
    private InputFilter chineseFilter() {
        return new InputFilter() {
            String regEx = "[\\u4e00-\\u9fa5]"; // unicode编码，判断是否为汉字
            @Override
            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int dstart, int dend) {
                float destCount = dest.toString().length()
                        + getChineseCount(dest.toString());
                float sourceCount = source.toString().length()
                        + getChineseCount(source.toString());
                if (destCount + sourceCount > 10) {
                    Log.e("log", "已经达到字数限制范围");
                    return "";

                } else {
                    return source;
                }
            }

            private float getChineseCount(String str) {
                float count = 0;
                Pattern p = Pattern.compile(regEx);
                Matcher m = p.matcher(str);
                while (m.find()) {
                    for (int i = 0; i <= m.groupCount(); i++) {
                        count =count + 1;//
                    }
                }
                return count;
            }
        };
    }
    public TextWatcher passwordListener() {
        return new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int length = s.length();
                tvPublishNumWy.setText(length + "/" + max);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };

    }
    private void initPopWindow(View v) {
        View view = LayoutInflater.from(CommentActivity.this).inflate(R.layout.item_popwindow, null, false);
        Button btn_start = view.findViewById(R.id.btn_start);
        Button btn_cancle = view.findViewById(R.id.btn_cancle);
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
        View inflate = LayoutInflater.from(CommentActivity.this).inflate(R.layout.activity_comment, null);
        popupWindow.showAtLocation(inflate, Gravity.BOTTOM, 0,0);
        //点击事件
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //打开相册，换头像,添加评论图片
                openGallery(v);
                popupWindow.dismiss();
            }
        });
        //取消
        btn_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }
    // 打开相册
    public void openGallery(View view) {
        Intent intent =new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");

        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_GALLERY
        startActivityForResult(intent,0);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 从相册返回的数据
        if(requestCode ==0) {
            if(data !=null) {
                Uri uri = data.getData();
                //图片  圆
                /*CircleCrop circleCrop = new CircleCrop();
                RequestOptions requestOptions = RequestOptions.bitmapTransform(circleCrop);*/
                Glide.with(App.context).load(uri).into(imgAddWy);
                //uri转换成file
                String[] arr = {MediaStore.Images.Media.DATA};
                Cursor cursor = managedQuery(uri, arr, null, null, null);
                int img_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                cursor.moveToFirst();
                String img_path = cursor.getString(img_index);
                File file = new File(img_path);

                RequestBody requestBody = RequestBody.create( MediaType.parse( "multipart/form-data"), file );
                formData = MultipartBody.Part.createFormData( "image", file.getName(), requestBody );
            }
        }
    }
    @Override
    public void initListener() {
        //取消
        tvCancleWy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //销毁页面
                finish();
            }
        });
        //添加图片
        imgAddWy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //popwindow
                initPopWindow(v);
            }
        });
        //发表
        tvPublishWy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //请求
                String string = etPublishWy.getText().toString().trim();
                if(!TextUtils.isEmpty(string)){
                    if(formData!=null){
                        Log.d("===", "onClick: "+formData);
                        presenter.getComment(string,formData);
                        //加分
                        presenter.getDoTask(1003);
                    }
                }
            }
        });
    }
    @Override
    public void initData() {
    }
    @Override
    public CommentPresenterImpl initPresenter() {
        return new CommentPresenterImpl();
    }
    @Override
    public void onSuccess(CommentBean commentBean) {
        String message = commentBean.getMessage();
        Toast.makeText(CommentActivity.this,message,Toast.LENGTH_SHORT).show();
        //销毁
        finish();
    }
    @Override
    public void onError(String msg) {
    }

    @Override
    public void onTaskSuccess(DoTaskBean doTaskBean) {
        String message = doTaskBean.getMessage();
        Log.d("==", "onTaskSuccess: "+message);
    }
}
