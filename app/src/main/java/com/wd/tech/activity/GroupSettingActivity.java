package com.wd.tech.activity;

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
import com.wd.tech.bean.gjybean.GroupInfoBean;
import com.wd.tech.mvp.gjymvp.groupinfo.GroupInfoPreenter;
import com.wd.tech.mvp.gjymvp.groupinfo.IGroupInfoContract;
import com.wd.tech.net.SpUtil;

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
    public void onSuccess(GroupInfoBean bean) {
        String message = bean.getMessage();
        if (message.equals("查询成功")) {

            GroupInfoBean.ResultBean result = bean.getResult();

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
            if(userId==userId){
                mTvParty.setVisibility(View.GONE);
                mRelativeIntroduce.setVisibility(View.GONE);
               mRelativeAdministration.setVisibility(View.GONE);

            }

        }
    }

    @Override
    public void onFailed(String error) {

    }
}
