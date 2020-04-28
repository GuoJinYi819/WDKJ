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
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.tech.App;
import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.base.BasePresenter;
import com.wd.tech.bean.wybean.beanmodifyheadPic.ModifyHeadPicBean;
import com.wd.tech.bean.wybean.beanselectuser.ResultBean;
import com.wd.tech.bean.wybean.beanselectuser.SelectUserBean;
import com.wd.tech.mvp.wymvp.mvpselectuser.ISelectUserContract;
import com.wd.tech.mvp.wymvp.mvpselectuser.SelectUserPresenterImpl;
import com.wd.tech.net.TimeToUtil;

import java.io.File;

public class SetupActivity extends BaseActivity<SelectUserPresenterImpl> implements ISelectUserContract.ISelectUserView {
    private android.widget.ImageView imgSetupBackWy;
    private com.facebook.drawee.view.SimpleDraweeView imgSetUpHeadWy;
    private android.widget.TextView tvSetUpNameWy;
    private android.widget.TextView tvSetUpSexWy;
    private android.widget.TextView tvSetUpSignatureWy;
    private android.widget.TextView tvSetUpBirthdayWy;
    private android.widget.TextView tvSetUpPhotoWy;
    private android.widget.TextView tvSetUpEmailWy;
    private android.widget.TextView tvSetUpintegralWy;
    private android.widget.TextView tvSetUpWhetherVipWy;
    private android.widget.TextView tvSetUpWhetherFaceIdWy;

    //设置 页面
    @Override
    public int initLayout() {
        return R.layout.activity_setup;
    }
    @Override
    public void initView() {
        imgSetupBackWy = (ImageView) findViewById(R.id.imgSetupBackWy);
        imgSetUpHeadWy = (SimpleDraweeView) findViewById(R.id.imgSetUpHeadWy);
        tvSetUpNameWy = (TextView) findViewById(R.id.tvSetUpNameWy);
        tvSetUpSexWy = (TextView) findViewById(R.id.tvSetUpSexWy);
        tvSetUpSignatureWy = (TextView) findViewById(R.id.tvSetUpSignatureWy);
        tvSetUpBirthdayWy = (TextView) findViewById(R.id.tvSetUpBirthdayWy);
        tvSetUpPhotoWy = (TextView) findViewById(R.id.tvSetUpPhotoWy);
        tvSetUpEmailWy = (TextView) findViewById(R.id.tvSetUpEmailWy);
        tvSetUpintegralWy = (TextView) findViewById(R.id.tvSetUpintegralWy);
        tvSetUpWhetherVipWy = (TextView) findViewById(R.id.tvSetUpWhetherVipWy);
        tvSetUpWhetherFaceIdWy = (TextView) findViewById(R.id.tvSetUpWhetherFaceIdWy);
    }
    @Override
    public void initListener() {
        //销毁
        imgSetupBackWy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //点击头像
        imgSetUpHeadWy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPopWindow(v);
            }
        });
        //点击  换名字
        tvSetUpNameWy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转
                Intent intent = new Intent(SetupActivity.this, ModifyNickName2Activity.class);
                startActivity(intent);
            }
        });
        //点击  换签名
        tvSetUpSignatureWy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳
                Intent intent = new Intent(SetupActivity.this, ModifySignatureActivity.class);
                startActivity(intent);
            }
        });
        //点击  换邮箱
        tvSetUpEmailWy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳
                Intent intent = new Intent(SetupActivity.this, ModifyEmailActivity.class);
                startActivity(intent);
            }
        });
    }
    private void initPopWindow(View v) {
        View view = LayoutInflater.from(SetupActivity.this).inflate(R.layout.item_popwindow, null, false);
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
        View inflate = LayoutInflater.from(SetupActivity.this).inflate(R.layout.activity_comment, null);
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
                Glide.with(App.context).load(uri).into(imgSetUpHeadWy);
                //uri转换成file
                String[] arr = {MediaStore.Images.Media.DATA};
                Cursor cursor = managedQuery(uri, arr, null, null, null);
                int img_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                cursor.moveToFirst();
                String img_path = cursor.getString(img_index);
                File file = new File(img_path);

                RequestBody requestBody = RequestBody.create( MediaType.parse( "multipart/form-data"), file );
                MultipartBody.Part image = MultipartBody.Part.createFormData("image", file.getName(), requestBody);
                //头像  请求接口
                presenter.getModifyHeadPic(image);
            }
        }
    }
    @Override
    public void initData() {
        presenter.getSelectUser();
    }
    @Override
    public SelectUserPresenterImpl initPresenter() {
        return new SelectUserPresenterImpl();
    }
    @Override
    public void onSuccess(SelectUserBean selectUser) {
        //成功
        ResultBean result = selectUser.getResult();
        //取值
        String headPic = result.getHeadPic();
        String nickName = result.getNickName();
        int sex = result.getSex();
        String signature = result.getSignature();
        long birthday = result.getBirthday();
        String phone = result.getPhone();
        String email = result.getEmail();
        int integral = result.getIntegral();
        int whetherVip = result.getWhetherVip();
        int whetherFaceId = result.getWhetherFaceId();
        //设置
        imgSetUpHeadWy.setImageURI(headPic);
        tvSetUpNameWy.setText(nickName);
        //性别
        if(sex==1){
            tvSetUpSexWy.setText("男");
        }else if(sex==2){
            tvSetUpSexWy.setText("女");
        }
        tvSetUpSignatureWy.setText(signature);
        //时间  生日
        String time = TimeToUtil.getTime(birthday);
        tvSetUpBirthdayWy.setText(time);
        tvSetUpPhotoWy.setText(phone);
        tvSetUpEmailWy.setText(email);
        tvSetUpintegralWy.setText(""+integral);
        //VIP
        if(whetherVip==1){
            tvSetUpWhetherVipWy.setText("是");
        }else if(whetherVip==2){
            tvSetUpWhetherVipWy.setText("否");
        }
        //绑定
        if(whetherFaceId==1){
            tvSetUpWhetherFaceIdWy.setText("解除绑定");
        }else if(whetherFaceId==2){
            tvSetUpWhetherFaceIdWy.setText("立即绑定");
        }
    }

    @Override
    public void onHeadSuccess(ModifyHeadPicBean modifyHeadPicBean) {
        //换  头像
    }

    @Override
    public void onError(String error) {
    }
}
