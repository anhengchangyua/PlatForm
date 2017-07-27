package com.zhy.guolinstudy.hll_ec.sign;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Patterns;
import android.view.View;

import com.zhy.guolinstudy.R;
import com.zhy.guolinstudy.R2;
import com.zhy.guolinstudy.hll_core.delegates.HLDelegate;
import com.zhy.guolinstudy.hll_core.net.RestClient;
import com.zhy.guolinstudy.hll_core.net.callback.ISuccess;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 注册逻辑
 * Created by wanyummy on 2017/7/25.
 */

public class SignUpDelegate extends HLDelegate {

    @BindView(R2.id.edit_sign_up_name)
    TextInputEditText mName = null;
    @BindView(R2.id.edit_sign_up_mail)
    TextInputEditText mMail = null;
    @BindView(R2.id.edit_sign_up_phone)
    TextInputEditText mPhone = null;
    @BindView(R2.id.edit_sign_up_password)
    TextInputEditText mPassWord = null;
    @BindView(R2.id.edit_sign_up_cppassword)
    TextInputEditText mCopyPassWord = null;

    @OnClick(R2.id.btn_sign_up)
    void onClickSignUp() {
        if (checkForm()) {
            RestClient.builder()
                    .url("")
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            SignHandler.onSignUp(response);
                        }
                    })
                    .build()
                    .post();
        }
    }

    @OnClick(R2.id.tv_link_sign_in)
    void onClickSignIn() {

        start(new SignInDelegate());
    }

    private boolean checkForm() {
        final String name = mName.getText().toString();
        final String email = mMail.getText().toString();
        final String phone = mPhone.getText().toString();
        final String password = mPassWord.getText().toString();
        final String cppassword = mCopyPassWord.getText().toString();

        boolean isPass = true;
        if (name.isEmpty()) {
            mName.setError("请输入姓名");
            isPass = false;
        } else {
            mName.setError(null);
        }

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mMail.setError("错误的邮箱格式");
            isPass = false;
        } else {
            mMail.setError(null);
        }

        if (phone.isEmpty() || phone.length() != 11) {
            mPhone.setError("手机号码错误");
            isPass = false;
        } else {
            mPhone.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            mPassWord.setError("手机号码错误");
            isPass = false;
        } else {
            mPassWord.setError(null);
        }

        if (cppassword.isEmpty() || cppassword.length() < 6 || !(cppassword.equals(password))) {
            mCopyPassWord.setError("密码验证错误");
            isPass = false;
        } else {
            mCopyPassWord.setError(null);
        }
        return isPass;

    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_up;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
