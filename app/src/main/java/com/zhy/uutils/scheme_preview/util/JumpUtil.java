package com.zhy.uutils.scheme_preview.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

import com.google.common.base.Splitter;
import com.google.common.collect.Maps;
import com.zhy.uutils.scheme_preview.Test1Activity;
import com.zhy.uutils.scheme_preview.SplashActivity;
import com.zhy.uutils.scheme_preview.Test2Activity;

import java.util.Map;
import java.util.Set;

/**
 * Created by wanyummy on 2017/12/29.
 */

public class JumpUtil {


    public static Intent parseIntent(Context context, String url, String title) {
        if (!isKownUrl(url)) {
            return null;
        }
        if (isHttps(url)) {
            return null;
        }

        Uri parse = Uri.parse(url);
        String scheme = parse.getScheme();
        String host = parse.getHost();
        String path = parse.getPath();

        Set<String> parameterNames = parse.getQueryParameterNames();
        Map<String, String> map = Maps.newHashMap();
        if (!parameterNames.isEmpty()) {
            for (String name : parameterNames) {
                map.put(name, parse.getQueryParameter(name));
            }
        }

        return parseUrl(context, host, path, map);

    }

    private static Intent parseUrl(Context context, String host, String path, Map<String, String> map) {
        Iterable<String> split = Splitter.on("/").split(path);

        Intent intent;
        switch (host) {
            case "test1":
                intent = new Intent(context, Test1Activity.class);
                try {
                    intent.putExtra(Constants.TITLE, map.get("title"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return intent;
            case "test2":
                intent = new Intent(context, Test2Activity.class);
                try {
                    intent.putExtra(Constants.TITLE, map.get("title"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return intent;
        }
        intent = new Intent(context, SplashActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

    private static boolean isKownUrl(String url) {
        return TextUtils.isEmpty(url) &&
                isScheme(url) &&
                isHttps(url);
    }

    private static boolean isScheme(String url) {
        return url.startsWith("test://");
    }

    private static boolean isHttps(String url) {
        return url.startsWith("http://") || url.startsWith("https://");
    }


}
