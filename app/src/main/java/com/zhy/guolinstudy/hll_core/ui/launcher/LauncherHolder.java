package com.zhy.guolinstudy.hll_core.ui.launcher;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;

import com.bigkoo.convenientbanner.holder.Holder;

/**
 * Created by wanyummy on 2017/7/25.
 */

public class LauncherHolder implements Holder<Integer> {

    private AppCompatImageView mImagView = null;


    @Override
    public View createView(Context context) {
        mImagView =new AppCompatImageView(context);
        return mImagView;
    }

    @Override
    public void UpdateUI(Context context, int position, Integer data) {
        mImagView.setBackgroundResource(data);
    }
}
