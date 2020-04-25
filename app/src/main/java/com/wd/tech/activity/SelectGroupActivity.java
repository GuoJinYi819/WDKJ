package com.wd.tech.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.wd.tech.R;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.base.BasePresenter;

public class SelectGroupActivity extends BaseActivity {

    private android.widget.ImageView mIvBlac;
    private android.widget.TextView mTvAdd;
    private android.widget.ListView mListView;

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
                AlertDialog alertDialog = dialog.create();
                EditText edContent = view.findViewById(R.id.edContent);
                Button btnQ = view.findViewById(R.id.btnQ);
                Button btnC = view.findViewById(R.id.btnC);
                btnQ.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String text = edContent.getText().toString();


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

    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        int groupId = intent.getIntExtra("groupId", -1);
        int friend = intent.getIntExtra("friend", -1);
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }
}
