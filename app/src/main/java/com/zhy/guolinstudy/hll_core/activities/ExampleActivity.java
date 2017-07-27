package com.zhy.guolinstudy.hll_core.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.widget.Toast;

import com.zhy.guolinstudy.hll_core.delegates.ExampleDelegate;
import com.zhy.guolinstudy.hll_core.delegates.HLDelegate;
import com.zhy.guolinstudy.hll_core.ui.launcher.ILauncherListener;
import com.zhy.guolinstudy.hll_core.ui.launcher.OnLauncherFinishTag;
import com.zhy.guolinstudy.hll_ec.launcher.LauncherDelegate;
import com.zhy.guolinstudy.hll_ec.sign.ISignListener;
import com.zhy.guolinstudy.hll_ec.sign.SignInDelegate;
import com.zhy.guolinstudy.hll_ec.sign.SignUpDelegate;

/**
 * Created by wanyummy on 2017/7/10.
 */

public class ExampleActivity extends ProxyActivity implements ISignListener, ILauncherListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏掉actionbar
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    @Override
    public HLDelegate setRootDelegate() {
        return new LauncherDelegate();
    }

    @Override
    public void onSignInSuccess() {

    }

    @Override
    public void onSignUpSuccess() {

    }

    @Override
    public void onLauncherFinish(OnLauncherFinishTag tag) {
        switch (tag) {
            case SIGNED:
                Toast.makeText(this, "启动结束，登录", Toast.LENGTH_LONG).show();
                startWithPop(new ExampleDelegate());
                break;

            case NOT_SIGHED:
                Toast.makeText(this, "启动结束，未登录", Toast.LENGTH_LONG).show();
                //启动下一个fragment的同时，把上边的从栈中清除
                startWithPop(new SignInDelegate());
                break;

            default:
                break;
        }
    }
}
