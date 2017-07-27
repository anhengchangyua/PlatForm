package com.zhy.guolinstudy.hll_core.web.client;

import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.zhy.guolinstudy.hll_core.web.WebDelegate;
import com.zhy.guolinstudy.hll_core.web.route.Router;

/**
 * Created by wanyummy on 2017/7/14.
 */

public class WebViewClientImpl extends WebViewClient {

    //需要传入一个delegate
    private final WebDelegate DELEGATE;

    public WebViewClientImpl(WebDelegate delegate) {
        this.DELEGATE = delegate;
    }


    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        Log.d("ss", url);
        return Router.getInstance().handleWebUrl(DELEGATE, url);
    }
}
