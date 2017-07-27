package com.zhy.guolinstudy.hll_core.net.download;

import android.os.AsyncTask;

import com.zhy.guolinstudy.hll_core.net.RestCreator;
import com.zhy.guolinstudy.hll_core.net.callback.IError;
import com.zhy.guolinstudy.hll_core.net.callback.IFailure;
import com.zhy.guolinstudy.hll_core.net.callback.IRequest;
import com.zhy.guolinstudy.hll_core.net.callback.ISuccess;

import java.util.WeakHashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by wanyummy on 2017/7/24.
 */

public class DownloadHandler {

    private final String URL;
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private final IRequest REQUEST;
    //文件名
    private final String NAME;
    //后缀
    private final String EXTENSION;
    //存放目录
    private final String DOWNLOAD_DIR;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;

    public DownloadHandler(String url,
                           IRequest request,
                           String name,
                           String extension,
                           String download_dir,
                           ISuccess success,
                           IFailure failure,
                           IError error) {
        this.URL = url;
        this.REQUEST = request;
        this.NAME = name;
        this.EXTENSION = extension;
        this.DOWNLOAD_DIR = download_dir;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
    }

    public final void handleDownload() {

        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }
        RestCreator.getRestService().download(URL, PARAMS).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                final ResponseBody body = response.body();
                final SaveFileTask saveFileTask = new SaveFileTask(REQUEST, SUCCESS);
                saveFileTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, DOWNLOAD_DIR, EXTENSION, body, NAME);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}
