package com.zhy.unetutil;

import android.content.Context;

import com.zhy.unetutil.callback.IError;
import com.zhy.unetutil.callback.IFailure;
import com.zhy.unetutil.callback.IRequest;
import com.zhy.unetutil.callback.ISuccess;
import com.zhy.unetutil.loader.LoaderStyle;

import java.io.File;
import java.util.WeakHashMap;

import okhttp3.RequestBody;

/**
 * Created by wanyummy on 2017/12/28.
 */

public class UNetClientBuilder {

    private String mUrl = null;
    //获取参数
    private final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();

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

    UNetClientBuilder() {

    }

//    public UNetClient build() {
////        return new UNetClient();
//    }
}

