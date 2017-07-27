package com.zhy.guolinstudy.hll_core.web.chromeclient;

import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/**
 * Created by wanyummy on 2017/7/20.
 */

public class WebViewChromeImpl extends WebChromeClient {
    //用来拦截对话alert 然后自己使用
    @Override
    public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
        return super.onJsAlert(view, url, message, result);
    }
}
