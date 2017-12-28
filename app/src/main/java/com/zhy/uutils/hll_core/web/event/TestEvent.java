package com.zhy.uutils.hll_core.web.event;

import android.webkit.WebView;
import android.widget.Toast;

/**
 * Created by wanyummy on 2017/7/21.
 */

public class TestEvent extends Event {


    @Override
    public String execute(String params) {

        Toast.makeText(getContext(), params, Toast.LENGTH_LONG).show();

        if (getAction().equals("test")) {

            final WebView webView = getWebView();
            webView.post(new Runnable() {
                @Override
                public void run() {
                    webView.evaluateJavascript("nativeCall();", null);
                }
            });
        }
        return null;
    }
}
