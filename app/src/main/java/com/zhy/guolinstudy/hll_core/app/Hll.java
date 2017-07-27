package com.zhy.guolinstudy.hll_core.app;

import android.content.Context;

import java.util.HashMap;

/**
 * 仅仅是一个对外的工具类，都是静态方法
 * Created by wanyummy on 2017/7/21.
 */

public final class Hll {

    //3 进行初始化处理

    public static Configurator init(Context context) {
        getConfigurations().put(ConfigType.APPLICATION_CONTEXT.name(), context.getApplicationContext());
        return Configurator.getInstance();
    }

    private static HashMap<Object, Object> getConfigurations() {

        return Configurator.getInstance().getHllConfigs();
    }

    //5 获取context
    public static Context getApplicationContext() {
        return (Context) getConfigurations().get(ConfigType.APPLICATION_CONTEXT.name());
    }


    public static <T> T getConfiguration(ConfigType apiHost) {
        return Configurator.getInstance().getConfiguration(apiHost);
    }
}
