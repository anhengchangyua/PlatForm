package com.zhy.guolinstudy.guolin14_download;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.zhy.guolinstudy.R;
import com.zhy.guolinstudy.hll_core.delegates.HLDelegate;

import butterknife.BindView;

/**
 * Created by wanyummy on 2017/8/21.
 */

public class DownloadDelegate extends HLDelegate {
    @BindView(R.id.single_task)
    Button singleTask;
    @BindView(R.id.multi_task)
    Button multiTask;
    @BindView(R.id.highest_priority)
    Button highestPriority;
    @BindView(R.id.dialog_task)
    Button dialogTask;
    @BindView(R.id.pop_task)
    Button popTask;
    @BindView(R.id.fragment_task)
    Button fragmentTask;
    @BindView(R.id.notification)
    Button notification;
    @BindView(R.id.service)
    Button service;


    @Override
    public Object setLayout() {
        return R.layout.download_delegate;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        singleTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start(new SingleTaskDelegate());
            }
        });
    }


}
