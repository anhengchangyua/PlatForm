package com.zhy.uutils.view_custome;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.zhy.uutils.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wanyummy on 2017/7/6.
 */

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.show)
    Button show;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zidingyi);
        ButterKnife.bind(this);

    }


    @OnClick(R.id.show)
    public void onClick() {
        startActivity(new Intent(this, MainActivitys.class));
        overridePendingTransition(R.anim.push_up_in, 0);
    }

}
