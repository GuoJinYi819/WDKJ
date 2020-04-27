package com.wd.tech.fragment.gjyfragment.adduser;

import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wd.tech.R;
import com.wd.tech.base.BaseFragment;
import com.wd.tech.base.BasePresenter;
import com.wd.tech.bean.gjybean.AddFriendBean;
import com.wd.tech.mvp.gjymvp.addfriend.AddFriendPresenter;
import com.wd.tech.mvp.gjymvp.addfriend.IAddFriendContract;

import java.util.HashMap;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : GuoJinYi
 * @version 创建时间：2020/4/25 0025 14:16
 * @Description: 用途：找人
 */
public class AddPersonFragment extends BaseFragment<AddFriendPresenter> implements IAddFriendContract.IAddFriendView {
    private EditText mEditPhone;

    @Override
    public int initLayout() {
        return R.layout.fragment_add_person;
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
                    hashMap.put("friendUid",phone);
                    hashMap.put("remark","快tm同意，over");
                    presenter.addFriend(hashMap);
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
    public AddFriendPresenter initPresenter() {
        return new AddFriendPresenter();
    }

    @Override
    public void onSuccess(AddFriendBean bean) {
        String message = bean.getMessage();
        Toast.makeText(getContext(), ""+message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailed(String error) {

    }
}
