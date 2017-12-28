package com.zhy.uutils.hll_core.web;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;

import com.zhy.uutils.hll_core.delegates.HLDelegate;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * Created by wanyummy on 2017/7/14.
 */

public abstract class WebDelegate extends HLDelegate implements IWebViewInitializer {

    //1 声明webview
    private WebView mWebView = null;

    //2 声明webview的弱引用集合内存敏感
    private final ReferenceQueue<WebView> WEB_VIEW_QUEUE = new ReferenceQueue<>();

    //3 设置URL
    private String mUrl = null;
    private boolean mIsWebViewAvailable = false;
    private HLDelegate mTopDelegate = null;

    //4 new一个对象的时候要用到构造函数,
    public WebDelegate() {

    }

    //5 创建interface接口来初始化
    public abstract IWebViewInitializer setInitializer();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        mUrl = args.getString("URL");
        initWebViews();
    }

    //7 新建一个方法用来初始化webview
    @SuppressLint("JavascriptInterface")
    public void initWebViews() {
        if (mWebView != null) {
            mWebView.removeAllViews();
            mWebView.destroy();
        } else {
            final IWebViewInitializer initializer = setInitializer();
            if (initializer != null) {
                final WeakReference<WebView> webViewWeakReference =
                        new WeakReference<>(new WebView(getContext()), WEB_VIEW_QUEUE);
                //获取webview
                mWebView = webViewWeakReference.get();
                mWebView = initializer.initWebView(mWebView);
                mWebView.setWebViewClient(initializer.initWebViewClient());
                mWebView.setWebChromeClient(initializer.initWebChromeClient());
                //  与原生交互
                mWebView.addJavascriptInterface(HLWebInterface.create(this), "hl");
                //8 WEBVIEW 可以用了
                mIsWebViewAvailable = true;
            } else {
                throw new NullPointerException("Initializer is null");
            }
        }
    }

    //11 设置顶层界面
    public void setTopDelegate(HLDelegate delegate) {
        mTopDelegate = delegate;
    }

    //12 回去顶部界面
    public HLDelegate getTopDelegate() {
        if (mTopDelegate == null) {
            mTopDelegate = this;
        }
        return mTopDelegate;
    }

    //9 获取webview
    public WebView getWebView() {
        if (mWebView == null) {
            throw new NullPointerException("webview is null");
        }
        return mIsWebViewAvailable ? mWebView : null;
    }

    //10 获取url
    public String getUrl() {
        if (mUrl == null) {
            throw new NullPointerException("webview is null");
        }
        return mUrl;
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mWebView != null) {
            mWebView.onPause();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mWebView != null) {
            mWebView.onResume();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mIsWebViewAvailable = false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mWebView != null) {
            mWebView.removeAllViews();
            mWebView.destroy();
            mWebView = null;
        }
    }
}
