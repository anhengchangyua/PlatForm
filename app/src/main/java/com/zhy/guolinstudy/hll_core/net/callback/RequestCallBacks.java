package com.zhy.guolinstudy.hll_core.net.callback;

import com.zhy.guolinstudy.hll_core.loadingui.HLLoader;
import com.zhy.guolinstudy.hll_core.loadingui.LoaderStyle;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by wanyummy on 2017/7/11.
 */

public class RequestCallBacks implements Callback<String> {
    private final IRequest REQUEST;
    private final IError ERROR;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final LoaderStyle LOADER_STYLE;
    private static final android.os.Handler HANDLER = new android.os.Handler();

    public RequestCallBacks(IRequest request, IError error, ISuccess success, IFailure failure, LoaderStyle style) {
        this.REQUEST = request;
        this.ERROR = error;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.LOADER_STYLE = style;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if (response.isSuccessful()) {
            if (call.isExecuted()) {
                if (SUCCESS != null) {
                    SUCCESS.onSuccess(response.body());
                }
            }
        } else {
            if (ERROR != null) {
                ERROR.onError(response.code(), response.message());
            }
        }

        stopLoading();


    }

    private void stopLoading() {
        if (LOADER_STYLE != null) {
            HANDLER.postDelayed(new Runnable() {
                @Override
                public void run() {
                    HLLoader.stopLoading();
                }
            }, 1000);

        }
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {

        if (FAILURE != null) {
            FAILURE.onFailure();
        }

        if (REQUEST != null) {
            REQUEST.onRequestEnd();
        }

        stopLoading();
    }
}
