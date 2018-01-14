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

public class UNetClient {

    private final String URL;
    private static WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();
    private final IRequest REQUEST;
    //文件名
    private final String NAME;
    //后缀
    private final String EXTENSION;
    //存放目录
    private final String DOWNLOAD_DIR;
    private final IError ERROR;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final RequestBody BODY;
    //加入loadstyle
    private final LoaderStyle LOADER_STYLE;
    private final Context CONTEXT;
    //加入文件参数
    private final File FILE;

    public UNetClient(String URL, IRequest REQUEST, String NAME,
                      String EXTENSION, String DOWNLOAD_DIR, IError ERROR,
                      ISuccess SUCCESS, IFailure FAILURE, RequestBody BODY,
                      LoaderStyle LOADER_STYLE, Context CONTEXT, File FILE) {
        this.URL = URL;
        this.REQUEST = REQUEST;
        this.NAME = NAME;
        this.EXTENSION = EXTENSION;
        this.DOWNLOAD_DIR = DOWNLOAD_DIR;
        this.ERROR = ERROR;
        this.SUCCESS = SUCCESS;
        this.FAILURE = FAILURE;
        this.BODY = BODY;
        this.LOADER_STYLE = LOADER_STYLE;
        this.CONTEXT = CONTEXT;
        this.FILE = FILE;
    }

    public UNetClientBuilder builder() {
        return new UNetClientBuilder();
    }


}
