package com.zhy.uutils.hll_core.net.rx;

import android.content.Context;

import com.zhy.uutils.hll_core.loadingui.LoaderStyle;
import com.zhy.uutils.hll_core.net.RestCreator;

import java.io.File;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by wanyummy on 2017/7/11.
 * 一些传值
 */

public class RxRestClientBuilder {
    private String mUrl = null;
    //获取全局的参数
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private RequestBody mBody = null;
    //加入loading
    private Context mContext = null;
    private LoaderStyle mLoaderStyle = null;
    //加入文件参数
    private File mFile = null;


    RxRestClientBuilder() {

    }

    public final RxRestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    //强制使用weakhashmap
    public final RxRestClientBuilder params(WeakHashMap<String, Object> params) {

        PARAMS.putAll(params);
        return this;
    }


    //传入文件参数
    public final RxRestClientBuilder file(File file) {
        this.mFile = file;
        return this;
    }

    public final RxRestClientBuilder file(String file) {
        this.mFile = new File(file);
        return this;
    }

    //传入键值对的时候：
    public final RxRestClientBuilder params(String key, Object value) {
        PARAMS.put(key, value);
        return this;
    }

    //传入原始数据
    public final RxRestClientBuilder raw(String raw) {

        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }


    //添加load方法
    public final RxRestClientBuilder loader(Context context, LoaderStyle loaderStyle) {

        this.mLoaderStyle = loaderStyle;
        this.mContext = context;
        return this;
    }

    //加载默认load方法
    public final RxRestClientBuilder loader(Context context) {

        this.mLoaderStyle = LoaderStyle.BallClipRotatePulseIndicator;
        this.mContext = context;
        return this;
    }


    public final RxRestClient build() {

        return new RxRestClient(mUrl, PARAMS, mBody, mLoaderStyle, mContext,mFile);
    }

}
