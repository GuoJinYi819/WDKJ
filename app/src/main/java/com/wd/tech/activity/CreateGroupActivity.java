package com.wd.tech.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.base.BasePresenter;
import com.wd.tech.bean.gjybean.CreateGroupBean;
import com.wd.tech.mvp.gjymvp.creategroup.CreatePresenter;
import com.wd.tech.mvp.gjymvp.creategroup.ICreateGroupContract;

import java.util.HashMap;

public class CreateGroupActivity extends BaseActivity<CreatePresenter> implements ICreateGroupContract.ICreateGroupView {

    private android.widget.ImageView mIvBack;
    private android.widget.ImageView mGroupPic;
    private android.widget.EditText mEditGroupName;
    private android.widget.EditText mEditGroupContent;
    private android.widget.Button mButonCreate;

    @Override
    public int initLayout() {
        return R.layout.activity_create_group;
    }

    @Override
    public void initView() {

        mIvBack = findViewById(R.id.ivBack);
        mGroupPic = findViewById(R.id.groupPic);
        mEditGroupName = findViewById(R.id.editGroupName);
        mEditGroupContent = findViewById(R.id.editGroupContent);
        mButonCreate = findViewById(R.id.butonCreate);
    }

    @Override
    public void initListener() {
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mButonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mEditGroupName.getText().toString();
                String content = mEditGroupContent.getText().toString();
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("name",name);
                hashMap.put("description",content);
                presenter.createGroup(hashMap);
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public CreatePresenter initPresenter() {
        return new CreatePresenter();
    }

    @Override
    public void onSuccess(CreateGroupBean bean) {
        String message = bean.getMessage();
        if (message.equals("创建成功")) {
            int groupId = bean.getGroupId();
            String name = mEditGroupName.getText().toString();

            Intent intent = new Intent(CreateGroupActivity.this, SendGroupActivity.class);
            intent.putExtra("groupId",groupId);
            intent.putExtra("groupName",name);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onFailed(String error) {

    }
}
