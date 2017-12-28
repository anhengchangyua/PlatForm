package com.zhy.uutils.scheme_preview;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Toast;

import com.zhy.uutils.R;

/**
 * Created by wanyummy on 2017/8/22.
 */

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheme);
        get();

    }

    public void get() {
        Intent intent = getIntent();
        Uri uri = intent.getData();
        if (uri != null) {
            dispatchUri(uri);
        } else {

        }
    }

    private void dispatchUri(Uri uri) {
        try {
            final String domain = uri.getAuthority();
            if (TextUtils.equals("auth_activity", domain)) {
                Toast.makeText(this, uri.getHost(), Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {

        }
    }
}
