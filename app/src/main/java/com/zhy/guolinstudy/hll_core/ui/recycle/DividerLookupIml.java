package com.zhy.guolinstudy.hll_core.ui.recycle;

import com.choices.divider.Divider;
import com.choices.divider.DividerItemDecoration;

/**
 * Created by wanyummy on 2017/8/7.
 */

public class DividerLookupIml implements DividerItemDecoration.DividerLookup {

    private final int COLOR;
    private final int SIZE;

    public DividerLookupIml(int COLOR, int SIZE) {
        this.COLOR = COLOR;
        this.SIZE = SIZE;
    }

    @Override
    public Divider getVerticalDivider(int position) {
        return new Divider.Builder().size(SIZE).color(COLOR).build();
    }

    @Override
    public Divider getHorizontalDivider(int position) {
        return new Divider.Builder().size(SIZE).color(COLOR).build();
    }
}
