package com.zhy.guolinstudy.hll_core.delegates;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.zhy.guolinstudy.R;
import com.zhy.guolinstudy.hll_core.net.RestClient;
import com.zhy.guolinstudy.hll_core.net.callback.IError;
import com.zhy.guolinstudy.hll_core.net.callback.IFailure;
import com.zhy.guolinstudy.hll_core.net.callback.ISuccess;

/**
 * Created by wanyummy on 2017/7/10.
 */

public class ExampleDelegate extends HLDelegate {

    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        testRequest();
    }

    private void testRequest() {

        RestClient.builder()
                .url("http://news.baidu.com/")
                .loader(getContext())
//                .params("", "")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
//                        Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        Toast.makeText(getContext(), "error", Toast.LENGTH_LONG).show();
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {

                    }
                })
                .build()
                .get();
    }
}
