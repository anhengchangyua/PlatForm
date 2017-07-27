package com.zhy.guolinstudy.hll_ec.launcher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.zhy.guolinstudy.R;
import com.zhy.guolinstudy.hll_core.delegates.HLDelegate;
import com.zhy.guolinstudy.hll_core.ui.launcher.ScrollLauncherTag;
import com.zhy.guolinstudy.hll_core.util.HLPreference;
import com.zhy.guolinstudy.hll_core.util.timer.BaseTimerTask;
import com.zhy.guolinstudy.hll_core.util.timer.ItimerListener;

import java.text.MessageFormat;
import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by wanyummy on 2017/7/25.
 */

public class LauncherDelegate extends HLDelegate implements ItimerListener {


    @BindView(R.id.tv_launcher_timer)
    AppCompatTextView mtvLauncherTimer = null;

    private Timer mTimer = null;
    private int count = 5;

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
