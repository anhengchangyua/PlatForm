package com.zhy.uutils.scheme_preview;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.zhy.uutils.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wanyummy on 2017/8/22.
 */

public class SchemeActivity extends AppCompatActivity {

    @BindView(R.id.tv_scheme)
    TextView tvScheme;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheme);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.tv_scheme)
    public void onClick() {

        Uri data = Uri.parse("wanyu://auth_activity");
        Intent intent = new Intent(Intent.ACTION_VIEW, data);
        try {
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
