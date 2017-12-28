package com.zhy.uutils.hll_core.ui.recycle;

import android.support.annotation.ColorInt;

import com.choices.divider.DividerItemDecoration;

/**
 * Created by wanyummy on 2017/8/7.
 */

public class BaseDecoration extends DividerItemDecoration {

    private BaseDecoration(@ColorInt int color, int size) {

        setDividerLookup(new DividerLookupIml(color, size));
    }

    public static BaseDecoration create(@ColorInt int color, int size) {
        return new BaseDecoration(color, size);
    }
}
