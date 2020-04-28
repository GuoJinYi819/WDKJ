package com.wd.tech.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.base.BasePresenter;
import com.wd.tech.bean.gjybean.DeleteGroupBean;
import com.wd.tech.bean.gjybean.GroupInfoBean;
import com.wd.tech.mvp.gjymvp.groupinfo.GroupInfoPreenter;
import com.wd.tech.mvp.gjymvp.groupinfo.IGroupInfoContract;
import com.wd.tech.net.ApiService;
import com.wd.tech.net.RetrofitUtil;
import com.wd.tech.net.SpUtil;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class GroupSettingActivity extends BaseActivity<GroupInfoPreenter> implements IGroupInfoContract.IGroupInfoView {

    private android.widget.ImageView mIvPic;
    private android.widget.ImageView mIvGroupPic;
    private android.widget.RelativeLayout mRelativeMember;
    private android.widget.TextView mTvParty;
    private android.widget.RelativeLayout mRelativeIntroduce;
    private android.widget.RelativeLayout mRelativeNotice;
    private android.widget.RelativeLayout mRelativeAdministration;
    private android.widget.TextView mTvQuery;
    private android.widget.Button mBtnClone;
    private android.widget.TextView mTvSum;

    @Override
    public int initLayout() {
        return R.layout.activity_group_setting;
    }

    @Override
    public void initView() {

        mIvPic = findViewById(R.id.ivPic);
        mIvGroupPic = findViewById(R.id.ivGroupPic);
        mRelativeMember = findViewById(R.id.relativeMember);
        mTvParty = findViewById(R.id.tvParty);
        mRelativeIntroduce = findViewById(R.id.relativeIntroduce);
        mRelativeNotice = findViewById(R.id.relativeNotice);
        mRelativeAdministration = findViewById(R.id.relativeAdministration);
        mTvQuery = findViewById(R.id.tvQuery);
        mBtnClone = findViewById(R.id.btnClone);
        mTvSum = findViewById(R.id.tvSum);
    }

    @Override
    public void initListener() {
        mIvPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mRelativeMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                int groupId = intent.getIntExtra("groupId", -1);
                Intent it = new Intent(GroupSettingActivity.this, GroupFriendActivity.class);
                it.putExtra("groupId",groupId);
                startActivity(it);
            }
        });
        mRelativeNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GroupSettingActivity.this, GroupNoticeActivity.class);
                startActivity(intent);
            }
        });
        mBtnClone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                int groupId = intent.getIntExtra("groupId", -1);
                String text = mBtnClone.getText().toString();
                if (text.equals("解散群聊")) {
                    RetrofitUtil instance = RetrofitUtil.getInstance();
                    ApiService service = instance.createService();
                    Observable<DeleteGroupBean> observable = service.deleteGroup(groupId);
                    observable.subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Consumer<DeleteGroupBean>() {
                                @Override
                                public void accept(DeleteGroupBean deleteGroupBean) throws Exception {
                                    String status = deleteGroupBean.getMessage();
                                    Toast.makeText(GroupSettingActivity.this, ""+status, Toast.LENGTH_SHORT).show();
                                    setResult(11,null);
                                    finish();
                                }
                            });
                }else if(text.equals("退出群聊")){
                    RetrofitUtil instance = RetrofitUtil.getInstance();
                    ApiService service = instance.createService();
                    Observable<DeleteGroupBean> observable = service.retreat(groupId);
                    observable.subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Consumer<DeleteGroupBean>() {
                                @Override
                                public void accept(DeleteGroupBean deleteGroupBean) throws Exception {
                                    String status = deleteGroupBean.getMessage();
                                    Toast.makeText(GroupSettingActivity.this, ""+status, Toast.LENGTH_SHORT).show();
                                    setResult(11,null);
                                    finish();
                                }
                            });
                }
            }
        });

    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        int groupId = intent.getIntExtra("groupId", -1);
        presenter.getGroupInfo(groupId);
    }

    @Override
    public GroupInfoPreenter initPresenter() {
        return new GroupInfoPreenter();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==10&&resultCode==10){
            Intent intent = getIntent();
            int groupId = intent.getIntExtra("groupId", -1);
            presenter.getGroupInfo(groupId);
        }
    }

    @Override
    public void onSuccess(GroupInfoBean bean) {
        String message = bean.getMessage();
        if (message.equals("查询成功")) {

            GroupInfoBean.ResultBean result = bean.getResult();

            String description = result.getDescription();
            int groupId = result.getGroupId();

            mRelativeIntroduce.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //跳转群简介
                    Intent intent = new Intent(GroupSettingActivity.this, GroupIntroduceActivity.class);
                    intent.putExtra("description",description);
                    intent.putExtra("groupId",groupId);
                    startActivityForResult(intent,10);

                }
            });

            mTvQuery.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //聊天
                    String groupName = result.getGroupName();
                    Intent intent = new Intent(GroupSettingActivity.this, SendGroupActivity.class);
                    intent.putExtra("groupId",groupId);
                    intent.putExtra("groupName",groupName);
                    startActivity(intent);
                }
            });

            String groupImage = result.getGroupImage();
            Glide.with(GroupSettingActivity.this)
                    .load(groupImage)
                    .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                    .into(mIvGroupPic);
            //当前人数
            int currentCount = result.getCurrentCount();
            mTvSum.setText("共"+currentCount+"人");

            SpUtil instance = SpUtil.getInstance();
            int userId = instance.getSpInt("userId");
            int ownerUid = result.getOwnerUid();
            if(userId!=ownerUid){
                mTvParty.setVisibility(View.GONE);
                mRelativeIntroduce.setVisibility(View.GONE);
               mRelativeAdministration.setVisibility(View.GONE);
               mBtnClone.setText("退出群聊");

            }

        }
    }

    @Override
    public void onFailed(String error) {

    }
}
