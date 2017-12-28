package com.zhy.uutils.hll_core;

import android.app.Application;

import com.zhy.uutils.hll_core.app.Hll;
import com.zhy.uutils.hll_core.web.event.TestEvent;
import com.zhy.uutils.hll_ec.database.DatabaseManager;

/**
 * Created by wanyummy on 2017/7/13.
 */

public class HLApplication extends Application {
    private static HLApplication instance;


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Hll.init(this)
                .withApiHost("http://www.niulinguo.com/fastec/index/index/json/")
                .withWebEvent("test", new TestEvent())
                .Configure();

        DatabaseManager.getInstance().init(this);
    }

    public static HLApplication getInstance() {
        return instance;
    }


}
