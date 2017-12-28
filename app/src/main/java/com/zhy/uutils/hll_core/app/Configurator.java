package com.zhy.uutils.hll_core.app;

import android.os.Handler;
import android.support.annotation.NonNull;

import com.zhy.uutils.hll_core.web.event.Event;
import com.zhy.uutils.hll_core.web.event.EventManager;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Interceptor;

/**
 * 一些配置放在这里使用
 * Created by wanyummy on 2017/7/21.
 */

public class Configurator {

    private static final HashMap<Object, Object> HLL_CONFIGS = new HashMap<>();
    //添加拦截器
    private static final ArrayList<Interceptor> INTERCEPTORS = new ArrayList<>();
    private static final Handler HANDLER = new Handler();


    private Configurator() {
        //1 在初始化的时候我们的初始化是没有完成的。
        HLL_CONFIGS.put(ConfigType.CONFIG_READY.name(), false);
        HLL_CONFIGS.put(ConfigType.HANDLER.name(), HANDLER);
    }

    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }

    static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    //4  返回处理
    final HashMap<Object, Object> getHllConfigs() {
        return HLL_CONFIGS;
    }


    //2 告诉我们的配置文件我们的初始化完成

    public static void Configure() {
        HLL_CONFIGS.put(ConfigType.CONFIG_READY.name(), true);
    }

    //5 配置apihost
    public final Configurator withApiHost(String host) {
        HLL_CONFIGS.put(ConfigType.API_HOST.name(), host);
        return this;
    }

    //6 配置交互事件
    public Configurator withWebEvent(@NonNull String name, @NonNull Event event) {
        final EventManager manager = EventManager.getInstance();
        manager.addEvent(name, event);
        return this;
    }

    //添加interceptor
    public final Configurator withInterceptor(Interceptor interceptor) {
        INTERCEPTORS.add(interceptor);
        HLL_CONFIGS.put(ConfigType.INTERCEPTOR, INTERCEPTORS);
        return this;
    }

    public final Configurator withInterceptor(ArrayList<Interceptor> interceptors) {
        INTERCEPTORS.addAll(interceptors);
        HLL_CONFIGS.put(ConfigType.INTERCEPTOR, INTERCEPTORS);
        return this;
    }

    //检查是否初始化
    private void checkConfiguration() {
        final boolean isReady = (boolean) HLL_CONFIGS.get(ConfigType.CONFIG_READY.name());
        if (!isReady) {
            throw new RuntimeException("Configuration is not read, call congfigure!");
        }
    }


    final <T> T getConfiguration(Enum<ConfigType> key) {
        checkConfiguration();
        return (T) HLL_CONFIGS.get(key.name());
    }

}
