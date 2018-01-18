package com.zhy.uutils.drag_view_practice;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.zhy.uutils.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author zhy
 * @time 2018/1/17 下午4:23
 * @description
 */

public class DragActivity extends Activity {

    @BindView(R.id.ssss)
    DragLayout ssss;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag_practice);
        ButterKnife.bind(this);

        ssss.setOnFinishScroll(new DragLayout.OnFinishScroll() {
            @Override
            public void complete() {
                finish();
            }
        });
    }
}
