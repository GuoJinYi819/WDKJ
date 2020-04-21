package com.wd.tech.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.wd.tech.R;
import com.wd.tech.adapter.gjyadapter.GroupAdapter;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.base.BasePresenter;
import com.wd.tech.bean.gjybean.JoinedGroupBean;
import com.wd.tech.mvp.gjymvp.joinedgroup.IJoinedGroupContract;
import com.wd.tech.mvp.gjymvp.joinedgroup.JoinedGroupPresenter;

import java.security.acl.Group;
import java.util.ArrayList;
import java.util.List;

//群组
public class GroupActivity extends BaseActivity<JoinedGroupPresenter> implements IJoinedGroupContract.IJoinedGroupView {

    private android.widget.ImageView mIvBlack;
    private android.widget.EditText mEditSeach;
    private android.widget.ListView mGroupListView;
    private GroupAdapter groupAdapter;

    @Override
    public int initLayout() {
        return R.layout.activity_group;
    }

    @Override
    public void initView() {

        mIvBlack = findViewById(R.id.ivBlack);
        mEditSeach = findViewById(R.id.editSeach);
        mGroupListView = findViewById(R.id.groupListView);
    }

    @Override
    public void initListener() {
        //关闭群组界面
        mIvBlack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //搜索框
        mEditSeach.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //改变前
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //内存改变监听
                if (groupAdapter != null) {
                    List<JoinedGroupBean.ResultBean> list = groupAdapter.getList();
                    List<JoinedGroupBean.ResultBean> l = new ArrayList<>();

                    for (int i = 0; i < list.size(); i++) {
                        JoinedGroupBean.ResultBean resultBean = list.get(i);
                        String groupName = resultBean.getGroupName();
                        String text = mEditSeach.getText().toString();
                        if (groupName.contains(text)) {
                            l.add(resultBean);
                        }
                    }
                    groupAdapter.setList(l);
                    groupAdapter.notifyDataSetChanged();
                    String text = mEditSeach.getText().toString();
                    if (TextUtils.isEmpty(text)) {
                        presenter.getJoinedGroup();
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                //改变后
            }
        });
    }

    @Override
    public void initData() {
        presenter.getJoinedGroup();
    }

    @Override
    public JoinedGroupPresenter initPresenter() {
        return new JoinedGroupPresenter();
    }

    @Override
    public void onSuccess(JoinedGroupBean bean) {
        List<JoinedGroupBean.ResultBean> result = bean.getResult();
        if (result != null) {
            groupAdapter = new GroupAdapter(result, GroupActivity.this);
            mGroupListView.setAdapter(groupAdapter);
        }
    }

    @Override
    public void onFailed(String error) {

    }
}
