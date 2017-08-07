package com.zhy.guolinstudy.hll_core.ui.recycle;

import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Created by wanyummy on 2017/8/7.
 */

public class MultipleHolder extends BaseViewHolder {
    public MultipleHolder(View view) {
        super(view);
    }

    //工厂模式
    public static MultipleHolder create(View view) {
        return new MultipleHolder(view);
    }
}
