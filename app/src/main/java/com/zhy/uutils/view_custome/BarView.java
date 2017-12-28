package com.zhy.uutils.view_custome;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.zhy.uutils.R;

/**
 * Created by wanyummy on 2017/7/6.
 */

public class BarView extends FrameLayout {

    public TextView titleView;
    public Button leftButton;

    public BarView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater.from(context).inflate(R.layout.view_zuhe, this);
        titleView = (TextView) findViewById(R.id.title_text);
        leftButton = (Button) findViewById(R.id.button_left);
        leftButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity) getContext()).finish();
            }
        });
    }



    public void setTitle(String title) {
        titleView.setText(title);
    }

    public void setLeftButtonListener(OnClickListener l) {
        leftButton.setOnClickListener(l);
    }

}
