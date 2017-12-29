package com.zhy.uutils.scheme_preview;

import android.app.TaskStackBuilder;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.zhy.uutils.scheme_preview.util.JumpUtil;
import com.zhy.uutils.scheme_preview.util.ViewUtil;
import com.zhy.uutils.view_custome.MainActivity;

/**
 * Created by wanyummy on 2017/8/22.
 */

public class AnalyzeActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            Uri data = getIntent().getData();
            if (data != null) {
                Log.i("AnalyzeActivity", "url: " + data.toString());
                Intent intent = JumpUtil.parseIntent(this, data.toString(), null);

                if (intent == null) {
                    finish();
                    return;
                }

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                if (ViewUtil.isLauncherActivity(this, MainActivity.class)) {
                    startActivity(intent);
                } else {
                    TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(this)
                            .addParentStack(intent.getComponent())
                            .addNextIntent(intent);
                    taskStackBuilder.startActivities();
                }
                finish();
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
