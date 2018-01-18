package com.zhy.uutils.drag_view_practice;

/**
 * @author zhy
 * @time 2018/1/17 下午4:13
 * @description
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.zhy.uutils.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DragMainActivity extends Activity {

    @BindView(R.id.dsb)
    Button dsb;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_drag);
        ButterKnife.bind(this);


    }

    @OnClick(R.id.dsb)
    public void onClick() {
        Intent intent = new Intent(DragMainActivity.this, DragActivity.class);
        startActivity(intent);
    }
}