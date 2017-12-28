package com.zhy.uutils;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by wanyummy on 2017/9/1.
 */

public class CanlendarActivityF extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canlendarf);

        findViewById(R.id.buttonBasic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CanlendarActivityF.this, BasicActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.buttonAsynchronous).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(CanlendarActivityF.this, AsynchronousActivity.class);
//                startActivity(intent);
            }
        });
    }
}
