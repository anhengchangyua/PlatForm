package com.zhy.guolinstudy.hll_core.delegates;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.zhy.guolinstudy.R;
import com.zhy.guolinstudy.hll_core.app.Hll;
import com.zhy.guolinstudy.hll_core.net.RestClient;
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
                .url("index_data")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(Hll.getApplicationContext(), response, Toast.LENGTH_LONG).show();

                    }
                })
                .build()
                .get();

    }
}
