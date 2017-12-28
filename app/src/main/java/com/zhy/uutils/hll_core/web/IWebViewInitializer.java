package com.zhy.uutils.hll_core.web;

import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by wanyummy on 2017/7/14.
 */

public interface IWebViewInitializer {

    WebView initWebView(WebView webView);

    WebViewClient initWebViewClient();

    WebChromeClient initWebChromeClient();

}
