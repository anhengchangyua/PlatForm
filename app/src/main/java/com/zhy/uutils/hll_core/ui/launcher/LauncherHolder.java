package com.zhy.uutils.hll_core.ui.launcher;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.bigkoo.convenientbanner.holder.Holder;
import com.zhy.uutils.R;

/**
 * Created by wanyummy on 2017/7/25.
 */

public class LauncherHolder implements Holder<Integer> {

    private AppCompatImageView mImagView = null;
    private AppCompatButton mButton = null;
    private AppCompatImageView mIv_laucnher = null;
    private static AppCompatImageButton mIv_laucnher_button = null;

    @Override
    public View createView(Context context) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View mView = inflater.inflate(R.layout.activity_launcher, null);
        mIv_laucnher = (AppCompatImageView) mView.findViewById(R.id.iv_launcher);
        mIv_laucnher_button = (AppCompatImageButton) mView.findViewById(R.id.iv_launcher_button);

        return mView;
    }

    @Override
    public void UpdateUI(final Context context, int position, Integer data) {
        mIv_laucnher.setBackgroundResource(data);
        mIv_laucnher_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "keyi", Toast.LENGTH_LONG).show();
            }
        });
    }

    public static final void setVisible() {
        mIv_laucnher_button.setVisibility(View.VISIBLE);
    }
}
