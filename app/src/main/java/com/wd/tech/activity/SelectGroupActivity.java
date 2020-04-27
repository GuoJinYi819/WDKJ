package com.wd.tech.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.base.BasePresenter;
import com.wd.tech.bean.gjybean.AddFriendGroupBean;
import com.wd.tech.bean.gjybean.FriendGroupListBean;
import com.wd.tech.bean.gjybean.TransferFriendBean;
import com.wd.tech.mvp.gjymvp.addfriendgroup.AddFriendGroupPresenter;
import com.wd.tech.mvp.gjymvp.addfriendgroup.IAddFriendGroupContract;
import com.wd.tech.net.ApiService;
import com.wd.tech.net.RetrofitUtil;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class SelectGroupActivity extends BaseActivity<AddFriendGroupPresenter> implements IAddFriendGroupContract.IAddFriendGroupView {

    private android.widget.ImageView mIvBlac;
    private android.widget.TextView mTvAdd;
    private android.widget.ListView mListView;
    private AlertDialog alertDialog;
    private int friend;

    @Override
    public int initLayout() {
        return R.layout.activity_select_group;
    }

    @Override
    public void initView() {

        mIvBlac = findViewById(R.id.ivBlac);
        mTvAdd = findViewById(R.id.tvAdd);
        mListView = findViewById(R.id.listView);
    }

    @Override
    public void initListener() {
        mTvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(SelectGroupActivity.this);
                View view = View.inflate(SelectGroupActivity.this, R.layout.dialog_view, null);
                dialog.setView(view);
                alertDialog = dialog.create();
                EditText edContent = view.findViewById(R.id.edContent);
                Button btnQ = view.findViewById(R.id.btnQ);
                Button btnC = view.findViewById(R.id.btnC);
                btnQ.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String text = edContent.getText().toString();
                        presenter.addFriendGroup(text);
                    }
                });
                btnC.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });

                alertDialog.show();

            }
        });
        showListView();
    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        int groupId = intent.getIntExtra("groupId", -1);
        friend = intent.getIntExtra("friend", -1);


    }

    @Override
    public AddFriendGroupPresenter initPresenter() {
        return new AddFriendGroupPresenter();
    }

    @Override
    public void onAddFriendGroupSuccess(AddFriendGroupBean bean) {
        String message = bean.getMessage();
        if (message.equals("创建分组成功")) {
            showListView();
            alertDialog.dismiss();
        }

    }

    @Override
    public void onFailed(String error) {

    }

    private void showListView(){
        RetrofitUtil instance = RetrofitUtil.getInstance();
        ApiService apiService = instance.createService();
        Observable<FriendGroupListBean> observable = apiService.getFriendGroupList();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<FriendGroupListBean>() {
                    @Override
                    public void accept(FriendGroupListBean friendGroupListBean) throws Exception {
                        String message = friendGroupListBean.getMessage();
                        if (message.equals("查询成功")) {
                            List<FriendGroupListBean.ResultBean> list = friendGroupListBean.getResult();
                            mListView.setAdapter(new BaseAdapter() {
                                @Override
                                public int getCount() {
                                    return list.size();
                                }

                                @Override
                                public Object getItem(int position) {
                                    return list.get(position);
                                }

                                @Override
                                public long getItemId(int position) {
                                    return position;
                                }

                                @Override
                                public View getView(int position, View convertView, ViewGroup parent) {
                                    View inflate = View.inflate(SelectGroupActivity.this, R.layout.item_groupwhole, null);
                                    TextView tvGroupName = inflate.findViewById(R.id.tvGroupName);
                                    FriendGroupListBean.ResultBean resultBean = list.get(position);
                                    String groupName = resultBean.getGroupName();
                                    tvGroupName.setText(groupName);
                                    return inflate;
                                }

                            });
                            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    FriendGroupListBean.ResultBean resultBean = list.get(position);
                                    int groupId = resultBean.getGroupId();

                                    //转移分组
                                    RetrofitUtil instance1 = RetrofitUtil.getInstance();
                                    ApiService service = instance1.createService();
                                    Observable<TransferFriendBean> group = service.transferGroup(groupId, friend);
                                    group.subscribeOn(Schedulers.io())
                                            .observeOn(AndroidSchedulers.mainThread())
                                            .subscribe(new Consumer<TransferFriendBean>() {
                                                @Override
                                                public void accept(TransferFriendBean transferFriendBean) throws Exception {
                                                    String message1 = transferFriendBean.getMessage();
                                                    Toast.makeText(SelectGroupActivity.this, ""+message1, Toast.LENGTH_SHORT).show();
                                                    if (message1.equals("转移成功")) {
                                                        finish();
                                                    }
                                                }
                                            });

                                }
                            });
                        }
                    }
                });
    }
}
