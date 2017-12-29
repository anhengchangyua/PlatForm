package com.zhy.uutils.scheme_preview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.zhy.uutils.R;
import com.zhy.uutils.scheme_preview.util.Constants;
import com.zhy.uutils.scheme_preview.util.NotifyUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wanyummy on 2017/12/29.
 */

public class AAAAAA extends AppCompatActivity {


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
        NotifyUtil.createNotify(this, Constants.KNOWN_SCHEME + "test1?title=首页测试2", "首页测试22");
    }
}
