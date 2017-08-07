package com.zhy.guolinstudy.guolin07_progress;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.bigkoo.svprogresshud.listener.OnDismissListener;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.zhy.guolinstudy.R;

/**
 * Created by wanyummy on 2017/7/8.
 */

public class ProgressMainActivity extends AppCompatActivity {
    TextView tv_progress;
    private KProgressHUD hud;
    private SVProgressHUD svProgressHUD;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);

        svProgressHUD = new SVProgressHUD(ProgressMainActivity.this);
        svProgressHUD.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(SVProgressHUD hud) {
                Toast.makeText(getApplicationContext(), "dismiss", Toast.LENGTH_LONG).show();
            }
        });

        tv_progress = (TextView) findViewById(R.id.tv_progress);
        tv_progress.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

//                show(v);
//                showWithMaskType(v);
                hud = KProgressHUD.create(ProgressMainActivity.this)
                        .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                        .setLabel("Please wait")
                        .setDetailsLabel("Downloading data")
                        .setCancellable(true)
                        .setAnimationSpeed(2)
                        .setDimAmount(0.5f)
                        .show();
//
//                scheduleDismiss();
            }
        });
    }

    public void showWithMaskType(View view) {
//        svProgressHUD.showWithMaskType(SVProgressHUD.SVProgressHUDMaskType.None);
        svProgressHUD.showWithMaskType(SVProgressHUD.SVProgressHUDMaskType.Black);
//        svProgressHUD.showWithMaskType(SVProgressHUD.SVProgressHUDMaskType.BlackCancel);
//        svProgressHUD.showWithMaskType(SVProgressHUD.SVProgressHUDMaskType.Clear);
//        svProgressHUD.showWithMaskType(SVProgressHUD.SVProgressHUDMaskType.ClearCancel);
//        svProgressHUD.showWithMaskType(SVProgressHUD.SVProgressHUDMaskType.Gradient);
//        svProgressHUD.showWithMaskType(SVProgressHUD.SVProgressHUDMaskType.GradientCancel);
    }


    public void show(View view) {
        svProgressHUD.show();
    }

    private void scheduleDismiss() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                hud.dismiss();
            }
        }, 2000);
    }

}

