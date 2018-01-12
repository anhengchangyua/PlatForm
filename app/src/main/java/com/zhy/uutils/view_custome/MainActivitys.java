package com.zhy.uutils.view_custome;

/**
 * @author zhy
 * @time 2018/1/12 下午3:54
 * @description
 */

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhy.uutils.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wanyummy on 2017/7/6.
 */

public class MainActivitys extends AppCompatActivity {


    int downy;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.ll_bottom)
    LinearLayout llBottom;
    @BindView(R.id.rl_ll)
    RelativeLayout rlLl;

    private int topBottom;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto);
        ButterKnife.bind(this);

        initView();

        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        int heightWindow = wm.getDefaultDisplay().getHeight();

        topBottom = heightWindow - getViewHeight(llBottom);


    }

    public static int getViewHeight(View view) {

        if (view == null) return 0;

        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(h, 0);

        return view.getMeasuredHeight();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initView() {

        rlLl.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        downy = (int) event.getY();
                        int a = (downy - topBottom);
                        if (a > 0) {
                            return true;
                        } else {
                            return false;
                        }
                    case MotionEvent.ACTION_MOVE:

                        break;
                    case MotionEvent.ACTION_UP:
                        int movey = (int) event.getY();
                        int v1 = downy - movey;
                        if (v1 > getViewHeight(llBottom) / 2) {
                            finish();
                            overridePendingTransition(0, R.anim.push_up_out);
                        }
                        break;
                }
                return true;
            }
        });
    }


}
