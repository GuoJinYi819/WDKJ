package com.wd.tech.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.wd.tech.R;
import com.wd.tech.adapter.gjyadapter.RecordAdapter;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.base.BasePresenter;
import com.wd.tech.bean.gjybean.FriendSeachBean;
import com.wd.tech.mvp.gjymvp.friendseach.FriendSeachPresenter;
import com.wd.tech.mvp.gjymvp.friendseach.IFriendSeachContract;
import com.wd.tech.net.SpUtil;

import java.util.List;

public class MyFriendSeachActivity extends BaseActivity<FriendSeachPresenter> implements IFriendSeachContract.IFriendSeachView {

    private android.widget.EditText mEditSeach;
    private android.widget.ImageView mIvClose;
    private android.widget.TextView mTvCancel;
    private androidx.recyclerview.widget.RecyclerView mRecyclerRecord;

    @Override
    public int initLayout() {
        return R.layout.activity_myfriend_seach;
    }

    @Override
    public void initView() {

        mEditSeach = findViewById(R.id.editSeach);
        mIvClose = findViewById(R.id.ivClose);
        mTvCancel = findViewById(R.id.tvCancel);
        mRecyclerRecord = findViewById(R.id.recyclerRecord);
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        mRecyclerRecord.setLayoutManager(linearLayoutManager);

        SpUtil instance = SpUtil.getInstance();
        String searchName = instance.getSpString("searchName");
        if (!TextUtils.isEmpty(searchName)) {
            presenter.getFriendSeach(searchName);
        }
    }

    @Override
    public void initListener() {
        //关闭界面
        mTvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //清空输入框
        mIvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditSeach.setText("");
            }
        });
        //搜索监听
        mEditSeach.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                String searchName = mEditSeach.getText().toString();
                if (!TextUtils.isEmpty(searchName)) {
                    SpUtil instance = SpUtil.getInstance();
                    instance.saveString("searchName",searchName);
                    presenter.getFriendSeach(searchName);
                }else {
                    Toast.makeText(MyFriendSeachActivity.this, "请输入关键字", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public FriendSeachPresenter initPresenter() {
        return new FriendSeachPresenter();
    }

    @Override
    public void onSuccess(FriendSeachBean bean) {
        List<FriendSeachBean.ResultBean> result = bean.getResult();
        if (result != null) {
            RecordAdapter recordAdapter = new RecordAdapter(result, MyFriendSeachActivity.this);
            mRecyclerRecord.setAdapter(recordAdapter);

        }
    }

    @Override
    public void onFailed(String error) {

    }
}
