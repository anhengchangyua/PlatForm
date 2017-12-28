package com.zhy.uutils.hll_ec.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.zhy.uutils.R;
import com.zhy.uutils.hll_core.app.AcountManager;
import com.zhy.uutils.hll_core.app.IUserChecker;
import com.zhy.uutils.hll_core.delegates.HLDelegate;
import com.zhy.uutils.hll_core.ui.launcher.ILauncherListener;
import com.zhy.uutils.hll_core.ui.launcher.OnLauncherFinishTag;
import com.zhy.uutils.hll_core.ui.launcher.ScrollLauncherTag;
import com.zhy.uutils.hll_core.util.HLPreference;
import com.zhy.uutils.hll_core.util.timer.BaseTimerTask;
import com.zhy.uutils.hll_core.util.timer.ItimerListener;

import java.text.MessageFormat;
import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author zwy.
 * @time 2017/7/25 9:25
 * @description
 */

public class LauncherDelegate extends HLDelegate implements ItimerListener {


    @BindView(R.id.tv_launcher_timer)
    AppCompatTextView mtvLauncherTimer = null;

    private Timer mTimer = null;
    private int count = 5;
    private ILauncherListener mILauncherListener = null;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ILauncherListener) {
            mILauncherListener = (ILauncherListener) activity;
        }
    }

    @OnClick(R.id.tv_launcher_timer)
    public void onClick() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
            checkIsShowScroll();
        }
    }

    private void initTimer() {
        mTimer = new Timer();
        final BaseTimerTask task = new BaseTimerTask(this);
        mTimer.schedule(task, 0, 1000);
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initTimer();
    }

    // 判断是否 显示滑动启动页
    private void checkIsShowScroll() {
        if (!HLPreference.getAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name())) {
            start(new LauncherScrollDelegate(), SINGLETASK);
        } else {
            //检查用户是否登录APP
            AcountManager.checkAccount(new IUserChecker() {
                @Override
                public void onSignIn() {
                    if (mILauncherListener != null) {
                        mILauncherListener.onLauncherFinish(OnLauncherFinishTag.SIGNED);
                    }
                }

                @Override
                public void onNotSignIn() {
                    if (mILauncherListener != null) {
                        mILauncherListener.onLauncherFinish(OnLauncherFinishTag.NOT_SIGHED);
                    }
                }
            });
        }
    }

    @Override
    public void ontimer() {
        getProxyActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mtvLauncherTimer != null) {
                    mtvLauncherTimer.setText(MessageFormat.format("跳过\n{0}s", count));
                    count--;
                    if (count < 0) {
                        if (mTimer != null) {
                            mTimer.cancel();
                            mTimer = null;
                            checkIsShowScroll();
                        }
                    }
                }
            }
        });
    }


}
