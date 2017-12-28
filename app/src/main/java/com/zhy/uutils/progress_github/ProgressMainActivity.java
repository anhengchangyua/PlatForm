package com.zhy.uutils.progress_github;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.zhy.uutils.R;

/**
 * Created by wanyummy on 2017/7/8.
 */

public class ProgressMainActivity extends AppCompatActivity {
    TextView tv_progress;
    public KProgressHUD hud;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);


        tv_progress = (TextView) findViewById(R.id.tv_progress);
        tv_progress.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                hud = KProgressHUD.create(ProgressMainActivity.this)
                        .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                        .setLabel("Please wait")
                        .setDetailsLabel("Downloading data")
                        .setCancellable(true)
                        .setAnimationSpeed(2)
                        .setDimAmount(0.5f)
                        .show();

            }
        });
    }


}

