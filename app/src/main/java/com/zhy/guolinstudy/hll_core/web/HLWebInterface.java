package com.zhy.guolinstudy.hll_core.web;

import android.webkit.JavascriptInterface;

import com.alibaba.fastjson.JSON;
import com.zhy.guolinstudy.hll_core.web.event.Event;
import com.zhy.guolinstudy.hll_core.web.event.EventManager;

/**
 * Created by wanyummy on 2017/7/14.
 * 用来与原生交互的
 */

final class HLWebInterface {
    // 创建一个不可改变的
    private final WebDelegate DELEGATE;

    private HLWebInterface(WebDelegate webDelegate) {
        this.DELEGATE = webDelegate;
    }

    static HLWebInterface create(WebDelegate delegate) {
        return new HLWebInterface(delegate);
    }

    //只有加注解才能响应
    @SuppressWarnings("unused")
    @JavascriptInterface
    public String event(String params) {

        final String action = JSON.parseObject(params).getString("action");
        final Event event = EventManager.getInstance().createEvent(action);
        if (event != null) {
            event.setAction(action);
            event.setDelegate(DELEGATE);
            event.setContext(DELEGATE.getContext());
            event.setUrl(DELEGATE.getUrl());
            return event.execute(params);
        }
        return null;
    }

}
