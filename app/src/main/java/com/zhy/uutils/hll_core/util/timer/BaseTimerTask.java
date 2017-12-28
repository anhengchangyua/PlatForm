package com.zhy.uutils.hll_core.util.timer;

import java.util.TimerTask;

/**
 * Created by wanyummy on 2017/7/25.
 */

public class BaseTimerTask extends TimerTask {
    //1 创建一个接口监听实现
    private ItimerListener mItimerListener = null;

    public BaseTimerTask(ItimerListener mItimerListener) {
        this.mItimerListener = mItimerListener;
    }

    @Override
    public void run() {
        if (mItimerListener != null) {
            mItimerListener.ontimer();
        }
    }
}
