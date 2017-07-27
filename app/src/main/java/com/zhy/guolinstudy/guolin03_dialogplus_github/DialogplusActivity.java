package com.zhy.guolinstudy.guolin03_dialogplus_github;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.OnClickListener;
import com.orhanobut.dialogplus.ViewHolder;
import com.zhy.guolinstudy.R;

/**
 * Created by wanyummy on 2017/7/6.
 */

public class DialogplusActivity extends AppCompatActivity implements View.OnClickListener {

    public TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialogplus);

        textView = (TextView) findViewById(R.id.textView);

        textView.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.textView:
                showDialog();

                break;

        }
    }

    private void showDialog() {
        // 1 导入compile 'com.orhanobut:dialogplus:1.11@aar'
        // 2 创建实例
        DialogPlus dialogPlus = DialogPlus.newDialog(this)
                .setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(DialogPlus dialog, View view) {
                        switch (view.getId()) {

                            case R.id.like_it_button:
                                Toast.makeText(DialogplusActivity.this, "We're glad that you like it", Toast.LENGTH_LONG).show();
                                break;
                            case R.id.love_it_button:
                                Toast.makeText(DialogplusActivity.this, "We're glad that you love it", Toast.LENGTH_LONG).show();
                                break;
                        }
                        dialog.dismiss();

                    }
                })
                //Enable expand animation same as Android L share dialog
                //设置扩展动画默认高度
                //.setExpanded(true,300)
                .setExpanded(true)
                .setContentHolder(new ViewHolder(R.layout.dialog_view_first))

                .create();
        dialogPlus.show();

    }
}
