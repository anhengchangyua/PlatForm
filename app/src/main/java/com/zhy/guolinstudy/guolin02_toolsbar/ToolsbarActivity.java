package com.zhy.guolinstudy.guolin02_toolsbar;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.zhy.guolinstudy.R;
import com.zhy.guolinstudy.guolin03_dialogplus_github.DialogplusActivity;

/**
 * Created by wanyummy on 2017/7/5.
 */

public class ToolsbarActivity extends AppCompatActivity  {

    public TextView tv_lose;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_lose = (TextView)findViewById(R.id.tv_lose);
        tv_lose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ToolsbarActivity.this, DialogplusActivity.class);
                startActivity(intent);
            }
        });
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

}
