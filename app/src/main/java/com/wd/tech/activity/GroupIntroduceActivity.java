package com.wd.tech.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.base.BasePresenter;
import com.wd.tech.bean.gjybean.ModifyGroupDescriptionBean;
import com.wd.tech.mvp.gjymvp.modifygroupdescription.IModifyGroupDescriptionContract;
import com.wd.tech.mvp.gjymvp.modifygroupdescription.ModifyGroupDescriptionPresenter;

public class GroupIntroduceActivity extends BaseActivity<ModifyGroupDescriptionPresenter> implements IModifyGroupDescriptionContract.IModifyGroupDescriptionView {

    private android.widget.TextView mTvConfirm;
    private android.widget.EditText mEditContent;

    @Override
    public int initLayout() {
        return R.layout.activity_group_introduce;
    }

    @Override
    public void initView() {

        mTvConfirm = findViewById(R.id.tvConfirm);
        mEditContent = findViewById(R.id.editContent);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        String description = intent.getStringExtra("description");
        mEditContent.setText(description);
        int groupId = intent.getIntExtra("groupId", -1);

        mTvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ss = mEditContent.getText().toString();
                presenter.setModifyGroupDescription(groupId,ss);
            }
        });
    }

    @Override
    public ModifyGroupDescriptionPresenter initPresenter() {
        return new ModifyGroupDescriptionPresenter();
    }

    @Override
    public void onSuccess(ModifyGroupDescriptionBean bean) {
        String message = bean.getMessage();
        if (message.equals("修改群备注成功")) {
            setResult(10,null);
            finish();
        }
    }

    @Override
    public void onFailed(String error) {

    }
}
