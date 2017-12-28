package com.zhy.uutils.hll_core.web.route;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.webkit.URLUtil;
import android.webkit.WebView;

import com.zhy.uutils.hll_core.delegates.HLDelegate;
import com.zhy.uutils.hll_core.web.WebDelegate;
import com.zhy.uutils.hll_core.web.WebDelegateImpl;

/**
 * 路由的截断处理
 * Created by wanyummy on 2017/7/17.
 */

public class Router {

    public Router() {

    }

    private static class Holder {
        private static final Router INSTANCE = new Router();
    }

    public static Router getInstance() {
        return Holder.INSTANCE;
    }

    public final boolean handleWebUrl(WebDelegate delegate, String url) {
        // 0 如果是电话协议
        if (url.contains("tel:")) {
            callPhone(delegate.getContext(), url);
            return true;
        }

        final HLDelegate topDelegate = delegate.getTopDelegate();
        // 如果不是就进行原生跳转
        final WebDelegateImpl webDelegate = WebDelegateImpl.create(url);
        topDelegate.start(webDelegate);

        return true;
    }

    //1 加载web
    public void loadWebPage(WebView webView, String url) {
        if (webView != null) {
            webView.loadUrl(url);
        } else {
            throw new NullPointerException("webview is null");
        }
    }

    //2 加载本地web
    public void loadLocalWebView(WebView webView, String url) {
        loadWebPage(webView, "file:///android_asset/" + url);
    }

    //3 总load方式
    public void loadPage(WebView webView, String url) {
        if (URLUtil.isNetworkUrl(url) || URLUtil.isAssetUrl(url)) {
            loadWebPage(webView, url);
        } else {
            loadLocalWebView(webView, url);
        }
    }

    //4 总的重载
    public final void loadPage(WebDelegate delegate, String url) {
        loadPage(delegate.getWebView(), url);
    }

    private void callPhone(Context context, String uri) {
        // 5 让用户决定是否打电话
        final Intent intent = new Intent(Intent.ACTION_DIAL);
        final Uri data = Uri.parse(uri);
        intent.setData(data);
        ContextCompat.startActivity(context, intent, null);
    }
}
