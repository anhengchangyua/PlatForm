package com.zhy.guolinstudy.hll_core.web;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.zhy.guolinstudy.hll_core.web.chromeclient.WebViewChromeImpl;
import com.zhy.guolinstudy.hll_core.web.client.WebViewClientImpl;
import com.zhy.guolinstudy.hll_core.web.route.RouteKeys;
import com.zhy.guolinstudy.hll_core.web.route.Router;

/**
 * Created by wanyummy on 2017/7/14.
 */

public class WebDelegateImpl extends WebDelegate {

    // 1 需要一个工厂方法来创建我们的impl类

    public static WebDelegateImpl create(String url) {

        final Bundle args = new Bundle();
        args.putString(RouteKeys.URL.name(), url);
        final WebDelegateImpl delegate = new WebDelegateImpl();
        delegate.setArguments(args);
        return delegate;

    }


    @Override
    public Object setLayout() {
        return getWebView();
    }


    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

        if (getUrl() != null) {
            //用原生的方式模拟web 跳转并进行页面加载，在web - client下面,需要用到WebViewClient
            //2 通过以router的方式跳转到此处，然后加载。
            Router.getInstance().loadPage(getWebView(),getUrl());
        }

    }



    @Override
    public IWebViewInitializer setInitializer() {
        return this;
    }

    @Override
    public WebView initWebView(WebView webView) {
        //3 新建一个类，这里写太多
        return new WebViewInitializer().createWebView(webView);
    }

    @Override
    public WebViewClient initWebViewClient() {
        final WebViewClientImpl client = new WebViewClientImpl(this);
        return client;
    }

    @Override
    public WebChromeClient initWebChromeClient() {
        //4 新建一个chrome类
        return new WebViewChromeImpl();
    }
}
