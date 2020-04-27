package com.wd.tech.fragment.gjyfragment.adduser;

import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wd.tech.R;
import com.wd.tech.base.BaseFragment;
import com.wd.tech.bean.gjybean.AddFriendBean;
import com.wd.tech.mvp.gjymvp.addgroup.AddGroupPresenter;
import com.wd.tech.mvp.gjymvp.addgroup.IAddGroupContract;

import java.util.HashMap;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/4/25 0025 14:16
 * @Description: 用途：完成特定功能
 */
public class AddGroupFragment extends BaseFragment<AddGroupPresenter> implements IAddGroupContract.IAddGroupView {
    private EditText mEditPhone;

    @Override
    public int initLayout() {
        return R.layout.fragment_add_group;
    }

    @Override
    public void initView() {

        mEditPhone = view.findViewById(R.id.editPhone);
    }

    @Override
    public void initListener() {
        mEditPhone.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    String phone = mEditPhone.getText().toString();
                    HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put("groupId",phone);
                    hashMap.put("remark","请求进群，over");
                    presenter.addGroup(hashMap);
                    mEditPhone.setText("");
                }
                return false;
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public AddGroupPresenter initPresenter() {
        return new AddGroupPresenter();
    }

    @Override
    public void onSuccess(AddFriendBean bean) {
        String message = bean.getMessage();
        Toast.makeText(getContext(), "" + message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailed(String error) {

    }
}
