package com.zhy.guolinstudy.hll_core.net;

import android.content.Context;

import com.zhy.guolinstudy.hll_core.loadingui.LoaderStyle;
import com.zhy.guolinstudy.hll_core.net.callback.IError;
import com.zhy.guolinstudy.hll_core.net.callback.IFailure;
import com.zhy.guolinstudy.hll_core.net.callback.IRequest;
import com.zhy.guolinstudy.hll_core.net.callback.ISuccess;

import java.io.File;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by wanyummy on 2017/7/11.
 * 一些传值
 */

public class RestClientBuilder {
    private String mUrl = null;
    //获取全局的参数
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private IRequest mIRequst = null;
    //文件名
    private String NAME = null;
    //后缀
    private String EXTENSION = null;
    //存放目录
    private String DOWNLOAD_DIR = null;
    private IError mIError = null;
    private ISuccess mISuccess = null;
    private IFailure mIFailure = null;
    private RequestBody mBody = null;
    //加入loading
    private Context mContext = null;
    private LoaderStyle mLoaderStyle = null;
    //加入文件参数
    private File mFile = null;


    RestClientBuilder() {

    }

    public final RestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    //强制使用weakhashmap
    public final RestClientBuilder params(WeakHashMap<String, Object> params) {

        PARAMS.putAll(params);
        return this;
    }


    //传入文件参数
    public final RestClientBuilder file(File file) {
        this.mFile = file;
        return this;
    }

    public final RestClientBuilder file(String file) {
        this.mFile = new File(file);
        return this;
    }

    //传入键值对的时候：
    public final RestClientBuilder params(String key, Object value) {
        PARAMS.put(key, value);
        return this;
    }

    //传入原始数据
    public final RestClientBuilder raw(String raw) {

        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }

    //请求
    public final RestClientBuilder request(IRequest request) {
        this.mIRequst = request;
        return this;
    }

    //成功回调
    public final RestClientBuilder success(ISuccess success) {
        this.mISuccess = success;
        return this;
    }

    public final RestClientBuilder error(IError error) {
        this.mIError = error;
        return this;
    }

    public final RestClientBuilder failure(IFailure failure) {
        this.mIFailure = failure;
        return this;
    }

    //添加load方法
    public final RestClientBuilder loader(Context context, LoaderStyle loaderStyle) {

        this.mLoaderStyle = loaderStyle;
        this.mContext = context;
        return this;
    }

    //加载默认load方法
    public final RestClientBuilder loader(Context context) {

        this.mLoaderStyle = LoaderStyle.BallClipRotatePulseIndicator;
        this.mContext = context;
        return this;
    }


    public final RestClient build() {

        return new RestClient(mUrl, PARAMS, mIRequst, NAME, EXTENSION, DOWNLOAD_DIR, mIError, mISuccess, mIFailure, mBody, mLoaderStyle, mContext, mFile);
    }

}
