package com.zhy.guolinstudy.guolin14_download;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.arialyy.annotations.Download;
import com.arialyy.aria.core.Aria;
import com.arialyy.aria.core.download.DownloadTask;
import com.zhy.guolinstudy.R;
import com.zhy.guolinstudy.hll_core.delegates.HLDelegate;

import butterknife.BindView;

/**
 * Created by wanyummy on 2017/8/21
 */

public class SingleTaskDelegate extends HLDelegate {
    private static final String DOWNLOAD_URL =
            "http://static.gaoshouyou.com/d/36/69/2d3699acfa69e9632262442c46516ad8.apk";
    @BindView(R.id.progressBar)
    HorizontalProgressBarWithNumber progressBar;
    @BindView(R.id.size)
    TextView size;
    @BindView(R.id.speed)
    TextView speed;
    @BindView(R.id.start)
    Button start;
    @BindView(R.id.stop)
    Button stop;
    @BindView(R.id.cancel)
    Button cancel;
    @BindView(R.id.handle_bar)
    LinearLayout handleBar;

    public Handler mUpdateHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    DownloadTask task = (DownloadTask) msg.obj;
                    progressBar.setProgress(task.getPercent());
                    break;

            }

        }
    };

    @Override
    public Object setLayout() {
        return R.layout.single_task_delegate;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Aria.download(this).register();
    }


    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Aria.download(getContext())
                        .load(DOWNLOAD_URL)
                        .setDownloadPath(Environment.getExternalStorageDirectory().getPath() + "/gggg.apk")
                        .start();
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Aria.download(getContext()).load(DOWNLOAD_URL).pause();
            }
        });
    }

    @Download.onTaskRunning(DOWNLOAD_URL)
    protected void running(DownloadTask task) {
        Message msg = new Message();
        msg.what = 1;
        msg.obj = task;
        mUpdateHandler.sendMessage(msg);
    }

    @Download.onPre(DOWNLOAD_URL)
    protected void onPre(DownloadTask task) {
    }

}
